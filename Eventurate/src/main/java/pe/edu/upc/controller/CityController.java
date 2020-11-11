package pe.edu.upc.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.entity.City;
import pe.edu.upc.serviceinterface.ICityService;
import pe.edu.upc.serviceinterface.ICountryService;

@Controller
@RequestMapping("/cities")
public class CityController {

	@Autowired
	private ICityService cS;
	@Autowired
	private ICountryService coS;
	
	@GetMapping("/new")
	public String newCity(Model model) {		
		model.addAttribute("listaPaises", coS.list());
		model.addAttribute("city",new City());
		return "city/city";	
	}
	
	@PostMapping("/save")
	public String saveCity(@Valid City city, BindingResult result, Model model, SessionStatus status)
		throws Exception{		
		if (result.hasErrors()) {
			model.addAttribute("listaPaises", coS.list());
			return "city/city";		
		}else {
			int rpta = cS.insert(city);
			if(rpta > 0) {
				model.addAttribute("listaPaises", coS.list());
				model.addAttribute("mensaje", "La ciudad ya existe!!");
				return "city/city";
			}else {
				model.addAttribute("listaCiudades",cS.list());
				return "redirect:/cities/list";
			}				
		}		
	}
	
	@GetMapping("/list")
	public String listCity(Model model) {		
		try {
			model.addAttribute("city",new City());
			model.addAttribute("listaCiudades",cS.list());
			model.addAttribute("listaPaises", coS.list());
		}catch (Exception e) {
			System.out.println("Error al listar las ciudades en el controller");
		}
		return "city/listCity";
	}
	
	@RequestMapping("/find")
	public String findCity(Model model,@Validated City city)throws ParseException{
		List<City> listaCiudades;
		listaCiudades =cS.findBynameCity(city.getNameCity());
		if(listaCiudades.isEmpty()) {
			model.addAttribute("listaPaises", coS.list());
			model.addAttribute("mensaje", "No se encontró");
		}
		model.addAttribute("listaPaises", coS.list());
		model.addAttribute("listaCiudades", listaCiudades);
		return "city/listCity";
	}
	
	@RequestMapping("/findCountry")
	public String findnameCountry(Model model,@Validated City city)throws ParseException{
		List<City> listaCiudades;
		listaCiudades =cS.findBycountry(city.getCountry().getNameCountry());
		if(listaCiudades.isEmpty()) {
			model.addAttribute("listaPaises", coS.list());
			model.addAttribute("mensaje", "No se encontró");
		}
		model.addAttribute("listaPaises", coS.list());
		model.addAttribute("listaCiudades", listaCiudades);
		return "city/listCity";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteCity(Model model, @PathVariable(value = "id") int id) {
		try {
			if(id > 0) {
				cS.delete(id);
			}
			model.addAttribute("city", new City());
			model.addAttribute("mensaje", "Se elimino correctamente");
			model.addAttribute("listaPaises", coS.list());
			model.addAttribute("listaCiudades", cS.list());
		} catch (Exception e) {
			model.addAttribute("city", new City());
			model.addAttribute("mensaje", "No se puede eliminar");
			model.addAttribute("listaPaises", coS.list());
			model.addAttribute("listaCiudades", cS.list());
		}
		return "city/listCity";
	}
	
	@RequestMapping("/irupdate/{id}")
	public String irUpdate(@PathVariable int id, Model model, RedirectAttributes objRedir){
		Optional<City>objCit= cS.searchId(id);
		if(objCit==null) {
			objRedir.addFlashAttribute("Mensaje","Ocurrio un error");
			return "redirect:/cities/list";
		}else {
			model.addAttribute("listaPaises", coS.list());
			model.addAttribute("city", objCit.get());
			return "city/ucity";
		}	
	}
	
	@PostMapping("/update")
	public String updateCard(@Valid City city, BindingResult result, Model model, SessionStatus status)
			throws Exception {

		if (result.hasErrors()) {
			model.addAttribute("listaPaises", coS.list());
			return "city/city";
		} else {
			cS.update(city);
			this.listCity(model);
			model.addAttribute("mensaje", "Se actualizó la tarjeta correctamente");
		}
		model.addAttribute("listaPaises", coS.list());
		model.addAttribute("listaCiudades", cS.list());
		return "redirect:/cities/list";
	}

}
