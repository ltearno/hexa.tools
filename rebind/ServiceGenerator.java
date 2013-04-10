package com.hexa.rebind;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import com.hexa.client.comm.Cache;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.typeinfo.JParameter;
import com.google.gwt.core.ext.typeinfo.JParameterizedType;
import com.google.gwt.core.ext.typeinfo.JPrimitiveType;
import com.google.gwt.core.ext.typeinfo.JType;
import com.google.gwt.core.ext.typeinfo.JTypeParameter;
import com.google.gwt.core.ext.typeinfo.TypeOracle;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

public class ServiceGenerator extends Generator
{
	//@SuppressWarnings("unused")
	GeneratorContext context = null;
	private TreeLogger logger = null;
	private TypeOracle typeOracle = null;
	private SourceWriter sw = null;
	
	String requestedClassName;
	String createdClassName;
	String fullCreatedClassName;
	String packageName;
	
	public String generate( TreeLogger logger, GeneratorContext context, String requestedClass )
	throws UnableToCompleteException
	{
		this.logger = logger;
		logger.log( TreeLogger.INFO, "Generate '" + requestedClass , null );
		
		this.context = context;
		
		typeOracle = context.getTypeOracle();
		
		JClassType requestedType = typeOracle.findType( requestedClass );
		if (requestedType == null)
		{
			logger.log( TreeLogger.ERROR, "Type '" + requestedClass + "' has not been found by the Oracle", null );
			throw new UnableToCompleteException();
		}
		
		requestedClassName = requestedType.getName();
		createdClassName = requestedClassName + "Impl";
		fullCreatedClassName = requestedClass + "Impl";
		packageName = requestedType.getPackage().getName();
		
		PrintWriter printWriter = context.tryCreate( logger, packageName, createdClassName );
		if (printWriter == null) {
			//logger.log( TreeLogger.INFO, requestedClass + " : CANNOT CREATE PRINT WRITER", null );
			return fullCreatedClassName;
		}
		
		ClassSourceFileComposerFactory composerFactory = new ClassSourceFileComposerFactory( packageName, createdClassName );
		composerFactory.setSuperclass( "ServiceBase" );
		composerFactory.addImplementedInterface( requestedClass );
		composerFactory.addImport( "com.hexa.client.comm.ServiceBase" );
		composerFactory.addImport( "java.util.ArrayList" );
		composerFactory.addImport( "java.util.HashMap" );
		composerFactory.addImport( "java.util.Iterator" );
		composerFactory.addImport( "java.util.Map.Entry" );
		composerFactory.addImport( "com.hexa.client.comm.GenericJSO" );
		composerFactory.addImport( "com.google.gwt.core.client.JavaScriptObject" );
		composerFactory.addImport( "com.hexa.client.comm.DataProxy" );
		composerFactory.addImport( "com.hexa.client.comm.DataProxyFastFactories" );
		composerFactory.addImport( "com.hexa.client.comm.IDataProxyFastFactory" );
		composerFactory.addImport( "com.hexa.client.comm.Service" );
		composerFactory.addImport( "com.hexa.client.comm.RequestDesc" );
		composerFactory.addImport( "com.hexa.client.comm.ServerComm" );
		composerFactory.addImport( "com.hexa.client.comm.CachedServerComm" );
		composerFactory.addImport( "com.hexa.client.comm.ResponseJSO" );
		composerFactory.addImport( "com.hexa.client.comm.JSOArrayInteger" );
		composerFactory.addImport( "com.hexa.client.comm.ServerComm.ServerCommCb" );
		composerFactory.addImport( "com.hexa.client.interfaces.ITablesManager" );
		composerFactory.addImport( "com.hexa.client.interfaces.IUserLogInfo" );
		composerFactory.addImport( "com.hexa.client.interfaces.IAsyncCallback" );
		composerFactory.addImport( "com.hexa.client.comm.ServerComm.ServerCommMessageCb" );
		composerFactory.addImport( "com.google.gwt.core.client.GWT" );
		composerFactory.addImport( "com.google.gwt.core.client.JsArray" );
		composerFactory.addImport( "com.google.gwt.core.client.JsArrayInteger" );
		composerFactory.addImport( "com.google.gwt.core.client.JsArrayString" );
		composerFactory.addImport( "com.google.gwt.http.client.URL" );
		composerFactory.addImport( "com.google.gwt.user.client.Window" );
		composerFactory.addImport( "com.google.gwt.user.client.rpc.AsyncCallback" );
		composerFactory.addImport( "com.hexa.client.common.HexaDate" );
		composerFactory.addImport( "com.hexa.client.common.HexaTime" );
		composerFactory.addImport( "com.hexa.client.common.HexaDateTime" );
		composerFactory.addImport( "com.hexa.client.comm.FactoredInterface" );
		composerFactory.addImport( "com.google.gwt.json.client.JSONValue" );
		composerFactory.addImport( "com.google.gwt.json.client.JSONObject" );
		composerFactory.addImport( "com.google.gwt.json.client.JSONArray" );
		composerFactory.addImport( "com.google.gwt.json.client.JSONString" );
		composerFactory.addImport( "com.google.gwt.json.client.JSONNumber" );
		composerFactory.addImport( "com.hexa.client.comm.callparams.ListMarshall" );
		composerFactory.addImport( "com.hexa.client.comm.callparams.SetMarshall" );
		composerFactory.addImport( "com.hexa.client.comm.callparams.MapMarshall" );
		
		sw = composerFactory.createSourceWriter( context, printWriter );
		if( sw == null )
		{
			// sw==null: already generated
			// logger.log( TreeLogger.ERROR, requestedClass + " : CANNOT CREATE SOURCEWRITER", null );
			return fullCreatedClassName;
		}
		
		//sw.println( "CachedServerComm srv = new CachedServerComm();" );
		//sw.println( "DataProxyFastFactories factory = new DataProxyFastFactories();" );
		//sw.println();
		
		JMethod[] methods = requestedType.getMethods();
		String interfaceChecksum = generateChecksum( methods );
		
		// generate server com initialisation method
		sw.println( "public void Init( String baseUrl, IUserLogInfo userLogInfo, ServerCommMessageCb serverCommMessageCb, Service service, ITablesManager tablesMng )" );
		sw.println( "{" );
		sw.println( "    setConfig( \""+interfaceChecksum+"\" );" );
		sw.println( "    initFactories();" );
		sw.println( "    srv.Init( baseUrl, userLogInfo, serverCommMessageCb, service, tablesMng );" );
		sw.println( "}" );
		sw.println();
		
		for( int i=0; i<methods.length; i++ ) {
			generateMethod( requestedClassName, interfaceChecksum, methods[i], i );
			sw.println();
		}
		
		generateCallParamMarshalls();
		
		generateOnResponseCallbacks();
		
		generateFactories();
		
		sw.println( "private void initFactories()" );
		sw.println( "{" );
		sw.println( "    HashMap<Class<?>, IDataProxyFastFactory> factories = new HashMap<Class<?>, IDataProxyFastFactory>();" );
		// TODO CHECK THAT
		for( JType dpfType : dataProxyFastFactories )
		{
			sw.println( "    factories.put( "+dpfType.getParameterizedQualifiedSourceName()+".class, (IDataProxyFastFactory) GWT.create( "+getDataProxyFastFactoryName(dpfType)+".class ) );" );
			//sw.println( "    factories.put( "+dpfType.getParameterizedQualifiedSourceName()+".class,  new "+ packageName+"."+getDataProxyFastFactoryName(dpfType)+"() );" );
			//composerFactory.addImport( packageName + "." + getDataProxyFastFactoryName(dpfType) );
		}
		sw.println( "    factory.init( factories );" );
		sw.println( "}" );
		sw.println();
		
		generateProxies();
		
		sw.commit( logger );
		
		// Generate the PHP interop service interface file
		OutputStream phpStream = context.tryCreateResource( logger, requestedClassName + ".interface.php" );
		if( phpStream != null )
		{
			PrintWriter phpPw = new PrintWriter( phpStream );
			generatePHP( phpPw, requestedType, interfaceChecksum );
			phpPw.flush();
			context.commitResource( logger, phpStream );
		}
		
		return fullCreatedClassName;
	}
	
