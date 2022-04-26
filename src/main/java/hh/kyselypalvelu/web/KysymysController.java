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
		
		// KORVAA ALLA OLEVAN -> siirto KyselyControlleriin
		// Avaa kysely -> esille muokkaa, lisää ja poistotoiminnot
		@RequestMapping("/kysely/{id}")
		public String avaaKysely(@PathVariable("id") Long kyselyId, Model model) {
			model.addAttribute("kysymys", new Kysymys());
			model.addAttribute("kyselyId", kyselyId);
			model.addAttribute("kysely", kyselyRepository.findById(kyselyId).get());
			model.addAttribute("kysymykset", kyselyRepository.findById(kyselyId).get().getKysymykset());
			return "kysely";
		}
		
		// YLLÄOLEVA TULEE KORVAAMAAN TÄMÄN!!!!!!!!!!!
		// lisää kyselyyn kysymyksiä 
		@RequestMapping("/addkysymys/{id}")
		public String muokkaaKyselya(@PathVariable("id") Long kyselyId, Model model) {
			model.addAttribute("kysymys", new Kysymys());
			model.addAttribute("kyselyId", kyselyId);
			model.addAttribute("kysely", kyselyRepository.findById(kyselyId).get());
			model.addAttribute("kysymykset", kyselyRepository.findById(kyselyId).get().getKysymykset());
			return "lisaakysymyksia";
		}
			
		// lisää kysymys kyselyyn
		@PostMapping("/addkysymys/{id}/save")
		public String tallennaKysymys(@PathVariable("id") Long kyselyId, Kysymys kysymys) {
			kysymysRepository.save(kysymys);
			return "redirect:/addkysymys/{id}"; // TODO: Muokkaa -> "redirect:/kysely/{id}"
		}
		
		// TODO: Tön poisto myöhemmin, tulee olemaan turha
		// Muokkaa koko kyselyä (nimi, kuvaus, kysymykset)
		@GetMapping("/muokkaa/{id}")
		public String muokkaaKyselynKysymyksiaTesti(@PathVariable("id") Long kyselyId, Model model) {
			model.addAttribute("kyselyId", kyselyId);
			model.addAttribute("kysymys", kyselyRepository.findById(kyselyId).get());
			model.addAttribute("kyselyt", kyselyRepository.findAll());
			model.addAttribute("kysymykset", kyselyRepository.findById(kyselyId).get().getKysymykset());
			return "muokkaakysymyksia";
		}
		
		// TODO: Tön poisto myöhemmin, tulee olemaan turha
		// tallenna muokkausmuutokset
		@PostMapping("/muokkaa/{id}/save")
		public String muokkaaKyselynKysymyksiaSaveTesti(@PathVariable("id") Long kyselyId, Kysymys kysymys, Kysely kysely) {
			kysymysRepository.save(kysymys);
			return "redirect:/addkysymys/{id}"; // TODO: Muokkaa -> "redirect:/kysely/{id}"
		}
		
		// TODO: Tämä endpoint: muokkaa kysymystä (?)
		/* 
		// Muokkaa kyselyn kysymyksiä
		@GetMapping("/edit/{id}")
		public String muokkaaKyselyTesti(@PathVariable("id") Long id, Model model) {
			model.addAttribute("kysely", kyselyRepository.findById(id));
			model.addAttribute("kysymys", kysymysRepository.findAll()); 	// findById (?)
			return "muokkaakyselya";	// muokkaakysymysta (?)
		}
		*/
		/*
		// TODO: Tallenna muokattu kysymys
		@PostMapping("/edit/{id}/save")
		public String editKysymys(@PathVariable("id") Long kyselyId, Kysymys kysymys) {					kysymysRepository.save(kysymys);
			return "redirect:/addkysymys/{id}"; // TODO: Muokkaa -> "redirect:/kysely/{id}"
		}
		*/
}