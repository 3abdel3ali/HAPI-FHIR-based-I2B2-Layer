package fhirResources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import service.ObservationFactDimensionService;
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
import ca.uhn.fhir.model.primitive.BoundCodeDt;
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
import entity.ObservationFactDimension;
import entity.PatientDimension;

/**
 * This is the most basic resource provider, showing only a single
 * read method on a resource provider
 */
public class PatientResourceProvider implements IResourceProvider {

	private Map<Long, Patient> myPatients = new HashMap<Long, Patient>();
	private Long myNextId = 1L;
	
	/** Constructor 
	 * @throws ParseException */
	
	
	public PatientResourceProvider() throws ParseException {
		//Abdelali
		//in my architecture view of the rest solution, the constructor should be empty
		//all the operations should be done in the methods
		
	}
	
	
	
	
	
	/** All Resource Providers must implement this method */
	@Override 
	public Class<? extends IResource> getResourceType() {
		return Patient.class;
	}

	/** Simple implementation of the "read" method */
	@Read()
	public Patient read(@IdParam IdDt theId) {
		Patient retVal = myPatients.get(theId.getIdPartAsLong());
		if (retVal == null) {
			throw new ResourceNotFoundException(theId);
		}
		return retVal;
	}

	/** Create/save a new resource */
	@Create
	public MethodOutcome create(@ResourceParam Patient thePatient) {
		// Give the resource the next sequential ID
		long id = myNextId++;
		thePatient.setId(new IdDt(id));
		
		// Store the resource in memory
		myPatients.put(id, thePatient);
		
		// Inform the server of the ID for the newly stored resource
		return new MethodOutcome(thePatient.getId());	
	}
	
	/** Simple "search" implementation **/
	@Search
	public List<Patient> search() {
		List<Patient> retVal = new ArrayList<Patient>();
		retVal.addAll(myPatients.values());
		return retVal;
	}
	
	/** A search with a parameter */
	@Search
	public List<Patient> search(@RequiredParam(name="birthdate") StringParam theParam) {
		   
		List<Patient> retVal = new ArrayList<Patient>(); //a list of "fhir patients" as a result of the @Search fhir request
		
		
		
		
		PatientDimensionService PatientDimensionService = new PatientDimensionService(); //a hibernate dao based class 
		List<PatientDimension> patients = PatientDimensionService.findByBirthDate(theParam.getValue().toString()); //using the dao layer to fire the good sql request ... (here an sql request allowing to 
		//retreive "i2b2 patients" regarding a birth date)
		
		for (PatientDimension b : patients) {//...and then allowing the mapping between the results of the "i2b2 patient" and the "fhir Patients"
			Long id = myNextId++; //mondatory for each fhir resource defined
			Patient pat1 = new Patient(); // defining a "fhir patient" for each "i2b2 patient"
			
			
			
			//******setting up the mapping*******//*
			pat1.setId(new IdDt(id));
			pat1.addIdentifier().setUse(IdentifierUseEnum.USUAL).setSystem("http://localidentifier.hegp.fr").setValue(b.getPATIENT_NUM().toString());
			
			
			pat1.setBirthDateWithDayPrecision(b.getBIRTH_DATE());
			
			
			//********************************Use case of a mapping with valueset and "CodeableConcept" objects !**********************************************************************************//*
			//when a valueset is already defined to code some values, it is recommended to use it combined with
			//the local code values
			//here is the example of the gender value which is coded by the "administrativeGenderCodesEnum" valueset in fhir and with a local HEGP i2b2 code values
			if(b.getSEX_CD().contains(":M")){
				BoundCodeDt<AdministrativeGenderEnum> genderSystem = pat1.getGenderElement();
				genderSystem.setValue(AdministrativeGenderEnum.MALE.getCode());
				
				
				
				
			}
			
			if(b.getSEX_CD().contains(":F")){
				
				BoundCodeDt<AdministrativeGenderEnum> genderSystem = pat1.getGenderElement();
				genderSystem.setValue(AdministrativeGenderEnum.FEMALE.getCode());
			}
			//****************************use case of a mapping with valueset and "CodeableConcept" objects !**************************************************************************************//*
			
			//******setting up the mapping*******//*
			
			retVal.add(pat1); //populating the list of "fhir patients" as the results of the fhir server displayed as an xml or a json file
		}
		


		return retVal;		
	}
	
	
	
	
	
	
	
	/*********************Search with resource references***************************************/
	@Search
	public List<Patient> searchMedicationOrdersWithLocalMedicationsCodes(@RequiredParam(name=MedicationOrder.SP_MEDICATION, chainWhitelist={Medication.SP_CODE}) ReferenceParam theMedicationCode) {
		
		List<Patient> retVal = new ArrayList<Patient>(); //a list of "fhir patients" as a result of the @Search fhir request
		String medicationCode = theMedicationCode.getValueAsString();
	

		
		ObservationFactDimensionService observationFactService = new ObservationFactDimensionService(); //a hibernate dao based class 
		List<ObservationFactDimension> observations = observationFactService.findObservationsByMedicationLocalCode(medicationCode); //using the dao layer to fire the good sql request ... (here an sql request allowing to 
		//retreive "i2b2 patients" regarding an observation on medication prescription)
		
		
		for (ObservationFactDimension o : observations) {//...and then allowing the mapping between the results of the "i2b2 patient" and the "fhir Patients"
			
			Long id = myNextId++; //mondatory for each fhir resource defined
			Patient pat1 = new Patient(); // defining a "fhir patient" for each "i2b2 patient"
			
			
			
			//******setting up the mapping*******//*
			pat1.setId(new IdDt(id));
			pat1.addIdentifier().setUse(IdentifierUseEnum.USUAL).setSystem("http://localidentifier.hegp.fr").setValue(o.getPatient().getPATIENT_NUM().toString());
			
			
			pat1.setBirthDateWithDayPrecision(o.getPatient().getBIRTH_DATE());
			if(o.getPatient().getSEX_CD().contains(":M")){
				
				BoundCodeDt<AdministrativeGenderEnum> genderSystem = pat1.getGenderElement();
				genderSystem.setValue(AdministrativeGenderEnum.MALE.getCode());
				
				
				
			}
			
			if(o.getPatient().getSEX_CD().contains(":F")){
				
				BoundCodeDt<AdministrativeGenderEnum> genderSystem = pat1.getGenderElement();
				genderSystem.setValue(AdministrativeGenderEnum.FEMALE.getCode());
			}
			
			
			retVal.add(pat1); //populating the list of "fhir patients" as the results of the fhir server displayed as an xml or a json file
			
			
		}
		
		return retVal;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
