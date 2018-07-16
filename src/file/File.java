package file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.text.*;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import model.PayRecurrent;
import model.Ingresos;
import model.output.AddtionalInformation;
import model.output.PayRecurrentOutput;



public class File {
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    
    SimpleDateFormat formatterOut = new SimpleDateFormat("yyyy-MM-dd");
    
    DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
    
    
    //public List<Ingresos> agruparregconcep(List<Ingresos> currentList_ing)throws FileNotFoundException, IOException, ParseException{
    /*public List<Ingresos> agruparregconcep(String fileName)throws FileNotFoundException, IOException, ParseException{
    	Ingresos group = null;
    	String cadena;
    	FileReader f = new FileReader(fileName);
    	BufferedReader b = new BufferedReader(f);
    	List<Ingresos> newList = new ArrayList();
    	
    	for (int i=0; i < currentList_ing.size(); i++ ) {
    		if (group == null)
    			group = currentList_ing.get(i);
			
			String concept = group.getCodFix();
			String user = group.getCode();
			
			if (group.getCodFix() == null)
				group.setCodFix("");
			
			if ((group.getCodFix().equals("21B7")) || (group.getCodFix().equals("21B8")) || (group.getCodFix().equals("21B1")) || (group.getCodFix().equals("21B6 "))) {
				List<Ingresos> usersInDates_conc = currentList_ing.stream().filter(filtro1 -> (filtro1.getCode().equals(user)) && filtro1.getCodFix().equals(concept)).collect(Collectors.toList());
				System.out.println("Tamano usersIndates"+usersInDates_conc.size());
				
				if (group.getCode().equals("C796636")) {
					System.out.println("usersInDates_conc"+usersInDates_conc);
				}
			
			}
			
    	}
    	return currentList_ing;
    }*/
    
  
	public List<PayRecurrentOutput> duplicarregistrosfechas(List<PayRecurrentOutput> currentList)throws FileNotFoundException, IOException, ParseException{
    //public List<PayRecurrentOutput> duplicarregistrosfechas(String fileName)throws FileNotFoundException, IOException, ParseException{
	
		for(int i=0; i< currentList.size(); i ++){
			PayRecurrentOutput currentPay = currentList.get(i);
			Long pricipalsSalaries = currentPay.getAdd().stream().filter(filtro -> filtro.getConcept().equals("10S1") || filtro.getConcept().equals("10S2") || filtro.getConcept().equals("21B4")  ).count();
			//Long pricipalsSalaries = currentPay.getAdd().stream().filter(filtro -> filtro.getConcept().equals("10S1") || filtro.getConcept().equals("10S2") ).count();
			if(pricipalsSalaries != currentPay.getAdd().size()){
				List<AddtionalInformation> newPays = new ArrayList<AddtionalInformation>();
				int lastRead = 0;
				for(int j = 0; j< currentPay.getAdd().size(); j++){
					AddtionalInformation currentAdd = new AddtionalInformation(currentPay.getAdd().get(j));
					if(!(currentAdd.getConcept().equals("10S1") || currentAdd.getConcept().equals("10S2") || currentAdd.getConcept().equals("21B4"))){
					//if(!(currentAdd.getConcept().equals("10S1") || currentAdd.getConcept().equals("10S2"))){
						Date tempEndDate = null;
						for(int k = lastRead; k< j+1  ; k++){
						//for(int k = lastRead; k< currentPay.getAdd().size() ; k++){

							AddtionalInformation currentAddAux = new AddtionalInformation(currentPay.getAdd().get(k));
							if(currentAdd.getStartDate().before(currentAddAux.getEndDate()) && currentAdd.getStartDate().compareTo(currentAddAux.getStartDate()) != 0 ){
								// =
								if(tempEndDate == null){
									tempEndDate = currentAdd.getEndDate();
								}else{
									if(tempEndDate.before(currentAdd.getEndDate()))
										tempEndDate = currentAdd.getEndDate();
								}
								Calendar cal = Calendar.getInstance();
								cal.setTime(currentAdd.getStartDate());
								cal.add(Calendar.DATE, -1);
								Date dateBefore1Days = cal.getTime();

								currentAddAux.setEndDate(dateBefore1Days);

								newPays.add(currentAddAux);
								currentAddAux = new AddtionalInformation(currentPay.getAdd().get(k));
								currentAddAux.setStartDate(currentAdd.getStartDate());
								newPays.add(currentAddAux);

							}else{
								//aca entran
								if((currentAddAux.getConcept().equals("10S1") || currentAddAux.getConcept().equals("10S2") || currentAddAux.getConcept().equals("21B4"))){
								//if((currentAddAux.getConcept().equals("10S1") || currentAddAux.getConcept().equals("10S2"))){
									newPays.add(currentAddAux);
								}else{
									if(tempEndDate != null){
										currentAddAux = new AddtionalInformation(currentAdd);
										currentAddAux.setEndDate(tempEndDate);
										newPays.add(currentAddAux);
										Calendar cal = Calendar.getInstance();
										cal.setTime(tempEndDate);
										cal.add(Calendar.DATE, +1);
										Date dateAfter1Days = cal.getTime();
										currentAddAux = new AddtionalInformation(currentAdd);
										currentAddAux.setStartDate(dateAfter1Days);
										newPays.add(currentAddAux);
									}else
										newPays.add(currentAddAux);
								}
							}
							lastRead = k;
							
						}lastRead = (lastRead == 0)? 0:++lastRead;
						
					}
					
				}
				currentPay.setAdd(newPays);

			}
		}
		
		return currentList;
	}


