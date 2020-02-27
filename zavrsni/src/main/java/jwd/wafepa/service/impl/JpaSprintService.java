package jwd.wafepa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Sprint;
import jwd.wafepa.repository.SprintRepository;
import jwd.wafepa.service.SprintService;
@Service
public class JpaSprintService implements SprintService {

	@Autowired
	SprintRepository sprintRepository;
	
	@Override
	public List<Sprint> findAll() {
		// TODO Auto-generated method stub
		return sprintRepository.findAll();
	}

	@Override
	public Sprint findOne(Long id) {
		// TODO Auto-generated method stub
		return sprintRepository.findOne(id);
	}

	@Override
	public Sprint save(Sprint sprint) {
		// TODO Auto-generated method stub
		return sprintRepository.save(sprint);
	}

	@Override
	public Sprint delete(Long id) {
		Sprint sprint=sprintRepository.findOne(id);
		
		if(sprint!=null) {
			sprintRepository.delete(id);
		}
		return sprint;
	}

}
