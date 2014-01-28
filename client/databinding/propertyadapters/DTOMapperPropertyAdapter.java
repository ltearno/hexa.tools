package com.hexa.client.databinding.propertyadapters;

import com.hexa.client.databinding.DTOMapper;

public class DTOMapperPropertyAdapter extends WriteOnlyPropertyAdapter
{
	Object destinationOfMapping;
	Object mapperResources = null;

	public DTOMapperPropertyAdapter( Object destinationOfMapping )
	{
		this.destinationOfMapping = destinationOfMapping;
	}

	@Override
	public void setValue( Object object )
	{
		if( mapperResources != null )
			DTOMapper.FreeMapping( mapperResources );

		// object is the new selected category
		if( object != null )
			mapperResources = DTOMapper.Map( object, destinationOfMapping );
	}
}
