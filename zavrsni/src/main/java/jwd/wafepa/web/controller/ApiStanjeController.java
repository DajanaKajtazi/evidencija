package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Stanje;
import jwd.wafepa.service.StanjeService;
import jwd.wafepa.service.ZadatakService;
import jwd.wafepa.support.StanjeToStanjeDTO;
import jwd.wafepa.web.dto.StanjeDTO;

@RestController
@RequestMapping(value = "/api/stanja")
public class ApiStanjeController {

	@Autowired
	StanjeService stanjeService;
	@Autowired
	StanjeToStanjeDTO toDto;
	
	
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<StanjeDTO>> getAll(){
		
		List<Stanje> stanja=stanjeService.findAll();
		if(stanja==null || stanja.isEmpty()) {
			
			return new ResponseEntity<List<StanjeDTO>>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<StanjeDTO>>(toDto.convert(stanja), HttpStatus.OK);
	}
	
	
}
