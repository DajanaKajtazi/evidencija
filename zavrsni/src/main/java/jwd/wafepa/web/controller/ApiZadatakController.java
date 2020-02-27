package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Stanje;
import jwd.wafepa.model.Zadatak;
import jwd.wafepa.service.SprintService;
import jwd.wafepa.service.StanjeService;
import jwd.wafepa.service.ZadatakService;
import jwd.wafepa.support.StanjeToStanjeDTO;
import jwd.wafepa.support.ZadatakDTOToZadatak;
import jwd.wafepa.support.ZadatakToZadatakDTO;
import jwd.wafepa.web.dto.StanjeDTO;
import jwd.wafepa.web.dto.ZadatakDTO;

@RestController
@RequestMapping(value = "/api/zadaci")
public class ApiZadatakController {

	@Autowired
	ZadatakService zadatakService;
	@Autowired
	ZadatakToZadatakDTO toDto;
	@Autowired
	ZadatakDTOToZadatak toZadatak;
	@Autowired
	StanjeService stanjeService;
	@Autowired
	StanjeToStanjeDTO toDTO;
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<ZadatakDTO>> getAll(@RequestParam(required = false) String ime,
			@RequestParam(required = false) Long sprintId, @RequestParam(defaultValue = "0") int pageNum){
		
		Page<Zadatak> zadaci=null;
		if(ime != null || sprintId != null) {
			zadaci=zadatakService.search(ime, sprintId, pageNum);
		}else {
			zadaci=zadatakService.findAll(pageNum);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(zadaci.getTotalPages()) );
		
		return new ResponseEntity<List<ZadatakDTO>>(toDto.convert(zadaci.getContent()), headers,HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<ZadatakDTO> getOne(@PathVariable Long id){
		
		Zadatak zadatak= zadatakService.findOne(id);
		if(zadatak==null) {
			return new ResponseEntity<ZadatakDTO>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ZadatakDTO>(toDto.convert(zadatak), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<ZadatakDTO> delete(@PathVariable Long id){
		
		Zadatak zadatak=zadatakService.delete(id);
		
		if(zadatak==null) {
			return new ResponseEntity<ZadatakDTO>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<ZadatakDTO>(toDto.convert(zadatak), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<ZadatakDTO> add(@Validated @RequestBody ZadatakDTO dto){
		
		Zadatak zadatak=zadatakService.save(toZadatak.convert(dto));
		
		return new ResponseEntity<ZadatakDTO>(toDto.convert(zadatak), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	ResponseEntity<ZadatakDTO> edit(@PathVariable Long id, @Validated @RequestBody ZadatakDTO dto){
		
		if(!id.equals(dto.getId())) {
			return new ResponseEntity<ZadatakDTO>(HttpStatus.BAD_REQUEST);
		}
		
		Zadatak zadatak=zadatakService.save(toZadatak.convert(dto));
		
		return new ResponseEntity<ZadatakDTO>(toDto.convert(zadatak), HttpStatus.OK);
	}
	
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}/prelaz")
	ResponseEntity<StanjeDTO> prelaz(@PathVariable Long id){
		
		Stanje stanje=stanjeService.prelaz(id);
		
		return new ResponseEntity<StanjeDTO>(toDTO.convert(stanje), HttpStatus.CREATED);
	}
}
