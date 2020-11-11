package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query("from Role r where r.nameRole like %:name%")
	List<Role> findBynameRole(@Param("name")String nameRole);
	
	@Query("select count(r.nameRole) from Role r where r.nameRole=:nameRole")
	public int searchRole(@Param("nameRole") String nameRole);
	
}
