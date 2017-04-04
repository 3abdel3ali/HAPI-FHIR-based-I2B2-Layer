/**
 * 
 */
package fhirServer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dAO.HibernateDao;

/**
 * @author 4021753
 *
 */

//Needs to be defined in the web.xml file

@WebListener
public class MyLifeCycleListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent event) {
        //TODO ON START
		
		System.out.println("Hello world :) :) :) :) :) :) :) I'm a new born fhir server servlet !!!!!!!!!!!!!!!!!!!!!!!!");
    }
	@Override
    public void contextDestroyed(ServletContextEvent event) {
        //TODO ON DESTROY
		
		//Need to check the input output tomcat log located here C:\dev\apache-tomcat-7.0.67-windows-x64\apache-tomcat-7.0.67\logs
		//to confirm that this run 		
		
		System.out.println("Bye bye world :( :( :( :( :( :( :( I'm an old fhir server servlet and getting to quit this world !!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("I'm getting to close all DB connexions ...");
		HibernateDao<MyLifeCycleListener, Long> dbManager = new HibernateDao<MyLifeCycleListener, Long>();
		int dbCpt = 0;
		while(dbManager.getCurrentSession() != null){
			
			dbManager.getCurrentSession().close();
		    dbCpt ++;
		    System.out.println("DB connexions number" + dbCpt + "  closed !!!!!!!!!!!!!!!!!!");
		}
		
		
    }

	
    
}
