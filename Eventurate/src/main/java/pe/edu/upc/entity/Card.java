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
@Table(name = "cards")
public class Card {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCard;

	private String cardNumber;

	@Column(name = "cardHolder", length = 60, nullable = false)
	private String cardHolder;

	@Column(name = "cardType", length = 15, nullable = false)
	private String cardType;

	@ManyToOne
	@JoinColumn(name = "idbanking_entity")
	private Banking_entity banking_entity;

	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Card(int idCard, String cardNumber, String cardHolder, String cardType, Banking_entity banking_entity) {
		super();
		this.idCard = idCard;
		this.cardNumber = cardNumber;
		this.cardHolder = cardHolder;
		this.cardType = cardType;
		this.banking_entity = banking_entity;
	}

	public int getIdCard() {
		return idCard;
	}

	public void setIdCard(int idCard) {
		this.idCard = idCard;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardHolder() {
		return cardHolder;
	}

	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Banking_entity getBanking_entity() {
		return banking_entity;
	}

	public void setBanking_entity(Banking_entity banking_entity) {
		this.banking_entity = banking_entity;
	}

	
}
