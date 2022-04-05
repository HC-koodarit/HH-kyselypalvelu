package hh.kyselypalvelu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
	
	public CommandLineRunner studentDemo(KyselyRepository kyselyRepository, KysymysRepository kysymysRepository) {
		return (args) -> {
			
			List<Kysymys> kysymykset = new ArrayList<Kysymys>();

			
			log.info("Tallenna kysely"); 
			Kysely jokuKysely = new Kysely("jokuKysely", kysymykset);
			kyselyRepository.save(jokuKysely);
				
			log.info("hae kaikki kyselyt"); 
			for (Kysely kysely : kyselyRepository.findAll()) {
				log.info(kysely.toString());
			}

		};
	}
	
}
