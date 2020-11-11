package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer>{
	
	@Query("From Event e Where e.nameEvent like %:name%")
	List<Event> findBynameEvent(@Param("name")String nameEvent);
	
	@Query("From Event e Where e.category.nameCategory like %:name%")
	List<Event> findBycategory(@Param("name")String nameCategory);

}
