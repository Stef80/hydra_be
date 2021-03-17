package net.agm.hydra.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.agm.hydra.model.Bookables;
import net.agm.hydra.repository.BookablesRepository;
import net.agm.hydra.services.BookablesService;

@Service
public class BookablesServiceImpl implements BookablesService{

	@Autowired
	BookablesRepository bookablesRepository;
	
	
	@Override
	public List<Bookables> getAll() {
		return bookablesRepository.findAll();
	}

	@Override
	public Bookables newBookable(String name, String description, String tenantId) {
		Bookables newBookable = new Bookables();
		if(name != null && !name.isEmpty()) {
			newBookable.setName(name);
			newBookable.setDescription(description);
			newBookable.setTenantId(tenantId);
			newBookable = bookablesRepository.save(newBookable);
		}
		return newBookable;
	}

}
