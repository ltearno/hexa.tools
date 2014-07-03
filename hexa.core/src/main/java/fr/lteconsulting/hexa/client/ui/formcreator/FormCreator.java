package fr.lteconsulting.hexa.client.ui.formcreator;

import java.util.HashMap;
import java.util.Map.Entry;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

class FormCreator
{
	private final int spacing = 5;

	private final StringBuilder sb = new StringBuilder();
	private final HashMap<String, Widget> wMap = new HashMap<String, Widget>();

	private final int totalWidth;

	private boolean fFirstItem = true;
	private int currentlyUsedWidth = 0;

	public FormCreator( int totalWidth )
	{
		this.totalWidth = totalWidth;
	}

	private void manageSpace()
	{
		if( !fFirstItem )
			space();
		fFirstItem = false;
	}

	public FormCreator label( String text, int width )
	{
		manageSpace();

		sb.append( "<div class='" + FormCreatorBundle.CSS.fl() + " " + FormCreatorBundle.CSS.b() + "' style='width:" + width + "px;'>" + text + " :</div>" );

		currentlyUsedWidth += width;

		return this;
	}

	public FormCreator space()
	{
		sb.append( "<div class='" + FormCreatorBundle.CSS.sp() + "' style='width:" + spacing + "px;'>&nbsp;</div>" );

		currentlyUsedWidth += spacing;

		return this;
	}

	public FormCreator field( Widget widget, int width )
	{
		manageSpace();

		String id = DOM.createUniqueId();

		wMap.put( id, widget );
		sb.append( "<div id='" + id + "' class='" + FormCreatorBundle.CSS.fl() + " " + FormCreatorBundle.CSS.b() + "' style='width:" + width + "px;'></div>" );

		currentlyUsedWidth += width;

		return this;
	}

	// automatically fills the line to the end
	public FormCreator field( Widget widget )
	{
		return field( widget, totalWidth - currentlyUsedWidth - (fFirstItem ? 0 : spacing) );
	}

	public FormCreator br()
	{
		if( !fFirstItem )
			sb.append( "<div style='clear:both;'></div>" );

		sb.append( "<div style='height:10px;'>&nbsp</div>" );

		fFirstItem = true;
		currentlyUsedWidth = 0;

		return this;
	}

	public FormCreator section( String title )
	{
		manageSpace();

		sb.append( "<div class='" + FormCreatorBundle.CSS.fl() + " " + FormCreatorBundle.CSS.b() + " " + FormCreatorBundle.CSS.section() + "' style='width:" + totalWidth + "px;'>" + title + "</div>" );
		br();

		return this;
	}

	public HTMLPanel create()
	{
		FormCreatorBundle.CSS.ensureInjected();

		HTMLPanel panel = new HTMLPanel( sb.toString() );
		panel.setStyleName( FormCreatorBundle.CSS.form() );
		panel.setWidth( totalWidth + "px" );

		for( Entry<String, Widget> e : wMap.entrySet() )
		{
			e.getValue().setWidth( "100%" );
			panel.add( e.getValue(), e.getKey() );
		}

		return panel;
	}
}

interface FormCreatorBundle extends ClientBundle
{
	public static FormCreatorBundle INSTANCE = GWT.create( FormCreatorBundle.class );
	public static FormCreatorCss CSS = INSTANCE.css();

	@Source( "FormCreator.css" )
	FormCreatorCss css();
}

interface FormCreatorCss extends CssResource
{
	String form();

	String section();

	String fl();

	String b();

	String sp();
}