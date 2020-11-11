package pe.edu.upc.controller;

import java.sql.Timestamp;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entity.Reservation;
import pe.edu.upc.serviceinterface.ICardService;
import pe.edu.upc.serviceinterface.IEventService;
import pe.edu.upc.serviceinterface.IReservationService;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

	@Autowired
	private IReservationService rS;

	@Autowired
	private IEventService eS;

	@Autowired
	private ICardService cS;

	@GetMapping("/new")
	public String newReservation(Model model) {
		model.addAttribute("listaEventos", eS.list());
		model.addAttribute("listaTarjetas", cS.list());
		model.addAttribute("reservation", new Reservation());
		return "reservation/reservation";
	}

	@PostMapping("/save")
	public String saveReservation(@Valid Reservation reservation, BindingResult result, Model model,
			SessionStatus status) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaEventos", eS.list());
			model.addAttribute("listaTarjetas", cS.list());
			return "reservation/reservation";
		} else {
			if ((reservation.getEvent().getAvailableEvent() != 0) && (reservation.getEvent().getAvailableEvent() >= reservation.getTickets())) {
				
				reservation.getEvent().setAvailableEvent(reservation.getEvent().getAvailableEvent() - reservation.getTickets());
				
				Date date= new Date();
				  
				 long time = date.getTime();				    
				 
				 Timestamp ts = new Timestamp(time);
				
				 reservation.setDateReservation(ts);
				rS.insert(reservation);
			}else {
				model.addAttribute("mensaje", "Tickets insuficientes");
				return "reservation/reservation";
			}
			
		}
		model.addAttribute("listaReservas", rS.list());
		return "redirect:/reservations/list";
	}
	
	@GetMapping("/list")
	public String listCity(Model model) {		
		try {
			model.addAttribute("listaReservas",rS.list());
		}catch (Exception e) {
			System.out.println("Error al listar las reservas en el controller");
		}
		return "reservation/listReservation";
	}
}
