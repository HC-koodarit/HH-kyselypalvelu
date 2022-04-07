package hh.kyselypalvelu.domain;

import org.springframework.data.repository.CrudRepository;

public interface VastausRepository extends CrudRepository<Vastaus, Long>{

	//List<Vastaus> findByVastaukset (String vastausteksti);
	
}
