package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.Stanje;

public interface StanjeService {

	List<Stanje> findAll();
	Stanje findOne(Long id);
	Stanje save(Stanje stanje);
	Stanje delete(Long id);
	Stanje prelaz(Long zadatakId);
}
