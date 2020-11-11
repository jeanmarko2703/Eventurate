package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Role;
import pe.edu.upc.repository.RoleRepository;
import pe.edu.upc.serviceinterface.IRoleService;
@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleRepository rR;

	@Override
	public int insert(Role role) {
		int rpta = rR.searchRole(role.getNameRole());
		if (rpta == 0) {
			rR.save(role);
		}
		return rpta;
	}

	@Override
	public List<Role> list() {
		// TODO Auto-generated method stub
		return rR.findAll();
	}

	@Override
	public List<Role> findBynameRole(String nameRole) {
		// TODO Auto-generated method stub
		return rR.findBynameRole(nameRole);
	}

	@Override
	public void delete(int idRole) {
		// TODO Auto-generated method stub
		rR.deleteById(idRole);
	}

	@Override
	public Optional<Role> searchId(int idRole) {
		// TODO Auto-generated method stub
		return rR.findById(idRole);
	}
	
}
