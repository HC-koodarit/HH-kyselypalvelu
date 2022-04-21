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

import hh.kyselypalvelu.domain.KyselyRepository;
import hh.kyselypalvelu.domain.KysymysRepository;
import hh.kyselypalvelu.domain.Vastaus;
import hh.kyselypalvelu.domain.VastausRepository;

@CrossOrigin
@Controller
public class VastausController {
	
	@Autowired
	private VastausRepository vastausRepository;
	
	@Autowired
	private KyselyRepository kyselyRepository;

	@Autowired
	private KysymysRepository kysymysRepository;

	// REST-palvelu: näytä kaikki vastaukset
	@GetMapping("/vastaukset")
	public @ResponseBody List<Vastaus> vastauslistaRest() {
		return (List<Vastaus>) vastausRepository.findAll();
	}
	
	// REST-palvelu: tallenna vastaus
	@PostMapping("/vastaukset")
	public @ResponseBody Vastaus saveVastausRest(@RequestBody Vastaus vastaus) {
		return vastausRepository.save(vastaus);
	}
	
	// REST-palvelu: näytä tietyn kyselyn vastaukset
	@GetMapping("/kyselyt/{id}/vastaukset")
	public @ResponseBody List<Vastaus> vastauslistaByKyselyRest(@PathVariable("id") Long kyselyId) {
		return vastausRepository.findByKyselyId(kyselyId);
	}

}
