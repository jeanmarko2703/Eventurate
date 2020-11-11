package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
	
	@Query("select count(c.nameCategory) from Category c where c.nameCategory=:nameCategory")
	public int searchCategory(@Param("nameCategory") String nombre);
	
	@Query("From Category c Where c.nameCategory like %:name%")
	List<Category> findBynameCategory(@Param("name")String nameCategory);

}