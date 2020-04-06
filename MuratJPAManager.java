import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;




public class MuratJPAManager {
	
	public static ArrayList<CountryClass> readCountriesFromFile1(String filename)
	{
		ArrayList<CountryClass> countries 
		= new ArrayList<CountryClass>();
		try 
		{
			FileReader reader = new FileReader(filename);
			BufferedReader bfr = new BufferedReader(reader);
			int id=1;
			while(true)
			{
				String line = bfr.readLine();
				if (line == null)
				{
					break;
				}
				String[] arr = line.split(",");
				String name = arr[0];
				String continent = arr[1];
				String capital = arr[2];
				int population = Integer.parseInt(arr[3]);
				CountryClass s = new CountryClass(id,name, continent, capital, population);
				
				countries.add(s);
				id++;
			}
			reader.close();
		
		}
		catch (FileNotFoundException e) {
			System.out.println("no file");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println("no have rights to read that file");
			e.printStackTrace();
		}
		return countries;
	}	

	public static void writeListToDB(ArrayList<CountryClass> countries) 
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs310");
		EntityManager entityManager= emf.createEntityManager();
		entityManager.getTransaction().begin();
		for(CountryClass s: countries)
		{
			CountryClass mycountry = new CountryClass(s.getId(),s.getName(),s.getContinent(),s.getCapital(),s.getPopulation());
			entityManager.persist(mycountry);	
		}
		entityManager.getTransaction().commit();
		System.out.println("Table Created!!");
	}
	
	public static CountryClass getCountryByID (int countryID) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs310");
		EntityManager entityManager= emf.createEntityManager();
		
		CountryClass countryy = entityManager.find(CountryClass.class, countryID);
		
		return countryy;
}
	
	public static void deleteCountryByID (int countryID) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs310");
		EntityManager entityManager =emf.createEntityManager();
		
		CountryClass DeletedCountry = entityManager.find(CountryClass.class, countryID);
		
		entityManager.getTransaction().begin();
		entityManager.remove(DeletedCountry);
		entityManager.getTransaction().commit();
	}
	
	
	
	public static void updateCountryByID (int countryID, int population) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("cs310");
		EntityManager entityManager =emf.createEntityManager();
		
		CountryClass toBeUpdated = entityManager.find(CountryClass.class, countryID);
		entityManager.getTransaction().begin();
		toBeUpdated.setPopulation(population);
		entityManager.getTransaction().commit();

	}
}

