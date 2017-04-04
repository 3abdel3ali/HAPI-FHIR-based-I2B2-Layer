/**
 * 
 */
package testDaoHibernate;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.util.List;

import javax.servlet.ServletContextEvent;

import entity.ObservationFactDimension;
import entity.PatientDimension;
import fhirServer.MyLifeCycleListener;
import service.ObservationFactDimensionService;
import service.PatientDimensionService;


/**
 * @author Abdelali Boussadi
 *
 *A class to test the Hibernate sql queries with the targetted data base
 */
public class TestDaoHibernate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		
		PatientDimensionService PatientDimensionService = new PatientDimensionService();
		ObservationFactDimensionService ObservationFactDimensionService = new ObservationFactDimensionService();
		
		
			        /*Book book1 = new Book("1", "The Brothers Karamazov", "Fyodor Dostoevsky");
			        Book book2 = new Book("2", "War and Peace", "Leo Tolstoy");
			        Book book3 = new Book("3", "Pride and Prejudice", "Jane Austen");
			        System.out.println("*** Persist - start ***");
			        bookService.persist(book1);
			        bookService.persist(book2);
			        bookService.persist(book3);
			        List<Book> books1 = bookService.findAll();
			        System.out.println("Books Persisted are :");
			        for (Book b : books1) {
			            System.out.println("-" + b.toString());
			        }
			        System.out.println("*** Persist - end ***");
			        System.out.println("*** Update - start ***");
			        book1.setTitle("The Idiot");
			        bookService.update(book1);
			        System.out.println("Book Updated is =>" +bookService.findById(book1.getId()).toString());
			        System.out.println("*** Update - end ***");
			        System.out.println("*** Find - start ***");*/
		
		
			        /*Double id1 =  (double) 2004297904;
			        
			        PatientDimension another = PatientDimensionService.findById(id1);
			        
			        System.out.println("Patient found with id " + id1 + " is =>" + another.toString());
			        System.out.println("*** Find - end ***");
			        System.out.println("*** Delete - start ***");
			        */
			        
			        
			        /*String id3 = book3.getId();
			        bookService.delete(id3);
			        System.out.println("Deleted book with id " + id3 + ".");
			        System.out.println("Now all books are " + bookService.findAll().size() + ".");
			        System.out.println("*** Delete - end ***");
			        System.out.println("*** FindAll - start ***");
			        List<Book> books2 = bookService.findAll();
			        System.out.println("Books found are :");
			        for (Book b : books2) {
			            System.out.println("-" + b.toString());
			        }
			        System.out.println("*** FindAll - end ***");
			        System.out.println("*** DeleteAll - start ***");
		        bookService.deleteAll();
			        System.out.println("Books found are now " + bookService.findAll().size());
			        System.out.println("*** DeleteAll - end ***");
			         System.exit(0);*/
			    
		
		/*System.out.println("*** FindAll - start ***");
        List<PatientDimension> patients = PatientDimensionService.findAll();
        System.out.println("Patients found are :");
        for (PatientDimension b : patients) {
            System.out.println("-" + b.getPATIENT_NUM());
        }
        System.out.println("*** FindAll - end ***");*/
		
		
		/**********test 10112016*************/
		/*System.out.println("*** FindAll - start ***");
        List<PatientDimension> patients = PatientDimensionService.findByBirthDate("1980-05-15");
        System.out.println("Patients found are :");
        for (PatientDimension b : patients) {
        	
            System.out.println(b.getPATIENT_NUM());
        }
        System.out.println("*** FindAll - end ***");*/
        /**********test 10112016*************/
        
        
        /**********test 25112016 - observation_fact*************/
		/*System.out.println("*** FindAll - start ***");
        List<ObservationFactDimension> observations = ObservationFactDimensionService.findObservationsByMedicationLocalCode("MED:17102");
        System.out.println("Patients found are :");
        for (ObservationFactDimension o : observations) {
            System.out.println(o.getPatient().getPATIENT_NUM());//using the one to many association between PatientDimension and ObservationFactDimension hibernate objects
        }
        System.out.println("*** FindAll - end ***");*/
        /**********test 25112016*************/
        
        
        
        /**************test 15022017*************************************************/
        
       /* System.out.println("*** FindAll - start ***");
        List<ObservationFactDimension> observations = ObservationFactDimensionService.findObservationsByMedicationCipCode("3400891725186");
        
        //System.out.println(observations.size());
        
      // System.out.println(observations.get(1).getLOCATION_CD());
        
       
        System.out.println("Patients found are :");
        for (ObservationFactDimension o : observations) {
           System.out.println(o.getPatient().getPATIENT_NUM());//using the one to many association between PatientDimension and ObservationFactDimension hibernate objects
        
           System.out.println(o.getId().getSTART_DATE() + "\n");
           System.out.println(o.getId().getENCOUNTER_NUM() + "\n");
           System.out.println(o.getPatient().getPATIENT_NUM() + "\n");
           System.out.println(o.getConcept().getCONCEPT_CD() + "\n");
           System.out.println(o.getConcept().getCONCEPT_PATH() + "\n");
           System.out.println(o.getTVAL_CHAR() + "\n");
           System.out.println(o.getUNITS_CD() + "\n");
           
           
           
           
           
            //System.out.println("-" + o.getConcept());//using the one to many association between PatientDimension and ObservationFactDimension hibernate objects
            
        }
        System.out.println("*** FindAll - end ***");
        */
        
		
		
		
		/*MyLifeCycleListener MyServletContextListner = new MyLifeCycleListener();
		MyServletContextListner.contextDestroyed(event);*/
		
        /**************************************************************/
        
        
        
        /***************test 20202017***********************************************************/
        
        
     /*   System.out.println("*** FindAll - start ***");
        List<ObservationFactDimension> observations = ObservationFactDimensionService.findObservationsByMedicationCipCodeNsourceSystemCD("3400891725186", "DXCARE");
        
        //System.out.println(observations.size());
        
      // System.out.println(observations.get(1).getLOCATION_CD());
        
       
        System.out.println("Patients found are :");
        for (ObservationFactDimension o : observations) {
           System.out.println(o.getPatient().getBIRTH_DATE() + "\n");//using the one to many association between PatientDimension and ObservationFactDimension hibernate objects
        
           System.out.println(o.getId().getSTART_DATE() + "\n");
           System.out.println(o.getVisit().getId().getENCOUNTER_NUM() + "\n");
           System.out.println(o.getPatient().getPATIENT_NUM() + "\n");
           System.out.println(o.getConcept().getCONCEPT_CD() + "\n");
           System.out.println(o.getConcept().getCONCEPT_PATH() + "\n");
           System.out.println(o.getTVAL_CHAR() + "\n");
           System.out.println(o.getUNITS_CD() + "\n");
           
           
           
           
           
            //System.out.println("-" + o.getConcept());//using the one to many association between PatientDimension and ObservationFactDimension hibernate objects
            
        }
        System.out.println("*** FindAll - end ***");*/
        
        
        
        /**************************************************************************/
        
        
		 /***************test 22202017***********************************************************/
        
        
        
		   System.out.println("*** FindAll - start ***");
	        List<ObservationFactDimension> observations = ObservationFactDimensionService.findObservationsByDiseaseIcd10Code("A539");
	        
	        //System.out.println(observations.size());
	        
	      // System.out.println(observations.get(1).getLOCATION_CD());
	        
	       
	        System.out.println("Patients found are :");
	        for (ObservationFactDimension o : observations) {
	           System.out.println(o.getPatient().getBIRTH_DATE() );//using the one to many association between PatientDimension and ObservationFactDimension hibernate objects
	        
	           /*System.out.println(o.getId().getSTART_DATE() + "\n");
	           System.out.println(o.getVisit().getId().getENCOUNTER_NUM() + "\n");
	           System.out.println(o.getPatient().getPATIENT_NUM() + "\n");
	           System.out.println(o.getConcept().getCONCEPT_CD() + "\n");
	           System.out.println(o.getConcept().getCONCEPT_PATH() + "\n");
	           System.out.println(o.getTVAL_CHAR() + "\n");
	           System.out.println(o.getUNITS_CD() + "\n");
	           */
	           
	           
	           
	           
	            //System.out.println("-" + o.getConcept());//using the one to many association between PatientDimension and ObservationFactDimension hibernate objects
	            
	        }
	        System.out.println("*** FindAll - end ***");
		
	        /*************** fin test 22202017***********************************************************/
	        
	}

}
