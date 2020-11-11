package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.City;
import pe.edu.upc.repository.CityRepository;
import pe.edu.upc.serviceinterface.ICityService;
@Service
public class CityServiceImpl implements ICityService {

	@Autowired
	private CityRepository cR;

	@Override
	public List<City> list() {
		return cR.findAll();
	}

	@Override
	public List<City> findBynameCity(String nameCity) {
		return cR.findBynameCity(nameCity);
	}

	@Override
	public List<City> findBycountry(String nameCountry) {
		return cR.findBycountry(nameCountry);
	}

	@Override
	public int insert(City cit) {
		int rpta = cR.searchCity(cit.getNameCity());
		if(rpta==0) {
			cR.save(cit);
		}
		return rpta;
	}

	@Override
	public void delete(int idCity) {
		cR.deleteById(idCity);
		
	}

	@Override
	public Optional<City> searchId(int idCity) {
		return cR.findById(idCity);
	}

	@Override
	public int update(City cit) {
		Optional<City> currentCity = cR.findById(cit.getIdCity());
		int rpta = cR.searchCity(cit.getNameCity());
		if (rpta == 0 || (rpta == 1 && currentCity.get().getNameCity().equals(cit.getNameCity()))) {
			cR.save(cit);
		}
		return rpta;
	}

}
