package hh.kyselypalvelu.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface KyselyRepository extends CrudRepository<Kysely, Long> {
	
	//List<Kysely> findByNimi(String nimi);

}