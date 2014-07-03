package fr.lteconsulting.hexa.rebind;

import java.io.OutputStream;
import java.io.PrintWriter;

import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;
import fr.lteconsulting.hexa.client.comm.CustomMethod;
import fr.lteconsulting.hexa.client.comm.FieldName;

public class DataProxySerializedGenerator extends Generator
{
	@Override
	public String generate( TreeLogger logger, GeneratorContext context, String requestedClass ) throws UnableToCompleteException
	{
		logger.log( TreeLogger.INFO, "Generate '" + requestedClass, null );

		TypeOracle typeOracle = context.getTypeOracle();

		JClassType requestedType = typeOracle.findType( requestedClass );
		if( requestedType == null )
		{
			logger.log( TreeLogger.ERROR, "Type '" + requestedClass + "' has not been found by the Oracle", null );
			throw new UnableToCompleteException();
		}

		String className = requestedType.getName() + "Impl";
		String fullClassName = requestedClass + "Impl";
		String packageName = requestedType.getPackage().getName();

		OutputStream phpStream = context.tryCreateResource( logger, requestedClass + "Serializer.php" );
		if( phpStream == null )
			return fullClassName;
		PrintWriter phpPw = new PrintWriter( phpStream );

		phpPw.println( "class " + requestedClass + "Serializer" );
		phpPw.println( "{" );
		phpPw.println( "    var $fields = array(" );

		PrintWriter printWriter = context.tryCreate( logger, packageName, className );
		if( printWriter == null )
		{
			logger.log( TreeLogger.INFO, requestedClass + " : CANNOT CREATE PRINT WRITER", null );
			return fullClassName;
		}

		ClassSourceFileComposerFactory composerFactory = new ClassSourceFileComposerFactory( packageName, className );
		composerFactory.addImport( "com.google.gwt.core.client.GWT" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.comm.DataProxy" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.comm.GenericJSO" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.comm.ResponseJSO" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.common.HexaDate" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.common.HexaTime" );
		composerFactory.addImport( "fr.lteconsulting.hexa.client.common.HexaDateTime" );
		composerFactory.addImplementedInterface( requestedClass );

		SourceWriter sw = composerFactory.createSourceWriter( context, printWriter );
		if( sw == null )
		{
			logger.log( TreeLogger.ERROR, requestedClass + " : CANNOT CREATE SOURCEWRITER", null );
			return fullClassName; // null, already generated
		}

		sw.println( "private GenericJSO jso = null;" );

		JMethod[] methods = requestedType.getMethods();

		sw.println( "public void init( GenericJSO jso ) {" );
		sw.indent();
		sw.println( "this.jso = jso;" );
		sw.outdent();
		sw.println( "}" );

		for( int m = 0; m < methods.length; m++ )
		{
			JMethod method = methods[m];

			FieldName fnAnnotation = method.getAnnotation( FieldName.class );
			CustomMethod cmAnnotation = method.getAnnotation( CustomMethod.class );
			if( cmAnnotation != null )
			{
				sw.println( "public " + method.getReturnType().getSimpleSourceName() + " " + method.getName() + "() {" );
				sw.indent();
				sw.println( cmAnnotation.body() );
				sw.outdent();
				sw.println( "}" );
			}
			else if( fnAnnotation != null )
			{
				sw.println( "public " + method.getReturnType().getSimpleSourceName() + " " + method.getName() + "() {" );
				sw.indent();
				if( method.getReturnType().getSimpleSourceName().compareTo( "HexaDate" ) == 0 )
				{
					sw.println( "return new HexaDate( jso.getStringByIdx( " + m + " ) );" );
				}
				else if( method.getReturnType().getSimpleSourceName().compareTo( "HexaTime" ) == 0 )
				{
					sw.println( "return new HexaTime( jso.getStringByIdx( " + m + " ) );" );
				}
				else if( method.getReturnType().getSimpleSourceName().compareTo( "HexaDateTime" ) == 0 )
				{
					sw.println( "return new HexaDateTime( jso.getStringByIdx( " + m + " ) );" );
				}
				else
				{
					String jsoType = method.getReturnType().getSimpleSourceName();
					if( method.getReturnType().getSimpleSourceName().compareTo( "int" ) == 0 )
						jsoType = "Int";
					else if( method.getReturnType().getSimpleSourceName().compareTo( "Integer" ) == 0 )
						jsoType = "Integer";
					else if( method.getReturnType().getSimpleSourceName().compareTo( "double" ) == 0 )
						jsoType = "Double";
					sw.println( "return jso.get" + jsoType + "ByIdx( " + m + " );" );
				}
				sw.outdent();
				sw.println( "}" );

				phpPw.print( "        \"" + fnAnnotation.fieldName() + "\"" );
				if( m < methods.length - 1 )
					phpPw.println( "," );
			}
		}

		sw.commit( logger );

		phpPw.println( " );" );
		phpPw.println( "    public function GetFieldOrder()" );
		phpPw.println( "    {" );
		phpPw.println( "        return $this->fields;" );
		phpPw.println( "    }" );
		phpPw.println();
		phpPw.println( "    public function TransformRow( $row )" );
		phpPw.println( "    {" );
		phpPw.println( "        $res = array();" );
		phpPw.println( "        foreach( $this->fields as $field )" );
		phpPw.println( "            $res[] = $row[$field];" );
		phpPw.println( "        return $res;" );
		phpPw.println( "    }" );
		phpPw.println( "}" );
		phpPw.flush();
		context.commitResource( logger, phpStream );

		return fullClassName;
	}
}
