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
			Kysymys kysymysh1 = new Kysymys("Koetko olevasi oikealla alalla?", hyvinvointiKysely, valintanappi);
			kysymysrepository.save(kysymysh1);
			
			Kysymys kysymysh2 = new Kysymys("Oletko tyytyväinen opintomenestykseesi?", hyvinvointiKysely, valintanappi);
			kysymysrepository.save(kysymysh2);
			
			Kysymys kysymysh3 = new Kysymys("Onko mielenterveysongelmat vaikuttaneet negatiivisesti opintomenestykseesi?", hyvinvointiKysely, valintanappi);
			kysymysrepository.save(kysymysh3);
			
			Kysymys kysymysh4 = new Kysymys("Koetko yksinäisyyttä koulussa?", hyvinvointiKysely, valintanappi);
			kysymysrepository.save(kysymysh4);
			
			Kysymys kysymysh5 = new Kysymys("Mitä kehitysideoita sinulla on hyvinvoinnin parantamiseksi Haaga-Heliassa?", hyvinvointiKysely, avoin);
			kysymysrepository.save(kysymysh5);
			
			Kysymys kysymysh6 = new Kysymys("Valitse ateriat, joita syöt säännöllisesti koulupäivän aikana ", hyvinvointiKysely, monivalinta);
			kysymysrepository.save(kysymysh6);
			
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

			// Vastaukset hyvinvointikysely
			vastausrepository.save(new Vastaus("Makaronilaatikko", kysymysk4));
			// Vastaukset kulttuurikysely
			vastausrepository.save(new Vastaus("Tilliliha", kysymysk5));
			
			// Vaihtoehdot valintanappikysymyksiin (hyvinvointikysely)
			Vaihtoehto valintanappi1 = new Vaihtoehto("Kyllä", kysymysh4);
			vaihtoehtorepository.save(valintanappi1);
			Vaihtoehto valintanappi2 = new Vaihtoehto("En", kysymysh4);
			vaihtoehtorepository.save(valintanappi2);
			
			Vaihtoehto valintanappi3 = new Vaihtoehto("Kyllä", kysymysh3);
			vaihtoehtorepository.save(valintanappi3);
			Vaihtoehto valintanappi4 = new Vaihtoehto("Ei", kysymysh3);
			vaihtoehtorepository.save(valintanappi4);
			Vaihtoehto valintanappi5 = new Vaihtoehto("En halua vastata", kysymysh3);
			vaihtoehtorepository.save(valintanappi5);
			
			
			// Vaihtoehdot monivalintakysymyksiin (hyvinvointikysely)
			Vaihtoehto monivalinta3 = new Vaihtoehto("Aamupala", kysymysh6);
			vaihtoehtorepository.save(monivalinta3);
			Vaihtoehto monivalinta4 = new Vaihtoehto("Lounas", kysymysh6);
			vaihtoehtorepository.save(monivalinta4);
			Vaihtoehto monivalinta5 = new Vaihtoehto("Välipala", kysymysh6);
			vaihtoehtorepository.save(monivalinta5);
			Vaihtoehto monivalinta6 = new Vaihtoehto("Päivällinen", kysymysh6);
			vaihtoehtorepository.save(monivalinta6);
			Vaihtoehto monivalinta7 = new Vaihtoehto("Iltapala", kysymysh6);
			vaihtoehtorepository.save(monivalinta7);
			
			
			log.info("hae kaikki kyselyt"); 
			for (Kysely kysely : kyselyrepository.findAll()) {
				log.info(kysely.toString());
			}
		};
	}
}