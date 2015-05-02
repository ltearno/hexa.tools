package fr.lteconsulting.hexa.demo.client.persistence;

import fr.lteconsulting.hexa.persistence.client.legacy.persistence.Article;
import fr.lteconsulting.hexa.persistence.client.legacy.persistence.Category;
import fr.lteconsulting.hexa.persistence.client.legacy.persistence.PersistenceConfigurationFactory;
import fr.lteconsulting.hexa.persistence.client.legacy.persistence.WithEntityClasses;

@WithEntityClasses( classes = { Article.class, Category.class } )
public interface MyPersistenceConfigurationFactory extends PersistenceConfigurationFactory
{
}
