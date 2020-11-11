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

import pe.edu.upc.entity.Banking_entity;
import pe.edu.upc.serviceinterface.IBanking_entityService;

@Controller
@RequestMapping("/banking_entities")
public class Banking_entityController { 

	@Autowired
	private IBanking_entityService bS;

	@GetMapping("/new")
	public String newBanking_entity(Model model) {

		model.addAttribute("banking_entity", new Banking_entity());
		return "banking_entity/banking_entity";
	}

	@PostMapping("/save")
	public String saveBanking_entity(@Valid Banking_entity banking_entity, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "banking_entity/banking_entity";
		} else {
			int rpta = bS.insert(banking_entity);
			if (rpta > 0) {
				model.addAttribute("mensaje", "La entidad bancaria ya existe!!");
				return "banking_entity/banking_entity";
			} else {
				model.addAttribute("listaEntidadesBancarias", bS.list());
				return "redirect:/banking_entities/list";

			}

		}

	}

	@GetMapping("/list")
	public String listBanking_entity(Model model) {

		try {
			model.addAttribute("banking_entity", new Banking_entity());
			model.addAttribute("listaEntidadesBancarias", bS.list());
		} catch (Exception e) {
			System.out.println("Error al listar en el controller");
		}
		return "/banking_entity/listBanking_entity";
	}
	
	@RequestMapping("/find")
	public String findBanking_entity(Model model, @Validated Banking_entity banking_entity) throws ParseException {
		
		List<Banking_entity> listaEntidadesBancarias;
		listaEntidadesBancarias = bS.findBynameBanking_entity(banking_entity.getNameBanking_entity());
		if(listaEntidadesBancarias.isEmpty()) {
			model.addAttribute("mensaje","No se encontro");
		} 
		model.addAttribute("listaEntidadesBancarias", listaEntidadesBancarias);
		return "banking_entity/listBanking_entity";	
	} 
	 
	@RequestMapping("/delete/{id}")
	public String deleteBanking_entity(Model model, @PathVariable(value = "id") int id) {
		try {
			if(id > 0) {
				bS.delete(id);
			}
			model.addAttribute("banking_entity", new Banking_entity());
			model.addAttribute("mensaje", "Se elimino correctamente");
			model.addAttribute("listaEntidadesBancarias", bS.list());
		} catch (Exception e) {
			model.addAttribute("banking_entity", new Banking_entity());
			model.addAttribute("mensaje", "Error al eliminar en el controller");
			model.addAttribute("listaEntidadesBancarias", bS.list());
		}
		return "banking_entity/listBanking_entity";
	}
	
	@RequestMapping("/irupdate/{id}")
	public String irUpdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Banking_entity> objBan = bS.searchId(id);
		if(objBan==null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/banking_entities/list";
		}else {
			model.addAttribute("banking_entity", objBan.get());
			return "banking_entity/ubanking_entity";
		}
	}
	
	@PostMapping("/update")
	public String updateBanking_entity(@Valid Banking_entity banking_entity, BindingResult result, Model model, SessionStatus status)
			throws Exception {

		if (result.hasErrors()) {
			return "banking_entity/banking_entity";
		} else {
			bS.insert(banking_entity);
			this.listBanking_entity(model);
			model.addAttribute("mensaje","Se actualizo correctamente");
		}

		model.addAttribute("listaEntidadesBancarias", bS.list());
		return "banking_entity/listBanking_entity";
	}

}
