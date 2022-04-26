package hh.kyselypalvelu.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.kyselypalvelu.domain.Kysely;
import hh.kyselypalvelu.domain.KyselyRepository;
import hh.kyselypalvelu.domain.Kysymys;
import hh.kyselypalvelu.domain.KysymysRepository;

@CrossOrigin
@Controller
public class KyselyController {

	@Autowired
	private KyselyRepository kyselyRepository;

	@Autowired
	private KysymysRepository kysymysRepository;

	//REST-Appit
		
		// REST-palvelu: näytä kaikki kyselyt
		@GetMapping("/kyselyt")
		public @ResponseBody List<Kysely> kyselylistaRest() {
			return (List<Kysely>) kyselyRepository.findAll();
		}
		
		//REST-palvelu: näytä kysely id:n perusteella
		@GetMapping("/kyselyt/{id}")
		public @ResponseBody Optional<Kysely> findKyselyByIdRest(@PathVariable("id") Long kyselyId) {
			return kyselyRepository.findById(kyselyId);
		}
		
		
	// Endpointit ja toiminnallisuudet
		// näytä kaikki kyselyt
		@GetMapping("/kyselylista")
		public String getKyselyt(Model model) {
			model.addAttribute("kyselyt", kyselyRepository.findAll());
			return "kyselylista";
		}
	
		// lisää uusi kysely
		@RequestMapping("/lisaakysely")
		public String lisaaKysely(Model model) {
			model.addAttribute("kysely", new Kysely());
			return "lisaakysely";
		}
	
		// tallenna kysely
		@RequestMapping(value ="/savekysely", method = RequestMethod.POST)
		public String tallennaKysely(Kysely kysely) {
			System.out.println(kysely);
			kyselyRepository.save(kysely);
			return "redirect:/kyselylista";
		}

	
		// lisää kysymyksiä kyselyyn. 
		@RequestMapping("/kyselylista/edit/{id}")
		public String lisaaKysymyksia(@PathVariable("id") Long kyselyId, Model model) {
			model.addAttribute("kyselyId", kyselyId);
			model.addAttribute("kysely", kyselyRepository.findById(kyselyId).get());
			model.addAttribute("kysymykset", kyselyRepository.findById(kyselyId).get().getKysymykset());
			model.addAttribute("kysymys", new Kysymys());
			return "lisaakysymyksia";
		}
		
		// Muokkaa kyselyn tietoja(nimi, kuvaus)
		@RequestMapping(value = "/muokkaakyselya/{id}", method = RequestMethod.GET)
		public String muokkaaKyselya(@PathVariable("id") Long kyselyid, Model model) {
			model.addAttribute("kysely", kyselyRepository.findById(kyselyid));
			return "muokkaakyselya";

		}
	
		// tallenna luotu kysely
		@RequestMapping(value ="/{id}/savekysely", method = RequestMethod.POST)
		public String tallennaKysely(@PathVariable("id") Long kyselyId, Kysely kysely, Model model) {
			model.addAttribute("kysely", kyselyRepository.findById(kyselyId));
			kyselyRepository.save(kysely);
			return "redirect:/kyselylista";
		}
		
		// tallenna ___
		@PostMapping("/edit/{id}/save")
		public String editKysymys(@PathVariable("id") Long kyselyId, Kysymys kysymys) {
			kysymysRepository.save(kysymys);
			return "redirect:/kysely/{id}"; // entinen addkysymys
		}
		
		@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
		public String deletekysely(@PathVariable("id") Long kyselyId, Model model) {
			kyselyRepository.deleteById(kyselyId);
	        return "redirect:../kyselylista";
	}

		// kopioi kysely
		@RequestMapping("/kyselylista/copy/{id}")
		public String kopioiKysely(@PathVariable("id") Long kyselyId, Model model) {
			model.addAttribute("kyselyId", kyselyId);
			model.addAttribute("kysely", kyselyRepository.findById(kyselyId).get());
			model.addAttribute("kysymykset", kyselyRepository.findById(kyselyId).get().getKysymykset());
			model.addAttribute("kysymys", new Kysymys());
			return "kopioikysely";
		}
		
		// näytä rest kotisivu
		// rest kotisivu
		@GetMapping(value="/rest")
		public String restPage(Model model) {
			return "rest";
		}

}