package pe.edu.upc.entity;

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
import javax.validation.constraints.Future;


import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "events")
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEvent;
	

	@Column(name = "nameEvent", length = 45, nullable = false)
	private String nameEvent;
	
	
	private int capacityEvent;
	
	private int availableEvent;
	

	private Double priceEvent;
	
	@Future(message = "La fecha debe ser futura")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="dateEvent")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateEvent;
	
	@ManyToOne
	@JoinColumn(name = "idCategory")
	private Category category;

	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Event(int idEvent, String nameEvent, int capacityEvent, int availableEvent,
			Double priceEvent,
			Date dateEvent,
			Category category) {
		super();
		this.idEvent = idEvent;
		this.nameEvent = nameEvent;
		this.capacityEvent = capacityEvent;
		this.availableEvent = availableEvent;
		this.priceEvent = priceEvent;
		this.dateEvent = dateEvent;
		this.category = category;
	}

	public int getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(int idEvent) {
		this.idEvent = idEvent;
	}

	public String getNameEvent() {
		return nameEvent;
	}

	public void setNameEvent(String nameEvent) {
		this.nameEvent = nameEvent;
	}

	public int getCapacityEvent() {
		return capacityEvent;
	}

	public void setCapacityEvent(int capacityEvent) {
		this.capacityEvent = capacityEvent;
	}

	public int getAvailableEvent() {
		return availableEvent;
	}

	public void setAvailableEvent(int availableEvent) {
		this.availableEvent = availableEvent;
	}

	public Double getPriceEvent() {
		return priceEvent;
	}

	public void setPriceEvent(Double priceEvent) {
		this.priceEvent = priceEvent;
	}

	public Date getDateEvent() {
		return dateEvent;
	}

	public void setDateEvent(Date dateEvent) {
		this.dateEvent = dateEvent;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	
}
