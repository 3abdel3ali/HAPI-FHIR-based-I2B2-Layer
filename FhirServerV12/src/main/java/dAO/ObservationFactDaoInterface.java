/**
 * @author Abdelali Boussadi
 */
package dAO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import entity.ObservationFactDimension;
import entity.PatientDimension;

/**
 * 
 * 
 * DAO of the i2b2 Observation_Fact Dimension Table
 * 
 */
public interface ObservationFactDaoInterface extends
		GenericDao<ObservationFactDimension, Serializable> {

	// No additional business operations !
	// This architecture is based on the "Programming to interfaces" design
	// pattern

	// There will be DAO Interfaces as much as entities that will persist in a
	// data base: ConceptDimensionDAO, ObservationFactDimensionDAO,...

}
