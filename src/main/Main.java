package main;


import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;




import file.File;
//import model.PayRecurrent;
import model.Ingresos;
import model.output.PayRecurrentOutput;

public class Main {
	
	public static void main(String arg []) throws IOException, ParseException{
	
		File file = new File() ;	
		
		List<PayRecurrentOutput> ls = file.read("C:\\Users\\ce54191\\Documents\\PayRecurrent\\Entrada\\3006_UNION_SORT_120718.txt");
		//List<PayRecurrentOutput> ls2 = file.readOther("C:\\Users\\ce54191\\Documents\\PayRecurrent\\Entrada\\QUINCE.txt", file.duplicarregistrosfechas(ls));
		//List<Ingresos> ls3 = file.agruparregconcep(currentList_ing);
		List<PayRecurrentOutput> ls2 = file.readOther("C:\\Users\\ce54191\\Documents\\PayRecurrent\\Entrada\\QUINCE.txt", ls);
		//List<PayRecurrentOutput> ls3 = file.duplicarregistrosfechas(ls2);
		for (int i = 0;  i < ls2.size(); i++) {
			 ls2.get(i).sort();
		}
		
		List<PayRecurrentOutput> ls3 = file.duplicarregistrosfechas(ls2);
		file.write("C:\\Users\\ce54191\\Documents\\PayRecurrent\\Salida\\3006_Salida_Codprueba1.txt",ls3);
		System.out.println("it ends");
	}
}
