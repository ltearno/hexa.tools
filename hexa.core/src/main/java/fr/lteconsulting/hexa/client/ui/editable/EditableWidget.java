package fr.lteconsulting.hexa.client.ui.editable;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.BlurHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public abstract class EditableWidget<WIDGET extends Widget & HasText & HasClickHandlers, COOKIE> extends Composite implements ClickHandler, KeyUpHandler, BlurHandler
{
	public interface Callback<WIDGET extends Widget & HasText & HasClickHandlers, COOKIE>
	{
		void onWantChange( String text, EditableWidget<WIDGET, COOKIE> widget, COOKIE cookie );
	}

	public interface OnEditCallback<WIDGET extends Widget & HasText & HasClickHandlers, COOKIE>
	{
		// return false to cancel edition
		// during the call to this method, callee can call setText() to set the
		// text in the textbox
		boolean onEdit( EditableWidget<WIDGET, COOKIE> widget, COOKIE cookie );
	}

	ImageResource m_loadingImage = null;

	WIDGET m_widget = null;

	boolean m_fEditing = false;
	String m_editingText = null;

	Callback<WIDGET, COOKIE> m_callback;
	OnEditCallback<WIDGET, COOKIE> m_onEditCallback = null;
	COOKIE m_cookie;

	SimplePanel m_spot = new SimplePanel();
	boolean fEditable = true; // by default

	TextBox m_box = null;
	// GrowingTextArea m_box = null;

	Image m_image = null;

	double m_fontSize;
	Unit m_fontSizeUnit = null;

	// This method is called when the display widget needs to be initialized
	abstract WIDGET implInitDisplayWidget();

	public EditableWidget( ImageResource loadingImage, Callback<WIDGET, COOKIE> callback, COOKIE cookie )
	{
		m_loadingImage = loadingImage;

		m_callback = callback;
		m_cookie = cookie;

		initWidget( m_spot );

		m_widget = implInitDisplayWidget();

		m_widget.addClickHandler( this );
	}

	public void setEditable( boolean fEditable )
	{
		this.fEditable = fEditable;
	}

	public void setOnEditCallback( OnEditCallback<WIDGET, COOKIE> callback )
	{
		m_onEditCallback = callback;
	}

	public void setText( String text )
	{
		if( m_fEditing )
		{
			m_editingText = text;
		}
		else
		{
			if( text==null || text.trim().isEmpty() )
				text = "...";
			m_widget.setText( text );
			m_spot.setWidget( m_widget );
		}
	}

	public void setFontSize( double value, Unit unit )
	{
		m_fontSize = value;
		m_fontSizeUnit = unit;

		m_widget.getElement().getStyle().setFontSize( m_fontSize, m_fontSizeUnit );
		if( m_box != null )
			m_box.getElement().getStyle().setFontSize( m_fontSize, m_fontSizeUnit );
	}

	@Override
	public void onClick( ClickEvent event )
	{
		if( !fEditable )
			return;

		m_editingText = null;
		if( m_onEditCallback != null )
		{
			m_fEditing = true;
			if( !m_onEditCallback.onEdit( this, m_cookie ) )
			{
				m_fEditing = false;
				return;
			}
			m_fEditing = false;
		}

		String text = m_editingText;
		if( text == null )
			text = m_widget.getText();

		if( m_box == null )
		{
			m_box = new TextBox();
			// m_box = new GrowingTextArea();
			if( m_fontSizeUnit != null )
				m_box.getElement().getStyle().setFontSize( m_fontSize, m_fontSizeUnit );
			m_box.addKeyUpHandler( this );
			m_box.addBlurHandler( this );
		}

		m_spot.setWidget( m_box );
		// if( text != null )
		// m_box.setVisibleLength( text.length() + 10 );
		m_box.setText( text );
		m_box.setFocus( true );
		m_box.selectAll();
	}

	@Override
	public void onKeyUp( KeyUpEvent event )
	{
		if( event.getNativeKeyCode() == KeyCodes.KEY_ENTER )
		{
			if( m_image == null )
				m_image = new Image( m_loadingImage );

			m_spot.setWidget( m_image );

			m_callback.onWantChange( m_box.getText(), this, m_cookie );
		}
		else if( event.getNativeKeyCode() == KeyCodes.KEY_ESCAPE )
		{
			m_spot.setWidget( m_widget );
		}
	}

	@Override
	public void onBlur( BlurEvent event )
	{
		m_spot.setWidget( m_widget );
	}
}
