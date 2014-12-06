package fr.lteconsulting.hexa.rebind;

import java.util.HashMap;
import java.util.HashSet;

import com.google.gwt.core.ext.linker.Artifact;
import com.google.gwt.core.ext.linker.Transferable;

import fr.lteconsulting.hexa.linker.HexaCssLinker;

@Transferable
public class HexaCssArtifact extends Artifact<HexaCssArtifact>
{
	private static final long serialVersionUID = -2530463324235624279L;
	
	HashSet<String> elements = new HashSet<>();
	HashMap<String, String> referencesMapping = new HashMap<>();
	
	public HashMap<String, String> getReferencesMapping()
	{
		return referencesMapping;
	}

	public HashSet<String> getElements()
	{
		return elements;
	}

	protected HexaCssArtifact()
	{
		super( HexaCssLinker.class );
	}

	@Override
	public int hashCode()
	{
		return elements.hashCode();
	}

	@Override
	protected int compareToComparableArtifact( HexaCssArtifact o )
	{
		int r = elements.size() - o.elements.size();
		if( r != 0 )
			return r;
		for( String e : elements )
			if( ! o.elements.contains( e ) )
				return -1;
		return 0;
	}

	@Override
	protected Class<HexaCssArtifact> getComparableArtifactType()
	{
		return HexaCssArtifact.class;
	}

	public void add( String cssReference )
	{
		elements.add( cssReference );
	}

	public void addReferencesMapping( String className, String mappedTo )
	{
		referencesMapping.put( className, mappedTo );
	}
}
