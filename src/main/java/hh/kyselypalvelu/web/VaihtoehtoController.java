package hh.kyselypalvelu.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.kyselypalvelu.domain.Vaihtoehto;
import hh.kyselypalvelu.domain.VaihtoehtoRepository;

@Controller
public class VaihtoehtoController {
	
	@Autowired
	private VaihtoehtoRepository vaihtoehtoRepository;
	
	
	// REST
	// Get vaihtoehdot REST
	@GetMapping("/vaihtoehdot")
	public @ResponseBody List<Vaihtoehto> vaihtoehtolistaRest() {
		return (List<Vaihtoehto>) vaihtoehtoRepository.findAll();
	}
	

	// Endpointsit
	// TODO: Vaihtoehtolista
	@RequestMapping(value = "/vaihtoehtolista")
	public String vaihtoehtolista(Model model) {
		model.addAttribute("vaihtoehdot", vaihtoehtoRepository.findAll());
		return "";		// TODO: endpoint
	}
	
	
	// TODO: Tallenna uusi vastausvaihtoehto monivalintakysymykseen
	
	/*@PostMapping("/kysely/{id}/vaihtoehto/tallenna")
	public String tallennaVaihtoehto) {
		
		return "redirect:/kysely/{id}";
	} */
	
	// TODO: Muokkaa vaihtoehtoa
	
	
	// TODO: Poista vaihtoehto
	

}
