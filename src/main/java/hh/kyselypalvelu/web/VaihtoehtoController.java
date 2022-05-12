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
import hh.kyselypalvelu.domain.KysymystyyppiRepository;
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
	
	// Lisää vastausvaihtoehto ja näytä jo luodut
	@RequestMapping(value = "/lisaavaihtoehto/{id}")
	public String lisaaVaihtoehto(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vaihtoehto", new Vaihtoehto());
		model.addAttribute("id", id);
		model.addAttribute("kysymys", kysymysRepository.findById(id).get());				
		model.addAttribute("vaihtoehdot", kysymysRepository.findById(id).get().getVaihtoehdot());
		return "monivalintakysymys";
	}
	
	
	// Tallenna vastausvaihtoehto monivalintakysymykseen
	@PostMapping("/lisaavaihtoehto/save")
	public String lisaaVaihtoehtoSave(Vaihtoehto vaihtoehto) {
		vaihtoehtoRepository.save(vaihtoehto);
		return "redirect:/lisaavaihtoehto/" + vaihtoehto.getKysymys().getId();
	}
	
	// Muokkaa vaihtoehtoa		// ei testattu
	@RequestMapping(value="/muokkaavaihtoehtoa/{id}")
	public String muokkaaVaihtoehtoa(@PathVariable("id") Long vaihtoehtoId, Model model) {
		model.addAttribute("vaihtoehto", vaihtoehtoRepository.findById(vaihtoehtoId).get());
		return "muokkaavaihtoehtoa";
	}
	
	// Tallenna muokattu vaihtoehto		// ei testattu
	@PostMapping("/muokkaavaihtoehtoa/save")
	public String muokkaaVaihtoehtoaSave(Vaihtoehto vaihtoehto) {
		vaihtoehtoRepository.save(vaihtoehto);
		return "redirect:/lisaavaihtoehto/" + vaihtoehto.getKysymys().getId();
	}
	
	// Poista vaihtoehto kysymyksestä
	@GetMapping("/poistavaihtoehto/{id}")
	public String poistaVaihtoehto(@PathVariable("id") Long id) {
		Long kysymysid = vaihtoehtoRepository.findById(id).get().getKysymys().getId();
		vaihtoehtoRepository.deleteById(id);
		return "redirect:/lisaavaihtoehto/" + kysymysid;
	}
	

}