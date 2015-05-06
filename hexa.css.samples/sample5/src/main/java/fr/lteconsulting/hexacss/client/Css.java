package fr.lteconsulting.hexacss.client;

import fr.lteconsulting.hexa.client.css.HexaCss;
import fr.lteconsulting.hexa.client.css.annotation.HexaCssExtra;

/**
 * Some CSS classes have been added in the skeleton.css file.
 * 
 * This is the interface to bind them
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 *
 */
public interface Css extends HexaCss
{
	@HexaCssExtra(name="example-grid")
	String exampleColumn();
}
