package javax.persistence;

import java.util.Map;

import javax.persistence.Cache;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.metamodel.Metamodel;

public interface EntityManagerFactory
{
	EntityManager createEntityManager();
	void close();
	EntityManager createEntityManager( @SuppressWarnings( "rawtypes" ) Map arg0 );
	Cache getCache();
	CriteriaBuilder getCriteriaBuilder();
	Metamodel getMetamodel();
	PersistenceUnitUtil getPersistenceUnitUtil();
	Map<String, Object> getProperties();
	boolean isOpen();
}
