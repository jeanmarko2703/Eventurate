package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import pe.edu.upc.entity.Country;

public interface ICountryService {
	
	public int insert(Country cou);
	
	public List<Country> list();
	
	List<Country> findBynameCountry(@Param("name")String name);
	
	public void delete(int idCountry);
	
	Optional<Country> searchId(int idCountry);

}
