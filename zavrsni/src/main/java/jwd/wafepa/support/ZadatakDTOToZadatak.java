package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Sprint;
import jwd.wafepa.model.Stanje;
import jwd.wafepa.model.Zadatak;
import jwd.wafepa.service.SprintService;
import jwd.wafepa.service.StanjeService;
import jwd.wafepa.service.ZadatakService;
import jwd.wafepa.web.dto.ZadatakDTO;
@Component
public class ZadatakDTOToZadatak implements Converter<ZadatakDTO, Zadatak>{

	@Autowired
	ZadatakService zadatakService;
	@Autowired
	StanjeService stanjeService;
	@Autowired
	SprintService sprintService;
	
	@Override
	public Zadatak convert(ZadatakDTO dto) {
		
		Sprint sprint=sprintService.findOne(dto.getSprintId());
		Stanje stanje=stanjeService.findOne(dto.getStanjeId());
		
		if(stanje!=null && sprint!=null) {
			Zadatak zadatak=null;
			if(dto.getId()!=null) {
				zadatak=zadatakService.findOne(dto.getId());
			}else {
				zadatak= new Zadatak();
			}
			
			zadatak.setBodovi(dto.getBodovi());
			zadatak.setIme(dto.getIme());
			zadatak.setSprint(sprint);
			zadatak.setStanje(stanje);
			zadatak.setZaduzeni(dto.getZaduzeni());
			
			return zadatak;
		}else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
		
	}
	
	public List<Zadatak> convert(List<ZadatakDTO> dto){
		
		List<Zadatak> zadaci= new ArrayList<Zadatak>();
		
		for(ZadatakDTO z:dto) {
			zadaci.add(convert(z));
		}
		return zadaci;
	}

}
