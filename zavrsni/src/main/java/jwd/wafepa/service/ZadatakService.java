package jwd.wafepa.service;



import org.springframework.data.domain.Page;


import jwd.wafepa.model.Zadatak;

public interface ZadatakService {

	Page<Zadatak> findAll(int pageNum);
	Zadatak findOne(Long id);
	Zadatak save(Zadatak zadatak);
	Zadatak delete(Long id);
	Page<Zadatak> search(
			String ime, 
			Long sprintId, 
			int pageNum);
}
