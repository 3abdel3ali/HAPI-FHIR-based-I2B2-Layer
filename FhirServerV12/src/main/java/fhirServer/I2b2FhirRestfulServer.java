package fhirServer;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.apache.log4j.Logger;

import ca.uhn.fhir.rest.server.EncodingEnum;
import ca.uhn.fhir.rest.server.RestfulServer;
import fhirResources.DiagnosticReportResourceProvider;
import fhirResources.PatientResourceProvider;
import fhirResources.MedicationOrderResourceProvider;
import fhirResources.MedicationResourceProvider;

@WebServlet("/i2b2Layer/*")
public class I2b2FhirRestfulServer extends RestfulServer {

	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(I2b2FhirRestfulServer.class);

	@Override
	protected void initialize() throws ServletException {

		// Set the resource providers used by this server 
		try {
			setResourceProviders(new PatientResourceProvider(), new MedicationOrderResourceProvider(), new MedicationResourceProvider(), new DiagnosticReportResourceProvider());
			//setResourceProviders(new MedicationOrderResourceProvider());
			//setResourceProviders(new MedicationResourceProvider());
			
			//Allows to change the default display response of the fhir server, 2 type of responses: xml (default) or json
			EncodingEnum typeFormatReponse = EncodingEnum.XML;
			I2b2FhirRestfulServer.this.setDefaultPrettyPrint(true);
			I2b2FhirRestfulServer.this.setDefaultResponseEncoding(typeFormatReponse);
			
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//logger.error("Sorry, something wrong!", e);
			logger.debug("Sorry, something wrong!", e);
			System.out.println("Sorry, something wrong!" + e.getMessage());
		}
		
		/* This just means to use Content-Types which are not technically
		 * FHIR compliant if a browser is detected (so that they display
		 * nicely for testing) */
		setUseBrowserFriendlyContentTypes(true);
		
	}
	
	
}
