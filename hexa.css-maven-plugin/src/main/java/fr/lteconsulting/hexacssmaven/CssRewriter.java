package fr.lteconsulting.hexacssmaven;

import java.util.Set;

import org.apache.maven.plugin.logging.Log;

import com.helger.commons.charset.CCharset;
import com.helger.css.ECSSVersion;
import com.helger.css.decl.CSSDeclaration;
import com.helger.css.decl.CSSMediaQuery;
import com.helger.css.decl.CSSMediaRule;
import com.helger.css.decl.CSSSelector;
import com.helger.css.decl.CSSSelectorSimpleMember;
import com.helger.css.decl.CSSStyleRule;
import com.helger.css.decl.CSSSupportsRule;
import com.helger.css.decl.CascadingStyleSheet;
import com.helger.css.decl.ICSSSelectorMember;
import com.helger.css.decl.ICSSTopLevelRule;
import com.helger.css.reader.CSSReader;
import com.helger.css.writer.CSSWriter;
import com.helger.css.writer.CSSWriterSettings;

/**
 * Rewrites CSS files, with minifying and optionnally pruning unused css classes.
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 *
 */
public class CssRewriter
{
	private final Log log;
	private final Set<String> usedClassNames;

	public CssRewriter( Set<String> usedClassNames, boolean doPrune, Log log )
	{
		if( doPrune )
			this.usedClassNames = usedClassNames;
		else
			this.usedClassNames = null;

		this.log = log;
	}

	public String process( String input )
	{
		CascadingStyleSheet css = CSSReader.readFromString( input, CCharset.CHARSET_UTF_8_OBJ, ECSSVersion.CSS30 );

		CascadingStyleSheet out = new CascadingStyleSheet();
		for( ICSSTopLevelRule rule : css.getAllRules() )
		{
			ICSSTopLevelRule outRule = filterRule( rule );
			if( outRule != null )
				out.addRule( outRule );
		}

		CSSWriterSettings settings = new CSSWriterSettings( ECSSVersion.CSS30, true );
		settings.setRemoveUnnecessaryCode( true );
		return new CSSWriter( settings ).getCSSAsString( out );
	}

	private ICSSTopLevelRule filterRule( ICSSTopLevelRule rule )
	{
		Class<?> c = rule.getClass();
		if( c == CSSMediaRule.class )
		{
			return filterMediaRule( (CSSMediaRule) rule );
		}
		else if( c == CSSStyleRule.class )
		{
			return filterStyleRule( (CSSStyleRule) rule );
		}
		else if( c == CSSSupportsRule.class )
		{
			return null;
		}
		else
		{
			return rule;
		}
	}

	private CSSMediaRule filterMediaRule( CSSMediaRule rule )
	{
		CSSMediaRule outRule = new CSSMediaRule();
		for( CSSMediaQuery q : rule.getAllMediaQueries() )
			outRule.addMediaQuery( q );

		for( ICSSTopLevelRule r : rule.getAllRules() )
		{
			ICSSTopLevelRule out = filterRule( r );
			if( out != null )
				outRule.addRule( out );
		}

		if( outRule.getRuleCount() == 0 )
			return null;

		return outRule;
	}

	private CSSStyleRule filterStyleRule( CSSStyleRule rule )
	{
		CSSStyleRule outRule = new CSSStyleRule();

		for( CSSSelector sel : ((CSSStyleRule) rule).getAllSelectors() )
		{
			boolean addSelector = true;

			if( usedClassNames != null )
			{
				for( ICSSSelectorMember member : sel.getAllMembers() )
				{
					if( member instanceof CSSSelectorSimpleMember )
					{
						CSSSelectorSimpleMember m = (CSSSelectorSimpleMember) member;
						String value = m.getValue();
						if( !value.startsWith( "." ) )
							continue;

						if( usedClassNames == null || usedClassNames.contains( value.substring( 1 ) ) )
						{
							addSelector = true;
							log.debug( "Found used class name '" + value + "' in selector" );
							break;
						}
					}
				}
			}

			if( addSelector )
				outRule.addSelector( sel );
		}

		if( outRule.getSelectorCount() == 0 )
			return null;

		for( CSSDeclaration declaration : rule.getAllDeclarations() )
			outRule.addDeclaration( declaration );

		return outRule;
	}
}
