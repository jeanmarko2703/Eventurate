package pe.edu.upc.serviceinterface;

import java.util.List;

import org.springframework.data.repository.query.Param;

import pe.edu.upc.entity.Event;

public interface IEventService {
	
	public void insert (Event eve);
	
	List<Event> list();
	
	List<Event> findBynameEvent(@Param("name")String nameEvent);
	
	List<Event> findBycategory(@Param("name")String namecategory);

}
