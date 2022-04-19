package hh.kyselypalvelu.web;

import java.util.List;

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
public class KysymysController {

	@Autowired
	private KysymysRepository kysymysRepository;

	@Autowired
	private KyselyRepository kyselyRepository;
	
	//REST-appit
		//Get kysymykset REST
		@GetMapping("/kysymykset")
		public @ResponseBody List<Kysymys> kysymyslistaRest() {
			return (List<Kysymys>) kysymysRepository.findAll();
		}
		
	//Endpointsit
		//Kysymyslista
		@RequestMapping(value = "/kysymyslista")
		public String kysymyslista(Model model) {
			model.addAttribute("kysymykset", kysymysRepository.findAll());
			return "kysymyslista";
		}
		
		// muokkaa kyselyä
		@RequestMapping("/addkysymys/{id}")
		public String muokkaaKyselya(@PathVariable("id") Long kyselyId, Model model) {
			model.addAttribute("kyselyId", kyselyId);
			model.addAttribute("kysely", kyselyRepository.findById(kyselyId).get());
			model.addAttribute("kysymykset", kyselyRepository.findById(kyselyId).get().getKysymykset());
			model.addAttribute("kysymys", new Kysymys());
			return "lisaakysymyksia";
		}
			
		// lisää kysymys
		@PostMapping("/addkysymys/{id}/save")
		public String tallennaKysymys(@PathVariable("id") Long kyselyId, Kysymys kysymys) {
			kysymysRepository.save(kysymys);
			return "redirect:/addkysymys/{id}";
		}
		
		@GetMapping("/muokkaa/{id}")
		public String muokkaaKyselynKysymyksiaTesti(@PathVariable("id") Long kyselyid, Model model) {
			model.addAttribute("kyselyid", kyselyid);
			model.addAttribute("kysymys", kyselyRepository.findById(kyselyid).get());
			model.addAttribute("kysymykset", kyselyRepository.findById(kyselyid).get().getKysymykset());
			model.addAttribute("kysymys", new Kysymys());
			return "muokkaakysymyksia";
		}
		
		@PostMapping("/muokkaa/{id}/save")
		public String muokkaaKyselynKysymyksiaSaveTesti(@PathVariable("id") Long kyselyId, Kysymys kysymys, Kysely kysely) {
			kysymysRepository.save(kysymys);
			kyselyRepository.save(kysely);
			return "redirect:/addkysymys/{id}";
		}
		
		@PostMapping("/savekysymykset")
		public String muokkaaKyselynKysymyksetTesti(@PathVariable("id") Long kyselyId, Kysymys kysymys) {
			kysymysRepository.save(kysymys);
			return "redirect:/addkysymys/{id}";
		}
}