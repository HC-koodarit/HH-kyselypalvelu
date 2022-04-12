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
	private KyselyRepository kyselyrepository;

	@Autowired
	private KysymysRepository kysymysrepository;

	//REST-Appit
		// REST-palvelu: näytä kaikki kyselyt
		@CrossOrigin
		@GetMapping("/kaikki")
		public @ResponseBody List<Kysymys> kyselytjakysymyksetRest() {
			return (List<Kysymys>) kysymysrepository.findAll();
		}
		
		// REST-palvelu: näytä kaikki kyselyt
		@CrossOrigin
		@GetMapping("/kyselyt")
		public @ResponseBody List<Kysely> kyselylistaRest() {
			return (List<Kysely>) kyselyrepository.findAll();
		}
		
		//REST-palvelu: näytä kysely id:n perusteella
		@CrossOrigin
		@GetMapping("/kyselyt/{id}")
		public @ResponseBody Optional<Kysely> findKyselyByIdRest(@PathVariable("id") Long kyselyId) {
			return kyselyrepository.findById(kyselyId);
		}
		
		//REST-palvelu: näytä tietyn kyselyn kysymykset
		@CrossOrigin
		@GetMapping("/kyselyt/{id}/kysymykset")
		public @ResponseBody List<Kysymys> findKysymyksetByKyselyIdRest(@PathVariable("id") Long kyselyId) {
			return kyselyrepository.findById(kyselyId).get().getKysymykset();
	
		}
		
	// Endpointit ja toiminnallisuudet
		// näytä kaikki kyselyt
		@GetMapping("/kyselylista")
		public String getKyselyt(Model model) {
			model.addAttribute("kyselyt", kyselyrepository.findAll());
			return "kyselylista";
		}
	
		// lisää uusi kysely
		@RequestMapping("/kyselylista/add")
		public String lisaaKysely(Model model) {
			model.addAttribute("kysely", new Kysely());
			return "lisaakysely";
		}
	
		// tallenna kysely
		@PostMapping("/savekysely")
		public String tallennaKysely(Kysely kysely) {
			kyselyrepository.save(kysely);
			return "redirect:/kyselylista";
		}
	
		// muokkaa kyselyä
		@RequestMapping("/kyselylista/edit/{id}")
		public String muokkaaKyselya(@PathVariable("id") Long kyselyId, Model model) {
			model.addAttribute("kyselyId", kyselyId);
			model.addAttribute("kysely", kyselyrepository.findById(kyselyId).get());
			model.addAttribute("kysymykset", kyselyrepository.findById(kyselyId).get().getKysymykset());
			model.addAttribute("kysymys", new Kysymys());
			return "lisaakysymyksia";
		}
	
		// lisää kysymys
		@PostMapping("/kyselylista/edit/{id}/save")
		public String tallennaKysymys(@PathVariable("id") Long kyselyId, Kysymys kysymys) {
			kysymysrepository.save(kysymys);
			return "redirect:/kyselylista/edit/{id}";
		}
}