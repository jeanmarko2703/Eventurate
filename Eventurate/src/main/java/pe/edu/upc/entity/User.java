package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;
	
	@Column(name="nameUser", nullable=false, length=70)
	private String nameUser;

	@Column(name="passwordUser", nullable=false, length=200)
	private String passwordUser;
	
	@Column(name="phoneUser", nullable=false, length=9)
	private String phoneUser;
	
	@Column(name="emailUser", nullable=false, length=70)
	private String emailUser;
	
	private Boolean statusUser;
	
	@Column(name="dniClient", length=8)
	private String dniClient;
	
	@Past(message = "La fecha debe ser pasada")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "birthDateClient")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthDateClient;
	
	@Column(name="rucCompany", length=11)
	private String rucCompany;
	
	@Column(name="domainCompany")
	private String domainCompany;

	@ManyToOne
	@JoinColumn(name="idRole",nullable=false)
	private Role role;
	
	@ManyToOne
	@JoinColumn(name="idCity",nullable=false)
	private City city;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int idUser, String nameUser, String passwordUser, String phoneUser, String emailUser,
			Boolean statusUser, String dniClient, Date birthDateClient, String rucCompany, String domainCompany,
			Role role ,City city) {
		super();
		this.idUser = idUser;
		this.nameUser = nameUser;
		this.passwordUser = passwordUser;
		this.phoneUser = phoneUser;
		this.emailUser = emailUser;
		this.statusUser = statusUser;
		this.dniClient = dniClient;
		this.birthDateClient = birthDateClient;
		this.rucCompany = rucCompany;
		this.domainCompany = domainCompany;
		this.role = role;
		this.city=city;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getPasswordUser() {
		return passwordUser;
	}

	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}

	public String getPhoneUser() {
		return phoneUser;
	}

	public void setPhoneUser(String phoneUser) {
		this.phoneUser = phoneUser;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public Boolean getStatusUser() {
		return statusUser;
	}

	public void setStatusUser(Boolean statusUser) {
		this.statusUser = statusUser;
	}

	public String getDniClient() {
		return dniClient;
	}

	public void setDniClient(String dniClient) {
		this.dniClient = dniClient;
	}

	public Date getBirthDateClient() {
		return birthDateClient;
	}

	public void setBirthDateClient(Date birthDateClient) {
		this.birthDateClient = birthDateClient;
	}

	public String getRucCompany() {
		return rucCompany;
	}

	public void setRucCompany(String rucCompany) {
		this.rucCompany = rucCompany;
	}

	public String getDomainCompany() {
		return domainCompany;
	}

	public void setDomainCompany(String domainCompany) {
		this.domainCompany = domainCompany;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
