import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {

		
		ArrayList<CountryClass> countryarray = new ArrayList<CountryClass>();
		countryarray = MuratJPAManager.readCountriesFromFile1("world.txt");
		MuratJPAManager.writeListToDB(countryarray);
	//	System.out.println(MuratJPAManager.getCountryByID(3));
	//	MuratJPAManager.updateCountryByID(5, 500);
		//MuratJPAManager.deleteCountryByID(5);
	}

}
