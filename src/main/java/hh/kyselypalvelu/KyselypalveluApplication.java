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
import hh.kyselypalvelu.domain.Vaihtoehto;
import hh.kyselypalvelu.domain.VaihtoehtoRepository;
import hh.kyselypalvelu.domain.Vastaus;
import hh.kyselypalvelu.domain.VastausRepository;

@SpringBootApplication
public class KyselypalveluApplication {
	
	private static final Logger log = LoggerFactory.getLogger(KyselypalveluApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(KyselypalveluApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner kyselyDemo(KyselyRepository kyselyrepository, KysymysRepository kysymysrepository, VastausRepository vastausrepository, KysymystyyppiRepository kysymystyyppirepository, VaihtoehtoRepository vaihtoehtorepository) {
		return (args) -> {

			log.info("Tallennetaan testikyselyitä"); 

			//Kyselyt
			Kysely hyvinvointiKysely = new Kysely("Hyvinvointikysely", "Opiskeluhyvinvoinnin kartoittaminen");
			kyselyrepository.save(hyvinvointiKysely);
			
			Kysely kulttuuriKysely = new Kysely("Kulttuurikysely", "Kysely kulttuurista");
			kyselyrepository.save(kulttuuriKysely);
			
			Kysely ruokaKysely = new Kysely("Ruokakysely", "Kysely ruoasta");
			kyselyrepository.save(ruokaKysely);
			
			//Kysymystyyppi
			Kysymystyyppi avoin = new Kysymystyyppi("Avoinkysymys");
			kysymystyyppirepository.save(avoin);
			
			Kysymystyyppi monivalinta = new Kysymystyyppi("Monivalintakysymys");
			kysymystyyppirepository.save(monivalinta);
			
			Kysymystyyppi valintanappi = new Kysymystyyppi("Valintanappikysymys");
			kysymystyyppirepository.save(valintanappi);
			
			//Kysymykset hyvinvointikyselyyn
			Kysymys kysymysh1 = new Kysymys("Koetko olevasi oikealla alalla?", hyvinvointiKysely, monivalinta);
			kysymysrepository.save(kysymysh1);
			
			Kysymys kysymysh2 = new Kysymys("Oletko tyytyväinen opintomenestykseesi?", hyvinvointiKysely, monivalinta);
			kysymysrepository.save(kysymysh2);
			
			Kysymys kysymysh3 = new Kysymys("Onko mielenterveysongelmat vaikuttaneet negatiivisesti opintomenestykseesi?", hyvinvointiKysely, valintanappi);
			kysymysrepository.save(kysymysh3);
			
			Kysymys kysymysh4 = new Kysymys("Koetko yksinäisyyttä koulussa?", hyvinvointiKysely, valintanappi);
			kysymysrepository.save(kysymysh4);
			
			Kysymys kysymysh5 = new Kysymys("Mitä kehitysideoita sinulla on hyvinvoinnin parantamiseksi Haaga-Heliassa?", hyvinvointiKysely, avoin);
			kysymysrepository.save(kysymysh5);
			
			//Kysymykset kulttuurikysely
			Kysymys kysymysk1 = new Kysymys("Mitä elokuvaa suosittelet?", kulttuuriKysely, avoin);
			kysymysrepository.save(kysymysk1);
			
			Kysymys kysymysk2 = new Kysymys("Mitä tv-sarjaa suosittelet?", kulttuuriKysely, avoin);
			kysymysrepository.save(kysymysk2);
			
			Kysymys kysymysk3 = new Kysymys("Mitä musiikkia suosittelet?", kulttuuriKysely, avoin);
			kysymysrepository.save(kysymysk3);
			
			Kysymys kysymysk4 = new Kysymys("Mikä on lempiruokasi?", ruokaKysely, avoin);
			kysymysrepository.save(kysymysk4);
			
			Kysymys kysymysk5 = new Kysymys("Mikä on inhokkiruokasi?", ruokaKysely, avoin);
			kysymysrepository.save(kysymysk5);
			
			Kysymys kysymysk6 = new Kysymys("Valitse ateriat, joita syöt säännöllisesti koulupäivän aikana ", ruokaKysely, monivalinta);
			kysymysrepository.save(kysymysk6);
			
			vastausrepository.save(new Vastaus("Makaronilaatikko", kysymysk4));
			vastausrepository.save(new Vastaus("Tilliliha", kysymysk5));
			
			// Vaihtoehdot valintanappikysymyksiin (hyvinvointikysely)
			Vaihtoehto vaihtoehto1 = new Vaihtoehto("Kyllä", kysymysh4);
			vaihtoehtorepository.save(vaihtoehto1);
			
			Vaihtoehto vaihtoehto2 = new Vaihtoehto("En", kysymysh4);
			vaihtoehtorepository.save(vaihtoehto2);
			
			// Vaihtoehdot monivalintakysymyksiin (hyvinvointikysely)
			Vaihtoehto vaihtoehto3 = new Vaihtoehto("Aamupala", kysymysk6);
			vaihtoehtorepository.save(vaihtoehto3);
						
			Vaihtoehto vaihtoehto4 = new Vaihtoehto("Lounas", kysymysk6);
			vaihtoehtorepository.save(vaihtoehto4);
			
			Vaihtoehto vaihtoehto5 = new Vaihtoehto("Välipala", kysymysk6);
			vaihtoehtorepository.save(vaihtoehto5);
			Vaihtoehto vaihtoehto6 = new Vaihtoehto("Päivällinen", kysymysk6);
			vaihtoehtorepository.save(vaihtoehto6);
			Vaihtoehto vaihtoehto7 = new Vaihtoehto("Iltapala", kysymysk6);
			vaihtoehtorepository.save(vaihtoehto7);
			
			
			
			log.info("hae kaikki kyselyt"); 
			for (Kysely kysely : kyselyrepository.findAll()) {
				log.info(kysely.toString());
			}
		};
	}
}