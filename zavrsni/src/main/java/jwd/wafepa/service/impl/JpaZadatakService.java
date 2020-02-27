package jwd.wafepa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Zadatak;
import jwd.wafepa.repository.ZadatakRepository;
import jwd.wafepa.service.ZadatakService;

@Service
public class JpaZadatakService implements ZadatakService {

	@Autowired
	ZadatakRepository zadatakRepository;
	
	@Override
	public Page<Zadatak> findAll(int pageNum) {
		// TODO Auto-generated method stub
		return zadatakRepository.findAll(new PageRequest(pageNum, 5));
	}

	@Override
	public Zadatak findOne(Long id) {
		// TODO Auto-generated method stub
		return zadatakRepository.findOne(id);
	}

	@Override
	public Zadatak save(Zadatak zadatak) {
		// TODO Auto-generated method stub
		return zadatakRepository.save(zadatak);
	}

	@Override
	public Zadatak delete(Long id) {
		Zadatak zadatak=zadatakRepository.findOne(id);
		
		if(zadatak!=null) {
			zadatakRepository.delete(id);
		}
		return zadatak;
	}

	@Override
	public Page<Zadatak> search(String ime, Long sprintId, int pageNum) {
		
		if(ime!=null) {
			ime="%"+ime+"%";
		}
		return zadatakRepository.search(ime, sprintId, new PageRequest(pageNum, 5));
	}

}
