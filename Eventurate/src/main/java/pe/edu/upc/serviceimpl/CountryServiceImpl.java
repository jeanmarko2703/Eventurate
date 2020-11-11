package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Country;
import pe.edu.upc.repository.CountryRepository;
import pe.edu.upc.serviceinterface.ICountryService;

@Service
public class CountryServiceImpl implements ICountryService{
	
	@Autowired
	private CountryRepository cR;
	
	@Override
	public List<Country> list() {
		return cR.findAll();
	}
	
	@Override
	public List<Country> findBynameCountry(String name) {
		return cR.findBynameCountry(name);
	}
	
	@Override
	public int insert(Country cou) {
		int rpta = cR.searchCountry(cou.getNameCountry());
		if(rpta==0) {
			cR.save(cou);
		}
		return rpta;
	}
	
	@Override
	public void delete(int idCountry) {
		cR.deleteById(idCountry);
	}
	
	@Override
	public Optional<Country> searchId(int idCountry) {
		return cR.findById(idCountry);
	}	

}
