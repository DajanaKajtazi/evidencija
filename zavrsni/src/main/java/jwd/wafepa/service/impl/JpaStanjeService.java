package jwd.wafepa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Stanje;
import jwd.wafepa.model.Zadatak;
import jwd.wafepa.repository.StanjeRepository;
import jwd.wafepa.repository.ZadatakRepository;
import jwd.wafepa.service.StanjeService;
@Service
public class JpaStanjeService implements StanjeService {

	@Autowired
	StanjeRepository stanjeRepository;
	@Autowired
	ZadatakRepository zadatakrepository;
	
	@Override
	public List<Stanje> findAll() {
		// TODO Auto-generated method stub
		return stanjeRepository.findAll();
	}

	@Override
	public Stanje findOne(Long id) {
		// TODO Auto-generated method stub
		return stanjeRepository.findOne(id);
	}

	@Override
	public Stanje save(Stanje stanje) {
		// TODO Auto-generated method stub
		return stanjeRepository.save(stanje);
	}

	@Override
	public Stanje delete(Long id) {
		Stanje stanje=stanjeRepository.findOne(id);
		if(stanje!=null) {
			stanjeRepository.delete(id);
		}
		return stanje;
	}

	@Override
	public Stanje prelaz(Long zadatakId) {
		
		if(zadatakId==null) {
			
		throw new IllegalArgumentException("Id ne moze da bude null");
		}
		Zadatak zadatak= zadatakrepository.findOne(zadatakId);
		if(zadatak==null) {
			throw new IllegalArgumentException("Ne postoji zadataka sa zadatim id");
		}
		
		Stanje stanje= zadatak.getStanje();
		if(!("Zavrsen").equals(stanje.getIme())) {
		
		stanje=stanjeRepository.findOne(stanje.getId()+1);
			
			zadatak.setStanje(stanje);
			
		zadatakrepository.save(zadatak);
		
		}
		return stanje;
		
		
		
		
	}

}
