package pe.edu.upc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Event;
import pe.edu.upc.repository.EventRepository;
import pe.edu.upc.serviceinterface.IEventService;

@Service
public class EventServiceImpl implements IEventService{
	
	@Autowired
	private EventRepository eR;
	
	@Transactional
	@Override
	public void insert (Event eve) {
		
		eR.save(eve);
	}
	
	@Override
	public List<Event> list() {
		
		return eR.findAll();
	}

	@Override
	public List<Event> findBynameEvent(String nameEvent) {
		// TODO Auto-generated method stub
		return eR.findBynameEvent(nameEvent);
	}

	@Override
	public List<Event> findBycategory(String namecategory) {
		
		return eR.findBycategory(namecategory);
	}
	
}
