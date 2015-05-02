package fr.lteconsulting.hexa.demo.client.persistence;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import fr.lteconsulting.hexa.client.tools.Action;
import fr.lteconsulting.hexa.persistence.client.legacy.persistence.Article;
import fr.lteconsulting.hexa.persistence.client.legacy.persistence.Category;
import fr.lteconsulting.hexa.persistence.client.legacy.persistence.PersistenceConfigurationFactory;

public class EMTest
{
	private EntityManager em;

	private void withinTransaction( Action a )
	{
		try
		{
			em.getTransaction().begin();
			a.exec();
			em.getTransaction().commit();
		}
		catch( Exception e )
		{
			em.getTransaction().rollback();

			log( "An exception occurred during a JPA tx : " + e.getMessage() );
		}
	}

	public void run()
	{
		// create persistence configuration
		PersistenceConfigurationFactory configFactory = (PersistenceConfigurationFactory) GWT.create( MyPersistenceConfigurationFactory.class );

		// initialize persistence parameters
		HashMap<String, Object> parameters = new HashMap<String, Object>();
		parameters.put( "entitiesConfiguration", configFactory.getPersistenceConfiguration() );

		// create an entitymanager factory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory( "db1", parameters );

		// create an entitymanager
		em = emf.createEntityManager();

		withinTransaction( testCreateArticles );

//		withinTransaction( test1 );
//		withinTransaction( test2 );
//		withinTransaction( test3 );
//		withinTransaction( test4 );
//		withinTransaction( test5 );
//		withinTransaction( test6 );
		withinTransaction( test7 );

		Button resetButton = new Button( "Clear Local Storage" );
		RootPanel.get().add( resetButton );
		resetButton.addClickHandler( new ClickHandler()
		{
			@Override
			public void onClick( ClickEvent event )
			{
				Storage.getLocalStorageIfSupported().clear();
			}
		} );
	}

	private final Action test7 = new Action()
	{
		@Override
		public void exec()
		{
			CriteriaBuilder cb = em.getCriteriaBuilder();

			CriteriaQuery<Article> cq = cb.createQuery( Article.class );
			Root<Article> e = cq.from(Article.class);

			cq.multiselect( e.get( "name" ), e.<Integer>get( "price" ) );
			//cq.multiselect( e.get( "name" ), cb.sum( e.<Integer>get( "price" ) ) );
			//cq.where( cb.greaterThan( e.<Integer>get( "id" ), 5 ) );
			//cq.groupBy( e.get( "name" ) );

			Query query = em.createQuery(cq);

			@SuppressWarnings( "unchecked" )
			List<Object[]> a = query.getResultList();
			log( "Query result count : " + a.size() );

			for( Object[] row : a )
				log( "name " + ((String)row[0]) + " price " + (row[1]) );
		}
	};

	private final Action test1 = new Action()
	{
		@Override
		public void exec()
		{
			CriteriaBuilder cb = em.getCriteriaBuilder();
			em.find( Article.class, 1 );
			CriteriaQuery<Article> cq = cb.createQuery( Article.class );
			Root<Article> e = cq.from(Article.class);
			cq.where( cb.equal( e.<Integer>get( "id" ), 5 ) );

			Query query = em.createQuery(cq);

			Article a = (Article) query.getSingleResult();
			log( "Query result :" );
			if( a == null )
			{
				log( "Article not found" );
			}
			else
			{
				log( "article: " + a );
				a.getCategory().getCodeEAN();
				log( "  category : " + a.getCategory() );
			}
		}
	};

	@SuppressWarnings( "unused" )
	private final Action test2 = new Action()
	{
		@Override
		public void exec()
		{
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Category> cq = cb.createQuery( Category.class );
			Root<Category> e = cq.from(Category.class);
			cq.where( cb.notEqual( e.<Integer>get( "id" ), 5 ) );

			Query queryC = em.createQuery( cq );
			List<Category> resultc = queryC.getResultList();
			log( "Query results :" );
			for( Category a : resultc )
			{
				log( "Category: " + a );
				for( Article aaa : a.getArticles() )
					log( " has " + aaa );
			}
		}
	};

	@SuppressWarnings( "unused" )
	private final Action test3 = new Action()
	{
		@Override
		public void exec()
		{
			Category c = em.find( Category.class, 1 );
			if( c == null )
				log( "NOT FOUND !" );
			else
				log( "FOUND ! " + c.toString() );

			for( int i=0; i<3; i++ )
			{
				c = new Category();
				c.setCodeEAN( "toto" );
				c.setMarque( "ma marque" );
				em.persist( c );

				log( "inserted with id " + c.getId() );
			}

			c = em.find( Category.class, 1 );
			if( c == null )
				log( "NOT FOUND !" );
			else
			{
				log( "FOUND ! " + c.toString() );
				em.remove( c );
			}
		}
	};

	@SuppressWarnings( "unused" )
	private final Action test4 = new Action()
	{
		@Override
		public void exec()
		{
			Category c = new Category();
			c.setCodeEAN( "TOTO" );
			c.setMarque( "Marque jaune" );

			Article a = new Article();
			a.setName( "un article" );
			a.setCategory( c );

			em.persist( a );
			em.persist( c );

			log( "article:" + a.getId() + " category:" + c.getId() );
		}
	};

	private final Action testCreateArticles = new Action()
	{
		@Override
		public void exec()
		{
			Category c = new Category();
			c.setCodeEAN( "TOTO" );
			c.setMarque( "Marque jaune" );

			em.persist( c );

			for( int i=0; i<10; i++ )
			{
				Article a = new Article();
				a.setName( "un article" );
				a.setCategory( c );
				a.setPrice( (int)(100 * Math.random()) );

				em.persist( a );
			}

			log( "Persisted 10 articles" );
		}
	};

	@SuppressWarnings( "unused" )
	private final Action test5 = new Action()
	{
		@Override
		public void exec()
		{
			Category c2 = em.find( Category.class, 44 );

			Article a = em.find( Article.class, 4 );
			if( a == null )
			{
				log( "not found article" );
			}
			else
			{
				log( "article 4 found : " + a );
				a.getCategory().getCodeEAN();
				log( "normally proxy is loaded : " + a );
			}

			a.setCategory( c2 );
		}
	};

	@SuppressWarnings( "unused" )
	private final Action test6 = new Action()
	{
		@Override
		public void exec()
		{
			Category c = em.find( Category.class, 16 );
			if( c != null )
			{
				log( "found category, here are the articles :" );
				List<Article> articles = c.getArticles();
				for( Article article : articles )
					log( "article : " + article );
			}
		}
	};

	private void log( String text )
	{
		RootPanel.get().add( new Label( text ) );
	}
}
