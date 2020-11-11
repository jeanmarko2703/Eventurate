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

import pe.edu.upc.entity.Category;
import pe.edu.upc.serviceinterface.ICategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private ICategoryService cS;

	@GetMapping("/new")
	public String newCategory(Model model) {
		model.addAttribute("category", new Category());
		return "category/category";
	}

	@PostMapping("/save")
	public String saveCategory(@Valid Category category, BindingResult result, Model model, SessionStatus status)
			throws Exception {

		if (result.hasErrors()) {
			return "category/category";
		} else {
			int rpta = cS.insert(category);
			if (rpta > 0) {
				model.addAttribute("mensaje", "La categoría ya existe!!");
				return "category/category";
			} else {
				model.addAttribute("listaCategorias", cS.list());
				status.setComplete();
			}
		}
		model.addAttribute("listaCategorias", cS.list());
		return "redirect:/categories/list";
	}

	@GetMapping("/list")
	public String listCategories(Model model) {
		try {
			model.addAttribute("category", new Category());
			model.addAttribute("listaCategorias", cS.list());
		} catch (Exception e) {
			System.out.println("Error al listar en el controller");
		}
		return "category/listCategory";
	}
	
	@RequestMapping("/find")
	public String findCategory(Model model, @Validated Category category) throws ParseException {
		
		List<Category> listaCategorias;
		listaCategorias = cS.findBynameCategory(category.getNameCategory());
		if(listaCategorias.isEmpty()) {
			model.addAttribute("mensaje","No se encontro");
		} 
		model.addAttribute("listaCategorias", listaCategorias);
		return "category/listCategory";	
	} 

	@RequestMapping("/delete/{id}")
	public String deleteCategory(Model model, @PathVariable(value = "id") int id) {

		try {
			if (id > 0) {
				cS.delete(id);
			}
			model.addAttribute("category", new Category());
			model.addAttribute("mensaje", "Se eliminó correctamente!");
			model.addAttribute("listaCategorias", cS.list());
		} catch (Exception e) {

			model.addAttribute("category", new Category());
			model.addAttribute("mensaje", "No se pudo eliminar!");
			model.addAttribute("listaCategorias", cS.list());

		}
		return "category/listCategory";
	}
	
	@RequestMapping("/irupdate/{id}")
	public String irUpdate(@PathVariable int id, Model model, RedirectAttributes objRedir) {
		
		Optional<Category> objCat = cS.searchId(id);
		
		if (objCat == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrió un error");
			return "redirect:/categories/list";
		} else {
			model.addAttribute("category", objCat.get());
			return "category/ucategory";
		}
	}
	
	@PostMapping("/update")
	public String updateCategory(@Valid Category category, BindingResult result, Model model, SessionStatus status)
			throws Exception {
	
		if (result.hasErrors()) {
			return "category/category";
		} else {
			cS.insert(category);
			this.listCategories(model);
			model.addAttribute("mensaje", "Se actualizó una categoría correctamente");
		}
		
		model.addAttribute("listaCategorias", cS.list());
		return "category/listCategory";
	}

}
