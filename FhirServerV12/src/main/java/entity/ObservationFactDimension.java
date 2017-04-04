/**
 * 
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Clob;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * @author Abdelali Boussadi
 * 
 * 
 *         Entity class representing the i2b2 observation_fact table
 */

@Entity
@Table(name = "I2B2DEMODATA.OBSERVATION_FACT")
public class ObservationFactDimension implements Serializable {

	// For datatype mapping between Oracle and Java, refere to
	// https://docs.oracle.com/cd/E19501-01/819-3659/gcmaz/
	
	/*COLUMN_NAME	DATA_TYPE	TYPE_NAME
ENCOUNTER_NUM	3	NUMBER
PATIENT_NUM	3	NUMBER
CONCEPT_CD	12	VARCHAR2
PROVIDER_ID	12	VARCHAR2
START_DATE	93	DATE
MODIFIER_CD	12	VARCHAR2
INSTANCE_NUM	3	NUMBER
VALTYPE_CD	12	VARCHAR2
TVAL_CHAR	12	VARCHAR2
NVAL_NUM	3	NUMBER
VALUEFLAG_CD	12	VARCHAR2
QUANTITY_NUM	3	NUMBER
UNITS_CD	12	VARCHAR2
END_DATE	93	DATE
LOCATION_CD	12	VARCHAR2
OBSERVATION_BLOB	2005	CLOB
CONFIDENCE_NUM	3	NUMBER
UPDATE_DATE	93	DATE
DOWNLOAD_DATE	93	DATE
IMPORT_DATE	93	DATE
SOURCESYSTEM_CD	12	VARCHAR2
UPLOAD_ID	3	NUMBER
SCHEME_KEY	12	VARCHAR2
PROV_KEY	12	VARCHAR2*/

	public ObservationFactDimension() {
	}

	@EmbeddedId
	private ObservationFactDimensionId id;

	/*
	 * @Id
	 * 
	 * @Column(name = "ENCOUNTER_NUM") private BigDecimal ENCOUNTER_NUM;
	 */

	/*
	 * @Column(name = "PATIENT_NUM") private Double PATIENT_NUM ;
	 */

	/*
	 * @Column(name = "CONCEPT_CD") private String CONCEPT_CD ;
	 */

	/*
	 * @Column(name = "PROVIDER_ID") private String PROVIDER_ID ;
	 */

	/*
	 * @Column(name = "START_DATE") private Date START_DATE ;
	 * 
	 * 
	 * @Column(name = "MODIFIER_CD") private String MODIFIER_CD ;
	 */

	@Column(name = "INSTANCE_NUM")
	private Long INSTANCE_NUM;

	@Column(name = "VALTYPE_CD")
	private String VALTYPE_CD;

	@Column(name = "TVAL_CHAR")
	private String TVAL_CHAR;

	@Column(name = "NVAL_NUM")
	private Long NVAL_NUM;

	@Column(name = "VALUEFLAG_CD")
	private String VALUEFLAG_CD;

	@Column(name = "QUANTITY_NUM")
	private Long QUANTITY_NUM;

	@Column(name = "UNITS_CD")
	private String UNITS_CD;

	@Column(name = "END_DATE")
	private Date END_DATE;

	@Column(name = "LOCATION_CD")
	private String LOCATION_CD;

	@Column(name = "OBSERVATION_BLOB")
	private Clob OBSERVATION_BLOB;
	@Column(name = "CONFIDENCE_NUM")
	private Long CONFIDENCE_NUM;
	@Column(name = "UPDATE_DATE")
	private Date UPDATE_DATE;
	@Column(name = "DOWNLOAD_DATE")
	private Date DOWNLOAD_DATE;

	@Column(name = "IMPORT_DATE")
	private Date IMPORT_DATE;

	@Column(name = "SOURCESYSTEM_CD")
	private String SOURCESYSTEM_CD;
	@Column(name = "UPLOAD_ID")
	private BigDecimal UPLOAD_ID;
	@Column(name = "SCHEME_KEY")
	private String SCHEME_KEY;
	@Column(name = "PROV_KEY")
	private String PROV_KEY;

