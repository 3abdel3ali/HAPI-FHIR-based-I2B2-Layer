/**
 * 
 */
package service;

import java.util.Date;
import java.util.List;

import dAO.HibernateDao;
import dAO.PatientDimensionDaoInterface;
import entity.PatientDimension;

/**
 * @author Abdelali Boussadi
 * 
 * //This class is the concrete implementation of the different operations of the Generic DAO and others
	//This class uses some of the methods defined in the HibernateDAO class to establish connection to the data base...
	
 *
 */




public class PatientDimensionService extends HibernateDao<PatientDimension, Long> implements PatientDimensionDaoInterface{
	
	
	
	
		    public void persist(PatientDimension entity) {
		    	openCurrentSessionwithTransaction();
		    	getCurrentSession().save(entity);
		    	closeCurrentSessionwithTransaction();
		    }
		 
		    public void update(PatientDimension entity) {
		    	openCurrentSessionwithTransaction();
		    	getCurrentSession().update(entity);
		    	closeCurrentSessionwithTransaction();
		    }
		 
		    public PatientDimension findById(Double id1) {
		    	openCurrentSession();
		    	PatientDimension patient = (PatientDimension) getCurrentSession().get(PatientDimension.class, id1);
		    	
		    	closeCurrentSession();
		        return patient;
		    	
		    }
		    
		    
		    public List<PatientDimension> findByBirthDate(String BIRTH_DATE) {
		    	openCurrentSession();
		    	List<PatientDimension> patients = (List<PatientDimension>) getCurrentSession().createQuery("FROM PatientDimension P WHERE P.BIRTH_DATE = to_date('" + BIRTH_DATE +"','YYYY-MM-DD')").list();
		    	closeCurrentSession();
		    	
		    	return patients;
		    }
		    
		    
		    
		    
		  
	

}
