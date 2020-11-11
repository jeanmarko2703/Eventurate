package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Country;


@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

	@Query("From Country c Where c.nameCountry like %:name%")
	List<Country> findBynameCountry(@Param("name")String nameCountry);
	
	@Query("select count(c.nameCountry) from Country c where c.nameCountry=:nameCountry")
	public int searchCountry(@Param("nameCountry") String nameCountry);
	
}
