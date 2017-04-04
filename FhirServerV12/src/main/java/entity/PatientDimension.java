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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import java.sql.Types;

//import org.hibernate.mapping.Set;

/**
 * @author Abdelali Boussadi
 * 
 * 
 *         Entity class representing the i2b2 patient dimension table
 */

@Entity
@Table(name = "I2B2DEMODATA.PATIENT_DIMENSION")
public class PatientDimension implements Serializable {
	
	
	/*public PatientDimension(Double pATIENT_NUM, String vITAL_STATUS_CD,
			Date bIRTH_DATE, Date dEATH_DATE, String sEX_CD,
			Double aGE_IN_YEARS_NUM, String lANGUAGE_CD, String rACE_CD,
			String mARITAL_STATUS_CD, String rELIGION_CD, Date uPDATE_DATE,
			Date dOWNLOAD_DATE, Date iMPORT_DATE, String sOURCESYSTEM_CD,
			int uPLOAD_ID) {
		super();
		PATIENT_NUM = pATIENT_NUM;
		VITAL_STATUS_CD = vITAL_STATUS_CD;
		BIRTH_DATE = bIRTH_DATE;
		DEATH_DATE = dEATH_DATE;
		SEX_CD = sEX_CD;
		AGE_IN_YEARS_NUM = aGE_IN_YEARS_NUM;
		LANGUAGE_CD = lANGUAGE_CD;
		RACE_CD = rACE_CD;
		MARITAL_STATUS_CD = mARITAL_STATUS_CD;
		RELIGION_CD = rELIGION_CD;
		UPDATE_DATE = uPDATE_DATE;
		DOWNLOAD_DATE = dOWNLOAD_DATE;
		IMPORT_DATE = iMPORT_DATE;
		SOURCESYSTEM_CD = sOURCESYSTEM_CD;
		UPLOAD_ID = uPLOAD_ID;
	}*/

	public PatientDimension() {
	}

	@Id
	// @Type(type="DECIMAL")
	@Column(name = "PATIENT_NUM")
	private BigDecimal PATIENT_NUM;

	@Column(name = "VITAL_STATUS_CD")
	private String VITAL_STATUS_CD;

	@Column(name = "BIRTH_DATE")
	private Date BIRTH_DATE;

	@Column(name = "DEATH_DATE")
	private Date DEATH_DATE;

	@Column(name = "SEX_CD")
	private String SEX_CD;

	@Column(name = "AGE_IN_YEARS_NUM")
	private BigDecimal AGE_IN_YEARS_NUM;

	@Column(name = "LANGUAGE_CD")
	private String LANGUAGE_CD;

	@Column(name = "RACE_CD")
	private String RACE_CD;

	@Column(name = "MARITAL_STATUS_CD")
	private String MARITAL_STATUS_CD;

	@Column(name = "RELIGION_CD")
	private String RELIGION_CD;

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

	// @OneToMany
	// @JoinColumn(name = "PATIENT_NUM")
	// private Set<ObservationFactDimension> observations = new
	// HashSet<ObservationFactDimension>();

	public BigDecimal getPATIENT_NUM() {
		return PATIENT_NUM;
	}

	public void setPATIENT_NUM(BigDecimal pATIENT_NUM) {
		PATIENT_NUM = pATIENT_NUM;
	}

	public String getVITAL_STATUS_CD() {
		return VITAL_STATUS_CD;
	}

	public void setVITAL_STATUS_CD(String vITAL_STATUS_CD) {
		VITAL_STATUS_CD = vITAL_STATUS_CD;
	}

	public Date getBIRTH_DATE() {
		return BIRTH_DATE;
	}

	public void setBIRTH_DATE(Date bIRTH_DATE) {
		BIRTH_DATE = bIRTH_DATE;
	}

	public Date getDEATH_DATE() {
		return DEATH_DATE;
	}

	public void setDEATH_DATE(Date dEATH_DATE) {
		DEATH_DATE = dEATH_DATE;
	}

	public String getSEX_CD() {
		return SEX_CD;
	}

	public void setSEX_CD(String sEX_CD) {
		SEX_CD = sEX_CD;
	}

	public BigDecimal getAGE_IN_YEARS_NUM() {
		return AGE_IN_YEARS_NUM;
	}

	public void setAGE_IN_YEARS_NUM(BigDecimal aGE_IN_YEARS_NUM) {
		AGE_IN_YEARS_NUM = aGE_IN_YEARS_NUM;
	}

	public String getLANGUAGE_CD() {
		return LANGUAGE_CD;
	}

	public void setLANGUAGE_CD(String lANGUAGE_CD) {
		LANGUAGE_CD = lANGUAGE_CD;
	}

	public String getRACE_CD() {
		return RACE_CD;
	}

	public void setRACE_CD(String rACE_CD) {
		RACE_CD = rACE_CD;
	}

	public String getMARITAL_STATUS_CD() {
		return MARITAL_STATUS_CD;
	}

	public void setMARITAL_STATUS_CD(String mARITAL_STATUS_CD) {
		MARITAL_STATUS_CD = mARITAL_STATUS_CD;
	}

	public String getRELIGION_CD() {
		return RELIGION_CD;
	}

	public void setRELIGION_CD(String rELIGION_CD) {
		RELIGION_CD = rELIGION_CD;
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

	/*
	 * public Set<ObservationFactDimension> getObservations() { return
	 * observations; }
	 * 
	 * public void setObservations(Set<ObservationFactDimension> observations) {
	 * this.observations = observations; }
	 */

}
