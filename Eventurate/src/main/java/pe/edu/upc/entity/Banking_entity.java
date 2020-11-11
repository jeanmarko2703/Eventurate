package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="banking_entities")
public class Banking_entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBanking_entity;
	
	@Column(name = "nameBanking_entity", nullable = false)
	private String nameBanking_entity;

	public Banking_entity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Banking_entity(int idBanking_entity, String nameBanking_entity) {
		super();
		this.idBanking_entity = idBanking_entity;
		this.nameBanking_entity = nameBanking_entity;
	}

	public int getIdBanking_entity() {
		return idBanking_entity;
	}

	public void setIdBanking_entity(int idBanking_entity) {
		this.idBanking_entity = idBanking_entity;
	}

	public String getNameBanking_entity() {
		return nameBanking_entity;
	}

	public void setNameBanking_entity(String nameBanking_entity) {
		this.nameBanking_entity = nameBanking_entity;
	}

}
