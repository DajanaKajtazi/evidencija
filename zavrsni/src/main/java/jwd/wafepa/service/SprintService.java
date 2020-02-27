package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.Sprint;

public interface SprintService {

	List<Sprint> findAll();
	Sprint findOne(Long id);
	Sprint save(Sprint sprint);
	Sprint delete(Long id);
}
