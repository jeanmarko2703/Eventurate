package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Role;

public interface IRoleService {
	public int insert(Role role);

	List<Role> list();

	List<Role> findBynameRole(String nameRole);
	
	public void delete (int idRole);
	
	Optional<Role> searchId(int idRole);

}
