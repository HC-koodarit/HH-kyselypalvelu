package hh.kyselypalvelu.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

@Controller
public class KysymysController {

	@Autowired
	private KysymysRepository kysymysRepository;

	private KyselyRepository kyselyRepository;
	
	//REST-palvelu: näytä kaikki kysymykset
	@GetMapping("/kysymykset")
	public @ResponseBody List<Kysymys> kysymyslistaRest() {
		return (List<Kysymys>) kysymysRepository.findAll();
	}
	
}