	// a many to one association added in order to allow hql requests navigation
	// between
	// PatientDimension and ObservationFactDimension hibernate objects
	@ManyToOne
	@JoinColumn(name = "PATIENT_NUM")
	private PatientDimension patient;

	// a many to many association added in order to allow hql requests
	// navigation between
	// ConceptDimension and ObservationFactDimension hibernate objects
	/*
	 * @ManyToMany(cascade = {CascadeType.ALL})
	 * 
	 * @JoinTable(name=
	 * "I2B2DEMODATA.OBSERVATION_FACT_I2B2DEMODATA.CONCEPT_DIMENSION",
	 * joinColumns={@JoinColumn(name = "CONCEPT_CD")},
	 * inverseJoinColumns={@JoinColumn(name="ENCOUNTER_NUM")}) private
	 * Set<ConceptDimension> concept;
	 */

	// a many to one association added in order to allow hql requests navigation
	// between
	// ConceptDimension and ObservationFactDimension hibernate objects
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "CONCEPT_CD") private ConceptDimension concept;
	 */

	@MapsId("ConceptDimensionId")
	@JoinColumns({ @JoinColumn(name = "CONCEPT_CD", referencedColumnName = "CONCEPT_CD"),

	})
	@ManyToOne
	ConceptDimension concept;

	// a many to one association added in order to allow hql requests navigation
	// between
	// PatientDimension and ObservationFactDimension hibernate objects
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "ENCOUNTER_NUM") private VisitDimension visit;
	 */

	@MapsId("VisitDimensionId")
	@JoinColumns({
			@JoinColumn(name = "ENCOUNTER_NUM", referencedColumnName = "ENCOUNTER_NUM"),
			@JoinColumn(name = "PATIENT_NUM", referencedColumnName = "PATIENT_NUM") })
	@ManyToOne
	VisitDimension visit;

	/*
	 * public BigDecimal getENCOUNTER_NUM() { return ENCOUNTER_NUM; } public
	 * void setENCOUNTER_NUM(BigDecimal eNCOUNTER_NUM) { ENCOUNTER_NUM =
	 * eNCOUNTER_NUM; }
	 */

	/*
	 * public Double getPATIENT_NUM() { return PATIENT_NUM; } public void
	 * setPATIENT_NUM(Double pATIENT_NUM) { PATIENT_NUM = pATIENT_NUM; }
	 */

	/*
	 * public String getCONCEPT_CD() { return CONCEPT_CD; } public void
	 * setCONCEPT_CD(String cONCEPT_CD) { CONCEPT_CD = cONCEPT_CD; }
	 */

	public ObservationFactDimensionId getId() {
		return id;
	}

	public void setId(ObservationFactDimensionId id) {
		this.id = id;
	}

	/*
	 * public String getPROVIDER_ID() { return PROVIDER_ID; } public void
	 * setPROVIDER_ID(String pROVIDER_ID) { PROVIDER_ID = pROVIDER_ID; } public
	 * Date getSTART_DATE() { return START_DATE; } public void
	 * setSTART_DATE(Date sTART_DATE) { START_DATE = sTART_DATE; } public String
	 * getMODIFIER_CD() { return MODIFIER_CD; } public void
	 * setMODIFIER_CD(String mODIFIER_CD) { MODIFIER_CD = mODIFIER_CD; }
	 */
	public Long getINSTANCE_NUM() {
		return INSTANCE_NUM;
	}

	public void setINSTANCE_NUM(Long iNSTANCE_NUM) {
		INSTANCE_NUM = iNSTANCE_NUM;
	}

	public String getVALTYPE_CD() {
		return VALTYPE_CD;
	}

	public void setVALTYPE_CD(String vALTYPE_CD) {
		VALTYPE_CD = vALTYPE_CD;
	}

	public String getTVAL_CHAR() {
		return TVAL_CHAR;
	}

	public void setTVAL_CHAR(String tVAL_CHAR) {
		TVAL_CHAR = tVAL_CHAR;
	}

	public Long getNVAL_NUM() {
		return NVAL_NUM;
	}

	public void setNVAL_NUM(Long nVAL_NUM) {
		NVAL_NUM = nVAL_NUM;
	}

	public String getVALUEFLAG_CD() {
		return VALUEFLAG_CD;
	}

	public void setVALUEFLAG_CD(String vALUEFLAG_CD) {
		VALUEFLAG_CD = vALUEFLAG_CD;
	}

	public Long getQUANTITY_NUM() {
		return QUANTITY_NUM;
	}

	public void setQUANTITY_NUM(Long qUANTITY_NUM) {
		QUANTITY_NUM = qUANTITY_NUM;
	}

	public String getUNITS_CD() {
		return UNITS_CD;
	}

	public void setUNITS_CD(String uNITS_CD) {
		UNITS_CD = uNITS_CD;
	}

	public Date getEND_DATE() {
		return END_DATE;
	}

	public void setEND_DATE(Date eND_DATE) {
		END_DATE = eND_DATE;
	}

	public String getLOCATION_CD() {
		return LOCATION_CD;
	}

	public void setLOCATION_CD(String lOCATION_CD) {
		LOCATION_CD = lOCATION_CD;
	}

	public Clob getOBSERVATION_BLOB() {
		return OBSERVATION_BLOB;
	}

	public void setOBSERVATION_BLOB(Clob oBSERVATION_BLOB) {
		OBSERVATION_BLOB = oBSERVATION_BLOB;
	}

	public Long getCONFIDENCE_NUM() {
		return CONFIDENCE_NUM;
	}

	public void setCONFIDENCE_NUM(Long cONFIDENCE_NUM) {
		CONFIDENCE_NUM = cONFIDENCE_NUM;
	}

	public Date getUPDATE_DATE() {
		return UPDATE_DATE;
	}

	public void setUPDATE_DATE(Date uPDATE_DATE) {
		UPDATE_DATE = uPDATE_DATE;
	}

	public Date getDOWNLOAD_DATE() {
		return DOWNLOAD_DATE;
	}

	public void setDOWNLOAD_DATE(Date dOWNLOAD_DATE) {
		DOWNLOAD_DATE = dOWNLOAD_DATE;
	}

	public Date getIMPORT_DATE() {
		return IMPORT_DATE;
	}

	public void setIMPORT_DATE(Date iMPORT_DATE) {
		IMPORT_DATE = iMPORT_DATE;
	}

	public String getSOURCESYSTEM_CD() {
		return SOURCESYSTEM_CD;
	}

	public void setSOURCESYSTEM_CD(String sOURCESYSTEM_CD) {
		SOURCESYSTEM_CD = sOURCESYSTEM_CD;
	}

	public BigDecimal getUPLOAD_ID() {
		return UPLOAD_ID;
	}

	public void setUPLOAD_ID(BigDecimal uPLOAD_ID) {
		UPLOAD_ID = uPLOAD_ID;
	}

	public String getSCHEME_KEY() {
		return SCHEME_KEY;
	}

	public void setSCHEME_KEY(String sCHEME_KEY) {
		SCHEME_KEY = sCHEME_KEY;
	}

	public String getPROV_KEY() {
		return PROV_KEY;
	}

	public void setPROV_KEY(String pROV_KEY) {
		PROV_KEY = pROV_KEY;
	}

	public PatientDimension getPatient() {
		return patient;
	}

	public void setPatient(PatientDimension patient) {
		this.patient = patient;
	}

	public ConceptDimension getConcept() {
		return concept;
	}

	public void setConcept(ConceptDimension concept) {
		this.concept = concept;
	}

	public VisitDimension getVisit() {
		return visit;
	}

	public void setVisit(VisitDimension visit) {
		this.visit = visit;
	}

}
