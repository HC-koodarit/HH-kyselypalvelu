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
import org.springframework.web.bind.annotation.ResponseBody;

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
					
		// tallenna uusi kysymys
		@PostMapping("/kysely/{id}/save")
		public String tallennaKysymys(@PathVariable("id") Long kyselyId, Kysymys kysymys) {
			kysymysRepository.save(kysymys);
			return "redirect:/kysely/{id}";	// vanha addkysymys
		}
		
		// Muokkaa kysymysta.
		@RequestMapping(value = "/muokkaakysymysta/{id}")
		public String muokkaKysymysta(@PathVariable("id") Long kysymysId, Model model) {
			model.addAttribute("kysymys", kysymysRepository.findById(kysymysId));
			return "muokkaakysymysta";
		}
		
		//Tämä toimii! :3
		// Tallenna muokattu kysymys
		@PostMapping("/muokkaakysymysta/save")
		public String muokkaaKyselynKysymysta(Kysymys kysymys) {
			kysymysRepository.save(kysymys);
			//kysytään kysymykseltä kyselyid
			Long kyselyid = kysymys.getKysely().getKyselyid();
			return "redirect:/kysely/" + kyselyid;
		}
		/*
		@GetMapping("/poistakysymys/{id}")
		public String poistaKysymys(@PathVariable("id") Long kysymysid, Model model, Kysymys kysymys) {
			Long kyselyid = kysymys.getKysely().getKyselyid();
			kysymysRepository.deleteById(kysymysid);
			return "redirect:/kysely/" + kyselyid;
		}
		 */
		// Tämä toimii kunnes keksitään ratkaisu redirectiin
		@GetMapping(value = "/poistakysymys/{id}")
		public String poistakysymys(@PathVariable("id") Long kysymysid, Model model) {
			kysymysRepository.deleteById(kysymysid);
			return "redirect:../kyselylista";
		}
}