/**
 * @author Abdelali Boussadi
 */
package dAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import entity.PatientDimension;

/**
 * 
 * 
 * This class allows to complete the operations defined in the GenericDAO class
 * by implementing operations in relation with data base Session and Transaction
 * 
 * 
 */

public class HibernateDao<T, Long> implements GenericDao<T, Serializable> {

	private Session currentSession;

	private Transaction currentTransaction;

	public HibernateDao() {
	}

	public void openCurrentSession() {
		currentSession = getSessionFactory().openSession();

	}

	public void openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();

	}

	public void closeCurrentSession() {
		currentSession.close();

	}

	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}

	private static SessionFactory getSessionFactory() {

		// loads configuration and mappings
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		// builds a session factory from the service registry
		SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);

		/*
		 * Configuration configuration = new
		 * Configuration().configure();//Selon:
		 * http://www.it1me.com/it-answers?id
		 * =32197562&ttl=UnknownEntityTypeException
		 * %3A+Unable+to+locate+persister+%28Hibernate+5.0%29 // Configuration
		 * estr considérée comme depricated StandardServiceRegistryBuilder
		 * builder = new StandardServiceRegistryBuilder()
		 * .applySettings(configuration.getProperties()); SessionFactory
		 * sessionFactory = configuration.buildSessionFactory(builder.build());
		 */
		return sessionFactory;
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	@Override
	public void persist(T entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public T findById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
