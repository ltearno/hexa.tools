package fr.lteconsulting.hexa.persistence.client.persistenceApiTest;

import fr.lteconsulting.hexa.persistence.client.legacy.persistence.PersistenceConfigurationFactory;
import fr.lteconsulting.hexa.persistence.client.legacy.persistence.WithEntityClasses;

@WithEntityClasses( classes = { Article.class, Category.class } )
public interface MyPersistenceConfigurationFactory extends PersistenceConfigurationFactory
{
}
