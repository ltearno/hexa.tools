package com.hexa.client.ui.Ribbon;

import java.util.ArrayList;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;

public class RibbonDef
{
	public ImageResource logo = null;
	public Widget additionalWidget = null;
	public ArrayList<RibbonDefTab> tabs = new ArrayList<RibbonDefTab>();
}
