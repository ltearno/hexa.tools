package fr.lteconsulting.client;

import fr.lteconsulting.hexa.databinding.tools.Property;

/**
 * A very simple dynamic DTO.
 * 
 * It is made of {@link Property} fields, which handle a value
 * and the possibility to subscribe.
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 *
 */
public class Category
{
	public final Property<String> name = new Property<String>( this, "name", null );
	public final Property<String> color = new Property<String>( this, "color", null );

	public Category( String name, String color )
	{
		this.name.setValue( name );
		this.color.setValue( color );
	}
}
