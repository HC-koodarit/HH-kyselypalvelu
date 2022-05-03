package hh.kyselypalvelu.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.kyselypalvelu.domain.Kysymystyyppi;
import hh.kyselypalvelu.domain.KysymystyyppiRepository;

@Controller
public class KysymystyyppiController {
	
	@Autowired
	private KysymystyyppiRepository kysymystyyppiRepository;
	
	// REST
	// GET Kysymystyypit REST
	@GetMapping("/kysymystyypit")
	public @ResponseBody List<Kysymystyyppi> kysymystyyppilistaRest() {
		return (List<Kysymystyyppi>) kysymystyyppiRepository.findAll();
	}
	
	// Endpointsit
	
	

}