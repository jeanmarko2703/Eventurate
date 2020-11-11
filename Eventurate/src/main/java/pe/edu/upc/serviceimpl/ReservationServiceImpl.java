package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Reservation;
import pe.edu.upc.repository.ReservationRepository;
import pe.edu.upc.serviceinterface.IReservationService;

@Service
public class ReservationServiceImpl implements IReservationService {

	@Autowired
	private ReservationRepository rR;

	@Override
	public void insert(Reservation reser) {
		rR.save(reser);
	}

	@Override
	public List<Reservation> list() {
		// TODO Auto-generated method stub
		return rR.findAll();
	}
	
}
