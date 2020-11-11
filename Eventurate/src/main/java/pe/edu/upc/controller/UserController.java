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
import pe.edu.upc.entity.Role;
import pe.edu.upc.entity.User;
import pe.edu.upc.serviceinterface.ICityService;
import pe.edu.upc.serviceinterface.IRoleService;
import pe.edu.upc.serviceinterface.IUserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService uS;

	@Autowired
	private ICityService cS;

	@Autowired
	private IRoleService rS;

	@GetMapping("/newUserClient")
	public String newClient(Model model) {
		model.addAttribute("listaCiudades", cS.list());
		model.addAttribute("userClient", new User());
		return "user/userClient";
	}

	@GetMapping("/newUserCompany")
	public String newCompany(Model model) {
		model.addAttribute("listaCiudades", cS.list());
		model.addAttribute("userCompany", new User());
		return "user/userCompany";
	}

	@GetMapping("/listClient")
	public String listClient(Model model) {
		try {
			model.addAttribute("userClient", new User());
			model.addAttribute("listaClientes", uS.listClients());
		} catch (Exception e) {
			System.out.println("Error al listar clientes en el controller");
		}
		return "user/listUserClient";
	}

	@GetMapping("/listCompany")
	public String listCompany(Model model) {
		try {
			model.addAttribute("userCompany", new User());
			model.addAttribute("listaEmpresas", uS.listCompanies());
		} catch (Exception e) {
			System.out.println("Error al listar empresas en el controller");
		}
		return "user/listUserCompany";
	}

	@RequestMapping("/irupdateClient/{id}")
	public String irupdateClient(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<User> objUser = uS.searchId(id);
		if (objUser == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/users/listClient";
		} else {
			model.addAttribute("listaClientes", uS.listClients());
			model.addAttribute("userClient", objUser.get());
			return "user/uuserClient";
		}
	}

	@RequestMapping("/irupdateCompany/{id}")
	public String irupdateCompany(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<User> objUser = uS.searchId(id);
		if (objUser == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/users/listCompany";
		} else {
			model.addAttribute("listaEmpresas", uS.listCompanies());
			model.addAttribute("userCompany", objUser.get());
			return "user/uuserCompany";
		}
	}

	@RequestMapping("/findClient")
	public String findClient(Model model, @Validated User user) throws ParseException {
		List<User> listaClientes;
		model.addAttribute("userClient", new User());
		listaClientes = uS.findBydniClient(user.getDniClient());
		if (listaClientes.isEmpty()) {
			model.addAttribute("mensaje", "No se encontró");
		}
		model.addAttribute("listaClientes", listaClientes);
		return "user/listUserClient";
	}

	@RequestMapping("/findCompany")
	public String findCompany(Model model, @Validated User user) throws ParseException {
		List<User> listaEmpresas;
		model.addAttribute("userCompany", new User());
		listaEmpresas = uS.findBynameUserCompany(user.getNameUser());
		if (listaEmpresas.isEmpty()) {
			model.addAttribute("mensaje", "No se encontró");
		}
		model.addAttribute("listaEmpresas", listaEmpresas);
		return "user/listUserCompany";
	}

	@RequestMapping("/deleteClient/{id}")
	public String deleteUserClient(Model model, @PathVariable(value = "id") int id) {
		try {
			if (id > 0) {
				uS.delete(id);
			}
			model.addAttribute("userClient", new User());
			model.addAttribute("city", new City());
			model.addAttribute("mensaje", "Se eliminó correctamente!");
			model.addAttribute("listaClientes", uS.listClients());
		} catch (Exception e) {
			model.addAttribute("userClient", new User());
			model.addAttribute("city", new City());
			model.addAttribute("mensaje", "No se puede eliminar!!");
			model.addAttribute("listaClientes", uS.listClients());
		}
		return "user/listUserClient";
	}

	@RequestMapping("/deleteCompany/{id}")
	public String deleteUserCompany(Model model, @PathVariable(value = "id") int id) {
		try {
			if (id > 0) {
				uS.delete(id);
			}
			model.addAttribute("userCompany", new User());
			model.addAttribute("city", new City());
			model.addAttribute("mensaje", "Se eliminó correctamente!");
			model.addAttribute("listaEmpresas", uS.listCompanies());
		} catch (Exception e) {
			model.addAttribute("userCompany", new User());
			model.addAttribute("city", new City());
			model.addAttribute("mensaje", "No se puede eliminar!!");
			model.addAttribute("listaEmpresas", uS.listCompanies());
		}
		return "user/listUserCompany";
	}

	@PostMapping("/saveClient")
	public String saveClient(@Valid User user, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaCiudades", cS.list());
			return "user/userClient";
		} else {
			for (Role r : rS.list()) {
				if (r.getIdRole() == 2) {
					user.setRole(r);
				}
			}
			int rpta = uS.insert(user);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe el usuario con el mismo Dni y/o ruc y/o email");
				return "/user/userClient";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}

		}
		model.addAttribute("listaClientes", uS.listClients());
		return "redirect:/cards/list";

	}

	@PostMapping("/saveCompany")
	public String saveCompany(@Valid User user, BindingResult result, Model model, SessionStatus status)
			throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("listaCiudades", cS.list());
			return "user/userCompany";
		} else {

			for (Role r : rS.list()) {
				if (r.getIdRole() == 3) {
					user.setRole(r);
				}
			}			
			int rpta = uS.insert(user);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe el usuario con el mismo Dni y/o ruc y/o email");
				return "/user/userCompany";
			} else {
				model.addAttribute("mensaje", "Se guardó correctamente");
				status.setComplete();
			}

		}
		model.addAttribute("listaEmpresas", uS.listCompanies());
		return "redirect:/users/listCompany";

	}

}
