package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Banking_entity;

public interface IBanking_entityService {
	
	public int insert(Banking_entity ban);
	
	List<Banking_entity> list();

	List<Banking_entity> findBynameBanking_entity(String nameBanking_entity);
	
	public void delete(int idBanking_entity);
	
	Optional<Banking_entity> searchId(int idBanking_entity);
}
