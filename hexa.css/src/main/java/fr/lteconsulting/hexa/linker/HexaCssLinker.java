package fr.lteconsulting.hexa.linker;

import java.util.Map.Entry;
import java.util.SortedSet;

import com.google.gwt.core.ext.LinkerContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.TreeLogger.Type;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.linker.AbstractLinker;
import com.google.gwt.core.ext.linker.ArtifactSet;
import com.google.gwt.core.ext.linker.EmittedArtifact.Visibility;
import com.google.gwt.core.ext.linker.LinkerOrder;
import com.google.gwt.core.ext.linker.LinkerOrder.Order;
import com.google.gwt.core.ext.linker.Shardable;
import com.google.gwt.core.ext.linker.SyntheticArtifact;

import fr.lteconsulting.hexa.rebind.HexaCssArtifact;

/**
 * This linker gathers all permutations HexaCss references in one file
 *
 */
@LinkerOrder( Order.POST )
@Shardable
public class HexaCssLinker extends AbstractLinker
{
	@Override
	public String getDescription()
	{
		return "HexaCssLinker, reporting css use accross source files";
	}
	
	@Override
	public ArtifactSet link( TreeLogger logger, LinkerContext context, ArtifactSet artifacts, boolean onePermutation ) throws UnableToCompleteException
	{
		if( onePermutation )
			return artifacts;
		
		logger.log( Type.INFO, "Linking HexaCss files..." );
		
		StringBuilder filesContents = new StringBuilder();
		SortedSet<HexaCssArtifact> csss = artifacts.find( HexaCssArtifact.class );
		if( csss != null )
		{
			for( HexaCssArtifact css : csss )
			{
				for( Entry<String,String> entry : css.getReferencesMapping().entrySet() )
					filesContents.append( entry.getValue() + "=" + entry.getKey() + "\n" );
			}
		}
		
		SyntheticArtifact createdArtifact = emitString( logger, filesContents.toString(), "hexas-css.mapping" );
		createdArtifact.setVisibility(Visibility.Public);
		
		artifacts = new ArtifactSet( artifacts );
		artifacts.add( createdArtifact );
		
		logger.log( Type.INFO, filesContents.toString() );
		
		logger.log( Type.INFO, "Added artifacts hexa-css.refs and hexa-css.less." );
	
		return artifacts;
	}
}
