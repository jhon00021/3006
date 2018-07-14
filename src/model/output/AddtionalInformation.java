package model.output;

import java.util.Comparator;
import java.util.Date;

public class AddtionalInformation implements Comparable{
	
	private Date endDate;
	
	private Date startDate;
	
	private String concept;
	
	private Double payvalue;
	
	private String currency;
	
	private String frecuency;
	
	private String comilla;

	public Date getEndDate() {
		return endDate;
	}
	public AddtionalInformation() {
		// TODO Auto-generated constructor stub
	}
	
	public AddtionalInformation(AddtionalInformation add) {
		this.endDate = add.getEndDate();
		this.startDate = add.getStartDate();
		this.concept = add.getConcept();
		this.payvalue = add.getPayvalue();
		this.currency = add.getCurrency();
		this.frecuency = add.getFrecuency();
		this.comilla = add.getComilla();
		}
	
	

	public AddtionalInformation(Date endDate, Date startDate, String concept,
			Double payvalue, String currency, String frecuency, String comilla) {
		this.endDate = endDate;
		this.startDate = startDate;
		this.concept = concept;
		this.payvalue = payvalue;
		this.currency = currency;
		this.frecuency = frecuency;
		this.comilla = comilla;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getConcept() {
		return concept;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public Double getPayvalue() {
		return payvalue;
	}

	public void setPayvalue(Double payvalue) {
		this.payvalue = payvalue;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getFrecuency() {
		return frecuency;
	}

	public void setFrecuency(String frecuency) {
		this.frecuency = frecuency;
	}

	public String getComilla() {
		return comilla;
	}

	public void setComilla(String comilla) {
		this.comilla = comilla;
	}
	@Override
	public int compareTo(Object arg0) {
		return this.startDate.compareTo( ( (AddtionalInformation) arg0).getStartDate());
		
		
	}
	
	

}
