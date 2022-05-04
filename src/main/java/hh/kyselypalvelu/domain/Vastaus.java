package hh.kyselypalvelu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Vastaus {
	@Id
	@Column(name="Vastaus_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long vastausid;
	private String vastausteksti;
	
	@ManyToOne
    @JoinColumn(name = "kysymys_id")
    private Kysymys kysymys;
	
	public Vastaus() {
		super();
	}
		
	public Vastaus(String vastausteksti, Kysymys kysymys) {
		super();
		this.vastausteksti = vastausteksti;
		this.kysymys = kysymys;
	}
	
	public Long getVastausid() {
		return vastausid;
	}

	public void setVastausid(Long vastausid) {
		this.vastausid = vastausid;
	}

	public String getVastausteksti() {
		return vastausteksti;
	}

	public void setVastausteksti(String vastausteksti) {
		this.vastausteksti = vastausteksti;
	}

	public Kysymys getKysymys() {
		return kysymys;
	}

	public void setKysymys(Kysymys kysymys) {
		this.kysymys = kysymys;
	}

	@Override
	public String toString() {
		return "Vastaus [vastausid=" + vastausid + ", vastausteksti=" + vastausteksti + ", kysymys=" + kysymys + "]";
	}
}