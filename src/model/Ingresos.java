package model;

import java.util.Date;

public class Ingresos {
	
	private String code;

	private String codFix;
	
	private Date starDate;
	
	private Date endDate;
	
	private Double value;
	
	public Ingresos(){
		
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodFix() {
		return codFix;
	}

	public void setCodFix(String codFix) {
		this.codFix = codFix;
	}

	public Date getStarDate() {
		return starDate;
	}

	public void setStarDate(Date starDate) {
		this.starDate = starDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
}
