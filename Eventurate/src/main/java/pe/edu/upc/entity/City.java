package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="cities")
public class City {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int idCity;
	
	@Column(name="nameCity",length=65,nullable=false)
	private String nameCity;
	
	@ManyToOne
	@JoinColumn(name="idCountry")
	 private Country country;

	public City() {
		super();
		// TODO Auto-generated constructor stub
	}

	public City(int idCity, String nameCity, Country country) {
		super();
		this.idCity = idCity;
		this.nameCity = nameCity;
		this.country = country;
	}

	public int getIdCity() {
		return idCity;
	}

	public void setIdCity(int idCity) {
		this.idCity = idCity;
	}

	public String getNameCity() {
		return nameCity;
	}

	public void setNameCity(String nameCity) {
		this.nameCity = nameCity;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}	
}

