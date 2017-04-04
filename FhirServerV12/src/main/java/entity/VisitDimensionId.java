package entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class VisitDimensionId implements Serializable {

	private BigDecimal ENCOUNTER_NUM;
	private BigDecimal PATIENT_NUM;

	public BigDecimal getENCOUNTER_NUM() {
		return ENCOUNTER_NUM;
	}

	public void setENCOUNTER_NUM(BigDecimal eNCOUNTER_NUM) {
		ENCOUNTER_NUM = eNCOUNTER_NUM;
	}

	public BigDecimal getPATIENT_NUM() {
		return PATIENT_NUM;
	}

	public void setPATIENT_NUM(BigDecimal pATIENT_NUM) {
		PATIENT_NUM = pATIENT_NUM;
	}

}
