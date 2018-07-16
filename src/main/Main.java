package main;


import java.io.IOException;
import java.text.ParseException;
import java.util.List;




import file.File;
//import model.PayRecurrent;
import model.Ingresos;
import model.output.PayRecurrentOutput;

public class Main {

	public static void main(String arg []) throws IOException, ParseException{

		File file = new File() ;

		List<PayRecurrentOutput> ls = file.read("/Users/Jhon/Documents/AngelaBBVA/AngelaBBVA/3006/3006_UNION_SORT_040718.txt");
		//List<PayRecurrentOutput> ls2 = file.readOther("C:\\Users\\ce54191\\Documents\\PayRecurrent\\Entrada\\QUINCE.txt", file.duplicarregistrosfechas(ls));
		//List<Ingresos> ls3 = file.agruparregconcep(currentList_ing);
		List<PayRecurrentOutput> ls2 = file.readOther("/Users/Jhon/Documents/AngelaBBVA/AngelaBBVA/3006/QUINCE.txt", ls);
		for (int i = 0;  i < ls2.size(); i++) {
			ls2.get(i).sort();
		}

		List<PayRecurrentOutput> ls3 = file.orderbyDataRange(ls2);
		List<PayRecurrentOutput> l4 = file.duplicarregistrosfechas(ls3);


		file.write("/Users/Jhon/Documents/AngelaBBVA/AngelaBBVA/3006/Salida77.txt",l4);
		System.out.println("it ends");
	}
}
