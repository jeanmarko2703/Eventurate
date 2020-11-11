package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.User;
import pe.edu.upc.repository.UserRepository;
import pe.edu.upc.serviceinterface.IUserService;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserRepository uR;

	@Override
	public int insert(User user) {
		int rpta = uR.searchUser(user.getEmailUser(),user.getDniClient(),user.getRucCompany());
		if (rpta == 0) {
			uR.save(user);
		}
		return rpta;
	}


	@Override
	public List<User> findBydniClient(String dniClient) {
		return uR.findBydniClient(dniClient);
	}

	@Override
	public List<User> findBynameUserCompany(String nameUser) {
		return uR.findBynameUserCompany(nameUser);
	}

	@Override
	public void delete(int idUser) {
		uR.deleteById(idUser);
		
	}

	@Override
	public Optional<User> searchId(int idUser) {
		return uR.findById(idUser);
	}


	@Override
	public List<User> listClients() {
		// TODO Auto-generated method stub
		return uR.listClients();
	}


	@Override
	public List<User> listCompanies() {
		// TODO Auto-generated method stub
		return uR.listCompanies();
	}
	

	
}
