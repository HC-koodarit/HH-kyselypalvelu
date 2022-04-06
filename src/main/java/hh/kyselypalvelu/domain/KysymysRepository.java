package hh.kyselypalvelu.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

public interface KysymysRepository extends CrudRepository<Kysymys, Long> {
	
	List<Kysymys> findbyKysymys (String kysymysteksti);

}
