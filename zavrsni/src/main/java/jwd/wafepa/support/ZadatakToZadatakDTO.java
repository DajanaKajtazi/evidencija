package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Zadatak;
import jwd.wafepa.web.dto.ZadatakDTO;
@Component
public class ZadatakToZadatakDTO implements Converter<Zadatak, ZadatakDTO>{

	@Override
	public ZadatakDTO convert(Zadatak zadatak) {
		
		ZadatakDTO dto= new ZadatakDTO();
		
		dto.setId(zadatak.getId());
		dto.setBodovi(zadatak.getBodovi());
		dto.setIme(zadatak.getIme());
		dto.setSprintId(zadatak.getSprint().getId());
		dto.setSprintIme(zadatak.getSprint().getIme());
		dto.setStanjeId(zadatak.getStanje().getId());
		dto.setStanjeIme(zadatak.getStanje().getIme());
		dto.setZaduzeni(zadatak.getZaduzeni());
		
		return dto;
	}

	public List<ZadatakDTO> convert(List<Zadatak> zadaci){
		
		List<ZadatakDTO> dto=new ArrayList<ZadatakDTO>();
		for(Zadatak zadatak:zadaci) {
			dto.add(convert(zadatak));
		}
		return dto;
	}
}
