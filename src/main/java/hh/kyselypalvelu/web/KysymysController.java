package hh.kyselypalvelu.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.kyselypalvelu.domain.Kysely;
import hh.kyselypalvelu.domain.Kysymys;
import hh.kyselypalvelu.domain.KysymysRepository;

public class KysymysController {
	
	private KysymysRepository kysymysRepository;
	
	@RequestMapping(value="/lisaakysymys")
	public String lisaaKysymys(Model model) {
		model.addAttribute("kysymys", new Kysymys());
		return "lisaakysymys";	
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Kysely kysely) {
		kysymysRepository.save(kysely);
		return "redirect:/_____";
	}

}
