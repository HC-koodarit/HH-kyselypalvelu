package hh.kyselypalvelu.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Kysely {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nimi;
	private String kuvaus;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kysely")
	private List<Kysymys> kysymykset;
	
	public Kysely() {
	}
	
	public Kysely(String nimi, String kuvaus) {
		super();
		this.nimi = nimi;
		this.kuvaus = kuvaus;
	}

	public Kysely(String nimi, String kuvaus, List<Kysymys> kysymykset) {
		super();
		this.nimi = nimi;
		this.kysymykset = kysymykset;
		this.kuvaus = kuvaus;
	}

	public Kysely(Long id, String nimi, String kuvaus, List<Kysymys> kysymykset) {
		super();
		this.id = id;
		this.nimi = nimi;
		this.kuvaus = kuvaus;
		this.kysymykset = kysymykset;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNimi() {
		return nimi;
	}

	public void setNimi(String nimi) {
		this.nimi = nimi;
	}
	
	public String getKuvaus() {
		return kuvaus;
	}

	public void setKuvaus(String kuvaus) {
		this.kuvaus = kuvaus;
	}

	public List<Kysymys> getKysymykset() {
		return kysymykset;
	}

	public void setKysymykset(List<Kysymys> kysymykset) {
		this.kysymykset = kysymykset;
	}

	@Override
	public String toString() {
		return "Kysely [id=" + id + ", nimi=" + nimi + ", kuvaus=" + kuvaus + "]";
	}
}