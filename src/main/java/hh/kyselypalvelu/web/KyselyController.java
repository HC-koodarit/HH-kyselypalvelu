package hh.kyselypalvelu.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.kyselypalvelu.domain.Kysely;
import hh.kyselypalvelu.domain.KyselyRepository;

@Controller
public class KyselyController {
	
	private KyselyRepository kyselyRepository;
	
	// TODO: näytä kaikki kyselyt
	
	
	// TODO: lisää kysely kysymyksineen
	@RequestMapping(value="/lisaakysely")
	public String lisaaKysely(Model model) {
		model.addAttribute("kysely", new Kysely());
		return "lisaakysely";
		
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Kysely kysely) {
		kyselyRepository.save(kysely);
		return "redirect:/_____";
	}
	
}
