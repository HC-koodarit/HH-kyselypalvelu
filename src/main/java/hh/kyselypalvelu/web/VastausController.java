package hh.kyselypalvelu.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.kyselypalvelu.domain.Vastaus;
import hh.kyselypalvelu.domain.VastausRepository;

@CrossOrigin
@Controller
public class VastausController {

	@Autowired
	private VastausRepository vastausRepository;

	// REST-palvelu: näytä kaikki vastaukset, ei käytösssä
	/*
	@GetMapping("/vastaukset")
	public @ResponseBody List<Vastaus> vastauslistaRest() {
		return (List<Vastaus>) vastausRepository.findAll();
	}
	*/

	// yhden vastauksen tallentaminen, ei käytössä
	/*
	 * // REST-palvelu: tallenna vastaus
	 * 
	 * @PostMapping("/vastaukset") public @ResponseBody Vastaus
	 * saveVastausRest(@RequestBody Vastaus vastaus) { return
	 * vastausRepository.save(vastaus); }
	 */

	// REST-palvelu: tallenna monta vastausta
	@PostMapping("/vastaukset")
	public @ResponseBody Iterable<Vastaus> saveVastauksetRest(@RequestBody Iterable<Vastaus> vastaukset) {
		return vastausRepository.saveAll(vastaukset);
	}

	// REST-palvelu: näytä tietyn kyselyn vastaukset
	@GetMapping("/kyselyt/{id}/vastaukset")
	public @ResponseBody List<Vastaus> vastauslistaByKyselyRest(@PathVariable("id") Long kyselyId) {
		return vastausRepository.findByKyselyId(kyselyId);
	}

	// REST-palvelu: näytä tietyn kysymyksen vastaukset
	@GetMapping("/kysymykset/{id}/vastaukset")
	public @ResponseBody List<Vastaus> vastauslistaByKysymysRest(@PathVariable("id") Long kysymysId) {
		return vastausRepository.findByKysymysId(kysymysId);
	}

}
