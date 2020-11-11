package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import pe.edu.upc.entity.City;

public interface ICityService {

	public int insert(City cit);

	List<City> list();

	List<City> findBynameCity(@Param("name") String nameCity);

	List<City> findBycountry(@Param("name") String nameCountry);
	
	public void delete(int idCity);
	
	Optional<City> searchId(int idCity);
	
	public int update(City cit);

}
