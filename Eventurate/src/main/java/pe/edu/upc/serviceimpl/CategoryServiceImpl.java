package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Category;
import pe.edu.upc.repository.CategoryRepository;
import pe.edu.upc.serviceinterface.ICategoryService;

@Service
public class CategoryServiceImpl implements ICategoryService {

	@Autowired
	private CategoryRepository cR;

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}

	@Override
	public void delete(int idCategory) {
		
		cR.deleteById(idCategory);
		
	}

	@Override
	public Optional<Category> searchId(int idCategory) {
		// TODO Auto-generated method stub
		return cR.findById(idCategory);
	}

	@Override
	public int insert(Category cat) {
		
		int rpta = cR.searchCategory(cat.getNameCategory());
		if (rpta == 0) {
			cR.save(cat);
		}
		return rpta;
	}

	@Override
	public List<Category> findBynameCategory(String nameCategory) {
		return cR.findBynameCategory(nameCategory);
	}


}