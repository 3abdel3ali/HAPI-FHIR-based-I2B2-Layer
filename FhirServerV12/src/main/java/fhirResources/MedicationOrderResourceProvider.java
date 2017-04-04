/**
 * @author Abdelali Boussadi
 * 
 * FHIR MedcicationOrder provider class: this class defines methods where a service class is called
 * and the mapping is defined
 */

package fhirResources;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import service.ObservationFactDimensionService;
import service.PatientDimensionService;
import ca.uhn.fhir.model.api.IDatatype;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.model.dstu2.composite.BoundCodeableConceptDt;
import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.composite.ContainedDt;
import ca.uhn.fhir.model.dstu2.composite.NarrativeDt;
import ca.uhn.fhir.model.dstu2.composite.PeriodDt;
import ca.uhn.fhir.model.dstu2.composite.QuantityDt;
import ca.uhn.fhir.model.dstu2.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.dstu2.composite.SimpleQuantityDt;
import ca.uhn.fhir.model.dstu2.composite.TimingDt;
import ca.uhn.fhir.model.dstu2.composite.TimingDt.Repeat;
import ca.uhn.fhir.model.dstu2.resource.Encounter;
import ca.uhn.fhir.model.dstu2.resource.Medication;
import ca.uhn.fhir.model.dstu2.resource.MedicationOrder;
import ca.uhn.fhir.model.dstu2.resource.MedicationOrder.DispenseRequest;
import ca.uhn.fhir.model.dstu2.resource.MedicationOrder.DosageInstruction;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.resource.ValueSet;
import ca.uhn.fhir.model.dstu2.valueset.AdministrativeGenderEnum;
import ca.uhn.fhir.model.dstu2.valueset.IdentifierUseEnum;
import ca.uhn.fhir.model.dstu2.valueset.TimingAbbreviationEnum;
import ca.uhn.fhir.model.dstu2.valueset.UnitsOfTimeEnum;
import ca.uhn.fhir.model.primitive.BoundCodeDt;
//import ca.uhn.fhir.model.primitive.BoundCodeableConceptDt;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.model.primitive.DecimalDt;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.parser.DataFormatException;
import ca.uhn.fhir.rest.annotation.Create;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.IncludeParam;
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


public class MedicationOrderResourceProvider implements IResourceProvider {

	private Map<Long, MedicationOrder> myPrescriptions = new HashMap<Long, MedicationOrder>();
	private Long myNextId = 1L;
	
	
	
	/** Constructor 
	 * @throws ParseException */
	
	
	public MedicationOrderResourceProvider() throws ParseException {
		//Abdelali
		//the constructor should be empty
		//all the operations should be done in the methods
		
	}
	
	
	
	
	
	/** All Resource Providers must implement this method */
	@Override 
	public Class<? extends IResource> getResourceType() {
		return MedicationOrder.class;
	}

	/** Simple implementation of the "read" method */
	@Read()
	public MedicationOrder read(@IdParam IdDt theId) {
		MedicationOrder retVal = myPrescriptions.get(theId.getIdPartAsLong());
		if (retVal == null) {
			throw new ResourceNotFoundException(theId);
		}
		return retVal;
	}

	/** Create/save a new resource */
	@Create
	public MethodOutcome create(@ResourceParam MedicationOrder theMedicationPrescription) {
		// Give the resource the next sequential ID
		long id = myNextId++;
		theMedicationPrescription.setId(new IdDt(id));
		
		// Store the resource in memory
		myPrescriptions.put(id, theMedicationPrescription);
		
		// Inform the server of the ID for the newly stored resource
		return new MethodOutcome(theMedicationPrescription.getId());	
	}
	
