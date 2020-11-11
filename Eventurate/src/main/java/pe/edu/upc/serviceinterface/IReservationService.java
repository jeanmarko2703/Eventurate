package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.Reservation;

public interface IReservationService {

	public void insert(Reservation reser);
	
	public List<Reservation> list();
	
	
}
