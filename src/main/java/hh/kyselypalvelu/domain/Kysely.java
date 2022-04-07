package hh.kyselypalvelu.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Kysely {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nimi;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kysely")
	private List<Kysymys> kysymykset;
	
	public Kysely() {
		super();
	}

	public Kysely(String nimi, List<Kysymys> kysymykset) {
		super();
		this.nimi = nimi;
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

	public List<Kysymys> getKysymykset() {
		return kysymykset;
	}

	public void setKysymykset(List<Kysymys> kysymykset) {
		this.kysymykset = kysymykset;
	}

	@Override
	public String toString() {
		return "Kysely [id=" + id + ", nimi=" + nimi + "]";
	}
}