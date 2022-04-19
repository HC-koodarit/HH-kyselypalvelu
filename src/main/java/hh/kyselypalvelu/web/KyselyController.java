package hh.kyselypalvelu.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

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
		@RequestMapping("/addkysely")
		public String lisaaKysely(Model model) {
			model.addAttribute("kysely", new Kysely());
			return "lisaakysely";
		}
	
		// tallenna kysely
		@PostMapping("/savekysely")
		public String tallennaKysely(Kysely kysely) {
			kyselyRepository.save(kysely);
			return "redirect:/kyselylista";
		}
	
		// muokkaa kyselyä
		@RequestMapping("/kyselylista/edit/{id}")
		public String muokkaaKyselya(@PathVariable("id") Long kyselyId, Model model) {
			model.addAttribute("kyselyId", kyselyId);
			model.addAttribute("kysely", kyselyRepository.findById(kyselyId).get());
			model.addAttribute("kysymykset", kyselyRepository.findById(kyselyId).get().getKysymykset());
			model.addAttribute("kysymys", new Kysymys());
			return "lisaakysymyksia";
		}
	
		// lisää kysymys
		@PostMapping("/kyselylista/edit/{id}/save")
		public String tallennaKysymys(@PathVariable("id") Long kyselyId, Kysymys kysymys) {
			kysymysRepository.save(kysymys);
			return "redirect:/kyselylista/edit/{id}";
		}
		
		@PostMapping("/edit/{id}/save")
		public String editKysymys(@PathVariable("id") Long kyselyId, Kysymys kysymys) {
			kysymysRepository.save(kysymys);
			return "redirect:/addkysymys/{id}";
		}
		

		// Muokkaa kyselyn kysymyksiä
		@GetMapping("/edit/{id}")
		public String muokkaaKyselyTesti(@PathVariable("id") Long id, Model model) {
			model.addAttribute("kysely", kyselyRepository.findById(id));
			model.addAttribute("kysymys", kysymysRepository.findAll());
			return "muokkaakyselyita";
		}
		
		// näytä rest kotisivu
		@GetMapping(value="/rest")
		public String restPage(Model model) {
			return "rest";
			}
}