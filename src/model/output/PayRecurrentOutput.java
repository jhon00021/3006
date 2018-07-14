package model.output;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class PayRecurrentOutput {
	
	//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy");
	
	private String user;
	
	private List<AddtionalInformation> add;


	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void sort(){
		Collections.sort(this.add);		
	}
	
	public List<AddtionalInformation> getAdd() {
		return add;
	}

	public void setAdd(List<AddtionalInformation> add) {
		this.add = add;
	}

	@Override
	public String toString() {
		String toReturn = "";
		
		Iterator it = add.iterator();
		while(it.hasNext()){
			AddtionalInformation additional = (AddtionalInformation )it.next();
			// TODO Auto-generated method stub
			String newnum = "";
			if (additional.getPayvalue() == null) {
				newnum = "";
			}
			else
			{
				newnum = String.format("%1$.3f", additional.getPayvalue());
			}
			
			String numeroRecogido = newnum;
			String numeroNuevo = numeroRecogido.replaceAll(",", ".");
			BigDecimal valornuevo =  new BigDecimal(numeroNuevo);
			toReturn += user+",,"+formatter1.format(additional.getEndDate())+",,"+formatter1.format(additional.getStartDate())+","+additional.getConcept()+","+valornuevo+","+
					additional.getCurrency()+","+additional.getFrecuency()+",,,,"+"\n";
			
		}
		
		
		//return user+",,"+formatter1.format(endDate)+",,"+formatter1.format(startDate)+","+concept+","+comilla+(payvalue == null? "" : String.format("%1$.3f", payvalue))+comilla+","+
		return toReturn;
	}

	
}
