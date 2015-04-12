package fr.lteconsulting.client;

import java.util.List;

import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.css.bindings.HexaBootstrapCss;
import fr.lteconsulting.hexa.client.databinding.Binder;
import fr.lteconsulting.hexa.client.databinding.Mode;
import fr.lteconsulting.hexa.client.databinding.NotifyPropertyChangedEvent;
import fr.lteconsulting.hexa.client.databinding.NotifyPropertyChangedEvent.Handler;
import fr.lteconsulting.hexa.client.databinding.tools.Property;

public class ArticleList extends Widget
{
	List<Article> articles;

	Article old = null;
	
	Property<Article> selectedArticle = new Property<>( this, "selectedArticle", null );

	public ArticleList( final List<Article> articles )
	{
		this.articles = articles;

		DivElement div = Document.get().createDivElement();
		div.addClassName( HexaBootstrapCss.CSS.listGroup() );
		setElement( div );

		for( final Article article : articles )
		{
			AnchorElement anchor = Document.get().createAnchorElement();
			anchor.setHref( "#" );
			anchor.addClassName( HexaBootstrapCss.CSS.listGroupItem() );
			Binder.Bind( article, "name" ).Mode( Mode.OneWay ).To( anchor, "innerText" );
			div.appendChild( anchor );
		}
		
		addDomHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				int index = DOM.getChildIndex( getElement(), event.getNativeEvent().getEventTarget().<Element>cast() );
				if( index < 0 )
					return;
				
				selectedArticle.setValue( ArticleList.this.articles.get( index ) );
				
				event.stopPropagation();
				event.preventDefault();
			}
		}, ClickEvent.getType() );

		NotifyPropertyChangedEvent.registerPropertyChangedEvent( this, "selectedArticle", new Handler()
		{	
			@Override
			public void onNotifyPropertChanged( NotifyPropertyChangedEvent event )
			{
				if( old != null )
					getElement().getChild( articles.indexOf( old ) ).<Element>cast().removeClassName( HexaBootstrapCss.CSS.active() );

				old = selectedArticle.getValue();

				if( old != null )
					getElement().getChild( articles.indexOf( old ) ).<Element>cast().addClassName( HexaBootstrapCss.CSS.active() );
			}
		} );
	}
}
