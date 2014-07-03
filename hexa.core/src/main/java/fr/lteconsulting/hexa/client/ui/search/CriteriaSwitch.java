package fr.lteconsulting.hexa.client.ui.search;

import java.util.Collection;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import fr.lteconsulting.hexa.client.ui.widget.ListBoxDiscrete;

public class CriteriaSwitch extends Composite implements ICriteriaWidget
{
	public interface Resources
	{
		ImageResource dropdown();
	}

	interface DefaultResources extends Resources, ClientBundle
	{
		@Override
		@Source( "images/dropdown.png" )
		ImageResource dropdown();
	}

	private static Resources defaultResources = null;
	private Resources resources = null;

	public interface XCriteriaSwitch
	{
		boolean getIsInline( ICriteriaMng mng );
	}

	XCriteriaSwitch callback;

	private boolean fReadOnly;

	ListBoxDiscrete<ICriteriaMng> lb;

	SimplePanel spotHorizontal = new SimplePanel();
	SimplePanel spotVertical = new SimplePanel();
	ICriteriaWidget iCriteria = null;

	public CriteriaSwitch( Collection<ICriteriaMng> criteriaMngs, XCriteriaSwitch callback, Resources resources, boolean fReadOnly )
	{
		this.fReadOnly = fReadOnly;

		if( resources == null )
		{
			if( defaultResources == null )
				defaultResources = GWT.create( DefaultResources.class );

			this.resources = defaultResources;
		}
		else
		{
			this.resources = resources;
		}

		lb = new ListBoxDiscrete<ICriteriaMng>( this.resources.dropdown(), this.resources.dropdown() );

		this.callback = callback;

		HorizontalPanel panel = new HorizontalPanel();
		panel.add( lb );
		panel.setCellWidth( lb, "150px" );
		panel.add( spotHorizontal );

		VerticalPanel v = new VerticalPanel();
		v.add( panel );
		v.add( spotVertical );

		initWidget( v );

		for( ICriteriaMng mng : criteriaMngs )
			lb.addItem( mng.getDisplayName(), mng );

		lb.addChangeHandler( new ChangeHandler()
		{
			@Override
			public void onChange( ChangeEvent event )
			{
				onSelChange();
			}
		} );
	}

	@Override
	public JSONValue getValue()
	{
		if( iCriteria == null )
			return null;
		return iCriteria.getValue();
	}

	@Override
	public void setValue( JSONValue json )
	{
		if( iCriteria == null )
			return;
		iCriteria.setValue( json );
	}

	public void setCriteriaMng( ICriteriaMng mng )
	{
		lb.setSelected( mng );
		updateCriteria( mng, null );
	}

	public void setCriteriaMng( ICriteriaMng mng, ICriteriaWidget widget )
	{
		lb.setSelected( mng );
		iCriteria = widget;

		boolean fIsInline = callback.getIsInline( mng );
		(fIsInline ? spotHorizontal : spotVertical).setWidget( iCriteria.asWidget() );
		(fIsInline ? spotVertical : spotHorizontal).clear();
	}

	// the selection box value has changed
	void onSelChange()
	{
		ICriteriaMng sel = lb.getSelected();
		updateCriteria( sel, null );
	}

	void updateCriteria( ICriteriaMng mng, JSONValue json )
	{
		if( mng == null )
		{
			iCriteria = null;
			spotHorizontal.clear();
			spotVertical.clear();
			return;
		}

		iCriteria = mng.createCriteriaWidget( json, fReadOnly );

		// get the alignment
		boolean fIsInline = callback.getIsInline( mng );
		(fIsInline ? spotHorizontal : spotVertical).setWidget( iCriteria.asWidget() );
		(fIsInline ? spotVertical : spotHorizontal).clear();
	}
}