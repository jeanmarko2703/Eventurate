package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="countries")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idCountry;
	
	@Column(name="nameCountry",length=15,nullable=false)
	private String nameCountry;
	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Country(int idCountry, String nameCountry) {
		super();
		this.idCountry = idCountry;
		this.nameCountry = nameCountry;
	}
	public int getIdCountry() {
		return idCountry;
	}
	public void setIdCountry(int idCountry) {
		this.idCountry = idCountry;
	}
	public String getNameCountry() {
		return nameCountry;
	}
	public void setNameCountry(String nameCountry) {
		this.nameCountry = nameCountry;
	}	
}
