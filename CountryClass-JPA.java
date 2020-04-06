import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="countries")
public class CountryClass {
	@Id
	@Column(name="id")
	private int id;

	@Column(name="name")
	private String name;
	@Column(name="continent")
	private String continent;
	@Column(name="capital")
	private String capital;
	@Column(name="population")
	private int population;
	
	public CountryClass() {
		super();
	}
	
	
	public CountryClass(int id, String name, String continent, String capital, int population) {
		super();
		this.id = id;
		this.name = name;
		this.continent = continent;
		this.capital = capital;
		this.population = population;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public Integer getPopulation() {
		return population;
	}
	public void setPopulation(Integer population) {
		this.population = population;
	}
	
	
}
