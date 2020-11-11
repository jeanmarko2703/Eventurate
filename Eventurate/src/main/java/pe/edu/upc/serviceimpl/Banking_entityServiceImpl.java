package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Banking_entity;
import pe.edu.upc.repository.Banking_entityRepository;
import pe.edu.upc.serviceinterface.IBanking_entityService;

@Service
public class Banking_entityServiceImpl implements IBanking_entityService{
	
	@Autowired
	private Banking_entityRepository bR;

	

	@Override
	public List<Banking_entity> list() {
		// TODO Auto-generated method stub
		return bR.findAll();
	}



	@Override
	public int insert(Banking_entity ban) {
		int rpta=bR.searchBanking_entity(ban.getNameBanking_entity());
		if(rpta==0) {
			bR.save(ban);
		}
		return rpta;
	}



	@Override
	public List<Banking_entity> findBynameBanking_entity(String nameBanking_entity) {
		// TODO Auto-generated method stub
		return bR.findBynameBanking_entity(nameBanking_entity);
	}



	@Override
	public void delete(int idBanking_entity) {
		bR.deleteById(idBanking_entity);
		
	}

	@Override
	public Optional<Banking_entity> searchId(int idBanking_entity) {
		// TODO Auto-generated method stub
		return bR.findById(idBanking_entity);
	}
}
