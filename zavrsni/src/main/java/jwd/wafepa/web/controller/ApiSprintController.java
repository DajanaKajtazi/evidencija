package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Sprint;
import jwd.wafepa.service.SprintService;
import jwd.wafepa.support.SprintToSprintDTO;
import jwd.wafepa.web.dto.SprintDTO;

@RestController
@RequestMapping(value = "/api/sprintovi")
public class ApiSprintController {

	@Autowired
	SprintService sprintService;
	@Autowired
	SprintToSprintDTO toDto;
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<SprintDTO>> getAll(){
		
		List<Sprint> sprintovi=sprintService.findAll();
		if(sprintovi==null || sprintovi.isEmpty()) {
			return new ResponseEntity<List<SprintDTO>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<SprintDTO>>(toDto.convert(sprintovi),HttpStatus.OK);
	}
}
