package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Banking_entity;

@Repository
public interface Banking_entityRepository extends JpaRepository<Banking_entity, Integer>{
	
	@Query("select count(be.nameBanking_entity) from Banking_entity be where be.nameBanking_entity=:nameBanking_entity")
	public int searchBanking_entity(@Param("nameBanking_entity") String nameBanking_entity);
	
	@Query("From Banking_entity be Where be.nameBanking_entity like %:name%")
	List<Banking_entity> findBynameBanking_entity(@Param("name")String nameBanking_entity);

}
