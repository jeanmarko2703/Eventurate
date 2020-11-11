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

import pe.edu.upc.entity.Country;
import pe.edu.upc.serviceinterface.ICountryService;
@Controller
@RequestMapping("/countries")
public class CountryController {

	@Autowired
	private ICountryService cS;
	
	@GetMapping("/new")
	public String newCountry(Model model){
		model.addAttribute("country", new Country());
		return "country/country";
	}
	
	@PostMapping("/save")
	public String saveCountry(@Valid Country country, BindingResult result, Model model,
			SessionStatus status) throws Exception{
		
		if(result.hasErrors()) {
			return "country/country";
		}else {
			int rpta = cS.insert(country);
			if (rpta > 0) {
				model.addAttribute("mensaje", "La tarjeta ya existe!!");
				return "country/country";
			} else {
				model.addAttribute("listaPaises", cS.list());
				return "country/listCountry";
			}
		}		
	}
	
	@GetMapping("/list")
	public String listCountries(Model model) {
		try {
			model.addAttribute("country", new Country());
			model.addAttribute("listaPaises", cS.list());
		}catch(Exception e) {
			System.out.println("Error al listar paises");
		}
		return "country/listCountry";
	}
	
	@RequestMapping("/find")
	public String findCountry(Model model,@Validated Country country)throws ParseException{
		List<Country> listaPaises;
		listaPaises =cS.findBynameCountry(country.getNameCountry());
		if(listaPaises.isEmpty()) {
			model.addAttribute("mensaje", "No se encontró");
		}
		model.addAttribute("listaPaises", listaPaises);
		return "country/listCountry";
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteCountry(Model model, @PathVariable(value = "id") int id) {
		try {
			if(id > 0) {
				cS.delete(id);
			}
			model.addAttribute("country", new Country());
			model.addAttribute("mensaje", "Se elimino correctamente");
			model.addAttribute("listaPaises", cS.list());
		} catch (Exception e) {
			model.addAttribute("country", new Country());
			model.addAttribute("mensaje", "No se puede eliminar este país");
			model.addAttribute("listaPaises", cS.list());
		}
		return "country/listCountry";
	}
	
	@RequestMapping("/irupdate/{id}")
	public String irUpdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Country> objCou = cS.searchId(id);
		if(objCou==null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/country/list";
		}else {
			model.addAttribute("listaPaises", cS.list());
			model.addAttribute("country", objCou.get());
			return "country/ucountry";
		}
	}
	
	@PostMapping("/update")
	public String updateCard(@Valid Country country, BindingResult result, Model model, SessionStatus status)
			throws Exception {

		if (result.hasErrors()) {
			return "country/country";
		} else {
			cS.insert(country);
			this.listCountries(model);
			model.addAttribute("mensaje", "Se actualizó el país correctamente");
		}
		model.addAttribute("listaPaises", cS.list());
		return "country/listCountry";
	}
	
	
}
