package hh.kyselypalvelu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.kyselypalvelu.domain.Kysely;
import hh.kyselypalvelu.domain.KyselyRepository;
import hh.kyselypalvelu.domain.Kysymys;
import hh.kyselypalvelu.domain.KysymysRepository;
import hh.kyselypalvelu.domain.Kysymystyyppi;
import hh.kyselypalvelu.domain.KysymystyyppiRepository;
import hh.kyselypalvelu.domain.Vastaus;
import hh.kyselypalvelu.domain.VastausRepository;

@SpringBootApplication
public class KyselypalveluApplication {
	
	private static final Logger log = LoggerFactory.getLogger(KyselypalveluApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KyselypalveluApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner kyselyDemo(KyselyRepository kyselyrepository, KysymysRepository kysymysrepository, VastausRepository vastausrepository) {
		return (args) -> {

			log.info("Tallennetaan testikyselyitä"); 

			Kysely hyvinvointiKysely = new Kysely("Hyvinvointikysely", "Opiskeluhyvinvoinnin kartoittaminen", null);
			kyselyrepository.save(hyvinvointiKysely);
			
			Kysymys ekahyv = new Kysymys("Koetko olevasi oikealla alalla?", hyvinvointiKysely);
			kysymysrepository.save(ekahyv);
			
			Kysymys tokahyv = new Kysymys("Oletko tyytyväinen opintomenestykseesi?", hyvinvointiKysely);
			kysymysrepository.save(tokahyv);
			
			Kysymys kolhyv = new Kysymys("Onko mielenterveysongelmat vaikuttaneet negatiivisesti opintomenestykseesi?", hyvinvointiKysely);
			kysymysrepository.save(kolhyv);
			
			Kysymys nelhyv = new Kysymys("Koetko yksinäisyyttä koulussa?", hyvinvointiKysely);
			kysymysrepository.save(nelhyv);
			
			Kysymys viishyv = new Kysymys("Mitä kehitysideoita sinulla on hyvinvoinnin parantamiseksi Haaga-Heliassa?", hyvinvointiKysely);
			kysymysrepository.save(viishyv);
			

			Kysely kulttuuriKysely = new Kysely("Kulttuurikysely", "Kysely kulttuurista");
			kyselyrepository.save(kulttuuriKysely);
			
			Kysely ruokaKysely = new Kysely("Ruokakysely", "Kysely ruoasta");
			kyselyrepository.save(ruokaKysely);
			
			Kysymys kysymys1 = new Kysymys("Mitä elokuvaa suosittelet?", kulttuuriKysely);
			kysymysrepository.save(kysymys1);
			
			Kysymys kysymys2 = new Kysymys("Mitä tv-sarjaa suosittelet?", kulttuuriKysely);
			kysymysrepository.save(kysymys2);
			
			Kysymys kysymys3 = new Kysymys("Mitä musiikkia suosittelet?", kulttuuriKysely);
			kysymysrepository.save(kysymys3);
			
			Kysymys kysymys4 = new Kysymys("Mikä on lempiruokasi?", ruokaKysely);
			kysymysrepository.save(kysymys4);
			
			Kysymys kysymys5 = new Kysymys("Mikä on inhokkiruokasi?", ruokaKysely);
			kysymysrepository.save(kysymys5);
			
			vastausrepository.save(new Vastaus("Makaronilaatikko", kysymys4));
			vastausrepository.save(new Vastaus("Tilliliha", kysymys5));

			log.info("hae kaikki kyselyt"); 
			for (Kysely kysely : kyselyrepository.findAll()) {
				log.info(kysely.toString());
			}
		};
	}
}