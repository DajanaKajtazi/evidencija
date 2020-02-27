package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Stanje;
import jwd.wafepa.web.dto.StanjeDTO;
@Component
public class StanjeToStanjeDTO implements Converter<Stanje, StanjeDTO>{

	@Override
	public StanjeDTO convert(Stanje stanje) {
		
		StanjeDTO dto= new StanjeDTO();
		
		dto.setId(stanje.getId());
		dto.setIme(stanje.getIme());
		
		return dto;
	}

	public List<StanjeDTO> convert(List<Stanje> stanja){
		
		List<StanjeDTO> dto=new ArrayList<StanjeDTO>();
		for(Stanje s:stanja) {
			dto.add(convert(s));
		}
		return dto;
	}
}
