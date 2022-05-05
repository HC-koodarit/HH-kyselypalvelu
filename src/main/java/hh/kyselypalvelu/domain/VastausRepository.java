package hh.kyselypalvelu.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VastausRepository extends CrudRepository<Vastaus, Long>{

	//List<Vastaus> findByVastaukset (String vastausteksti);
	
	@Query(value="select v.* from Kysymys k, Vastaus v where k.kysely_id = ?1 and v.kysymys_id = k.id", nativeQuery = true)
	public List<Vastaus> findByKyselyId(Long kyselyId);
	
}
