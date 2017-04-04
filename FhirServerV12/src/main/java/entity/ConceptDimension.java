/**
 * 
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Clob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Abdelali Boussadi
 * 
 * 
 *         Entity class representing the i2b2 concept dimension table
 */

@Entity
@Table(name = "I2B2DEMODATA.CONCEPT_DIMENSION")
public class ConceptDimension implements Serializable {

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

	public ConceptDimension() {
	}

	@Id
	@Column(name = "CONCEPT_PATH")
	private String CONCEPT_PATH;

	@Column(name = "CONCEPT_CD")
	private String CONCEPT_CD;

	@Column(name = "NAME_CHAR")
	private String NAME_CHAR;

	@Column(name = "CONCEPT_BLOB")
	private Long CONCEPT_BLOB;

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

	public String getCONCEPT_PATH() {
		return CONCEPT_PATH;
	}

	public void setCONCEPT_PATH(String cONCEPT_PATH) {
		CONCEPT_PATH = cONCEPT_PATH;
	}

	public String getCONCEPT_CD() {
		return CONCEPT_CD;
	}

	public void setCONCEPT_CD(String cONCEPT_CD) {
		CONCEPT_CD = cONCEPT_CD;
	}

	public String getNAME_CHAR() {
		return NAME_CHAR;
	}

	public void setNAME_CHAR(String nAME_CHAR) {
		NAME_CHAR = nAME_CHAR;
	}

	public Long getCONCEPT_BLOB() {
		return CONCEPT_BLOB;
	}

	public void setCONCEPT_BLOB(Long cONCEPT_BLOB) {
		CONCEPT_BLOB = cONCEPT_BLOB;
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

}
