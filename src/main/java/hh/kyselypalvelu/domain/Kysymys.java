package hh.kyselypalvelu.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Kysymys {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long kysymysid;
	private String kysymysteksti;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "kyselyid")
    private Kysely kysely;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id")
	private Kysymystyyppi kysymystyyppi;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kysymys")
	private List<Vastaus> vastaukset;
	
	public Kysymys() {
		super();
	}

	public Kysymys(String kysymysteksti, Kysely kysely, Kysymystyyppi kysymystyyppi, List<Vastaus> vastaukset) {
		super();
		this.kysymysteksti = kysymysteksti;
		this.kysely = kysely;
		this.kysymystyyppi = kysymystyyppi;
		this.vastaukset = vastaukset;
	}

	public Kysymys(String kysymysteksti, Kysely kysely) {
		super();
		this.kysymysteksti = kysymysteksti;
		this.kysely = kysely;
	}

	public Kysymys(Long kysymysid, String kysymysteksti, Kysely kysely) {
		super();
		this.kysymysid = kysymysid;
		this.kysymysteksti = kysymysteksti;
		this.kysely = kysely;
	}

	public Kysymystyyppi getKysymystyyppi() {
		return kysymystyyppi;
	}

	public void setKysymystyyppi(Kysymystyyppi kysymystyyppi) {
		this.kysymystyyppi = kysymystyyppi;
	}

	public Long getKysymysid() {
		return kysymysid;
	}

	public void setKysymysid(Long kysymysid) {
		this.kysymysid = kysymysid;
	}

	public String getKysymysteksti() {
		return kysymysteksti;
	}

	public void setKysymysteksti(String kysymysteksti) {
		this.kysymysteksti = kysymysteksti;
	}

	public Kysely getKysely() {
		return kysely;
	}

	public void setKysely(Kysely kysely) {
		this.kysely = kysely;
	}

	public List<Vastaus> getVastaukset() {
		return vastaukset;
	}

	public void setVastaukset(List<Vastaus> vastaukset) {
		this.vastaukset = vastaukset;
	}

	@Override
	public String toString() {
		return "Kysymys [kysymysid=" + kysymysid + ", kysymysteksti=" + kysymysteksti + ", kysely=" + kysely
				+ ", kysymystyyppi=" + kysymystyyppi + ", vastaukset=" + vastaukset + "]";
	}
	
/*
	@Override
	public String toString() {
		
		if (this.kysely != null) {
			return "Kysymys [kysymysid=" + kysymysid + ", kysymysteksti=" + kysymysteksti + ", kysely=" + this.getKysely() + "]";
		}
		
		else {
			return "Kysymys [kysymysid=" + kysymysid + ", kysymysteksti=" + kysymysteksti + "]";
		}
	}
	*/
}