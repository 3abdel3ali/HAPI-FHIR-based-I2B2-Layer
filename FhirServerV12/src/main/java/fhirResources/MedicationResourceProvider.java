package fhirResources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.PatientDimensionService;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.resource.Medication;
import ca.uhn.fhir.model.dstu2.resource.MedicationOrder;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.resource.ValueSet;
import ca.uhn.fhir.model.dstu2.valueset.AdministrativeGenderEnum;
import ca.uhn.fhir.model.dstu2.valueset.IdentifierUseEnum;
//import ca.uhn.fhir.model.primitive.BoundCodeableConceptDt;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.OptionalParam;
import ca.uhn.fhir.rest.annotation.Read;
import ca.uhn.fhir.rest.annotation.RequiredParam;
import ca.uhn.fhir.rest.annotation.ResourceParam;
import ca.uhn.fhir.rest.annotation.Search;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.param.ReferenceParam;
import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.server.IResourceProvider;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import entity.PatientDimension;

/**
 * This is the most basic resource provider, showing only a single
 * read method on a resource provider
 */
public class MedicationResourceProvider implements IResourceProvider {

	private Map<Long, Medication> myMeds = new HashMap<Long, Medication>();
	private Long myNextId = 1L;
	
	/** Constructor 
	 * @throws ParseException */
	
	
	public MedicationResourceProvider() throws ParseException {
		//Abdelali
		//in my architecture view of the rest solution, the constructor should be empty
		//all the operations should be done in the methods
		
	}
	
	
	
	
	
	/** All Resource Providers must implement this method */
	@Override 
	public Class<? extends IResource> getResourceType() {
		return Medication.class;
	}

	/** Simple implementation of the "read" method */
	@Read()
	public Medication read(@IdParam IdDt theId) {
		Medication retVal = myMeds.get(theId.getIdPartAsLong());
		if (retVal == null) {
			throw new ResourceNotFoundException(theId);
		}
		return retVal;
	}

	/** Create/save a new resource */
	@Create
	public MethodOutcome create(@ResourceParam Medication theMeds) {
		// Give the resource the next sequential ID
		long id = myNextId++;
		theMeds.setId(new IdDt(id));
		
		// Store the resource in memory
		myMeds.put(id, theMeds);
		
		// Inform the server of the ID for the newly stored resource
		return new MethodOutcome(theMeds.getId());	
	}
	
		
	/** Simple "search" implementation **/
	@Search
	public List<Medication> search() {
		List<Medication> retVal = new ArrayList<Medication>();
		retVal.addAll(myMeds.values());
		return retVal;
	}
	
	
	
	
	
	
	
	
	
	

}
