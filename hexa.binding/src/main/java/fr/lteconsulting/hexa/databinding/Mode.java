package fr.lteconsulting.hexa.databinding;

/**
 * Defines the data binding mode used between two data end points.<br/>
 * A data binding can happen forward, backward or both ways.
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 *
 */
public enum Mode
{
	/**
	 * Mode used for a data binding happening only from source to destination
	 */
	OneWay,
	
	/**
	 * Mode used for a data binding happening only from destination to source
	 */
	TwoWay,
	
	/**
	 * Mode used for a data binding happening in both ways between source and destination
	 */
	OneWayToSource
}
