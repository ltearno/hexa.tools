package fr.lteconsulting.hexa.client.ui.widget;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Cursor;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.dom.client.Style.VerticalAlign;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;
import fr.lteconsulting.hexa.client.tools.JQuery;

public class Slider extends Widget
{
	public interface Callback
	{
		void onSliderValueChange( int value, Object cookie );
	}

	Callback callback;
	Object cookie;

	public Slider( int min, int max, int step, String[] captions, Callback callback, Object cookie )
	{
		JQuery.ensureScriptsLoaded();

		this.callback = callback;
		this.cookie = cookie;

		int maxLength = 0;
		for( String s : captions )
			maxLength = Math.max( s.length(), maxLength );

		maxLength = (maxLength + 1) / 2;

		setElement( Document.get().createDivElement() );
		getElement().getStyle().setMargin( 10, Unit.PX );
		getElement().getStyle().setMarginRight( maxLength, Unit.EM );
		build( getElement(), min, max, step );

		if( captions == null || captions.length < max - min )
			return;
		for( int i = min; i <= max; i++ )
		{
			Element caption = DOM.createDiv();
			caption.getStyle().setPosition( Position.ABSOLUTE );
			caption.getStyle().setLeft( 30, Unit.PX );
			// caption.getStyle().setWidth( width - 30, Unit.PX );
			caption.getStyle().setWidth( maxLength, Unit.EM );
			caption.getStyle().setBottom( (double) ((i - min) * 100) / (double) (max - min), Unit.PCT );
			caption.getStyle().setVerticalAlign( VerticalAlign.MIDDLE );
			caption.getStyle().setCursor( Cursor.DEFAULT );
			caption.setInnerText( captions[i - min] );

			double h = 1.2;
			caption.getStyle().setHeight( h, Unit.EM );
			caption.getStyle().setMarginBottom( -h / 2, Unit.EM );

			getElement().appendChild( caption );
		}
	}

	public void setAnimate( boolean fAnimate )
	{
		setAnimateImpl( getElement(), fAnimate );
	}

	public void setValue( int value )
	{
		Callback cb = callback;
		callback = null;
		setValueImpl( getElement(), value );
		callback = cb;
	}

	public int getValue()
	{
		return getValueImpl( getElement() );
	}

	private void onSliderChangeImpl( int value )
	{
		if( callback == null )
			return;

		callback.onSliderValueChange( value, cookie );
	}

	private final native void build( Element e, int minValue, int maxValue, int stepValue )
	/*-{
		var me = this;
		var onSliderChange = function( event, ui )
		{
			me.@fr.lteconsulting.hexa.client.ui.widget.Slider::onSliderChangeImpl(I)( ui.value );
		};
		$wnd.$( e ).slider( {min:minValue, max:maxValue, step:stepValue, orientation:'vertical', change: onSliderChange } );
	}-*/;

	private final native void setValueImpl( Element e, int value )
	/*-{
		$wnd.$( e ).slider( "value", value );
	}-*/;

	private final native int getValueImpl( Element e )
	/*-{
		return $wnd.$( e ).slider( "value" );
	}-*/;

	private final native void setAnimateImpl( Element e, boolean fAnimate )
	/*-{
		$wnd.$( e ).slider( "option", { animate:fAnimate } );
	}-*/;
}
