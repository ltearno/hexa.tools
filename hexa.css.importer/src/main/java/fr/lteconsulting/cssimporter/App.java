package fr.lteconsulting.cssimporter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.helger.commons.charset.CCharset;
import com.helger.css.ECSSVersion;
import com.helger.css.decl.CSSMediaRule;
import com.helger.css.decl.CSSSelector;
import com.helger.css.decl.CSSSelectorSimpleMember;
import com.helger.css.decl.CSSStyleRule;
import com.helger.css.decl.CascadingStyleSheet;
import com.helger.css.decl.ICSSSelectorMember;
import com.helger.css.decl.ICSSTopLevelRule;
import com.helger.css.reader.CSSReader;

/**
 * Hello world!
 *
 */
public class App
{
	public static void main( String[] args )
	{
		App app = new App();
		app.run( args );
	}

	private void run( String[] args )
	{
		System.out.println( "HexaCss importer" );

		// input file path
		String sourcePath = "C:\\Documents\\Repos\\hexa.tools\\hexa.css\\src\\main\\resources\\fr\\lteconsulting\\hexa\\client\\css\\bindings\\foundation.css";

		// output file path (default = .)
		// output class fqn (default = fr.lteconsulting.Css)

		String singletonName = "CSS";
		String className = "FoundationHexaCss";
		String packageName = "fr.lteconsulting.hexa.client.css.bindings";

		String input;
		try
		{
			input = new Scanner( new File( sourcePath ), "UTF8" ).useDelimiter( "\\A" ).next();
		}
		catch( FileNotFoundException e )
		{
			e.printStackTrace();
			return;
		}

		CascadingStyleSheet css = CSSReader.readFromString( input, CCharset.CHARSET_UTF_8_OBJ, ECSSVersion.CSS30 );

		Set<String> classNames = new HashSet<>();

		for( ICSSTopLevelRule rule : css.getAllRules() )
			filterRule( rule, classNames );

		// System.out.println( classNames );

		StringBuilder res = new StringBuilder();

		res.append( "package " + packageName + ";\r\n" );
		res.append( "\r\n" );
		res.append( "import com.google.gwt.core.client.GWT;\r\n" );
		res.append( "\r\n" );
		res.append( "import fr.lteconsulting.hexa.client.css.HexaCss;\r\n" );
		res.append( "import fr.lteconsulting.hexa.client.css.annotation.HexaCssExtra;\r\n" );
		res.append( "\r\n" );
		res.append( "public interface " + className + " extends HexaCss\r\n" );
		res.append( "{\r\n" );
		res.append( "	public static final " + className + " " + singletonName + " = GWT.create( " + className + ".class );\r\n" );

		ArrayList<String> names = new ArrayList<>( classNames );
		Collections.sort( names );

		for( String original : names )
		{
			String transformed = transform( original );

			res.append( "\r\n" );
			res.append( "	@HexaCssExtra( name = \"" + original + "\" )\r\n" );
			res.append( "	String " + transformed + "();\r\n" );
		}

		res.append( "}\r\n" );

		System.out.println( res.toString() );
	}

	private String transform( String s )
	{
		String prefix = "fa-";
		if( s.equals( "fa" ) )
			return "fa";
		if( s.startsWith( prefix ) )
			s = s.substring( prefix.length() );

		StringBuilder sb = new StringBuilder();
		boolean nextUppercase = false;
		for( int i = 0; i < s.length(); i++ )
		{
			char c = s.charAt( i );
			if( c == '-' )
			{
				nextUppercase = true;
			}
			else
			{
				if( nextUppercase )
				{
					String current = sb.toString();
					if( !current.isEmpty() && current.charAt( current.length() - 1 ) >= '0' && current.charAt( current.length() - 1 ) <= '9' )
						sb.append( "_" + c );
					else
						sb.append( ("" + c).toUpperCase() );
				}
				else
				{
					sb.append( c );
				}
				nextUppercase = false;
			}
		}
		return sb.toString();
	}

	private void filterRule( ICSSTopLevelRule rule, Set<String> classNames )
	{
		Class<?> c = rule.getClass();
		if( c == CSSMediaRule.class )
			filterMediaRule( (CSSMediaRule) rule, classNames );
		else if( c == CSSStyleRule.class )
			filterStyleRule( (CSSStyleRule) rule, classNames );
	}

	private void filterMediaRule( CSSMediaRule rule, Set<String> classNames )
	{
		for( ICSSTopLevelRule r : rule.getAllRules() )
			filterRule( r, classNames );
	}

	private void filterStyleRule( CSSStyleRule rule, Set<String> classNames )
	{
		for( CSSSelector sel : ((CSSStyleRule) rule).getAllSelectors() )
		{
			for( ICSSSelectorMember member : sel.getAllMembers() )
			{
				if( member instanceof CSSSelectorSimpleMember )
				{
					CSSSelectorSimpleMember m = (CSSSelectorSimpleMember) member;
					String value = m.getValue();
					if( !value.startsWith( "." ) )
						continue;

					classNames.add( value.substring( 1 ) );
				}
			}
		}
	}
}
