package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	@Query("select count(u.emailUser) from User u where u.emailUser=:emailUser or u.dniClient=:dniClient or u.rucCompany=:rucCompany")
	public int searchUser(@Param("emailUser")String emailUser,@Param("dniClient")String dniClient,@Param("rucCompany")String rucCompany);
	
	@Query("from User u where u.dniClient like %:dniClient% and u.role.nameRole='ROLE_CLIENT'")
	List<User> findBydniClient(@Param("dniClient") String dniClient);
	
	@Query("from User u where u.nameUser like %:nameUser% and u.role.nameRole='ROLE_COMPANY'")
	List<User> findBynameUserCompany(@Param("nameUser") String nameUser);
	
	@Query("from User u where u.role.nameRole='ROLE_CLIENT'")
	List<User> listClients();
	
	@Query("from User u where u.role.nameRole='ROLE_COMPANY'")
	List<User> listCompanies(); 
}
