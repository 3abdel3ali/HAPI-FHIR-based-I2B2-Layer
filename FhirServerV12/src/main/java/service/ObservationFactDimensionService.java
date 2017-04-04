/**
 * 
 */
package service;

import java.util.Date;
import java.util.List;

import dAO.HibernateDao;
import dAO.ObservationFactDaoInterface;
import dAO.PatientDimensionDaoInterface;
import entity.ObservationFactDimension;
import entity.PatientDimension;

/**
 * @author Abdelali Boussadi
 * 
 * //This class is the concrete implementation of the different operations of the Generic DAO and others
	//This class uses some of the methods defined in the HibernateDAO class to establish connection to the data base...
	
 *
 */




public class ObservationFactDimensionService extends HibernateDao<ObservationFactDimension, Long> implements ObservationFactDaoInterface{
	
	
	
	
		    public void persist(ObservationFactDimension observation) {
		    	openCurrentSessionwithTransaction();
		    	getCurrentSession().save(observation);
		    	closeCurrentSessionwithTransaction();
		    }
		 
		    public void update(ObservationFactDimension observation) {
		    	openCurrentSessionwithTransaction();
		    	getCurrentSession().update(observation);
		    	closeCurrentSessionwithTransaction();
		    }
		 
		    		    

		    public List<ObservationFactDimension> findObservationsByMedicationLocalCode(String CONCEPT_CD_MED) {
		    	openCurrentSession();
		    	//List<ObservationFactDimension> observations = (List<ObservationFactDimension>) getCurrentSession().createQuery("from ObservationFactDimension o where o.CONCEPT_CD = '" + CONCEPT_CD_MED + "'") .list();
		    	List<ObservationFactDimension> observations = (List<ObservationFactDimension>) getCurrentSession().createQuery("from ObservationFactDimension o where o.concept.CONCEPT_CD = '" + CONCEPT_CD_MED + "'") .list();
		    	
		    	closeCurrentSession();
		    	return observations;
		    }
		    
		    
		    public List<ObservationFactDimension> findObservationsByMedicationCipCode(String CONCEPT_CD_MED) {
		    	openCurrentSession();
		    	String query = "from ObservationFactDimension o where o.concept.NAME_CHAR LIKE :CONCEPT_CD_MED  and o.SCHEME_KEY = 'MED:'";
		    	//String query = "from ObservationFactDimension o where o.concept.NAME_CHAR LIKE :CONCEPT_CD_MED  and o.SCHEME_KEY = 'MED:'";
		    	
		    	
		    	List<ObservationFactDimension> observations = (List<ObservationFactDimension>) getCurrentSession().createQuery(query)
		    			.setString("CONCEPT_CD_MED", "%[" + CONCEPT_CD_MED + "]%")
		    			.list();
		    	
		    	//This to test passing parameter 
		    	/*String query = "from ObservationFactDimension o  where str(o.TVAL_CHAR) like :CONCEPT_CD_MED and o.SCHEME_KEY = 'MED:'";
		    	List<ObservationFactDimension> observations = (List<ObservationFactDimension>) getCurrentSession().createQuery(query)
		    			.setString("CONCEPT_CD_MED", CONCEPT_CD_MED + "%")
		    			.list();*/
		    	
		    	
		    	
		    	//This to test a join request between ObservationFactDimension and ConceptDimension classes
		    	/*String query = "from ObservationFactDimension o where o.concept.NAME_CHAR = '" + CONCEPT_CD_MED + "' and o.SCHEME_KEY = 'MED:'";
		    	List<ObservationFactDimension> observations = (List<ObservationFactDimension>) getCurrentSession().createQuery(query).list();*/
		    	
		    //System.out.println(observations.get(1).getLOCATION_CD());
		    	
		    	closeCurrentSession();
		    	return observations;
		    }
		    
		    
		    
