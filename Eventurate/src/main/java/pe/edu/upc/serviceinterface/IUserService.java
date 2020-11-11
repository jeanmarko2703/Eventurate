package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.User;

public interface IUserService {

	public int insert(User user);

	List<User> listClients();
	
	List<User> listCompanies();

	List<User> findBydniClient(String dniClient);
	
	List<User> findBynameUserCompany(String nameUser);
	
	public void delete (int idUser);
	
	Optional<User> searchId(int idUser);
	
}
