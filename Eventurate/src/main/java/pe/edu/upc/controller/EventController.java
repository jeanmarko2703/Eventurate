package pe.edu.upc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.sun.el.parser.ParseException;

import pe.edu.upc.entity.Event;
import pe.edu.upc.serviceinterface.ICategoryService;
import pe.edu.upc.serviceinterface.IEventService;

@Controller
@RequestMapping("/events")
public class EventController {

	@Autowired
	private IEventService eS;

	@Autowired
	private ICategoryService cS;

	@GetMapping("/new")
	public String newEvent(Model model) {

		model.addAttribute("listaCategorias", cS.list());
		model.addAttribute("event", new Event());
		return "event/event";
	}

	@PostMapping("/save")
	public String saveEvent(@Valid Event event, BindingResult result, Model model, SessionStatus status)
			throws Exception {

		if (result.hasErrors()) {
			model.addAttribute("listaCategorias", cS.list());
			return "event/event";
		} else {
			event.setAvailableEvent(event.getCapacityEvent());
			eS.insert(event);
		}
		model.addAttribute("listaEventos", eS.list());
		return "redirect:/events/listEvCompany";
	}

	@GetMapping("/listEvCompany")
	public String listEventEmpresa(Model model) {
		try {
			model.addAttribute("event", new Event());
			model.addAttribute("listaCategorias", cS.list());
			model.addAttribute("listaEventos", eS.list());
		} catch (Exception e) {
			System.out.println("Error al listar eventos de empresa");
		}
		return "event/listEventCompany";
	}

	@GetMapping("/listEvClient")
	public String listEventCliente(Model model) {
		try {
			model.addAttribute("event", new Event());
			model.addAttribute("listaEventos", eS.list());
			model.addAttribute("listaCategorias", cS.list());
		} catch (Exception e) {
			System.out.println("Error al listar eventos de clientes");
		}
		return "event/listEventClient";
	}

	@GetMapping("/list")
	public String listEventAdministrador(Model model) {
		try {
			model.addAttribute("event", new Event());
			model.addAttribute("listaCategorias", cS.list());
			model.addAttribute("listaEventos", eS.list());
		} catch (Exception e) {
			System.out.println("Error al listar eventos de administrador");
		}
		return "event/listEvent";
	}
	
	@RequestMapping("/find")
	public String findEvent (Model model, @Validated Event event) throws ParseException{
		List<Event> listaEventos;
		listaEventos = eS.findBynameEvent(event.getNameEvent());
		if(listaEventos.isEmpty()) {
			model.addAttribute("listaCategorias", cS.list());
			model.addAttribute("mensaje", "No se encontro");
	}
		model.addAttribute("listaCategorias", cS.list());
		model.addAttribute("listaEventos", listaEventos);
		return "event/listEvent";
	}

	@RequestMapping("/findEvClient")
	public String findEventClient(Model model,@Validated Event event)throws ParseException{
		List<Event> listaEventos;
		listaEventos =eS.findBynameEvent(event.getNameEvent());
		if(listaEventos.isEmpty()) {
			model.addAttribute("listaCategorias", cS.list());
			model.addAttribute("mensaje", "No se encontró");
		}
		model.addAttribute("listaCategorias", cS.list());
		model.addAttribute("listaEventos", listaEventos);
		return "event/listEventClient";
	}
	
	@RequestMapping("/findEvCompany")
	public String findEventCompany(Model model,@Validated Event event)throws ParseException{
		List<Event> listaEventos;
		listaEventos =eS.findBynameEvent(event.getNameEvent());
		if(listaEventos.isEmpty()) {
			model.addAttribute("listaCategorias", cS.list());
			model.addAttribute("mensaje", "No se encontró");
		}
		model.addAttribute("listaCategorias", cS.list());
		model.addAttribute("listaEventos", listaEventos);
		return "event/listEventCompany";
	}
	
	@RequestMapping("/findCategoryClient")
	public String findNameCategoryClient(Model model,@Validated Event event)throws ParseException{
		List<Event> listaEventos;
		listaEventos =eS.findBycategory(event.getCategory().getNameCategory());
		if(listaEventos.isEmpty()) {
			model.addAttribute("listaCategorias", cS.list());
			model.addAttribute("mensaje", "No se encontró");
		}
		model.addAttribute("listaCategorias", cS.list());
		model.addAttribute("listaEventos", listaEventos);
		return "event/listEventClient";
	}
	
	@RequestMapping("/findCategoryCompany")
	public String findNameCategoryCompany(Model model,@Validated Event event)throws ParseException{
		List<Event> listaEventos;
		listaEventos =eS.findBycategory(event.getCategory().getNameCategory());
		if(listaEventos.isEmpty()) {
			model.addAttribute("listaCategorias", cS.list());
			model.addAttribute("mensaje", "No se encontró");
		}
		model.addAttribute("listaCategorias", cS.list());
		model.addAttribute("listaEventos", listaEventos);
		return "event/listEventCompany";
	}
	
	@RequestMapping("/findCategory")
	public String findNameCategory(Model model,@Validated Event event)throws ParseException{
		List<Event> listaEventos;
		listaEventos =eS.findBycategory(event.getCategory().getNameCategory());
		if(listaEventos.isEmpty()) {
			model.addAttribute("listaCategorias", cS.list());
			model.addAttribute("mensaje", "No se encontró");
		}
		model.addAttribute("listaCategorias", cS.list());
		model.addAttribute("listaEventos", listaEventos);
		return "event/listEvent";
	}
	
}
