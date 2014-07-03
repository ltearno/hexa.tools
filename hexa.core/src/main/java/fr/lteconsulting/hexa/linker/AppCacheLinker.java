package fr.lteconsulting.hexa.linker;

import java.util.UUID;

import com.google.gwt.core.ext.LinkerContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.linker.AbstractLinker;
import com.google.gwt.core.ext.linker.Artifact;
import com.google.gwt.core.ext.linker.ArtifactSet;
import com.google.gwt.core.ext.linker.EmittedArtifact;
import com.google.gwt.core.ext.linker.LinkerOrder;
import com.google.gwt.core.ext.linker.LinkerOrder.Order;
import com.google.gwt.core.ext.linker.Shardable;
import com.google.gwt.core.ext.linker.impl.SelectionInformation;

/**
 * Linker for public path resources in the Application Cache.
 * <p>
 * <strong>Warning</strong>This linker sends all permutations to clients, making
 * it inappropriate for most production use.</strong>
 * <p>
 * To use:
 * <ol>
 * <li>Add {@code manifest="YOURMODULENAME.appcache"} to the {@code <html>} tag
 * in your base html file. E.g., {@code <html manifest="mymodule.appcache">}</li>
 * <li>Add a mime-mapping to your web.xml file:
 * <p>
 * 
 * <pre>
 * {@code <mime-mapping>
 * <extension>manifest</extension>
 * <mime-type>text/cache-manifest</mime-type>
 * </mime-mapping>
 * }
 * </pre>
 * 
 * </li>
 * </ol>
 * <p>
 * On every compile, this linker will regenerate the appcache.nocache.manifest
 * file with files from the public path of your module.
 * <p>
 * To obtain a manifest that contains other files in addition to those generated
 * by this linker, create a class that inherits from this one and overrides
 * {@code otherCachedFiles()}, and use it as a linker instead:
 * <p>
 * 
 */

@Shardable
@LinkerOrder( Order.POST )
public class AppCacheLinker extends AbstractLinker
{
	private static String MANIFEST;

	@Override
	public String getDescription()
	{
		return "AppCacheLinker";
	}

	@Override
	public ArtifactSet link( TreeLogger logger, LinkerContext context, ArtifactSet artifacts, boolean onePermutation ) throws UnableToCompleteException
	{
		MANIFEST = context.getModuleName() + ".appcache";

		ArtifactSet toReturn = new ArtifactSet( artifacts );

		if( onePermutation )
		{
			return toReturn;
		}

		if( toReturn.find( SelectionInformation.class ).isEmpty() )
		{
			logger.log( TreeLogger.INFO, "DevMode warning: Clobbering " + MANIFEST + " to allow debugging. Recompile before deploying your app!" + artifacts );
			// artifacts = null;
			return toReturn;
		}

		// Create the general cache-manifest resource for the landing page:
		toReturn.add( emitLandingPageCacheManifest( context, logger, artifacts ) );
		return toReturn;
	}

	/**
	 * Obtains the extra files to include in the manifest. Ensures the returned
	 * array is not null.
	 */
	protected String[] getCacheExtraFiles( TreeLogger logger, LinkerContext context )
	{
		return new String[] { "SQLiteExplorer.html", "SQLiteExplorer.css", "favicon.ico" };
	}

	/**
	 * Creates the cache-manifest resource specific for the landing page.
	 * 
	 * @param context
	 *            the linker environment
	 * @param logger
	 *            the tree logger to record to
	 * @param artifacts
	 *            {@code null} to generate an empty cache manifest
	 */
	@SuppressWarnings( "rawtypes" )
	private Artifact<?> emitLandingPageCacheManifest( LinkerContext context, TreeLogger logger, ArtifactSet artifacts ) throws UnableToCompleteException
	{
		StringBuilder publicSourcesSb = new StringBuilder();
		StringBuilder staticResoucesSb = new StringBuilder();

		if( artifacts != null )
		{
			// Iterate over all emitted artifacts, and collect all cacheable
			// artifacts
			for( Artifact artifact : artifacts )
			{
				if( artifact instanceof EmittedArtifact )
				{
					EmittedArtifact ea = (EmittedArtifact) artifact;
					String pathName = ea.getPartialPath();
					if( pathName.endsWith( "symbolMap" ) || pathName.endsWith( ".xml.gz" ) || pathName.endsWith( "rpc.log" ) || pathName.endsWith( "gwt.rpc" ) || pathName.endsWith( "manifest.txt" ) || pathName.startsWith( "rpcPolicyManifest" )
							|| pathName.startsWith( "soycReport" ) || pathName.endsWith( ".cssmap" ) )
					{
						continue;// skip these resources
					}
					else
					{
						publicSourcesSb.append( context.getModuleName() ).append( "/" ).append( pathName.replace( "\\", "/" ) ).append( "\n" );
					}
				}
			}

			String[] cacheExtraFiles = getCacheExtraFiles( logger, context );
			for( int i = 0; i < cacheExtraFiles.length; i++ )
			{
				staticResoucesSb.append( cacheExtraFiles[i] );
				staticResoucesSb.append( "\n" );
			}
		}

		// build cache list
		StringBuilder sb = new StringBuilder();
		sb.append( "CACHE MANIFEST\n" );
		sb.append( "# " + UUID.randomUUID() + "-" + System.currentTimeMillis() + "\n" );
		// we have to generate this unique id because the resources can change
		// but
		// the hashed cache.html files can remain the same.
		sb.append( "# Note: must change this every time for cache to invalidate\n" );
		sb.append( "\n" );
		sb.append( "CACHE:\n" );
		sb.append( "# Static app files\n" );
		sb.append( staticResoucesSb );
		sb.append( "\n# Generated app files\n" );
		sb.append( publicSourcesSb );
		sb.append( "\n\n" );
		sb.append( "# All other resources require the user to be online.\n" );
		sb.append( "NETWORK:\n" );
		sb.append( "*\n" );
		sb.append( "http://*\n" );
		sb.append( "\n\n" );
		sb.append( "# Available values: fast, prefer-online\n" );
		sb.append( "SETTINGS:\n" );
		sb.append( "fast\n" );
		logger.log( TreeLogger.DEBUG, "Be sure your landing page's <html> tag declares a manifest: <html manifest=" + MANIFEST + "\">" );

		// Create the manifest as a new artifact and return it:
		return emitString( logger, sb.toString(), "../" + MANIFEST );
	}
}
