/**
 * 
 */
package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import java.sql.Clob;
import java.sql.Types;

//import org.hibernate.mapping.Set;

/**
 * @author Abdelali Boussadi
 * 
 * 
 *         Entity class representing the i2b2 patient dimension table
 */

@Entity
@Table(name = "I2B2DEMODATA.VISIT_DIMENSION")
public class VisitDimension implements Serializable {

	public VisitDimension() {
	}

	@EmbeddedId
	private VisitDimensionId id;

	/*
	 * @Id //@Type(type="DECIMAL")
	 * 
	 * @Column(name = "ENCOUNTER_NUM") private BigDecimal ENCOUNTER_NUM;
	 */

	@Column(name = "ACTIVE_STATUS_CD")
	private String ACTIVE_STATUS_CD;

	@Column(name = "START_DATE")
	private Date START_DATE;

	@Column(name = "END_DATE")
	private Date END_DATE;

	@Column(name = "INOUT_CD")
	private String INOUT_CD;

	@Column(name = "LOCATION_CD")
	private String LOCATION_CD;

	@Column(name = "LOCATION_PATH")
	private String LOCATION_PATH;

	@Column(name = "LENGTH_OF_STAY")
	private int LENGTH_OF_STAY;

	@Column(name = "VISIT_BLOB")
	private Clob VISIT_BLOB;

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

	/*
	 * public BigDecimal getENCOUNTER_NUM() { return ENCOUNTER_NUM; }
	 * 
	 * public void setENCOUNTER_NUM(BigDecimal eNCOUNTER_NUM) { ENCOUNTER_NUM =
	 * eNCOUNTER_NUM; }
	 */

	public VisitDimensionId getId() {
		return id;
	}

	public void setId(VisitDimensionId id) {
		this.id = id;
	}

	public String getACTIVE_STATUS_CD() {
		return ACTIVE_STATUS_CD;
	}

	public void setACTIVE_STATUS_CD(String aCTIVE_STATUS_CD) {
		ACTIVE_STATUS_CD = aCTIVE_STATUS_CD;
	}

	public Date getSTART_DATE() {
		return START_DATE;
	}

	public void setSTART_DATE(Date sTART_DATE) {
		START_DATE = sTART_DATE;
	}

	public Date getEND_DATE() {
		return END_DATE;
	}

	public void setEND_DATE(Date eND_DATE) {
		END_DATE = eND_DATE;
	}

	public String getINOUT_CD() {
		return INOUT_CD;
	}

	public void setINOUT_CD(String iNOUT_CD) {
		INOUT_CD = iNOUT_CD;
	}

	public String getLOCATION_CD() {
		return LOCATION_CD;
	}

	public void setLOCATION_CD(String lOCATION_CD) {
		LOCATION_CD = lOCATION_CD;
	}

	public String getLOCATION_PATH() {
		return LOCATION_PATH;
	}

	public void setLOCATION_PATH(String lOCATION_PATH) {
		LOCATION_PATH = lOCATION_PATH;
	}

	public int getLENGTH_OF_STAY() {
		return LENGTH_OF_STAY;
	}

	public void setLENGTH_OF_STAY(int lENGTH_OF_STAY) {
		LENGTH_OF_STAY = lENGTH_OF_STAY;
	}

	public Clob getVISIT_BLOB() {
		return VISIT_BLOB;
	}

	public void setVISIT_BLOB(Clob vISIT_BLOB) {
		VISIT_BLOB = vISIT_BLOB;
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
