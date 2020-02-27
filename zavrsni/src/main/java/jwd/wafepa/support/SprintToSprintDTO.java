package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Sprint;
import jwd.wafepa.web.dto.SprintDTO;
@Component
public class SprintToSprintDTO implements Converter<Sprint, SprintDTO> {

	@Override
	public SprintDTO convert(Sprint sprint) {
		
		SprintDTO dto= new SprintDTO();
		
		dto.setId(sprint.getId());
		dto.setIme(sprint.getIme());
		dto.setUkupnoBodova(sprint.getUkupnoBodova());
		
		return dto;
	}

	public List<SprintDTO> convert(List<Sprint> sprint){
		
		List<SprintDTO> dto= new ArrayList<SprintDTO>();
		for(Sprint s:sprint) {
			dto.add(convert(s));
		}
		return dto;
	}
}
