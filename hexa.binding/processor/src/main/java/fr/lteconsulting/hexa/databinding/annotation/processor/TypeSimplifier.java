/*
 * Copyright (C) 2012 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.lteconsulting.hexa.databinding.annotation.processor;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.WildcardType;
import javax.lang.model.util.SimpleTypeVisitor6;
import javax.lang.model.util.Types;

/**
 * A class from the Google Auto project on Github
 * Special thanks to the authors !
 */
final public class TypeSimplifier
{
	private final Types typeUtils;

	public TypeSimplifier( Types typeUtils )
	{
		this.typeUtils = typeUtils;
	}

	String simplify( TypeMirror type )
	{
		return type.accept( TO_STRING_TYPE_VISITOR, new StringBuilder() ).toString();
	}

	// From the Google Auto project, thanks !
	// The formal type parameters of the given type.
	// It will return the angle-bracket part of:
	// Foo<SomeClass>
	// Foo<T extends SomeClass>
	// Foo<T extends Number>
	// Foo<E extends Enum<E>>
	// Foo<K, V extends Comparable<? extends K>>
	// Type variables need special treatment because we only want to include
	// their bounds when they
	// are declared, not when they are referenced. We don't want to include the
	// bounds of the second E
	// in <E extends Enum<E>> or of the second K in <K, V extends Comparable<?
	// extends K>>. That's
	// why we put the "extends" handling here and not in ToStringTypeVisitor.
	public String formalTypeParametersString( TypeElement type )
	{
		List<? extends TypeParameterElement> typeParameters = type.getTypeParameters();
		if( typeParameters.isEmpty() )
		{
			return "";
		}
		else
		{
			StringBuilder sb = new StringBuilder( "<" );
			String sep = "";
			for( TypeParameterElement typeParameter : typeParameters )
			{
				sb.append( sep );
				sep = ", ";
				appendTypeParameterWithBounds( sb, typeParameter );
			}
			return sb.append( ">" ).toString();
		}
	}

	// From the Google Auto project, thanks !
	// The actual type parameters of the given type.
	// If we have a class Foo<T extends Something> extends Foo<T>.
	// <T extends Something> is the formal type parameter list and
	// <T> is the actual type parameter list, which is what this method returns.
	static public String actualTypeParametersString( TypeElement type )
	{
		List<? extends TypeParameterElement> typeParameters = type.getTypeParameters();
		if( typeParameters.isEmpty() )
		{
			return "";
		}
		else
		{
			StringBuilder sb = new StringBuilder();
			sb.append( "<" );
			String sep = "";
			for( TypeParameterElement typeP : typeParameters )
			{
				sb.append( sep );
				sep = ", ";
				sb.append( typeP.getSimpleName() );
			}
			sb.append( ">" );
			return sb.toString();
		}
	}

	private void appendTypeParameterWithBounds( StringBuilder sb, TypeParameterElement typeParameter )
	{
		sb.append( typeParameter.getSimpleName() );
		String sep = " extends ";
		for( TypeMirror bound : typeParameter.getBounds() )
		{
			if( !bound.toString().equals( "java.lang.Object" ) )
			{
				sb.append( sep );
				sep = " & ";
				bound.accept( TO_STRING_TYPE_VISITOR, sb );
			}
		}
	}

	private final ToStringTypeVisitor TO_STRING_TYPE_VISITOR = new ToStringTypeVisitor();

	/**
	 * Visitor that produces a string representation of a type for use in
	 * generated code. The visitor takes into account the imports defined by
	 * {@link #typesToImport} and will use the short names of those types.
	 *
	 * <p>
	 * A simpler alternative would be just to use TypeMirror.toString() and
	 * regular expressions to pick apart the type references and replace
	 * fully-qualified types where possible. That depends on unspecified
	 * behaviour of TypeMirror.toString(), though, and is vulnerable to
	 * formatting quirks such as the way it omits the space after the comma in
	 * {@code java.util.Map<java.lang.String, java.lang.String>}.
	 */
	private class ToStringTypeVisitor extends SimpleTypeVisitor6<StringBuilder, StringBuilder>
	{
		@Override
		protected StringBuilder defaultAction( TypeMirror type, StringBuilder sb )
		{
			return sb.append( type );
		}

		@Override
		public StringBuilder visitArray( ArrayType type, StringBuilder sb )
		{
			return visit( type.getComponentType(), sb ).append( "[]" );
		}

		@Override
		public StringBuilder visitDeclared( DeclaredType type, StringBuilder sb )
		{
			TypeElement typeElement = (TypeElement) typeUtils.asElement( type );
			String typeString = typeElement.getQualifiedName().toString();
			sb.append( typeString );
			appendTypeArguments( type, sb );
			return sb;
		}

		void appendTypeArguments( DeclaredType type, StringBuilder sb )
		{
			List<? extends TypeMirror> arguments = type.getTypeArguments();
			if( !arguments.isEmpty() )
			{
				sb.append( "<" );
				String sep = "";
				for( TypeMirror argument : arguments )
				{
					sb.append( sep );
					sep = ", ";
					visit( argument, sb );
				}
				sb.append( ">" );
			}
		}

		@Override
		public StringBuilder visitWildcard( WildcardType type, StringBuilder sb )
		{
			sb.append( "?" );
			TypeMirror extendsBound = type.getExtendsBound();
			TypeMirror superBound = type.getSuperBound();
			if( superBound != null )
			{
				sb.append( " super " );
				visit( superBound, sb );
			}
			else if( extendsBound != null )
			{
				sb.append( " extends " );
				visit( extendsBound, sb );
			}
			return sb;
		}
	}

	/**
	 * Returns the name of the given type, including any enclosing types but not
	 * the package.
	 */
	static String classNameOf( TypeElement type )
	{
		String name = type.getQualifiedName().toString();
		String pkgName = packageNameOf( type );
		return pkgName.isEmpty() ? name : name.substring( pkgName.length() + 1 );
	}

	/**
	 * Returns the name of the package that the given type is in. If the type is
	 * in the default (unnamed) package then the name is the empty string.
	 */
	static String packageNameOf( TypeElement type )
	{
		while( true )
		{
			Element enclosing = type.getEnclosingElement();
			if( enclosing instanceof PackageElement )
			{
				return ((PackageElement) enclosing).getQualifiedName().toString();
			}
			type = (TypeElement) enclosing;
		}
	}

	static String simpleNameOf( String s )
	{
		if( s.contains( "." ) )
		{
			return s.substring( s.lastIndexOf( '.' ) + 1 );
		}
		else
		{
			return s;
		}
	}

	/**
	 * Returns the qualified name of a TypeMirror.
	 */
	public static String getTypeQualifiedName(TypeMirror type) throws CodeGenerationIncompleteException
	{
		if(type.toString().equals("<any>")) {
			throw new CodeGenerationIncompleteException("Type reported as <any> is likely a not-yet " +
				"generated parameterized type.");
		}

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
