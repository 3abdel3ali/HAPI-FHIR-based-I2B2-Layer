/**
 * @author Abdelali Boussadi
 * 
 * FHIR DiagnosticReport provider class: this class defines methods where a service class is called
 * and the mapping is defined
 *        
 */

package fhirResources;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import service.ObservationFactDimensionService;
import service.PatientDimensionService;
import ca.uhn.fhir.model.api.IResource;
import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.model.dstu2.composite.CodeableConceptDt;
import ca.uhn.fhir.model.dstu2.composite.CodingDt;
import ca.uhn.fhir.model.dstu2.composite.IdentifierDt;
import ca.uhn.fhir.model.dstu2.composite.PeriodDt;
import ca.uhn.fhir.model.dstu2.resource.DiagnosticReport;
import ca.uhn.fhir.model.dstu2.resource.Encounter;
import ca.uhn.fhir.model.dstu2.resource.Medication;
import ca.uhn.fhir.model.dstu2.resource.MedicationOrder;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.resource.ValueSet;
import ca.uhn.fhir.model.dstu2.valueset.AdministrativeGenderEnum;
import ca.uhn.fhir.model.dstu2.valueset.IdentifierUseEnum;
import ca.uhn.fhir.model.primitive.BoundCodeDt;
//import ca.uhn.fhir.model.primitive.BoundCodeableConceptDt;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.model.primitive.IdDt;
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

public class DiagnosticReportResourceProvider implements IResourceProvider {

	private Long myNextId = 1L;

	/**
	 * Constructor
	 * 
	 * @throws ParseException
	 */

	public DiagnosticReportResourceProvider() throws ParseException {
		// Abdelali Boussadi
		// the constructor is empty
		// all the operations should be done in the methods

	}

	/** All Resource Providers must implement this method */
	@Override
	public Class<? extends IResource> getResourceType() {
		return DiagnosticReport.class;
	}

