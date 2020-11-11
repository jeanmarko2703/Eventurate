package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.City;

@Repository
public interface CityRepository  extends JpaRepository<City, Integer>{

	@Query("From City c Where c.nameCity like %:name%")
	List<City> findBynameCity(@Param("name")String nameCity);
	
	@Query("From City c Where c.country.nameCountry like %:name%")
	List<City> findBycountry(@Param("name")String nameCountry);
	
	@Query("select count(c.nameCity) from City c where c.nameCity=:nameCity")
	public int searchCity(@Param("nameCity") String nameCity);
	
}
