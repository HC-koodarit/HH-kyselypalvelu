package hh.kyselypalvelu.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VastausRepository extends CrudRepository<Vastaus, Long>{

	//List<Vastaus> findByVastaukset (String vastausteksti);
	
	@Query(value="select v.* from Kysymys k, Vastaus v where k.kyselyid = ?1 and v.kysymysid = k.kysymysid", nativeQuery = true)
	public List<Vastaus> findByKyselyId(Long kyselyId);
	
}
