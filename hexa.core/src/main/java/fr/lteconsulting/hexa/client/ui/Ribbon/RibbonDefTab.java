package fr.lteconsulting.hexa.client.ui.Ribbon;

import java.util.ArrayList;

public class RibbonDefTab
{
	public String id;

	public RibbonDefTab( String id )
	{
		this.id = id;
	}

	public String name;
	public ArrayList<RibbonDefButton> buttons = new ArrayList<RibbonDefButton>();
}
