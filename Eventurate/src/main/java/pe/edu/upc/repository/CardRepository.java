package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer>{
	
	@Query("From Card c Where c.cardHolder like %:name% or c.banking_entity.nameBanking_entity like %:name%")
	List<Card> findBynameCard(@Param("name")String cardHolder);
	
	@Query("select count(c.cardHolder) from Card c where c.cardHolder=:cardHolder")
	public int searchCard(@Param("cardHolder") String cardHolder);

}
