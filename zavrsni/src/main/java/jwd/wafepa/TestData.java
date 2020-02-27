package jwd.wafepa;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Sprint;
import jwd.wafepa.model.Stanje;
import jwd.wafepa.model.Zadatak;
import jwd.wafepa.service.SprintService;
import jwd.wafepa.service.StanjeService;
import jwd.wafepa.service.ZadatakService;



@Component
public class TestData {



	@Autowired
	ZadatakService zadatakService;
	@Autowired
	StanjeService stanjeService;
	@Autowired
	SprintService sprintService;
	
	@PostConstruct
	public void init() {
		
		
		Sprint s1= new Sprint();
		s1.setIme("sprint1");
		s1.setUkupnoBodova(9);
		sprintService.save(s1);
		
		Sprint s2= new Sprint();
		s2.setIme("sprint2");
		s2.setUkupnoBodova(8);
		sprintService.save(s2);
		
		
		

		Stanje stanje1= new Stanje();
		
		stanje1.setIme("Nov");
		stanjeService.save(stanje1);
		

		Stanje stanje2= new Stanje();
		
		stanje2.setIme("U toku");
		stanjeService.save(stanje2);
		

		Stanje stanje3= new Stanje();
		
		stanje3.setIme("Zavrsen");
		stanjeService.save(stanje3);
		
		
		Zadatak z1= new Zadatak();
		z1.setBodovi(5);
		z1.setIme("zad1");
		z1.setSprint(s2);
		z1.setStanje(stanje2);
		z1.setZaduzeni("zaduzeni1");
		zadatakService.save(z1);
		
		Zadatak z2= new Zadatak();
		z2.setBodovi(4);
		z2.setIme("zad2");
		z2.setSprint(s1);
		z2.setStanje(stanje1);
		z2.setZaduzeni("zaduzeni2");
		zadatakService.save(z2);
		
		Zadatak z3= new Zadatak();
		z3.setBodovi(6);
		z3.setIme("zad3");
		z3.setSprint(s1);
		z3.setStanje(stanje3);
		z3.setZaduzeni("zaduzeni3");
		zadatakService.save(z3);
		
		
		
		
		
	}
}
