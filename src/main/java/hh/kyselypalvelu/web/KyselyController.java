package hh.kyselypalvelu.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.kyselypalvelu.domain.Kysely;
import hh.kyselypalvelu.domain.KyselyRepository;
import hh.kyselypalvelu.domain.Kysymys;

@Controller
public class KyselyController {
	
	@Autowired
	private KyselyRepository kyselyRepository;
	
	// näytä kaikki kyselyt
	@GetMapping("/kyselylista")
	public String getKyselyt(Model model) {
		model.addAttribute("kyselyt", kyselyRepository.findAll());
		return "kyselylista";
	}
	
	// lisää uusi kysely
	@RequestMapping("/kyselylista/add")
	public String lisaaKysely(Model model) {
		model.addAttribute("kysely", new Kysely());
		return "lisaakysely";
	}
	
	// tallenna kysely
	@PostMapping("/kyselylista/save")
	public String tallennaKysely(Kysely kysely) {
		kyselyRepository.save(kysely);
		return "redirect:/kyselylista";
	}
	
	// muokkaa kyselyä
	@RequestMapping("/kyselylista/edit/{id}")
	public String muokkaaKyselya(@PathVariable("id") Long kyselyId, Model model) {
		model.addAttribute("book", kyselyRepository.findById(kyselyId));
		return "lisaakysymyksia";
	}
	
}
