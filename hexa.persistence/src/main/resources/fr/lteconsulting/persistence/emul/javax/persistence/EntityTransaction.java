package javax.persistence;

public interface EntityTransaction
{
	void begin();
	void commit();
	boolean getRollbackOnly();
	boolean isActive();
	void rollback();
	void setRollbackOnly();
}
