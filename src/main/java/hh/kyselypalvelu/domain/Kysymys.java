package hh.kyselypalvelu.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "kysymys")
public class Kysymys {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String kysymysteksti;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "kysely_id")
    private Kysely kysely;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kysymys")
	private List<Vastaus> vastaukset;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kysymys")
	private List<Vaihtoehto> vaihtoehto;
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "kysymystyyppi_id")
    private Kysymystyyppi kysymystyyppi;
	
	public Kysymys() {}

	public Kysymys(String kysymysteksti, Kysely kysely) {
		super();
		this.kysymysteksti = kysymysteksti;
		this.kysely = kysely;
	}

	public Kysymys(String kysymysteksti, Kysely kysely, Kysymystyyppi kysymystyyppi) {
		super();
		this.kysymysteksti = kysymysteksti;
		this.kysely = kysely;
		this.kysymystyyppi = kysymystyyppi;
	}

	public Kysymys(Long id, String kysymysteksti, Kysely kysely) {
		super();
		this.id = id;
		this.kysymysteksti = kysymysteksti;
		this.kysely = kysely;
	}
	
	public Kysymys(String kysymysteksti, Kysely kysely, Kysymystyyppi kysymystyyppi, List<Vastaus> vastaukset) {
		super();
		this.kysymysteksti = kysymysteksti;
		this.kysely = kysely;
		this.kysymystyyppi = kysymystyyppi;
		this.vastaukset = vastaukset;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKysymysteksti() {
		return kysymysteksti;
	}

	public void setKysymysteksti(String kysymysteksti) {
		this.kysymysteksti = kysymysteksti;
	}

	public Kysymystyyppi getKysymystyyppi() {
		return kysymystyyppi;
	}

	public void setKysymystyyppi(Kysymystyyppi kysymystyyppi) {
		this.kysymystyyppi = kysymystyyppi;
	}

	public Kysely getKysely() {
		return kysely;
	}

	public void setKysely(Kysely kysely) {
		this.kysely = kysely;
	}

	public List<Vaihtoehto> getVaihtoehto() {
		return vaihtoehto;
	}

	public void setVaihtoehto(List<Vaihtoehto> vaihtoehto) {
		this.vaihtoehto = vaihtoehto;
	}

	public List<Vastaus> getVastaukset() {
		return vastaukset;
	}

	public void setVastaukset(List<Vastaus> vastaukset) {
		this.vastaukset = vastaukset;
	}

	@Override
	public String toString() {
		return "Kysymys [id=" + id + ", kysymysteksti=" + kysymysteksti + ", kysely=" + kysely
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