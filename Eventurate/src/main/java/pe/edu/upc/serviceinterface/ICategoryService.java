package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Category;

public interface ICategoryService {
	
	public int insert (Category cat);
	
	public List<Category> list();
	
	List<Category> findBynameCategory(String nameCategory);
	
	public void delete(int idCategory);
	
	Optional<Category> searchId(int idCategory);	

}