	private String generateChecksum( JMethod[] methods )
	{
		StringBuilder b = new StringBuilder();
		
		for( int i=0; i<methods.length; i++ )
			b.append( methods[i].getJsniSignature() );
		
		return String.valueOf( Math.abs( b.toString().hashCode() ) );
	}
	
	private void generatePHP( PrintWriter w, JClassType requestedType, String interfaceChecksum )
	{
		w.println( "<?php" );
		w.println();
		
		JMethod[] methods = requestedType.getMethods();
		
		w.println( "function _gwtio_get_"+requestedClassName+"_CheckSum() { return \""+interfaceChecksum+"\"; }" );
		w.println();
		
		w.println( "function _gwtio_get_"+requestedClassName+"_ServiceMethods() { return array( " );
		for( JMethod method : methods ) {
			w.println( "    '" + method.getName() + "'," );
		}
		w.println( "); }" );
		w.println();
		
		w.println( "interface I" + requestedClassName );
		w.println( "{" );
		for( JMethod method : methods ) {
			JParameter[] params = method.getParameters();
			
			JClassType cbType = typeOracle.findType( params[params.length-1].getType().getQualifiedSourceName() );
			if( cbType == null ) {
				w.println( "*** ERROR : Unable to find callback type : " + params[params.length-1].getType().getQualifiedSourceName() );
				return;
			}
			
			w.println( "    // " + params[params.length-1].getType().getParameterizedQualifiedSourceName() );
			for( JMethod cbMethod : cbType.getMethods() ) {
				w.print( "    // " + "    " + cbMethod.getName() + "( " );
				for( JParameter cbP : cbMethod.getParameters() )
					w.print( cbP.getType().getParameterizedQualifiedSourceName() + " " + cbP.getName() + ", " );
				w.println( " )" );
			}
			
			w.print( "    public function " + method.getName() + "( " );
			for( int p=0; p<params.length-1; p++ ) {
				w.print( "$" + params[p].getName() );
				if( p < params.length - 2 )
					w.print( ", " );
			}
			w.println( " );" );
			//w.println( "    }" );
			w.println();
		}
		w.println( "}" );
		w.println();
		w.println( "?>" );
	}
	
