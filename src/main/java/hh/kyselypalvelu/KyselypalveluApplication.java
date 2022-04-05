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
	public CommandLineRunner kyselyDemo(KyselyRepository kyselyRepository, KysymysRepository kysymysRepository) {
		return (args) -> {
			
			List<Kysymys> kysymykset = new ArrayList<Kysymys>();

			log.info("Tallennetaan testikyselyitä"); 
			Kysely kulttuuriKysely = new Kysely("Kulttuurikysely", kysymykset);
			kyselyRepository.save(kulttuuriKysely);
			
			Kysymys ekakys = new Kysymys("Mitä elokuvaa suosittelet?", kulttuuriKysely);
			Kysymys tokakys = new Kysymys("Mitä tv-sarjaa suosittelet?", kulttuuriKysely);
			Kysymys kolmaskys = new Kysymys("Mitä musiikkia suosittelet?", kulttuuriKysely);
			kysymykset.add(ekakys);
			kysymykset.add(tokakys);
			kysymykset.add(kolmaskys);
			
			Kysely ruokaKysely = new Kysely("Ruokakysely", kysymykset);
			kyselyRepository.save(ruokaKysely);
			
			Kysymys ekakysruoka = new Kysymys("Mikä on lempiruokasi?", ruokaKysely);
			Kysymys tokakysruoka = new Kysymys("Mikä on inhokkiruokasi?", ruokaKysely);
			kysymykset.add(ekakysruoka);
			kysymykset.add(tokakysruoka);
				
			log.info("hae kaikki kyselyt"); 
			for (Kysely kysely : kyselyRepository.findAll()) {
				log.info(kysely.toString());
			}

		};
	}
	
}
