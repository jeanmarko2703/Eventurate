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

import pe.edu.upc.entity.Role;
import pe.edu.upc.serviceinterface.IRoleService;

@Controller
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private IRoleService rS;

	@GetMapping("/new")
	public String newRole(Model model) {
		model.addAttribute("role", new Role());
		return "role/role";

	}

	@PostMapping("/save")
	public String saveRole(@Valid Role role, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "role/role";
		} else {
			int rpta = rS.insert(role);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe el Rol");
				return "/role/role";
			} else {
				model.addAttribute("listaRoles", rS.list());
				model.addAttribute("mensaje", "Se guardó correctamente");
				//status.setComplete();
			}
		}
		//model.addAttribute("listaLaboratorios", lS.list());
		return "redirect:/roles/list";

	}

	@GetMapping("/list")
	public String listRoles(Model model) {
		try {
			model.addAttribute("role", new Role());
			model.addAttribute("listaRoles", rS.list());
		} catch (Exception e) {
			System.out.println("Error al listar en el controller");
		}
		return "role/listRoles";

	}

	@RequestMapping("/find")
	public String findRole(Model model, @Validated Role role) throws ParseException {
		List<Role> listaRoles;
		listaRoles = rS.findBynameRole(role.getNameRole());
		if (listaRoles.isEmpty()) {
			model.addAttribute("mensaje", "No se encontró");
		}
		model.addAttribute("listaRoles", listaRoles);
		return "role/listRoles";
	}

	@RequestMapping("/delete/{id}")
	public String deleteRole(Model model, @PathVariable(value = "id") int id) {
		try {
			if (id > 0) {
				rS.delete(id);
			}
			model.addAttribute("role", new Role());
			model.addAttribute("mensaje", "Se eliminó correctamente!");
			model.addAttribute("listaRoles", rS.list());
		} catch (Exception e) {
			model.addAttribute("role", new Role());
			model.addAttribute("mensaje", "No se puede eliminar!!");
			model.addAttribute("listaRoles", rS.list());

		}

		return "role/listRoles";

	}
	
	@RequestMapping("/irupdate/{id}")
	public String irupdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Role> objRole = rS.searchId(id);
		if (objRole == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/roles/list";
		} else {
			model.addAttribute("listaRoles", rS.list());
			model.addAttribute("role", objRole.get());
			return "role/urole";
		}
	}
	
	@PostMapping("/update")
	public String updateRole(@Valid Role role, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			return "role/role";
		} else {
			rS.insert(role);
			model.addAttribute("mensaje", "Se guardó correctamente");
		}
		model.addAttribute("listaRoles", rS.list());
		return "redirect:/roles/list";
	}
	
}