		    public List<ObservationFactDimension> findObservationsByMedicationCipCodeV2(String CONCEPT_CD_MED) {
		    	openCurrentSession();
		    	String query = "from ObservationFactDimension o where "
		    			//+ "o.encounter_num = o.visit.encounter_num       and   "
		    			+ "o.visit.id.PATIENT_NUM = o.patient.PATIENT_NUM   and   "
		    			+ "o.visit.SOURCESYSTEM_CD = 'DXCARE'   and   "
		    			+ "( o.visit.START_DATE - o.patient.BIRTH_DATE) >= 23725   and   "
		    			+ "o.TVAL_CHAR NOT IN ('DAS', 'DR', 'DP')                  and   "
		    			+ "o.SCHEME_KEY = 'MED:'                                   and   "
		    			+ "o.id.CONCEPT_CD <> 'MED:0'                                 and   "
		    			
		    			+ "o.concept.NAME_CHAR LIKE :CONCEPT_CD_MED ";
		    	List<ObservationFactDimension> observations = (List<ObservationFactDimension>) getCurrentSession().createQuery(query)
		    			.setString("CONCEPT_CD_MED", "%[" + CONCEPT_CD_MED + "]%")
		    			.list();
		    	
		    	
		    	
		    	closeCurrentSession();
		    	return observations;
		    }
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    public List<ObservationFactDimension> findObservationsByMedicationCipCodeNsourceSystemCD(String CONCEPT_CD_MED, String SOURCESYSTEM_CD) {
		    	openCurrentSession();
		    	String query = "from ObservationFactDimension o where "
		    			//+ "o.encounter_num = o.visit.encounter_num       and   "
		    			+ "o.visit.id.PATIENT_NUM = o.patient.PATIENT_NUM   and   "
		    			+ "o.visit.SOURCESYSTEM_CD = '" + SOURCESYSTEM_CD + "'     and   "
		    			//+ "( o.visit.START_DATE - o.patient.BIRTH_DATE) >= 23725   and   "
		    			+ "o.TVAL_CHAR NOT IN ('DAS', 'DR', 'DP')                  and   "
		    			+ "o.id.CONCEPT_CD <> 'MED:0'                                 and   "
		    			+ "o.SCHEME_KEY = 'MED:'                                      and   "
		    			
		    			+ "o.concept.NAME_CHAR LIKE :CONCEPT_CD_MED ";
		    	List<ObservationFactDimension> observations = (List<ObservationFactDimension>) getCurrentSession().createQuery(query)
		    			.setString("CONCEPT_CD_MED", "%[" + CONCEPT_CD_MED + "]%")
		    			.list();
		    	
		    	
		    	
		    	closeCurrentSession();
		    	return observations;
		    }
		    
		    
		    
		    
		    
		    public List<ObservationFactDimension> findObservationsByDiseaseIcd10Code(String TVAL_CHAR_DIAG) {
		    	openCurrentSession();
		    	String query = "from ObservationFactDimension o where "
		    			//+ "o.encounter_num = o.visit.encounter_num        and   "
		    			+ "o.visit.id.PATIENT_NUM = o.patient.PATIENT_NUM   and   "
		    			+ "o.visit.SOURCESYSTEM_CD = 'DXCARE'               and   "
		    			
		    			+ "( o.visit.START_DATE - o.patient.BIRTH_DATE) >= 23725   and   "
		    			
		    			
		    			+ "o.SCHEME_KEY = 'CIM10:'                                      and   "
		    			
		    			+ "o.TVAL_CHAR = '" + TVAL_CHAR_DIAG + "'" ;
		    	List<ObservationFactDimension> observations = (List<ObservationFactDimension>) getCurrentSession().createQuery(query)
		    			//.setString("TVAL_CHAR_DIAG", TVAL_CHAR_DIAG)
		    			.list();
		    	
		    	
		    	
		    	closeCurrentSession();
		    	return observations;
		    }
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
	

}
