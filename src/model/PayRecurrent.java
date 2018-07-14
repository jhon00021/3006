package model;

import java.util.Date;

public class PayRecurrent {
	private String code;
	
	private String dossier;
	
	private String empresa;
	
	private Date datestart;
	
	private Date datefire;
	
	private String concep;

	private Date datestartconc;
	
	private Date dateendconc;
	
	private Double value;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDossier() {
		return dossier;
	}

	public void setDossier(String dossier) {
		this.dossier = dossier;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public Date getDatestart() {
		return datestart;
	}

	public void setDatestart(Date datestart) {
		this.datestart = datestart;
	}

	public Date getDatefire() {
		return datefire;
	}

	public void setDatefire(Date datefire) {
		this.datefire = datefire;
	}

	public String getConcep() {
		return concep;
	}

	public void setConcep(String concep) {
		this.concep = concep;
	}
	
	public Date getDatestartconc() {
		return datestartconc;
	}

	public void setDatestartconc(Date datestartconc) {
		this.datestartconc = datestartconc;
	}

	public Date getDateendconc() {
		return dateendconc;
	}

	public void setDateendconc(Date dateendconc) {
		this.dateendconc = dateendconc;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}
	
}
