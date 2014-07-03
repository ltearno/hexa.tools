package fr.lteconsulting.hexa.server.qpath;

import java.util.HashMap;

@SuppressWarnings( { "rawtypes", "unchecked" } )
public class AutoDTOs
{
	private QPath qpath;

	private HashMap<Class, AutoDTO> autoDTOs = new HashMap<Class, AutoDTO>();

	public AutoDTOs( QPath qpath )
	{
		this.qpath = qpath;
	}

	public <T> AutoDTO<T> get( Class<T> clazz )
	{
		AutoDTO<T> autoDTO = autoDTOs.get( clazz );
		if( autoDTO == null )
		{
			autoDTO = new AutoDTO<T>( clazz, qpath );
			autoDTOs.put( clazz, autoDTO );
		}

		return autoDTO;
	}
}
