package fr.lteconsulting.hexa.databinding.annotation.processor;

import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;

public class Utils
{
	/**
	 * Returns the qualified name of a TypeMirror
	 * 
	 * @param type
	 * @return
	 */
	public static String getTypeQualifiedName( TypeMirror type )
	{
		switch( type.getKind() )
		{
			case ARRAY:
				return getTypeQualifiedName( ((ArrayType) type).getComponentType() ) + "[]";
			case BOOLEAN:
				return "boolean";
			case BYTE:
				return "byte";
			case CHAR:
				return "char";
			case DOUBLE:
				return "double";
			case FLOAT:
				return "float";
			case INT:
				return "int";
			case LONG:
				return "long";
			case SHORT:
				return "short";
			case DECLARED:
				StringBuilder b = new StringBuilder();
				b.append( ((TypeElement) ((DeclaredType) type).asElement()).getQualifiedName().toString() );
				if( !((DeclaredType) type).getTypeArguments().isEmpty() )
				{
					b.append( "<" );
					boolean addComa = false;
					for( TypeMirror pType : ((DeclaredType) type).getTypeArguments() )
					{
						if( addComa )
							b.append( ", " );
						else
							addComa = true;
						b.append( getTypeQualifiedName( pType ) );
					}
					b.append( ">" );
				}
				return b.toString();
			default:
				return type.toString();
		}
	}
}
