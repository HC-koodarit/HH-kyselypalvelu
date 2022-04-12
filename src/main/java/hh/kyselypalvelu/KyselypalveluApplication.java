package hh.kyselypalvelu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hh.kyselypalvelu.domain.Kysely;
import hh.kyselypalvelu.domain.Kysymys;
import hh.kyselypalvelu.domain.KyselyRepository;
import hh.kyselypalvelu.domain.KysymysRepository;

@SpringBootApplication
public class KyselypalveluApplication {
	
	private static final Logger log = LoggerFactory.getLogger(KyselypalveluApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KyselypalveluApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner kyselyDemo(KyselyRepository kyselyrepository, KysymysRepository kysymysrepository) {
		return (args) -> {

			log.info("Tallennetaan testikyselyitä"); 
			Kysely kulttuuriKysely = new Kysely("Kulttuurikysely", "Kysely kulttuurista");
			kyselyrepository.save(kulttuuriKysely);
			
			Kysely ruokaKysely = new Kysely("Ruokakysely", "Kysely ruoasta");
			kyselyrepository.save(ruokaKysely);
	
			kysymysrepository.save(new Kysymys("Mitä elokuvaa suosittelet?", kulttuuriKysely));
			
			kysymysrepository.save(new Kysymys("Mitä tv-sarjaa suosittelet?", kulttuuriKysely));
			
			kysymysrepository.save(new Kysymys("Mitä musiikkia suosittelet?", kulttuuriKysely));
			
			kysymysrepository.save(new Kysymys("Mikä on lempiruokasi?", ruokaKysely));
			
			kysymysrepository.save(new Kysymys("Mikä on inhokkiruokasi?", ruokaKysely));
				
			log.info("hae kaikki kyselyt"); 
			for (Kysely kysely : kyselyrepository.findAll()) {
				log.info(kysely.toString());
			}
		};
	}
}