	public List<PayRecurrentOutput> orderbyDataRange(List<PayRecurrentOutput> currentList)throws FileNotFoundException, IOException, ParseException{

		for(int i=0; i< currentList.size(); i ++){
			PayRecurrentOutput currentPay = currentList.get(i);
			Long pricipalsSalaries = currentPay.getAdd().stream().filter(filtro -> filtro.getConcept().equals("10S1") || filtro.getConcept().equals("10S2") || filtro.getConcept().equals("21B4")  ).count();
			if(pricipalsSalaries != currentPay.getAdd().size()){
				List<AddtionalInformation> newPays = new ArrayList<AddtionalInformation>();
				newPays = currentPay.getAdd();
				for(int j = 0; j< currentPay.getAdd().size(); j++){
					AddtionalInformation currentAdd = new AddtionalInformation(currentPay.getAdd().get(j));
					if(!(currentAdd.getConcept().equals("10S1") || currentAdd.getConcept().equals("10S2") || currentAdd.getConcept().equals("21B4"))){

						for(int k = 0; k< currentPay.getAdd().size(); k++){

							if(currentAdd.getEndDate().after(currentPay.getAdd().get(k).getStartDate()) && k>j){
								//change positions
								AddtionalInformation currentAddAuxJ = new AddtionalInformation(currentPay.getAdd().get(j));
								AddtionalInformation currentAddAuxK = new AddtionalInformation(currentPay.getAdd().get(k));
								newPays.set(k,currentAddAuxJ);
								newPays.set(j,currentAddAuxK);

							}

							//AddtionalInformation currentAdd = new AddtionalInformation(currentPay.getAdd().get(j));

						}



						}

				}
				currentPay.setAdd(newPays);

			}
		}

		return currentList;
	}

    
    public List<PayRecurrentOutput> read(String fileName)throws FileNotFoundException, IOException, ParseException{
        String cadena;
		FileReader f = new FileReader(fileName);
		BufferedReader b = new BufferedReader(f);
		List<PayRecurrentOutput> list = new ArrayList();
		PayRecurrentOutput currentPayRecurrentOutput = new PayRecurrentOutput();
		List<AddtionalInformation> addList = new ArrayList<AddtionalInformation>();
		AddtionalInformation add = new AddtionalInformation();
		currentPayRecurrentOutput.setAdd(addList);
		String lastUserRead = "";
		

		while((cadena = b.readLine())!=null) {			
			PayRecurrent payrec = new PayRecurrent();
			payrec.setCode(cadena.substring(0, 7));
			payrec.setDossier(cadena.substring(7, 10));
			payrec.setEmpresa(cadena.substring(10, 13));
			payrec.setDatestart(formatter.parse(cadena.substring(13, 23)));
			payrec.setDatefire(formatter.parse(cadena.substring(23, 33)));
			payrec.setConcep(cadena.substring(33, 36));
			payrec.setDatestartconc(formatter.parse(cadena.substring(36, 46)));
			payrec.setDateendconc(formatter.parse(cadena.substring(46, 56)));
			payrec.setValue(Double.valueOf(cadena.substring(56, 69)));
			
			if(!lastUserRead.equals("")){

				if(!lastUserRead.equals(payrec.getCode())){
					lastUserRead = payrec.getCode();

					currentPayRecurrentOutput.setAdd(addList);
					list.add(currentPayRecurrentOutput);
					currentPayRecurrentOutput = new PayRecurrentOutput();
					addList = new ArrayList<AddtionalInformation>();
				}

			}else lastUserRead = payrec.getCode();

				
			currentPayRecurrentOutput.setUser(payrec.getCode());
			
			//Stream<PayRecurrentOutput> varlist = list.stream();
		//	long onList = varlist.filter(filtro -> filtro.getUser().equals(payrec.getCode())).count();
		//	int tamlist = list.size();
		//	if(onList > 0){ 
			int bandera = 0;
			if (addList.size() == 1){

						//if (((payrec.getConcep().equals("100")) || (payrec.getConcep().equals("101")) || (payrec.getConcep().equals("102") || (payrec.getConcep().equals("510") || (payrec.getConcep().equals("518") || (payrec.getConcep().equals("206")) || (payrec.getConcep().equals("240"))))))) {
						if ((!(addList.get(addList.size()-1).getConcept().equals("100"))) && (!(addList.get(addList.size()-1).getConcept().equals("101"))) && (!(addList.get(addList.size()-1).getConcept().equals("102"))) && (!(addList.get(addList.size()-1).getConcept().equals("510"))) && (!(addList.get(addList.size()-1).getConcept().equals("518"))) && (!(addList.get(addList.size()-1).getConcept().equals("240")))){
							add.setStartDate(addList.get(addList.size()-1).getStartDate());
							/*if(currentCompensacionOutput.getEndDate().compareTo(formatter.parse("2999-12-31")) == 0){
								currentCompensacionOutput.setEndDate(formatter.parse("9999-12-31"));
							}*/
							add.setEndDate(payrec.getDateendconc());
							if ((add.getEndDate().compareTo(formatter.parse("2018-07-01")) > 0) && (addList.get(addList.size()-1).getEndDate().compareTo(formatter.parse("2999-12-31")) < 0)) {
								add.setEndDate(formatter.parse("2999-12-31"));
							}
							bandera = 1;
						}
						else {
							add.setStartDate(payrec.getDatestartconc());
							add.setEndDate(payrec.getDateendconc());
						if ((add.getEndDate().compareTo(formatter.parse("2018-07-01")) > 0) && (addList.get(addList.size()-1).getEndDate().compareTo(formatter.parse("2999-12-31")) < 0)) {
							add.setEndDate(formatter.parse("2999-12-31"));
							}
						}
							
				}

				
				if ((addList.size() > 0) && bandera == 0){
				//	PayRecurrentOutput regant = list.get(list.size()- 1);
					
				
						
						if ((payrec.getDateendconc().equals(addList.get(addList.size()-1).getEndDate())) && ((payrec.getConcep().equals("100")) || (payrec.getConcep().equals("101")) || (payrec.getConcep().equals("102") || (payrec.getConcep().equals("510") || (payrec.getConcep().equals("518") || (payrec.getConcep().equals("206")) || (payrec.getConcep().equals("240"))))))) {
							add.setStartDate(addList.get(addList.size()-1).getStartDate());
							/*if(currentCompensacionOutput.getEndDate().compareTo(formatter.parse("2999-12-31")) == 0){
								currentCompensacionOutput.setEndDate(formatter.parse("9999-12-31"));
							}*/
							add.setEndDate(payrec.getDateendconc());
							if ((add.getEndDate().compareTo(formatter.parse("2018-07-01")) > 0) && (addList.get(addList.size()-1).getEndDate().compareTo(formatter.parse("2999-12-31")) < 0)) {
								add.setEndDate(formatter.parse("2999-12-31"));
							}
						}
						else {
							add.setStartDate(payrec.getDatestartconc());
							add.setEndDate(payrec.getDateendconc());
						if ((add.getEndDate().compareTo(formatter.parse("2018-07-01")) > 0) && (addList.get(addList.size()-1).getEndDate().compareTo(formatter.parse("2999-12-31")) < 0)) {
							add.setEndDate(formatter.parse("2999-12-31"));
						}
						}
						
						
					

					
				}
				
				
			//}
			else{
				if ((!payrec.getConcep().equals("100")) && (!payrec.getConcep().equals("101")) && (!payrec.getConcep().equals("102") && (!payrec.getConcep().equals("510") && (!payrec.getConcep().equals("518") && (!payrec.getConcep().equals("206")) && (!payrec.getConcep().equals("240")))))) {
					add.setStartDate(payrec.getDatestartconc());
					add.setEndDate(payrec.getDateendconc());
					if ((add.getEndDate().compareTo(formatter.parse("2018-07-01")) > 0) && (add.getEndDate().compareTo(formatter.parse("2999-12-31")) < 0)) {
						add.setEndDate(formatter.parse("2999-12-31"));
					}
				}
				else {
					add.setStartDate(payrec.getDatestart());
					add.setEndDate(payrec.getDateendconc());
				if ((add.getEndDate().compareTo(formatter.parse("2018-07-01")) > 0) && (add.getEndDate().compareTo(formatter.parse("2999-12-31")) < 0)) {
					add.setEndDate(formatter.parse("2999-12-31"));
				}
				}
			}
				
				
				
				if ((payrec.getConcep() != null) && (!payrec.getConcep().equals("104"))){
					switch (payrec.getConcep()){
					case "100": add.setConcept("10S1"); break;
					case "101": add.setConcept("10S2"); break;
					case "102": add.setConcept("21B4"); break;
					case "105": add.setConcept("21B7"); break;
					case "106": add.setConcept("21B8"); break;
					case "110": add.setConcept("21B1"); break;
					case "240": add.setConcept("12S1"); break;
					case "510": add.setConcept("11S1"); break;
					case "518": add.setConcept("11S1"); break;
					case "729": add.setConcept("21B6"); break;
					default: add.setConcept("");  break;
					}
				}
				
				
				add.setPayvalue(payrec.getValue());
				
				
				
				
				add.setCurrency("COP");
				
				add.setFrecuency("01");
				add.setComilla("\"");
				
				if(currentPayRecurrentOutput.getUser() != null) {
					addList.add(add);
					add = new AddtionalInformation();
				}
				
			}
		
		if(currentPayRecurrentOutput.getUser() != null){
			
			//addList.add(add);
			currentPayRecurrentOutput.setAdd(addList);
			list.add(currentPayRecurrentOutput);
		}
        b.close();
		return list;
		
		}
		
    
    public List<PayRecurrentOutput> readOther(String fileName,List<PayRecurrentOutput> list)throws FileNotFoundException, IOException, ParseException{
        String cadena = "";
		FileReader f = new FileReader(fileName);
		BufferedReader b = new BufferedReader(f);
		int i = 0;
		boolean band = true;
		String user = null;
		String quince = null;
		while(cadena!=null) {
			if (cadena.equals("")){
				cadena = b.readLine();
			}
			
			band = false;
			user = cadena.substring(0,7);
			quince= cadena.substring(7,10);
			
			while(list.get(i).getUser().equals(user)){

				band = true;
				//list.get(i).getAdd().setFrecuency("02");
				int numAdds = list.get(i).getAdd().size();
				for(int a = 0; a< numAdds; a++){
					list.get(i).getAdd().get(a).setFrecuency("02");
				}
				
				
				if(i+1 != list.size()){
					if(list.get(i+1).getUser().equals(user))
						i+=1;
					else{
						break;

					}
				}else
					break;
					
				
			}
			if(band && !cadena.equals("")){
				i++;
				cadena = b.readLine();
			}
			else{
				//System.out.println(user + " " + list.get(i).getUser() + "1");
				boolean aux = false;
				int aux2 = 0;
				while(list.get(i).getUser().compareTo(user) < 0){
					if(i+1 != list.size()){
						aux2++;
						i++;
						//System.out.println(user + " " + list.get(i).getUser() + "2");
						aux = true;
					}
					else{
						break;
					}
				}
				if(!aux){
					if(!(user.compareTo(list.get(i).getUser()) >= 0)){
						//System.out.println(user + " " + list.get(i).getUser() + "3");
						cadena = b.readLine();
					}
				}	
			} 
		}

		return list;
	}


    public void write(String path,List<PayRecurrentOutput> ls)throws FileNotFoundException, IOException{
		FileWriter fichero = null;
	//	System.out.println("IN");

        fichero = new FileWriter(path);
        final PrintWriter pw  = new PrintWriter(fichero);
        
        ls.forEach(line -> pw.print(line.toString()) );
		System.out.println("FIN");

        fichero.close();
		
	}
     
}
