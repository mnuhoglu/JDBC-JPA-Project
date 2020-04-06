package hw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



public class JDBCManager {	

	public static ArrayList<CountryClass> readCountriesFromFile1(String filename)
	{
		ArrayList<CountryClass> countries 
		= new ArrayList<CountryClass>();
		try 
		{
			FileReader reader = new FileReader(filename);
			BufferedReader bfr = new BufferedReader(reader);
			while(true)
			{
				String line = bfr.readLine();
				if (line == null)
				{
					break;
				}
				//System.out.println(line);
				String[] arr = line.split(",");
				String name = arr[0];
				String continent = arr[1];
				String capital = arr[2];
				int population = Integer.parseInt(arr[3]);
				CountryClass s = new CountryClass(name, continent, capital, population);
				
				countries.add(s);
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
	
	public static void writeListToDB(ArrayList<CountryClass> allCountries) {
		
		try
		{
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "");		
			for (CountryClass c : allCountries)
			{
				PreparedStatement ps =  connection.prepareStatement("insert into countries (name,continent,capital,population) values (?,?,?,?) ");
				ps.setString(1, c.getName());
				ps.setString(2, c.getContinent());
				ps.setString(3, c.getCapital());
				ps.setInt(4, c.getPopulation());
				
				ps.execute();
			}
			
			
			System.out.println("Data inserted!!!");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static CountryClass getCountryByID (int countryID) {
		
		try
		{
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "");		
			PreparedStatement ps =  connection.prepareStatement("select name, continent, capital, population from countries where id = ?");
			ps.setInt(1, countryID);
			ResultSet rs =	ps.executeQuery();
			rs.next();
			
				String name = rs.getString("name");
				String cont  = rs.getString("continent");
				String capital  = rs.getString("capital");
				int pop = rs.getInt("population");
			CountryClass cs = new CountryClass(name,cont,capital,pop);
			return cs;

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
		
		
	}
	
	public static void deleteCountryByID (int countryID) {
		
		try
		{
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "");		
			PreparedStatement ps =  connection.prepareStatement("delete from countries where id = ? ");
			ps.setInt(1, countryID);
			ps.execute();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
				
	}
	
	public static void updateCountryPopulationByID (int countryID, int population) {
		
		try
		{
			Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cs310?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey", "root", "");		
			PreparedStatement ps =  connection.prepareStatement("update countries set population = ? where id = ?");
			ps.setInt(1, population);
			ps.setInt(2, countryID);
			ps.execute();
	
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	
		
		
	}
	
	

