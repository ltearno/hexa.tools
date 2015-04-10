package fr.lteconsulting.hexa.rebind;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.ext.linker.Artifact;
import com.google.gwt.core.ext.linker.Transferable;

import fr.lteconsulting.hexa.linker.HexaCssLinker;

@Transferable
public class HexaCssArtifact extends Artifact<HexaCssArtifact>
{
	private static final long serialVersionUID = -2530463324235624279L;
	
	Map<String, String> referencesMapping;
	
	public Map<String, String> getReferencesMapping()
	{
		return referencesMapping;
	}

	protected HexaCssArtifact( Map<String, String> referencesMapping )
	{
		super( HexaCssLinker.class );
		this.referencesMapping = new HashMap<>( referencesMapping);
	}

	@Override
	protected int compareToComparableArtifact( HexaCssArtifact o )
	{
		int r = referencesMapping.size() - o.referencesMapping.size();
		if( r != 0 )
			return r;
		for( String e : referencesMapping.keySet() )
			if( ! o.referencesMapping.containsKey( e ) )
				return -1;
		return 0;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((referencesMapping == null) ? 0 : referencesMapping
						.hashCode());
		return result;
	}

	@Override
	protected Class<HexaCssArtifact> getComparableArtifactType()
	{
		return HexaCssArtifact.class;
	}

	public void addReferencesMapping( String className, String mappedTo )
	{
		referencesMapping.put( className, mappedTo );
	}
}
