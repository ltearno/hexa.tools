package fr.lteconsulting.client;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.AnchorElement;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import fr.lteconsulting.hexa.client.css.bindings.HexaBootstrapCss;
import fr.lteconsulting.hexa.client.databinding.Binder;
import fr.lteconsulting.hexa.client.databinding.Mode;
import fr.lteconsulting.hexa.client.databinding.NotifyPropertyChangedEvent;
import fr.lteconsulting.hexa.client.databinding.NotifyPropertyChangedEvent.Handler;
import fr.lteconsulting.hexa.client.databinding.propertyadapters.ObjectPropertiesUtils;
import fr.lteconsulting.hexa.client.databinding.watchablecollection.WatchableCollection;
import fr.lteconsulting.hexa.client.databinding.watchablecollection.WatchableCollection.Change;
import fr.lteconsulting.hexa.client.tools.Action1;
import fr.lteconsulting.hexa.client.ui.widget.ListBox;

/**
 * This widget presents the different Articles and allow to select one
 * 
 * @author Arnaud Tournier
 * (c) LTE Consulting - 2015
 * http://www.lteconsulting.fr
 *
 */
public class ArticleList extends Composite
{
	@UiField
	ListBox<Article> listBox;
	
	@UiField
	DivElement listDiv;
	
	@UiField
	Button add;
	
	@UiField
	Button delete;

	Element currentActiveElement = null;
	
	private final WatchableCollection<Article> articles = Repository.getArticles();

	private static ArticleListUiBinder uiBinder = GWT.create( ArticleListUiBinder.class );

	interface ArticleListUiBinder extends UiBinder<Widget, ArticleList>
	{
	}

	public ArticleList()
	{
		initWidget( uiBinder.createAndBindUi( this ) );
		
		initListBox();

		initArticleList();

		registerSelectArticleClickAction();

		registerSelectedArticleProperty();
		
		initAddHandler();
		
		initDeleteHandler();
		
		listBox.getElement().focus();
	}

	private void initListBox()
	{
		/**
		 * Use the {@link WatchableCollection} subscription system
		 * to get changes happening to the list
		 */
		articles.addCallbackAndSendAll( new Action1<List<Change>>()
		{
			@Override
			public void exec( List<Change> param )
			{
				for( Change c : param )
				{
					// Each change has a type and conveys the item that was concerned
					switch( c.getType() )
					{
						case ADD:
							listBox.addItem( c.<Article> getItem().getName(), c.<Article> getItem() );
							break;
						case REMOVE:
							listBox.removeItem( c.<Article> getItem() );
							break;
					}
				}
			}
		} );
		
		/**
		 * Bind the selected article to the list box.
		 * 
		 * This is a two way data binding !
		 */
		Binder.Bind( articles, "selected" ).To( listBox );
	}

	private void initArticleList()
	{
		/**
		 * Use the {@link WatchableCollection} subscription system
		 * to get changes happening to the list
		 */
		articles.addCallbackAndSendAll( new Action1<List<Change>>()
		{
			@Override
			public void exec( List<Change> changes )
			{
				for( Change c : changes )
				{
					// Each change has a type and conveys the item that was concerned
					switch( c.getType() )
					{
						case ADD:
							AnchorElement anchor = Document.get().createAnchorElement();
							anchor.setHref( "#" );
							anchor.addClassName( HexaBootstrapCss.CSS.listGroupItem() );
							Binder.Bind( c.getItem(), "name" ).Mode( Mode.OneWay ).To( anchor, "innerText" );
							listDiv.appendChild( anchor );
	
							break;
	
						case REMOVE:
							listDiv.getChild( c.getIndex() ).removeFromParent();
	
							break;
					}
				}
			}
		} );
	}

	private void registerSelectArticleClickAction()
	{
		addDomHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				int index = DOM.getChildIndex( listDiv, event.getNativeEvent().getEventTarget().<Element> cast() );
				if( index < 0 )
					return;
	
				/**
				 * Set the "selected" property of the articles list (and notifies others).
				 * 
				 * Since the articles's class ({@link WatchableCollection} does not have
				 * a "selected" getter nor field, the binding mecanism will use the dynamic
				 * property system)
				 */
				ObjectPropertiesUtils.SetProperty( articles, "selected", articles.get( index ) );
	
				event.stopPropagation();
				event.preventDefault();
			}
		}, ClickEvent.getType() );
	}

	private void registerSelectedArticleProperty()
	{
		/**
		 * Register to the "selected" property of the articles list to change the active
		 * state of the corresponding item.
		 */
		NotifyPropertyChangedEvent.registerPropertyChangedEvent( articles, "selected", new Handler()
		{
			@Override
			public void onNotifyPropertChanged( NotifyPropertyChangedEvent event )
			{
				// deactivate previously selected item
				if( currentActiveElement != null )
					currentActiveElement.removeClassName( HexaBootstrapCss.CSS.active() );
	
				/**
				 * Get the "selected" property of the articles list
				 * 
				 * Since the articles's class ({@link WatchableCollection} does not have
				 * a "selected" getter nor field, the binding mecanism will use the dynamic
				 * property system)
				 */
				Article article = ObjectPropertiesUtils.GetProperty( articles, "selected" );
				if( article != null )
				{
					int index = articles.indexOf( article );
					if( index >= 0 )
						currentActiveElement = listDiv.getChild( index ).<Element> cast();
				}
	
				// activate selected item
				if( currentActiveElement != null )
					currentActiveElement.addClassName( HexaBootstrapCss.CSS.active() );
			}
		} );
	}

	private void initAddHandler()
	{
		add.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				/**
				 * Add a new article...
				 */
				Article newArticle = Repository.createRandomArticle();
				articles.add( newArticle );
				
				/**
				 * And select it with the "selected" dynamic property of the articles list
				 */
				ObjectPropertiesUtils.SetProperty( articles, "selected", newArticle );
			}
		} );
	}

	private void initDeleteHandler()
	{
		delete.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				/**
				 * Retreive the selected article through the "selected" dynamic property of the articles list
				 */
				Article toRemove = ObjectPropertiesUtils.GetProperty( articles, "selected" );
				
				if(toRemove != null )
				{
					/**
					 * Remove the article from the list.
					 * 
					 * Since the list is a {@link WatchableCollection}, all watchers will be notified
					 */
					articles.remove( toRemove );
					
					/**
					 * Selects an article
					 */
					ObjectPropertiesUtils.SetProperty( articles, "selected", articles.isEmpty() ? null : articles.get(articles.size()-1) );
				}
			}
		} );
	}
}
