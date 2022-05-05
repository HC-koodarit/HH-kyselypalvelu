package hh.kyselypalvelu.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Vaihtoehto {
	@Id
	@Column(name="vaihtoehto_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String nimi;
	
	@ManyToOne
    @JoinColumn(name = "kysymys_id")
	private Kysymys kysymys;
	
	public Vaihtoehto() {}

	public Vaihtoehto(String nimi, Kysymys kysymys) {
		super();
		this.nimi = nimi;
		this.kysymys = kysymys;
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

	public Kysymys getKysymys() {
		return kysymys;
	}

	public void setKysymys(Kysymys kysymys) {
		this.kysymys = kysymys;
	}

	@Override
	public String toString() {
		return "Vaihtoehto [id=" + id + ", nimi=" + nimi + ", kysymys=" + kysymys + "]";
	}

}
