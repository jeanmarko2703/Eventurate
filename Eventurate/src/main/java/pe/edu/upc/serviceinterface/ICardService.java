package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Card;

public interface ICardService {

	public int insert(Card car);

	List<Card> list();

	List<Card> findBynameCard(String cardHolder);

	public void delete(int idCard);

	Optional<Card> searchId(int idCard);
	
	public int update(Card car);

}
