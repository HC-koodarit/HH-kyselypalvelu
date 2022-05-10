package hh.kyselypalvelu.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.kyselypalvelu.domain.Kysely;
import hh.kyselypalvelu.domain.KysymysRepository;
import hh.kyselypalvelu.domain.Vaihtoehto;
import hh.kyselypalvelu.domain.VaihtoehtoRepository;

@Controller
public class VaihtoehtoController {
	
	@Autowired
	private VaihtoehtoRepository vaihtoehtoRepository;
	
	@Autowired
	private KysymysRepository kysymysRepository;
	
	// REST
	// Get vaihtoehdot REST
	@GetMapping("/vaihtoehdot")
	public @ResponseBody List<Vaihtoehto> vaihtoehtolistaRest() {
		return (List<Vaihtoehto>) vaihtoehtoRepository.findAll();
	}

	// Endpointsit
	// TODO: Vaihtoehtolista	// ei tällähetkellä käyttöä
	@RequestMapping(value = "/vaihtoehtolista")
	public String vaihtoehtolista(Model model) {
		model.addAttribute("vaihtoehdot", vaihtoehtoRepository.findAll());
		return "monivalintakysymys";		// TODO: endpoint
	}
	
	// Lisaa vastausvaihtoehto ja näytä jo luodut
	@RequestMapping(value = "/lisaavaihtoehto/{id}")
	public String lisaaVaihtoehto(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vaihtoehto", new Vaihtoehto());
		model.addAttribute("id", id);
		model.addAttribute("kysymys", kysymysRepository.findById(id).get());				
		model.addAttribute("vaihtoehdot", kysymysRepository.findById(id).get().getVaihtoehto());	// hakee kaikki vaihtoehdot
		return "monivalintakysymys";
	}
	
	
	// Tallenna vastausvaihtoehto monivalintakysymykseen
	@PostMapping("/lisaavaihtoehto/{id}/save")
	public String lisaaVaihtoehtoSave(@PathVariable("id") Long kysymysId, Vaihtoehto vaihtoehto) {
		vaihtoehtoRepository.save(vaihtoehto);
		return "redirect:/kysely/{id}";
	}
	
	// Muokkaa vaihtoehtoa		// ei testattu
	@RequestMapping(value="/muokkaavaihtoehtoa/{id}")
	public String muokkaaVaihtoehtoa(@PathVariable("id") Long vaihtoehtoId, Model model) {
		model.addAttribute("vaihtoehto", vaihtoehtoRepository.findById(vaihtoehtoId));
		return "muokkaavaihtoehtoa";	// huom! luo template :P
	}
	
	// Tallenna muokattu vaihtoehto		// ei testattu
	@PostMapping("/muokkaavaihtoehtoa/save")
	public String muokkaaVaihtoehtoaSave(Vaihtoehto vaihtoehto) {
		vaihtoehtoRepository.save(vaihtoehto);
		// kysytään vaihtoehdolta kysely id
		Long kyselyid = vaihtoehto.getKysymys().getKysely().getId();
		return "redirect:/kysely/" + kyselyid;
	}
	
	// TODO: Poista vaihtoehto kysymyksestä		// ei testattu
	@GetMapping("/poistavaihtoehto/{id}")
	public String poistaVaihtoehto(@PathVariable("id") Long id, Model model) {
		Vaihtoehto vaihtoehto = vaihtoehtoRepository.findById(id).get();
		Long kyselyid = vaihtoehto.getKysymys().getKysely().getId();
		vaihtoehtoRepository.deleteById(id);
		return "redirect:/kysely/" + kyselyid;	// tai jonnekki muualle
	}
	

}