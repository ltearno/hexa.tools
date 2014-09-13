package fr.lteconsulting.hexa.linker;

import java.util.HashSet;
import java.util.SortedSet;

import com.google.gwt.core.ext.LinkerContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.linker.AbstractLinker;
import com.google.gwt.core.ext.linker.ArtifactSet;
import com.google.gwt.core.ext.linker.LinkerOrder;
import com.google.gwt.core.ext.linker.EmittedArtifact.Visibility;
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
		{
			return artifacts;
		}
		
		HashSet<String> elements = new HashSet<String>();
		
		StringBuilder filesContents = new StringBuilder();
		SortedSet<HexaCssArtifact> csss = artifacts.find( HexaCssArtifact.class );
		if( csss != null )
		{
			for( HexaCssArtifact css : csss )
			{
				elements.addAll( css.getElements() );
				
				for( String content : css.getFileContents().values() )
				{
					filesContents.append( content );
					filesContents.append( "\n" );
				}
			}
		}
		
		if( ! elements.isEmpty() )
		{
			StringBuilder sb = new StringBuilder();
			for( String element : elements )
			{
				sb.append( element );
				sb.append( "\n" );
			}
		
			SyntheticArtifact createdArtifact = emitString( logger, sb.toString(), "hexas-css.refs" );
			createdArtifact.setVisibility(Visibility.Public);
			artifacts.add( createdArtifact );
			
			createdArtifact = emitString( logger, filesContents.toString(), "hexas-css.less" );
			createdArtifact.setVisibility(Visibility.Public);
			artifacts.add( createdArtifact );
		}
		
		return artifacts;
	}
}
