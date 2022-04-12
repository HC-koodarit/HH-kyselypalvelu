package hh.kyselypalvelu.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Kysymys {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String kysymysteksti;
	
	@ManyToOne
    @JoinColumn(name = "kyselyid")
    private Kysely kysely;
	
	public Kysymys() {
		super();
	}

	public Kysymys(String kysymysteksti, Kysely kysely) {
		super();
		this.kysymysteksti = kysymysteksti;
		this.kysely = kysely;
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

	public Kysely getKysely() {
		return kysely;
	}

	public void setKysely(Kysely kysely) {
		this.kysely = kysely;
	}

	@Override
	public String toString() {
		
		if (this.kysely != null) {
			return "Kysymys [id=" + id + ", kysymysteksti=" + kysymysteksti + ", kysely=" + this.getKysely() + "]";
		}
		
		else {
			return "Kysymys [id=" + id + ", kysymysteksti=" + kysymysteksti + "]";
		}
	}
}