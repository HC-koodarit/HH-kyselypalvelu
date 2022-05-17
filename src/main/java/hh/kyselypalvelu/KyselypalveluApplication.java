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
			Kysely hyvinvointiKysely = new Kysely("Hyvinvointikysely Haaga-Helian opiskelijoille", "Kyselyn tarkoituksena on kartoittaa Haaga-Helian opiskelijoiden hyvinvointia. "
					+ "Kyselyn avulla selvitetään, miten hyvin opiskelijat pärjäävät ja jaksavat opinnoissaan. Vastausten avulla pyritään löytämään keinoja opiskelijoiden hyvinvoinnin edistämiseksi.");
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
			Kysymys kysymysh1 = new Kysymys("Minkä ikäinen olet?", hyvinvointiKysely, valintanappi);
			kysymysrepository.save(kysymysh1);
			
			Kysymys kysymysh2 = new Kysymys("Mikä on sukupuolesi?", hyvinvointiKysely, valintanappi);
			kysymysrepository.save(kysymysh2);
			
			Kysymys kysymysh3 = new Kysymys("Kuinka mones opiskeluvuosi sinulla on meneillään Haaga-Heliassa?", hyvinvointiKysely, valintanappi);
			kysymysrepository.save(kysymysh3);
			
			Kysymys kysymysh4 = new Kysymys("Koetko olevasi oikealla alalla?", hyvinvointiKysely, valintanappi);
			kysymysrepository.save(kysymysh4);
			
			Kysymys kysymysh5 = new Kysymys("Oletko tyytyväinen opintomenestykseesi?", hyvinvointiKysely, valintanappi);
			kysymysrepository.save(kysymysh5);
			
			Kysymys kysymysh6 = new Kysymys("Koetko olevasi väsynyt opintojen takia?", hyvinvointiKysely, valintanappi);
			kysymysrepository.save(kysymysh6);
			
			Kysymys kysymysh7 = new Kysymys("Onko sinulla ollut mielenterveyden kanssa ongelmia opintojen aikana?", hyvinvointiKysely, valintanappi);
			kysymysrepository.save(kysymysh7);
			
			Kysymys kysymysh8 = new Kysymys("Jos vastasit edelliseen kysymykseen kyllä, minkälaisia ongelmia?", hyvinvointiKysely, avoin);
			kysymysrepository.save(kysymysh8);
			
			Kysymys kysymysh9 = new Kysymys("Ovatko mielenterveysongelmat vaikuttaneet opintomenestykseesi?", hyvinvointiKysely, valintanappi);
			kysymysrepository.save(kysymysh9);
			
			Kysymys kysymysh10 = new Kysymys("Jos vastasit edelliseen kysymykseen kyllä, miten ne ovat vaikuttaneet?", hyvinvointiKysely, avoin);
			kysymysrepository.save(kysymysh10);
			
			Kysymys kysymysh11 = new Kysymys("Onko etäopiskelu vaikuttanut negatiivisesti motivaatioosi opinnoissa?", hyvinvointiKysely, valintanappi);
			kysymysrepository.save(kysymysh11);
			
			Kysymys kysymysh12 = new Kysymys("Onko etäopiskelu vaikuttanut opintomenestykseesi negatiivisesti?", hyvinvointiKysely, valintanappi);
			kysymysrepository.save(kysymysh12);
			
			Kysymys kysymysh13 = new Kysymys("Mitä kehitysideoita sinulla olisi etäopiskelun parantamiseksi?", hyvinvointiKysely, avoin);
			kysymysrepository.save(kysymysh13);
			
			Kysymys kysymysh14 = new Kysymys("Koetko yksinäisyyttä koulussa?", hyvinvointiKysely, valintanappi);
			kysymysrepository.save(kysymysh14);
			
			Kysymys kysymysh15 = new Kysymys("Mitä kehitysideoita sinulla olisi yksinäisyyden estämiseksi Haaga-Heliassa?", hyvinvointiKysely, avoin);
			kysymysrepository.save(kysymysh15);
			
			Kysymys kysymysh16 = new Kysymys("Valitse ateriat, joita syöt säännöllisesti koulupäivän aikana.", hyvinvointiKysely, monivalinta);
			kysymysrepository.save(kysymysh16);
			
			Kysymys kysymysh17 = new Kysymys("Koetko, että säännöllisesti/epäsäännöllisesti syöminen vaikuttaa jaksamiseesi opinnoissa?", hyvinvointiKysely, avoin);
			kysymysrepository.save(kysymysh17);
			
			// Kysymykset kulttuurikyselyyn
			Kysymys kysymysk1 = new Kysymys("Mitä elokuvaa suosittelet?", kulttuuriKysely, avoin);
			kysymysrepository.save(kysymysk1);
			
			Kysymys kysymysk2 = new Kysymys("Mitä tv-sarjaa suosittelet?", kulttuuriKysely, avoin);
			kysymysrepository.save(kysymysk2);
			
			Kysymys kysymysk3 = new Kysymys("Mitä musiikkia suosittelet?", kulttuuriKysely, avoin);
			kysymysrepository.save(kysymysk3);
			
			// Kysymykset ruokakyselyyn
			Kysymys kysymysk4 = new Kysymys("Mikä on lempiruokasi?", ruokaKysely, avoin);
			kysymysrepository.save(kysymysk4);
			
			Kysymys kysymysk5 = new Kysymys("Mikä on inhokkiruokasi?", ruokaKysely, avoin);
			kysymysrepository.save(kysymysk5);

			// Vastaukset ruokakyselyyn
			vastausrepository.save(new Vastaus("Makaronilaatikko", kysymysk4));
			vastausrepository.save(new Vastaus("Tilliliha", kysymysk5));
			
			// Vaihtoehdot valintanappi- ja monivalintakysymyksiin (hyvinvointikysely)
			Vaihtoehto valintanappi1 = new Vaihtoehto("19-vuotias tai nuorempi", kysymysh1);
			vaihtoehtorepository.save(valintanappi1);
			Vaihtoehto valintanappi2 = new Vaihtoehto("20–24-vuotias", kysymysh1);
			vaihtoehtorepository.save(valintanappi2);
			Vaihtoehto valintanappi3 = new Vaihtoehto("25–29-vuotias", kysymysh1);
			vaihtoehtorepository.save(valintanappi3);
			Vaihtoehto valintanappi4 = new Vaihtoehto("30–34-vuotias", kysymysh1);
			vaihtoehtorepository.save(valintanappi4);
			Vaihtoehto valintanappi5 = new Vaihtoehto("35–39-vuotias", kysymysh1);
			vaihtoehtorepository.save(valintanappi5);
			Vaihtoehto valintanappi6 = new Vaihtoehto("40-vuotias tai vanhempi", kysymysh1);
			vaihtoehtorepository.save(valintanappi6);
			
			Vaihtoehto valintanappi7 = new Vaihtoehto("Nainen", kysymysh2);
			vaihtoehtorepository.save(valintanappi7);
			Vaihtoehto valintanappi8 = new Vaihtoehto("Mies", kysymysh2);
			vaihtoehtorepository.save(valintanappi8);
			Vaihtoehto valintanappi9 = new Vaihtoehto("Muu", kysymysh2);
			vaihtoehtorepository.save(valintanappi9);
			
			Vaihtoehto valintanappi10 = new Vaihtoehto("1. vuosi", kysymysh3);
			vaihtoehtorepository.save(valintanappi10);
			Vaihtoehto valintanappi11 = new Vaihtoehto("2. vuosi", kysymysh3);
			vaihtoehtorepository.save(valintanappi11);
			Vaihtoehto valintanappi12 = new Vaihtoehto("3. vuosi", kysymysh3);
			vaihtoehtorepository.save(valintanappi12);
			Vaihtoehto valintanappi13 = new Vaihtoehto("4. tai myöhempi vuosi", kysymysh3);
			vaihtoehtorepository.save(valintanappi13);
			
			Vaihtoehto valintanappi14 = new Vaihtoehto("1 – Täysin väärällä", kysymysh4);
			vaihtoehtorepository.save(valintanappi14);
			Vaihtoehto valintanappi15 = new Vaihtoehto("2 – Jokseenkin väärällä", kysymysh4);
			vaihtoehtorepository.save(valintanappi15);
			Vaihtoehto valintanappi16 = new Vaihtoehto("3 – En osaa sanoa", kysymysh4);
			vaihtoehtorepository.save(valintanappi16);
			Vaihtoehto valintanappi17 = new Vaihtoehto("4 – Jonkseenkin oikealla", kysymysh4);
			vaihtoehtorepository.save(valintanappi17);
			Vaihtoehto valintanappi18 = new Vaihtoehto("5 – Täysin oikealla", kysymysh4);
			vaihtoehtorepository.save(valintanappi18);
			
			Vaihtoehto valintanappi19 = new Vaihtoehto("1 – Täysin tyytymätön", kysymysh5);
			vaihtoehtorepository.save(valintanappi19);
			Vaihtoehto valintanappi20 = new Vaihtoehto("2 – Jokseenkin tyytymätön", kysymysh5);
			vaihtoehtorepository.save(valintanappi20);
			Vaihtoehto valintanappi21 = new Vaihtoehto("3 – En osaa sanoa", kysymysh5);
			vaihtoehtorepository.save(valintanappi21);
			Vaihtoehto valintanappi22 = new Vaihtoehto("4 – Jokseenkin tyytyväinen", kysymysh5);
			vaihtoehtorepository.save(valintanappi22);
			Vaihtoehto valintanappi23 = new Vaihtoehto("5 – Täysin tyytyväinen", kysymysh5);
			vaihtoehtorepository.save(valintanappi23);
			
			Vaihtoehto valintanappi24 = new Vaihtoehto("Kyllä", kysymysh6);
			vaihtoehtorepository.save(valintanappi24);
			Vaihtoehto valintanappi25 = new Vaihtoehto("En", kysymysh6);
			vaihtoehtorepository.save(valintanappi25);
			Vaihtoehto valintanappi26 = new Vaihtoehto("Jonkin verran", kysymysh6);
			vaihtoehtorepository.save(valintanappi26);
			
			Vaihtoehto valintanappi27 = new Vaihtoehto("Kyllä", kysymysh7);
			vaihtoehtorepository.save(valintanappi27);
			Vaihtoehto valintanappi28 = new Vaihtoehto("Ei", kysymysh7);
			vaihtoehtorepository.save(valintanappi28);
			Vaihtoehto valintanappi29 = new Vaihtoehto("En halua vastata", kysymysh7);
			vaihtoehtorepository.save(valintanappi29);
			
			Vaihtoehto valintanappi30 = new Vaihtoehto("Kyllä", kysymysh9);
			vaihtoehtorepository.save(valintanappi30);
			Vaihtoehto valintanappi31 = new Vaihtoehto("Ei", kysymysh9);
			vaihtoehtorepository.save(valintanappi31);
			Vaihtoehto valintanappi32 = new Vaihtoehto("En halua vastata", kysymysh9);
			vaihtoehtorepository.save(valintanappi32);
			
			Vaihtoehto valintanappi33 = new Vaihtoehto("Kyllä", kysymysh11);
			vaihtoehtorepository.save(valintanappi33);
			Vaihtoehto valintanappi34 = new Vaihtoehto("Ei", kysymysh11);
			vaihtoehtorepository.save(valintanappi34);
			
			Vaihtoehto valintanappi35 = new Vaihtoehto("Kyllä", kysymysh12);
			vaihtoehtorepository.save(valintanappi35);
			Vaihtoehto valintanappi36 = new Vaihtoehto("Ei", kysymysh12);
			vaihtoehtorepository.save(valintanappi36);
			
			Vaihtoehto valintanappi37 = new Vaihtoehto("Kyllä", kysymysh14);
			vaihtoehtorepository.save(valintanappi37);
			Vaihtoehto valintanappi38 = new Vaihtoehto("En", kysymysh14);
			vaihtoehtorepository.save(valintanappi38);
			
			Vaihtoehto monivalinta1 = new Vaihtoehto("Aamupala", kysymysh16);
			vaihtoehtorepository.save(monivalinta1);
			Vaihtoehto monivalinta2 = new Vaihtoehto("Lounas", kysymysh16);
			vaihtoehtorepository.save(monivalinta2);
			Vaihtoehto monivalinta3 = new Vaihtoehto("Välipala", kysymysh16);
			vaihtoehtorepository.save(monivalinta3);
			Vaihtoehto monivalinta4 = new Vaihtoehto("Päivällinen", kysymysh16);
			vaihtoehtorepository.save(monivalinta4);
			Vaihtoehto monivalinta5 = new Vaihtoehto("Iltapala", kysymysh16);
			vaihtoehtorepository.save(monivalinta5);
			
			log.info("hae kaikki kyselyt"); 
			for (Kysely kysely : kyselyrepository.findAll()) {
				log.info(kysely.toString());
			}
		};
	}
}