	// ** Serching FHIR DiagnosticReport resources with a parameter "SP_CODE"
	// including FHIR Patient (Subject) and FHIR Encounter Resources *//*
	@Search
	public List<DiagnosticReport> searchDiagnosticReportWithIcd10Codes(
			@RequiredParam(name = DiagnosticReport.SP_CODE) StringParam theDiagnosisCode,
			@IncludeParam(allow = { "DiagnosticReport:subject",
					"DiagnosticReport:encounter" }) Set<Include> theIncludes) {

		List<DiagnosticReport> retVal = new ArrayList<DiagnosticReport>(); // a
																			// list
																			// of
																			// "fhir DiagnosticReport"
																			// as
																			// a
																			// result
																			// of
																			// the
																			// @Search
																			// fhir
																			// request

		String diagnosisCode = theDiagnosisCode.getValue();
		// Using the service layer to request the i2b2 database
		ObservationFactDimensionService ObservationFactDimensionService = new ObservationFactDimensionService();
		List<ObservationFactDimension> i2b2observations = ObservationFactDimensionService
				.findObservationsByDiseaseIcd10Code(diagnosisCode);

		boolean patient_Children = false;
		boolean encounter_Children = false;

		// Testing the Include parameters

		if (theIncludes != null) {

			for (Include next : theIncludes) {

				System.out.println(next.getValue());

				if (next.getValue().contains("DiagnosticReport:subject")) {
					patient_Children = true;

				}

				if (next.getValue().contains("DiagnosticReport:encounter")) {
					encounter_Children = true;

				}

			}

		}

		for (ObservationFactDimension o : i2b2observations) {
			// ...and then allowing the mapping between the results of the i2b2
			// ObservationFact and the fhir DiagnosticReport Resources

			Long id = myNextId++; // mondatory for each fhir resource defined
			DiagnosticReport diagRep = new DiagnosticReport();
			diagRep.setId(new IdDt(id));
			diagRep.getCode().addCoding().setCode(o.getTVAL_CHAR())
					.setSystem("http://hl7.org/fhir/sid/icd-10");

			diagRep.addIdentifier().setSystem("http://localidentifier.hegp.fr")
					.setValue(o.getConcept().getCONCEPT_PATH());

			if (patient_Children) { // Mapping the fhir Patient resource if
									// included

				IdDt subjectId = diagRep.getSubject().getReference();
				Patient pat1 = new Patient(); // defining a "fhir patient" for
												// each "i2b2 patient"
				pat1.setId(subjectId);

				pat1.addIdentifier().setUse(IdentifierUseEnum.USUAL)
						.setSystem("http://localidentifier.hegp.fr")
						.setValue(o.getPatient().getPATIENT_NUM().toString());
				pat1.setBirthDateWithDayPrecision(o.getPatient()
						.getBIRTH_DATE());
				if (o.getPatient().getSEX_CD().contains(":M")) {// Gender
																// mapping

					// CodingDt genderSystem = null;

					BoundCodeDt<AdministrativeGenderEnum> genderSystem = pat1
							.getGenderElement();
					genderSystem.setValue(AdministrativeGenderEnum.MALE
							.getCode());
					// genderSystem.setSystem("http://hl7.org/fhir/administrative-gender");
					// genderSystem.setCode(AdministrativeGenderCodesEnum.M.getCode());
					// genderSystem.setCode(AdministrativeGenderEnum.MALE.getCode());
					// genderSystem.setDisplay("Male");
					// to know how to populate values of an already valueset, we
					// need to navigate here:
					// http://hapifhir.io/apidocs-dstu/ca/uhn/fhir/model/dstu/valueset/package-tree.html
					// pat1.setGender(AdministrativeGenderEnum.MALE);
					// here is the local codification system value

					// CodingDt genderLocal = pat1.getGender().addCoding();
					// genderLocal.setSystem("http://localidentifier.hegp.fr");
					// genderLocal.setCode(o.getPatient().getSEX_CD());

				}

				if (o.getPatient().getSEX_CD().contains(":F")) {

					BoundCodeDt<AdministrativeGenderEnum> genderSystem = pat1
							.getGenderElement();
					genderSystem.setValue(AdministrativeGenderEnum.FEMALE
							.getCode());
					// pat1.setGender(AdministrativeGenderEnum.FEMALE);
					/*
					 * CodingDt genderSystem = pat1.getGender().addCoding();
					 * genderSystem
					 * .setSystem("http://hl7.org/fhir/administrative-gender");
					 * genderSystem
					 * .setCode(AdministrativeGenderCodesEnum.F.getCode());
					 * genderSystem.setDisplay("Female");
					 * 
					 * 
					 * 
					 * CodingDt genderLocal = pat1.getGender().addCoding();
					 * genderLocal.setSystem("http://localidentifier.hegp.fr");
					 * genderLocal.setCode(o.getPatient().getSEX_CD());
					 */
				}

				diagRep.getSubject().setResource(pat1);// adding a reference
														// between the father
														// (DiagnosticReport)
														// and the children
														// after the children
														// (Patient) mapping
														// finished

			}

			if (encounter_Children) {// mapping the fhir Encounter Resource if
										// included

				IdDt encounterId = diagRep.getEncounter().getReference();
				Encounter encounter = new Encounter(); // defining a
														// "fhir encounter" for
														// each "i2b2 patient"
				encounter.setId(encounterId);
				encounter.addIdentifier().setUse(IdentifierUseEnum.USUAL)
						.setSystem("http://localidentifier.hegp.fr")
						.setValue(o.getId().getENCOUNTER_NUM().toString());
				PeriodDt myPeriod = new PeriodDt();
				Long myEncounterPeriod_id = myNextId++;
				myPeriod.setId(new IdDt(myEncounterPeriod_id));
				myPeriod.setStartWithSecondsPrecision(o.getVisit()
						.getSTART_DATE());
				myPeriod.setEndWithSecondsPrecision(o.getVisit().getEND_DATE());
				encounter.setPeriod(myPeriod);
				diagRep.getEncounter().setResource(encounter);
				// System.out.println("id encounter" +
				// encounter.getIdentifier());

			}

			retVal.add(diagRep);
		}

		return retVal;
	}

}