package fr.lteconsulting.hexa.client.ui.Criteria;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;

import fr.lteconsulting.hexa.client.comm.GenericJSO;
import fr.lteconsulting.hexa.client.interfaces.ICriteriaFieldMng;
import fr.lteconsulting.hexa.client.ui.Styles;

public class Criteria extends Composite
{
	public static String getBeautifulText( String searchString )
	{
		return CriteriaInternal.getBeautifulText( searchString );
	}

	boolean fMode; // false == no criteria displayed
	SimplePanel spot = new SimplePanel();

	Anchor searchButton = new Anchor( "Click to specify your research..." );

	String criteriaSpotId = DOM.createUniqueId();
	String removeButtonId = DOM.createUniqueId();

	HTMLPanel panel = new HTMLPanel( "<div><a id='" + removeButtonId + "' href='#' style='float:right'>remove</a><div id='" + criteriaSpotId + "'/></div>" );
	CriteriaInternal realCriteria;

	public Criteria( ICriteriaFieldMng[] criteriaMngs )
	{
		realCriteria = new CriteriaInternal( criteriaMngs );

		spot.addStyleName( Styles.CSS.framedPanel() );
		initWidget( spot );

		panel.add( realCriteria, criteriaSpotId );
		setMode( false );

		Anchor a = new Anchor( "remove" );
		a.getElement().getStyle().setFloat( Style.Float.RIGHT );
		a.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				event.preventDefault();
				event.stopPropagation();
				setMode( false );
			}
		} );
		panel.addAndReplaceElement( a, removeButtonId );

		searchButton.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				setMode( true );
			}
		} );
	}

	public JSONObject getSearchJson()
	{
		if( !fMode )
			return null;

		return realCriteria.getSearchJson().isObject();
	}

	public JavaScriptObject getSearchJs()
	{
		JSONObject obj = getSearchJson();

		if( obj == null )
			return null;

		return obj.getJavaScriptObject();
	}

	public void setSearchString( GenericJSO jso )
	{
		if( jso == null )
		{
			setMode( false );
			return;
		}

		setMode( true );
		realCriteria.setSearchString( jso );
	}

	void setMode( boolean fMode )
	{
		this.fMode = fMode;

		if( fMode )
			spot.setWidget( panel );
		else
			spot.setWidget( searchButton );
	}
}
