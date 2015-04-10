package fr.lteconsulting.hexacssmaven;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.maven.plugin.logging.Log;

/**
 * Loads a CSS file and a mapping file. Every selector in the css file are
 * changed to their mapped name. Selectors in the css file that are not used
 * in the mapping file can be pruned (optional).
 */
public class CssRename
{
	/**
	 * Process an input file
	 * 
	 * @param inputFile The input file
	 * @param maapingFile The file containing mapping information (lines in the form of <newName>:<oldName>)
	 */
	public static void processFile( String inputFile, String mappingFile, String outputFile, boolean doPrune, Log log ) throws IOException
	{
		Path mappingPath = Paths.get( mappingFile );

		Path inputPath = Paths.get( inputFile );
		String input = Files.lines( inputPath ).reduce( "", new BinaryOperator<String>()
		{
			@Override
			public String apply( String arg0, String arg1 )
			{
				return arg0 + arg1;
			}
		} );

		// list of used class names
		Set<String> usedClassNames = new HashSet<>();

		// remove comments
		input = input.replaceAll( "(?s)/\\*.*?\\*/", "" );

		// replace class names
		input = replaceClassNames( input, mappingPath, usedClassNames, log );

		log.debug( usedClassNames.size() + " used css classes in the mapping file" );
		log.debug( "used css classes : " + usedClassNames );

		// prune unused classes
		//input = pruneUnusedClasses( input, usedClassNames, doPrune, log );

		// write output
		Path outputPath = Paths.get( outputFile );
		BufferedWriter writer = Files.newBufferedWriter( outputPath, StandardOpenOption.CREATE );
		writer.append( input );
		writer.close();
		
		log.debug( "Wrote to file " + outputPath );
		log.debug( input );
	}

	private static String pruneUnusedClasses( String input, Set<String> usedClassNames, boolean doPrune, Log log )
	{
		if(!doPrune)
			return input;
		
		StringBuilder sb = new StringBuilder();
		Pattern p = Pattern.compile( "([^\\}\\{]+)(\\{[^\\}]+\\})" );
		Matcher m = p.matcher( input );

		int nbUsed = 0;
		int nbNotUsed = 0;

		while( m.find() )
		{
			String selector = m.group( 1 );

			String[] sels = selector.split( "[ \\:\\>\\+]" );
			boolean used = false;
			for( String sel : sels )
			{
				if( !sel.startsWith( "." ) )
					continue;

				if( usedClassNames.contains( sel.substring( 1 ) ) )
				{
					used = true;
					break;
				}
			}

			if( used )
				nbUsed++;
			else
				nbNotUsed++;

			log.debug( (used ? "" : "***NOT_USED*** ") + "selector: " + selector + " content: " + m.group( 2 ) );

			// really prune the class if not used !
			if( (!doPrune) || used )
			{
				sb.append( selector );
				sb.append( m.group( 2 ) );
			}
		}

		log.debug( "Nb used: " + nbUsed + " / not used: " + nbNotUsed );

		return sb.toString();
	}

	private static String replaceClassNames( String input, Path mappingPath, Set<String> usedClassNames, Log log ) throws IOException
	{
		List<String> lines = Files.readAllLines( mappingPath );

		List<String[]> renames = new ArrayList<>();
		for( String line : lines )
			renames.add( line.split( "=" ) );

		Collections.sort( renames, new Comparator<String[]>()
		{
			@Override
			public int compare( String[] a, String[] b )
			{
				return ((Integer) b[1].length()).compareTo( a[1].length() );
			}
		} );

		for( String[] line : renames )
		{
			log.info( line[1] + " => " + line[0] );
			input = input.replaceAll( line[1], line[0] );
			usedClassNames.add( line[0] );
		}

		return input;
	}
}
