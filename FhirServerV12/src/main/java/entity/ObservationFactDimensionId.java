package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Embeddable;

@Embeddable
public class ObservationFactDimensionId implements Serializable {

	private String CONCEPT_CD;
	private BigDecimal ENCOUNTER_NUM;
	private String MODIFIER_CD;
	private String PROVIDER_ID;
	private Date START_DATE;

	public String getCONCEPT_CD() {
		return CONCEPT_CD;
	}

	public void setCONCEPT_CD(String cONCEPT_CD) {
		CONCEPT_CD = cONCEPT_CD;
	}

	public BigDecimal getENCOUNTER_NUM() {
		return ENCOUNTER_NUM;
	}

	public void setENCOUNTER_NUM(BigDecimal eNCOUNTER_NUM) {
		ENCOUNTER_NUM = eNCOUNTER_NUM;
	}

	public String getMODIFIER_CD() {
		return MODIFIER_CD;
	}

	public void setMODIFIER_CD(String mODIFIER_CD) {
		MODIFIER_CD = mODIFIER_CD;
	}

	public String getPROVIDER_ID() {
		return PROVIDER_ID;
	}

	public void setPROVIDER_ID(String pROVIDER_ID) {
		PROVIDER_ID = pROVIDER_ID;
	}

	public Date getSTART_DATE() {
		return START_DATE;
	}

	public void setSTART_DATE(Date sTART_DATE) {
		START_DATE = sTART_DATE;
	}

}