	private String genMethodPrototype( JMethod method )
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append( "public " );
		
		// if the method is type parameterized, echo the type specification
		JTypeParameter[] typeParams = method.getTypeParameters();
		if( typeParams.length > 0 )
		{
			sb.append( "<" );
			for( int i=0; i<typeParams.length; i++ )
			{
				JTypeParameter tp = typeParams[i];
				sb.append( tp.getQualifiedSourceName() );
				
				if( i < typeParams.length-1 )
					sb.append( ", " );
			}
			sb.append( ">" );
		}
		
		sb.append( " void " + method.getName() + "( " );
		
		for( int i=0; i<method.getParameters().length; i++ )
		{
			JParameter param = method.getParameters()[i];
			sb.append( param.getType().getParameterizedQualifiedSourceName() + " " + param.getName() );
			if( i < method.getParameters().length - 1 )
				sb.append( ", " );
		}
		
		sb.append( " )" );
		
		return sb.toString();
	}
	
	private String getParamMarshall( JType type )
	{
		JPrimitiveType prType = type.isPrimitive();
		if( prType != null )
		{
			if( prType == JPrimitiveType.INT )
				return "intMarshall";
			else if( prType == JPrimitiveType.DOUBLE )
				return "doubleMarshall";
			else if( prType == JPrimitiveType.BOOLEAN )
				return "booleanMarshall";
		}
		
		if( type.getSimpleSourceName().equals( "Integer" ) )
			return "intMarshall";
		if( type.getSimpleSourceName().equals( "Double" ) )
			return "doubleMarshall";
		if( type.getSimpleSourceName().equals( "Boolean" ) )
			return "booleanMarshall";
		if( type.getSimpleSourceName().equals( "String" ) )
			return "stringMarshall";
		if( type.getSimpleSourceName().equals( "HexaDate" ) )
			return "dateMarshall";
		if( type.getSimpleSourceName().equals( "HexaTime" ) )
			return "timeMarshall";
		if( type.getSimpleSourceName().equals( "HexaDateTime" ) )
			return "dateTimeMarshall";
		if( implementsInterface( type, "ITable" ) )
			return "itableMarshall";
		if( isJSOType(type) )
			return "jsoMarshall";
		
		if( implementsInterface( type, "java.util.Map" ) )
		{
			// find the marshall name for that type
			JClassType[] paramType = type.isParameterized().getTypeArgs();
			assert( paramType.length == 2 );
			String marshallName = "marshall_map_" + paramType[0].getSimpleSourceName() + "_" + paramType[1].getSimpleSourceName();
			
			// register that marshall
			if( ! marshallsToGenerate.containsKey( marshallName ) )
				marshallsToGenerate.put( marshallName, type );
			
			// return the name of that marshall
			return marshallName;
		}
		
		// TODO : replace that with : when type implements java.util.List
		if( implementsInterface( type, "java.util.List" ) )
		{
			// find the marshall name for that type
			JClassType[] paramType = type.isParameterized().getTypeArgs();
			assert( paramType.length == 1 );
			String marshallName = "marshall_list_" + paramType[0].getSimpleSourceName();
			
			// register that marshall
			if( ! marshallsToGenerate.containsKey( marshallName ) )
				marshallsToGenerate.put( marshallName, type );
			
			// return the name of that marshall
			return marshallName;
		}
		
		// TODO : replace that with : when type implements java.util.Set
		if( implementsInterface( type, "java.util.Set" ) )
		{
			// find the marshall name for that type
			JClassType[] paramType = type.isParameterized().getTypeArgs();
			assert( paramType.length == 1 );
			String marshallName = "marshall_set_" + paramType[0].getSimpleSourceName();
			
			// register that marshall
			if( ! marshallsToGenerate.containsKey( marshallName ) )
				marshallsToGenerate.put( marshallName, type );
			
			// return the name of that marshall
			return marshallName;
		}
		
		return null;
	}
	
	Map<String,JType> marshallsToGenerate = new HashMap<String, JType>();
	
	private void generateCallParamMarshalls()
	{
		for( Entry<String,JType> e : marshallsToGenerate.entrySet() )
		{
			String marshallName = e.getKey();
			JType type = e.getValue();
			
			if( implementsInterface( type, "java.util.Map" ) )
			{
				JClassType[] paramType = type.isParameterized().getTypeArgs();
				assert( paramType.length == 2 );
				
				JType keyType = paramType[0];
				String keyTypeName = keyType.getParameterizedQualifiedSourceName();
				JType valueType = paramType[1];
				String valueMarshallName = getParamMarshall( valueType );
				
				sw.println( "MapMarshall<"+keyTypeName+","+valueType.getParameterizedQualifiedSourceName()+"> "+marshallName+" = new MapMarshall<"+keyTypeName+","+valueType.getParameterizedQualifiedSourceName()+">( " + valueMarshallName + " );" );
			}
			else if( implementsInterface( type, "java.util.List" ) )
			{
				JClassType[] paramType = type.isParameterized().getTypeArgs();
				assert( paramType.length == 1 );
				
				JType subType = paramType[0];
				String subMarshallName = getParamMarshall( subType );
				
				sw.println( "ListMarshall<"+subType.getParameterizedQualifiedSourceName()+"> "+marshallName+" = new ListMarshall<"+subType.getParameterizedQualifiedSourceName()+">( " + subMarshallName + " );" );
			}
			else if( implementsInterface( type, "java.util.Set" ) )
			{
				JClassType[] paramType = type.isParameterized().getTypeArgs();
				assert( paramType.length == 1 );
				
				JType subType = paramType[0];
				String subMarshallName = getParamMarshall( subType );
				
				sw.println( "SetMarshall<"+subType.getParameterizedQualifiedSourceName()+"> "+marshallName+" = new SetMarshall<"+subType.getParameterizedQualifiedSourceName()+">( " + subMarshallName + " );" );
			}
			else
			{
				sw.println( "ERROR : CANNOT GENERATE MARSHALL WITH NAME " + marshallName +" / " + type.getQualifiedSourceName() );
				JClassType c = type.isClassOrInterface();
				if( c != null )
				{
					JType[] interfaces = c.getImplementedInterfaces();
					for( int i=0; i<interfaces.length; i++ )
						sw.println( interfaces[i].getQualifiedSourceName() );
				}
			}
		}
	}
	
	private boolean implementsInterface( JType type, String interfaceName )
	{
		if( type.getQualifiedSourceName().equals( interfaceName ) )
			return true;
		
		JClassType c = type.isClassOrInterface();
		if( c != null )
		{
			JType[] interfaces = c.getImplementedInterfaces();
			for( int i=0; i<interfaces.length; i++ )
				if( interfaces[i].getSimpleSourceName().equals( interfaceName ) 
						|| interfaces[i].getQualifiedSourceName().equals( interfaceName ) )
					return true;
			return false;
		}
		
		return false;
	}
	
	// returns true if need to urlencode the string
	private String paramToString( JParameter param )
	{
		JType type = param.getType();
		
		// get marshall name
		String marshall = getParamMarshall( type );
		
		if( marshall != null )
			return marshall+".marshall( " + param.getName() + " )";
		
		return "*** ERROR : The service method parameter cannot be marshalled : " + type.getQualifiedSourceName();
	}
	
	private void generateMethod( String service, String interfaceChecksum, JMethod method, int methodOrdinal )
	{
		JParameter[] params = method.getParameters();
		
		// Use cache annotation to manage the cache
		Cache cacheAnnotation = method.getAnnotation( Cache.class );
		String fUseCache = "true";
		if( cacheAnnotation != null )
			fUseCache = cacheAnnotation.useCache() ? "true" : "false";
		String fInvalidateCache = "false";
		if( cacheAnnotation != null )
			fInvalidateCache = cacheAnnotation.inv() ? "true" : "false";
		
		// writes the method prototype
		sw.println( genMethodPrototype( method ) );
		sw.println( "{" );
		sw.indent();
		
		// writes the code to marshall the called method parameters
		sw.println( "JSONArray call_params = new JSONArray();" );
		for( int i=0; i<params.length-1; i++ ) // last param is the callback
			sw.println( "call_params.set( "+i+", "+paramToString( params[i] )+" );" );
		
		// writes the code to create the request description object
		sw.println( "RequestDesc desc = new RequestDesc( \""+service+"\", \""+interfaceChecksum+"\", "+methodOrdinal+", call_params );" );
		
		// register and get the name of the callback to use
		String onResponseCallback = registerOnResponseCallback( params[params.length-1].getType() );
		
		// writes the code to call the cached server
		sw.println();
		sw.println( "srv.sendRequest( "+fUseCache+", "+fInvalidateCache+", desc, callback, " + onResponseCallback + ");" );
		sw.outdent();
		sw.println( "}" );
	}
	
	int currentNumber = 0;
	class OnResponseCallbackInfo
	{	
		String callbackName;
		JType callbackType;
		
		OnResponseCallbackInfo( JType callbackType )
		{
			this.callbackType = callbackType;
			callbackName = "onResponseCallback_" + (currentNumber++);
		}
		
		public boolean isFor( JType callbackType )
		{
			if( callbackType.getParameterizedQualifiedSourceName().compareTo( this.callbackType.getParameterizedQualifiedSourceName() ) != 0 )
				return false;
			return true;
		}
	}
	
	HashSet<JType> dataProxyFastFactories = new HashSet<JType>();
	String getDataProxyFastFactoryName( JType dataProxyFastType )
	{
		return dataProxyFastType.getSimpleSourceName() + "Factory";
	}
	String getDataProxyFastImplName( JType dataProxyFastType )
	{
		return dataProxyFastType.getSimpleSourceName() + "Jso";
	}
	
	String registerFactory( JType dataProxyFastType )
	{
		sw.println( "// REGISTERED_FACTORY FOR "+dataProxyFastType.getParameterizedQualifiedSourceName() );
		dataProxyFastFactories.add( dataProxyFastType );
		return getDataProxyFastFactoryName( dataProxyFastType );
	}
	
	// TODO everything
	void generateFactories()
	{
		for( JType type : dataProxyFastFactories )
		{
			generateFactory( type );
			
			// Generate the data stub (JSO emulating data interface)
			//packageName+"."+getDataProxyFastFactoryName(type);
			
			String dataImplName = getDataProxyFastImplName(type);
		    PrintWriter pw2 = context.tryCreate( logger, packageName, dataImplName );
		    if( pw2 != null )
		    {
			    ClassSourceFileComposerFactory cf2 = new ClassSourceFileComposerFactory( packageName, dataImplName );
			    cf2.addImport( "com.hexa.client.comm.GenericJSO" );
			    cf2.addImport( "com.google.gwt.core.client.JavaScriptObject" );
			    cf2.addImport( "com.google.gwt.core.client.GWT" );
			    cf2.addImport( "com.hexa.client.tools.HexaTools" );
			    cf2.addImplementedInterface( type.getParameterizedQualifiedSourceName() );
			    cf2.setSuperclass( "GenericJSO" );
			  //sw.println( "static class "+getDataProxyFastImplName(type)+" extends GenericJSO implements "+type.getParameterizedQualifiedSourceName() );
			    SourceWriter sw2 = cf2.createSourceWriter( context, pw2 );
			    DataProxyFastFactoryGenerator.generateDataImpl( dataImplName, type, sw2, logger );
			    sw2.commit( logger );
		    }
		    
		}
	}
	
	private void generateFactory( JType type )
	{
		sw.println( "// FACTORY FOR " + type.getParameterizedQualifiedSourceName() );
		sw.println( "@FactoredInterface( clazz="+type.getParameterizedQualifiedSourceName()+".class )" );
		sw.println( "public interface " + getDataProxyFastFactoryName( type ) + " extends IDataProxyFastFactory {}" );
		//DataProxyFastFactoryGenerator.generate( type, sw, logger );
	}
	
	/*
	 * 
	 
	 class DaatDataProxyFastFactory implements DataProxyFastFactories.IDataProxyFastFactory
	{
		class DaatImpl extends GenericJSO implements Daat
		{
			protected DaatImpl() {}
			
			public final int getId()
			{
				return getInt( "field_name" );
			}
		}
		
		@Override
		public <T> T getData( JavaScriptObject obj )
		{
			return (T)((DaatImpl)obj);
		}
	}
	 
	 */
	
	ArrayList<OnResponseCallbackInfo> onResponseCallbacks = new ArrayList<OnResponseCallbackInfo>();
	
	String registerOnResponseCallback( JType callbackType )
	{
		for( OnResponseCallbackInfo info : onResponseCallbacks )
			if( info.isFor( callbackType ) )
				return info.callbackName;
		
		OnResponseCallbackInfo info = new OnResponseCallbackInfo( callbackType );
		onResponseCallbacks.add( info );
		return info.callbackName;
	}
	
	void generateOnResponseCallbacks()
	{
		for( OnResponseCallbackInfo info : onResponseCallbacks )
		{
			sw.println();
			generateOnResponseCallback( info );
			sw.println();
		}
	}
	
	void generateOnResponseCallback( OnResponseCallbackInfo info )
	{
		sw.println( "// Callback type : " + info.callbackType.getParameterizedQualifiedSourceName() );
		sw.println( "ServerCommCb " + info.callbackName + " = new ServerCommCb() {" );
		
		sw.indent();
		
		generateOnResponseBody( info.callbackType );
		
		sw.outdent();
		sw.println( "};" );
	}
	
	void generateOnResponseBody( JType callbackType )
	{
		sw.println( "public void onResponse(Object cookie, ResponseJSO response, int msgLevel, String msg)" );
		sw.println( "{" );
		sw.indent();
		
		String cbMethodName = null;//JMethod cbMethod = null;
		JType[] cbParamTypes = null;
		
		assert callbackType!=null : "No callback type specified for generating onResponse body";
		
		String cbTypeTxt = callbackType.getQualifiedSourceName();
		JClassType cbType = typeOracle.findType( cbTypeTxt );
		assert cbType!=null : "Cannot find type : "+cbTypeTxt;
		if( cbType.getSimpleSourceName().equals("IAsyncCallback") )
		{
			JType asyncCallbackType = callbackType;
			JParameterizedType asyncCallbackPType = asyncCallbackType.isParameterized();
			JClassType[] asyncCallbackPTypes = asyncCallbackPType.getTypeArgs();
			//logger.log( TreeLogger.WARN, "Callback interface is IAsyncCallback =>" + cbType.getParameterizedQualifiedSourceName(), null );
			
			JType cbParamType = asyncCallbackPTypes[0];
			//logger.log( TreeLogger.WARN, "Inside IAsyncCallback is the type =>" + cbParamType.getParameterizedQualifiedSourceName(), null );
			
			cbParamTypes = new JType[1];
			cbParamTypes[0] = cbParamType;
			
			cbMethodName = "onSuccess";
		}
		else if( cbType.getMethods().length != 1 )
		{
			if( cbType.getSimpleSourceName().compareTo("AsyncCallback") == 0 )
			{
				JType asyncCallbackType = callbackType;
				JParameterizedType asyncCallbackPType = asyncCallbackType.isParameterized();
				JClassType[] asyncCallbackPTypes = asyncCallbackPType.getTypeArgs();
				//logger.log( TreeLogger.WARN, "Callback interface is AsyncCallback =>" + cbType.getParameterizedQualifiedSourceName(), null );
				
				JType cbParamType = asyncCallbackPTypes[0];
				//logger.log( TreeLogger.WARN, "Inside AsyncCallback is the type =>" + cbParamType.getParameterizedQualifiedSourceName(), null );
				
				cbParamTypes = new JType[1];
				cbParamTypes[0] = cbParamType;
				
				cbMethodName = "onSuccess";
			}
			else
			{
				sw.println( "*** ERROR : The callback type has more than one method : " + cbType.getQualifiedSourceName() );
				return;
			}
		}
		else
		{
			sw.println( "// type of callback is : " + callbackType.getParameterizedQualifiedSourceName() );
			JParameterizedType parameterizedType = callbackType.isParameterized();// cbType.isParameterized();
			if( parameterizedType != null )
			{				
				cbType = callbackType.isClassOrInterface();
				
				JMethod cbMethod = cbType.getMethods()[0];
				cbMethodName = cbType.getMethods()[0].getName();
				
				JParameter[] cbParams = cbMethod.getParameters();
				cbParamTypes = new JType[cbParams.length];
				for( int i=0; i<cbParams.length; i++ )
					cbParamTypes[i] = cbParams[i].getType();
			}
			else
			{
				JMethod cbMethod = cbType.getMethods()[0];
				cbMethodName = cbMethod.getName();
				JParameter[] cbParams = cbMethod.getParameters();
				cbParamTypes = new JType[cbParams.length];
				for( int i=0; i<cbParams.length; i++ )
					cbParamTypes[i] = cbParams[i].getType();
			}
		}
		
		for( int i=0; i<cbParamTypes.length; i++ )
		{
			String paramTypeName = cbParamTypes[i].getSimpleSourceName();
			
			if( paramTypeName.compareTo( "int" ) == 0 )
			{
				sw.println( "int param"+i+" = response.getInt("+i+");" );
			}
			else if( paramTypeName.compareTo( "boolean" ) == 0 )
			{
				sw.println( "String param"+i+" = response.getBoolean("+i+");" );
			}
			else if( paramTypeName.compareTo( "Integer" ) == 0 )
			{
				sw.println( "Integer param"+i+" = response.getInt("+i+");" );
			}
			else if( paramTypeName.compareTo( "Double" ) == 0 )
			{
				sw.println( "Double param"+i+" = response.getDouble("+i+");" );
			}
			else if( paramTypeName.compareTo( "String" ) == 0 )
			{
				sw.println( "String param"+i+" = response.getString("+i+");" );
			}
			else if( implementsInterface( cbParamTypes[i], "DataProxyFast" ) )
			{
				// TODO register the factory for this type for generating them later
				registerFactory( cbParamTypes[i] );
				
				// write code to call the factory
				sw.println( "// FactoryCall" );
				String elementType = cbParamTypes[i].getQualifiedSourceName();
				sw.println( getDataProxyFastImplName(cbParamTypes[i])+" tmpObj"+i+" = response.getJSO("+i+").cast();" );
				sw.println( elementType + " param"+i+" = tmpObj"+i+";" );
				//sw.println( elementType + " param"+i+" = factory.getData( "+elementType+".class, tmpObj"+i+" );" );
				
				//sw.println( getDataProxyFastImplName(cbParamTypes[i])+" tmpObj"+i+" = response.getJSO("+i+").cast();" );
				//sw.println( elementType + " param"+i+" = ("+elementType+")tmpObj"+i+";" );
				////sw.println( elementType + " param"+i+" = factory.getData( "+elementType+".class, response.getJSO("+i+") );" );
			}
			else if( paramTypeName.equals( "Iterable" ) )
			{
				JParameterizedType type = cbParamTypes[i].isParameterized();
				assert( type != null ) : "*** ERROR : An Iterable should have be parametrized : " + cbType.getQualifiedSourceName();
				JClassType[] typeArgs = type.getTypeArgs();
				assert( typeArgs.length == 1 ) : "*** ERROR : An Iterable can contain only and at least one type arg : " + cbType.getQualifiedSourceName();
				
				registerFactory( typeArgs[0] );
				
				sw.println( "// FactoryCall" );
				String elementType = typeArgs[0].getQualifiedSourceName();
				sw.println( "JsArray<JavaScriptObject> objTmp"+i+" = response.getJSO("+i+").cast();" );
				sw.println( "Iterable<" + elementType + "> param"+i+" = factory.getList( "+elementType+".class, objTmp"+i+" );" );
			}
			else if( implementsInterface( cbParamTypes[i], "java.util.List" ) /*paramTypeName.compareTo( "ArrayList" ) == 0*/ )
			{
				JParameterizedType type = cbParamTypes[i].isParameterized();
				if( type == null )
				{
					sw.println( "*** ERROR : An ArrayList should have be parametrized : " + cbType.getQualifiedSourceName() );
					return;
				}
				
				JClassType[] typeArgs = type.getTypeArgs();
				if( typeArgs.length != 1 ) {
					sw.println( "*** ERROR : An ArrayList can contain only and at least one type arg : " + cbType.getQualifiedSourceName() );
					return;
				}
				
				JClassType pType = typeArgs[0];
				String pTypeName = pType.getSimpleSourceName();
				String jsArrayType = null;
				String jsoGetElemMethod = null;
				if( pTypeName.compareTo( "Integer") == 0 ) {
					jsArrayType = "JSOArrayInteger";
					jsoGetElemMethod = "getArrayInteger";
				}
				else if( pTypeName.compareTo( "Double") == 0 ) {
					jsArrayType = "JSOArrayDouble";
					jsoGetElemMethod = "getArrayDouble";
				}
				else if( pTypeName.compareTo( "String") == 0 ) {
					jsArrayType = "JsArrayString";
					jsoGetElemMethod = "getArrayString";
				}
				if( jsArrayType!=null && jsoGetElemMethod!=null ) {
					sw.println( "ArrayList<"+pTypeName+"> param"+i+" = new ArrayList<"+pTypeName+">();" );
					sw.println( jsArrayType + " jsos"+i+" = response."+jsoGetElemMethod+"("+i+");" );
					sw.println( "for( int i=0; i<jsos"+i+".length(); i++ )" );
					sw.println( "	param"+i+".add( jsos"+i+".get(i) );" );
				}
				else {
					// assume pType is one that is serializable...
					String elementType = pType.getQualifiedSourceName();
					
					// Assume DataProxy
					String proxyName = registerProxy( pType );
					//sw.println( "ArrayList<"+elementType+"> param"+i+" = deserializeArray( response.getArray("+i+"), new "+proxyName+"() );" );
					sw.println( "ArrayList<"+elementType+"> param"+i+" = deserializeArray( response.getArray("+i+"), "+proxyName+" );" );
				}
			}
			else if( isJSOType( cbParamTypes[i] ) )
			{
				String elementType = cbParamTypes[i].getQualifiedSourceName();
				sw.println( elementType + " param"+i+" = response.getJSO("+i+").cast();" );
				//sw.println( "JavaScriptObject param"+i+" = response.getJSO("+i+");" );
			}
			else// if( implementsInterface( cbParamTypes[i], "DataProxy" ) || implementsInterface( cbParamTypes[i], "DataProxySerialized" ) )
			{
				// assume that the type is serializable...
				String elementType = cbParamTypes[i].getQualifiedSourceName();
				
				sw.println( elementType + " param"+i+" = null;" );
				sw.println( "if( response.getJSO("+i+") != null )" );
				sw.println( "{" );
				sw.println( "    param"+i+" = GWT.create( "+elementType+".class );" );
				sw.println( "    param"+i+".init( response.getJSO("+i+") );" );
				sw.println( "}" );
			}
			//else
			//{
			//	logger.log( TreeLogger.ERROR, "Type "+paramTypeName+" has no policy to be returned to the application from the server..." );
			//}
			
			if( i < cbParamTypes.length - 1 )
				sw.println();
		}
		
		sw.println();
		
		String prmsTxt = "";
		for( int i=0; i<cbParamTypes.length; i++ ) {
			prmsTxt = prmsTxt + "param" + i;
			if( i < cbParamTypes.length-1 )
				prmsTxt = prmsTxt + ", ";
		}
		sw.println( "(("+cbType.getSimpleSourceName()+")cookie)."+cbMethodName+"( "+prmsTxt+" );" );
		sw.outdent();
		sw.println( "}" );
	}
	
	HashMap<String,JClassType> proxiesToGenerate = new HashMap<String, JClassType>();
	String registerProxy( JClassType pType )
	{
		proxiesToGenerate.put( pType.getQualifiedSourceName(), pType );
		return getProxyName( pType );
	}
	
	String getProxyName( JClassType pType )
	{
		return "proxy_" + pType.getSimpleSourceName();
	}
	
	void generateProxies()
	{
		sw.println( "	public abstract class Proxy<T> {" );
		sw.println( "	    abstract public T create( GenericJSO jso );" );
		sw.println( "}" );
		sw.println();
		sw.println( "@SuppressWarnings( \"unused\" )" );
		sw.println( "private <T> ArrayList<T> deserializeArray( JsArray<GenericJSO> jsoss, Proxy<T> proxy ) {" );
		sw.println( "	ArrayList<T> list = new ArrayList<T>();" );
		sw.println( "	int length = jsoss.length();" );
		sw.println( "	for (int i = 0; i < length; i++)" );
		sw.println( "		list.add( proxy.create( jsoss.get(i) ) );" );
		sw.println( "	return list;" );
		sw.println( "}" );
		sw.println();
		
		for( Iterator<Entry<String,JClassType>> it=proxiesToGenerate.entrySet().iterator(); it.hasNext(); )
			generateProxy( it.next().getValue() );
	}
	
	void generateProxy( JClassType pType )
	{
		String fullName = pType.getParameterizedQualifiedSourceName();
		
		sw.println( "Proxy<"+fullName+"> "+getProxyName(pType)+" = new Proxy<"+fullName+">() {" );
		sw.indent();
		sw.println( "public "+fullName+" create( GenericJSO jso )" );
		sw.println( "{" );
		sw.indent();
		if( isJSOType(pType) )
		{
			sw.println( "return jso.cast();" );
		}
		else
		{
			sw.println( fullName+" o = GWT.create( "+fullName+".class );" );
			sw.println( "o.init(jso);" );
			sw.println( "return o;" );
		}
		sw.outdent();
		sw.println( "}" );
		sw.outdent();
		sw.println( "};" );
		sw.println();
	}
	
	boolean isJSOType( JType type )
	{
		JClassType cType = type.isClass();
		if( cType == null )
			cType = type.isInterface();
		if( cType == null )
			return false;
		
		for( JClassType t=cType; t!=null; t=t.getSuperclass() )
		{
			sw.println( "// supertype : " + t.getSimpleSourceName() );
			JTypeParameter typeParam = t.isTypeParameter();
			if( typeParam != null )
			{
				sw.println( "// which is a type parameter" );
				JClassType[] bounds = typeParam.getBounds();
				for( int b=0; b<bounds.length; b++ )
				{
					sw.println( "// which is bound to : " + bounds[b].getSimpleSourceName() );
					if( bounds[b].getSimpleSourceName().compareTo( "JavaScriptObject" ) == 0 )
						return true;
				}
			}
			
			if( t.getSimpleSourceName().compareTo( "JavaScriptObject" ) == 0 )
				return true;
		}
		
		return false;
	}
}
