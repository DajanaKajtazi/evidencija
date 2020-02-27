package jwd.wafepa.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class ZadatakDTO {

	private Long id;
	@NotEmpty
	@Length(max=40)
	private String ime;

	private String zaduzeni;
	
	@Min(value = 0)
	@Max(value = 20)
	private Integer bodovi;
	
	private Long stanjeId;
	private Long sprintId;
	
	private String stanjeIme;
	private String sprintIme;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getZaduzeni() {
		return zaduzeni;
	}
	public void setZaduzeni(String zaduzeni) {
		this.zaduzeni = zaduzeni;
	}
	public Integer getBodovi() {
		return bodovi;
	}
	public void setBodovi(Integer bodovi) {
		this.bodovi = bodovi;
	}
	public Long getStanjeId() {
		return stanjeId;
	}
	public void setStanjeId(Long stanjeId) {
		this.stanjeId = stanjeId;
	}
	public Long getSprintId() {
		return sprintId;
	}
	public void setSprintId(Long sprintId) {
		this.sprintId = sprintId;
	}
	public String getStanjeIme() {
		return stanjeIme;
	}
	public void setStanjeIme(String stanjeIme) {
		this.stanjeIme = stanjeIme;
	}
	public String getSprintIme() {
		return sprintIme;
	}
	public void setSprintIme(String sprintIme) {
		this.sprintIme = sprintIme;
	}
	
	
	
	
}
