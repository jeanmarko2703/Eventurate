package pe.edu.upc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="reservations")
public class Reservation {

	@Id
	@GeneratedValue
	private int idReservation;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "dateReservation")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateReservation;
	
	private int tickets;
	
	@ManyToOne
	@JoinColumn(name="idEvent")
	private Event event;
	
	@ManyToOne
	@JoinColumn(name="idCard")
	private Card card;

	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(int idReservation, Date dateReservation, int tickets, Event event, Card card) {
		super();
		this.idReservation = idReservation;
		this.dateReservation = dateReservation;
		this.tickets = tickets;
		this.event = event;
		this.card = card;
	}

	public int getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	public Date getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Date dateReservation) {
		this.dateReservation
		= dateReservation;
	}

	public int getTickets() {
		return tickets;
	}

	public void setTickets(int tickets) {
		this.tickets = tickets;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}
	
	
	
}