	/** Simple "search" implementation **/
	@Search
	public List<MedicationOrder> search() {
		List<MedicationOrder> retVal = new ArrayList<MedicationOrder>();
		
		MedicationOrder medorder = new MedicationOrder();
		//System.out.println(medorder.STATUS.getParamName());
		
		retVal.addAll(myPrescriptions.values());
		return retVal;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
	
	
	
	*//** A search with a parameter *//*
	@Search
	public List<MedicationPrescription> searchMedicationOrdersWithLocalMedicationsCodes(
			@RequiredParam(name=MedicationPrescription.SP_MEDICATION, chainWhitelist={Medication.SP_CODE}) ReferenceParam theMedicationCode,
			@IncludeParam(allow={"MedicationPrescription:patient"}) String theInclude){
		
		List<MedicationPrescription> retVal = new ArrayList<MedicationPrescription>(); //a list of "fhir MedicationPrescription" as a result of the @Search fhir request
		String medicationCode = theMedicationCode.getValueAsString();
	

		ObservationFactDimensionService ObservationFactDimensionService = new ObservationFactDimensionService();
		
        List<ObservationFactDimension> i2b2observations = ObservationFactDimensionService.findObservationsByMedicationLocalCode(medicationCode);//This should be used for searching medication concept based on a local code, 
		//concept_cd = MED:27312

		
		
for (ObservationFactDimension o : i2b2observations) {//...and then allowing the mapping between the results of the "i2b2 patient" and the "fhir Patients"
			
	        Long id = myNextId++; //mondatory for each fhir resource defined
	        MedicationPrescription medOrder = new MedicationPrescription(); 			
	        medOrder.setId(new IdDt(id));
			medOrder.setDateWrittenWithSecondsPrecision(o.getId().getSTART_DATE());//mapping example for the father resource
			
			//System.out.println(theInclude);
			
			if ("MedicationPrescription:patient".equals(theInclude)) {//mapping example for the children resource
				
				// The resource reference should contain the ID of the patient
			    IdDt subjectId = medOrder.getPatient().getReference();
				Patient pat1 = new Patient(); // defining a "fhir patient" for each "i2b2 patient"
				pat1.setId(subjectId);
				
				pat1.addIdentifier().setUse(IdentifierUseEnum.USUAL).setSystem("http://localidentifier.hegp.fr").setValue(o.getPatient().getPATIENT_NUM().toString());
				pat1.setBirthDateWithSecondsPrecision(o.getPatient().getBIRTH_DATE());
				if(o.getPatient().getSEX_CD().contains(":M")){
					CodingDt genderSystem = pat1.getGender().addCoding();
					genderSystem.setSystem("http://hl7.org/fhir/administrative-gender"); 				
					genderSystem.setCode(AdministrativeGenderCodesEnum.M.getCode());
					genderSystem.setDisplay("Male");
					//to know how to populate values of an already valueset, we need to navigate here: http://hapifhir.io/apidocs-dstu/ca/uhn/fhir/model/dstu/valueset/package-tree.html
					
					//here is the local codification system value
					CodingDt genderLocal = pat1.getGender().addCoding();
					genderLocal.setSystem("http://localidentifier.hegp.fr");
					genderLocal.setCode(o.getPatient().getSEX_CD());
					
					
					
				}
				
				if(o.getPatient().getSEX_CD().contains(":F")){
					
					CodingDt genderSystem = pat1.getGender().addCoding();
					genderSystem.setSystem("http://hl7.org/fhir/administrative-gender");
					genderSystem.setCode(AdministrativeGenderCodesEnum.F.getCode());
					genderSystem.setDisplay("Female");
					
					
					
					CodingDt genderLocal = pat1.getGender().addCoding();
					genderLocal.setSystem("http://localidentifier.hegp.fr");
					genderLocal.setCode(o.getPatient().getSEX_CD());
				}
				
				medOrder.getPatient().setResource(pat1);//adding a reference between the father and the children after the children mapping finished
				
			}
			
			
			
			*//*************Mapping the Encounter fhir resource with the i2b2 data encounter num and incuding it in the MedicationPrescription fhir resource******************************//*
			//this is a test, may be the good design for this is to not allow mapping of referenced resources if the fhir client does'nt requested it explicitelly ...should be discussed
			
			    IdDt encounterId = medOrder.getEncounter().getReference();
				Encounter encounter = new Encounter(); // defining a "fhir patient" for each "i2b2 patient"
				encounter.setId(encounterId);
			    encounter.addIdentifier().setUse(IdentifierUseEnum.USUAL).setSystem("http://localidentifier.hegp.fr").setValue(o.getId().getENCOUNTER_NUM().toString());
			    medOrder.getEncounter().setResource(encounter);
			*//****************************************************************************//*
				
			retVal.add(medOrder); //populating the list of "fhir medication order" as the results of the fhir server displayed as an xml or a json file
			
			
			
}
        
        
        
		
		return retVal;
	}
	
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	//** A search with a parameter *//*
	@Search
	public List<MedicationOrder> searchMedicationOrdersWithCipMedicationsCodes(
			@RequiredParam(name=MedicationOrder.SP_MEDICATION, chainWhitelist={Medication.SP_CODE}) ReferenceParam theMedicationCode,
			@IncludeParam(allow={"MedicationOrder:patient", "MedicationOrder:encounter" , "MedicationOrder:medication"}) Set<Include> theIncludes ){//this serach() method can handle 0, 1 or 2 refrences to other resources
		//including a reference to patient and encounter resources
		
		List<MedicationOrder> retVal = new ArrayList<MedicationOrder>(); //a list of "fhir MedicationPrescription" as a result of the @Search fhir request
		String medicationCode = theMedicationCode.getValueAsString();
	    
		//System.out.println(medicationCode);

		ObservationFactDimensionService ObservationFactDimensionService = new ObservationFactDimensionService();
		
        //List<ObservationFactDimension> i2b2observations = ObservationFactDimensionService.findObservationsByMedicationLocalCode(medicationCode);//This should be used for searching medication concept based on a local code, 
		//concept_cd = MED:27312

		List<ObservationFactDimension> i2b2observations = ObservationFactDimensionService.findObservationsByMedicationCipCodeNsourceSystemCD(medicationCode, "DXCARE"); //this should be used for searching medication concepts based on CIP codes,
		//NAME_CHAR like '%['+ medicationCode +']%'
		
		
		
		boolean patient_Children = false;
		boolean encounter_Children = false;
		boolean medication_Children = false;
		
		if(theIncludes != null){
			
			for(Include next : theIncludes){
				
				//System.out.println(next.getValue());
				
				if(next.getValue().contains("MedicationOrder:patient")){
					patient_Children = true;
					
				}
				
				if(next.getValue().contains("MedicationOrder:encounter")){
					encounter_Children = true;
					
				}
				
				if(next.getValue().contains("MedicationOrder:medication")){
					medication_Children = true;
					
				}
				
			}
			
		}
		
		//System.out.println(patient_Children + "   " + encounter_Children + "   " + medication_Children);
		
		
		/*Iterator<Include> it = theIncludes.iterator();
		
		
		while(it.hasNext()){//this allows to check if one or two references are requested by the user as a "children" resource for the MedicationPrescription resource which is the "father" resource
			
			System.out.println("je suis ici:" + it.next().getValue());
			
			try {
				if (it.next().getValue().equals("MedicationOrder:patient")) {
					
					patient_Children = true;
					System.out.println("ok pour patient");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				if (it.next().getValue().contains("MedicationOrder:encounter")) {
					
					encounter_Children = true;
					System.out.println("ok pour encounter");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			try {
				if (it.next().getValue().contains("MedicationOrder:medication")) {
					
					medication_Children = true;
					System.out.println("ok pour medication");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		
for (ObservationFactDimension o : i2b2observations) {//...and then allowing the mapping between the results of the "i2b2 patient" and the "fhir Patients"
			
	        Long id = myNextId++; //mondatory for each fhir resource defined
	        MedicationOrder medOrder = new MedicationOrder(); 			
	        medOrder.setId(new IdDt(id));
			medOrder.setDateWrittenWithSecondsPrecision(o.getId().getSTART_DATE());//mapping example for the father resource
			
			
			
			/*List <DosageInstruction> MyDosageInstructionList = null;
			DosageInstruction MyDosage = new DosageInstruction();
			MyDosage.setText(o.getUNITS_CD());
			MyDosageInstructionList.add(MyDosage);
			
			medOrder.setDosageInstruction(MyDosageInstructionList);*/
			
			
			//Solution 1
			medOrder.addDosageInstruction().setText(o.getUNITS_CD()); //this allows to display the UNITS_CD field in one "shot". This could be helpfull for the client rather than to use the diffrent
			//fields of the Fhir medicationOrder fields.
			
			//Solution 2
			String[] i2b2_units_cd_split = o.getUNITS_CD().split("_");
			
			String[] i2b2_quantity = i2b2_units_cd_split[0].split(";");
			float i2b2_quantity_sum =0; int cpt=0;
			
			
				for(cpt=0; cpt < i2b2_quantity.length; cpt++){
					if(i2b2_quantity[cpt] != null && !i2b2_quantity[cpt].isEmpty()){
						//System.out.println("i2b2_quantity[cpt]    " + i2b2_quantity[cpt].toString());	
					String i2b2_q = i2b2_quantity[cpt].replace(",", ".");	
					
					i2b2_quantity_sum = i2b2_quantity_sum + Float.parseFloat(i2b2_q);
					
					}
					
					
					}
			
			
			//Mapping the first field of the i2b2 UNITS_CD: "quantité" with the Quantity Fhir field of the DispenseRequest class
			DispenseRequest myDispenseRequest = new DispenseRequest();
			Long myDispenseRequest_id = myNextId++;
			myDispenseRequest.setId(new IdDt(myDispenseRequest_id));
			SimpleQuantityDt myQuantity = new SimpleQuantityDt();
			Long myQuantity_id = myNextId++;
			myQuantity.setId(new IdDt(myQuantity_id));
			myQuantity.setValue(i2b2_quantity_sum);
			//Mapping the third field of the i2b2 UNITS_CD: "UNITPRESCT" with the Quantity Fhir field of the DispenseRequest class
			myQuantity.setUnit(i2b2_units_cd_split[3]);
			myQuantity.setSystem("http://localidentifier.hegp.fr");
			myDispenseRequest.setQuantity(myQuantity);
			medOrder.setDispenseRequest(myDispenseRequest);
			
			////Mapping the second field of the i2b2 UNITS_CD: "FRZHEURE", "FRZJOUR and "NBJOURS" with the Timing Fhir field of the DosageInstruction class
			Long myDosageInstruction_id = myNextId++;
			DosageInstruction myDosageInstruction = new DosageInstruction();
			myDosageInstruction.setId(new IdDt(myDosageInstruction_id));
			TimingDt myTiming = new TimingDt();
			Long myTiming_Id = myNextId++;
			myTiming.setId(new IdDt(myTiming_Id));
			BoundCodeableConceptDt<TimingAbbreviationEnum> timingCode = new BoundCodeableConceptDt();
			timingCode.setText(i2b2_units_cd_split[1] + "_" + i2b2_units_cd_split[2]+ "_" + i2b2_units_cd_split[6]);
			myTiming.setCode(timingCode);
			myDosageInstruction.setTiming(myTiming);
			
			
			BoundCodeableConceptDt dosageInstructionMethod = new BoundCodeableConceptDt();
			dosageInstructionMethod.addCoding().setCode(i2b2_units_cd_split[5]).setSystem("http://localidentifier.hegp.fr");
			myDosageInstruction.setMethod(dosageInstructionMethod);
			
			
			BoundCodeableConceptDt dosageInstructionRoute = new BoundCodeableConceptDt();
			dosageInstructionRoute.addCoding().setCode(i2b2_units_cd_split[4]).setSystem("http://localidentifier.hegp.fr");
            myDosageInstruction.setRoute(dosageInstructionRoute);
			//timingCode.addCoding().setSystem("http://localidentifier.hegp.fr");
			
			//myTiming.setCode(timingCode);
			//Repeat myReapet = new Repeat();
			//DecimalDt myDecimalDt = new DecimalDt();
			
			
			
			
				
			
			//myReapet.setPeriod(1);
			//BoundCodeableConceptDt<TimingAbbreviationEnum> myUnitOfTime = new BoundCodeableConceptDt();
			//myUnitOfTime.setText(UnitsOfTimeEnum.D.getCode());
			//myReapet.setPeriodUnits(UnitsOfTimeEnum.D);
			//myReapet.setPeriod(Long.parseLong(i2b2_units_cd_split[2]));
			//myTiming.setRepeat(myReapet);
			
			medOrder.addDosageInstruction(myDosageInstruction);
			
			
		    ////Mapping the second field of the i2b2 UNITS_CD: "FRZJOUR" with the Timing Fhir field of the DosageInstruction class
			
			
			
			//ende solution 2
			
			
			/*Long idDosageInstructions = myNextId++; //mondatory for each fhir resource defined
			Iterator<DosageInstruction> itDosageInstruction = medOrder.getDosageInstruction().iterator();
			//itDosageInstruction.next().setId(new IdDt(idDosageInstructions));
			
			
			try {
				itDosageInstruction.next().setText(o.getUNITS_CD());
				medOrder.setDosageInstruction((List<DosageInstruction>) itDosageInstruction);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			*/
			
			//System.out.println(patient_Children + "   " + encounter_Children + "   " + medication_Children);
			
			
			try {
				if (patient_Children) {//mapping example for the children resource: patient
					//System.out.println(" first !!!!!!!!!!!!!! ");
					// The resource reference should contain the ID of the patient
				    IdDt subjectId = medOrder.getPatient().getReference();
					Patient pat1 = new Patient(); // defining a "fhir patient" for each "i2b2 patient"
					pat1.setId(subjectId);
					
					pat1.addIdentifier().setUse(IdentifierUseEnum.USUAL).setSystem("http://localidentifier.hegp.fr").setValue(o.getPatient().getPATIENT_NUM().toString());
					pat1.setBirthDateWithDayPrecision(o.getPatient().getBIRTH_DATE());
					if(o.getPatient().getSEX_CD().contains(":M")){
						
						//CodingDt genderSystem = null;
						
						BoundCodeDt<AdministrativeGenderEnum> genderSystem = pat1.getGenderElement();
						genderSystem.setValue(AdministrativeGenderEnum.MALE.getCode());
						//genderSystem.setSystem("http://hl7.org/fhir/administrative-gender"); 				
						//genderSystem.setCode(AdministrativeGenderCodesEnum.M.getCode());
						//genderSystem.setCode(AdministrativeGenderEnum.MALE.getCode());
						//genderSystem.setDisplay("Male");
						//to know how to populate values of an already valueset, we need to navigate here: http://hapifhir.io/apidocs-dstu/ca/uhn/fhir/model/dstu/valueset/package-tree.html
						//pat1.setGender(AdministrativeGenderEnum.MALE);
						//here is the local codification system value
						
						//CodingDt genderLocal = pat1.getGender().addCoding();
						//genderLocal.setSystem("http://localidentifier.hegp.fr");
						//genderLocal.setCode(o.getPatient().getSEX_CD());
						
						
						
					}
					
					if(o.getPatient().getSEX_CD().contains(":F")){
						
						
						
						BoundCodeDt<AdministrativeGenderEnum> genderSystem = pat1.getGenderElement();
						genderSystem.setValue(AdministrativeGenderEnum.FEMALE.getCode());
						
						//pat1.setGender(AdministrativeGenderEnum.FEMALE);
						/*CodingDt genderSystem = pat1.getGender().addCoding();
						genderSystem.setSystem("http://hl7.org/fhir/administrative-gender");
						genderSystem.setCode(AdministrativeGenderCodesEnum.F.getCode());
						genderSystem.setDisplay("Female");
						
						
						
						CodingDt genderLocal = pat1.getGender().addCoding();
						genderLocal.setSystem("http://localidentifier.hegp.fr");
						genderLocal.setCode(o.getPatient().getSEX_CD());*/
					}
					
					
					
					
					medOrder.getPatient().setResource(pat1);//adding a reference between the father and the children after the children mapping finished
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			//*************Mapping the Encounter fhir resource with the i2b2 data encounter num and incuding it in the MedicationPrescription fhir resource******************************//*
			//this allows to dispaly the Encounter resource only when it is requested by the user
			
			try {
				if (encounter_Children) {//mapping example for the children resource: encounter
					
					//System.out.println(" second !!!!!!!!!!!!!! ");
					
					IdDt encounterId = medOrder.getEncounter().getReference();
						Encounter encounter = new Encounter(); // defining a "fhir encounter" for each "i2b2 patient"
						encounter.setId(encounterId);
					    encounter.addIdentifier().setUse(IdentifierUseEnum.USUAL).setSystem("http://localidentifier.hegp.fr").setValue(o.getId().getENCOUNTER_NUM().toString());
					    PeriodDt myPeriod = new PeriodDt();
					    Long myEncounterPeriod_id = myNextId++;
					    myPeriod.setId(new IdDt(myEncounterPeriod_id));
					    myPeriod.setStartWithSecondsPrecision(o.getVisit().getSTART_DATE());
					    myPeriod.setEndWithSecondsPrecision(o.getVisit().getEND_DATE());
					    encounter.setPeriod(myPeriod);
					    medOrder.getEncounter().setResource(encounter);
					   
					    
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   
			//****************************************************************************//*
			
			
			
			
			try {
				if (medication_Children) {//mapping example for the children resource: medication
					
					
					//System.out.println(" third !!!!!!!!!!!!!! ");
					//defining a medication to be contained in the medicationorder classe defined later: medOrder
					Long medId = myNextId++; //mondatory for each fhir resource defined
					// Adding medication to Contained.
					Medication medResource = new Medication();
					medResource.setId(new IdDt(medId));
					
					//defining a local code for medication code
					CodingDt medicationLocalCode = medResource.getCode().addCoding();
					medicationLocalCode.setSystem("http://localidentifier.hegp.fr");
					medicationLocalCode.setCode(o.getId().getCONCEPT_CD());
					medicationLocalCode.setDisplay(o.getTVAL_CHAR());
					
					//defining a cip code for medication code
					    CodingDt medicationCipCode = medResource.getCode().addCoding();
					    medicationCipCode.setSystem("https://www.data.gouv.fr/fr/datasets/medicaments-rembourses-par-lassurance-maladie/");
					    String[] concept_path_split = o.getConcept().getCONCEPT_PATH().split("\\\\");
					  
					    //System.out.println(concept_path_split[8]);
					    
					    Pattern pattern = Pattern.compile("\\[(.*)\\]");
					    Matcher matcher = pattern.matcher(concept_path_split[9]);
					    if (matcher.find())
					    {
					    	//System.out.println(concept_path_split[9]);
					        //System.out.println(matcher.group(1));
					    	medicationCipCode.setCode(matcher.group(1));
							
					    }
					    //medicationCipCode.setCode(o.getConcept().getCONCEPT_PATH());
						
					  //defining an ATC code for medication code
					    CodingDt medicationAtcCode = medResource.getCode().addCoding();
					    medicationAtcCode.setSystem("http://www.whocc.no/atc");
					    
					    Pattern patternAtc = Pattern.compile("\\((.*)\\)");
					    Matcher matcherAtc = patternAtc.matcher(concept_path_split[8]);
					    if (matcherAtc.find())
					    {
					    	
					    	medicationAtcCode.setCode(matcherAtc.group(1));
							
					    }
					    
					
					//Defining the relation between the Medication and MedicationOrder fhir classes
					
					ResourceReferenceDt medRefDt = new ResourceReferenceDt();
					medRefDt.setResource(medResource);
					medRefDt.setDisplay("MedRef");
					
					medOrder.setMedication(medRefDt);
					
					
					
					
					    
					    
					    
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
			
			
			
				
			retVal.add(medOrder); //populating the list of "fhir medication order" as the results of the fhir server displayed as an xml or a json file
			
			
			
}
        
        
        
		
		return retVal;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/** A search with a parameter *//*
	@Search
	public List<MedicationPrescription> searchMedicationOrdersWithLocalMedicationsCodes(
			@RequiredParam(name=MedicationPrescription.SP_MEDICATION, chainWhitelist={Medication.SP_CODE}) ReferenceParam theMedicationCode){
		
		List<MedicationPrescription> retVal = new ArrayList<MedicationPrescription>(); //a list of "fhir MedicationPrescription" as a result of the @Search fhir request
		String medicationCode = theMedicationCode.getValueAsString();
	

		ObservationFactDimensionService ObservationFactDimensionService = new ObservationFactDimensionService();
        List<ObservationFactDimension> i2b2observations = ObservationFactDimensionService.findObservationsByMedicationLocalCode(medicationCode);

		
for (ObservationFactDimension o : i2b2observations) {//...and then allowing the mapping between the results of the "i2b2 patient" and the "fhir Patients"
			
	        Long id = myNextId++; //mondatory for each fhir resource defined
	        MedicationPrescription medOrder = new MedicationPrescription(); 
			
	        medOrder.setId(new IdDt(id));
			medOrder.setDateWrittenWithSecondsPrecision(o.getId().getSTART_DATE());//mapping example for the father resource
			
			
			
			retVal.add(medOrder); //populating the list of "fhir medication order" as the results of the fhir server displayed as an xml or a json file
			
			
			
}
        
        
        
		
		return retVal;
	}*/
	
	
	

}
