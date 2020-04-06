package hw;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		
		ArrayList<CountryClass> countryarray = new ArrayList<CountryClass>();
		countryarray = JDBCManager.readCountriesFromFile1("world.txt");
		JDBCManager.writeListToDB(countryarray);
	}

}
