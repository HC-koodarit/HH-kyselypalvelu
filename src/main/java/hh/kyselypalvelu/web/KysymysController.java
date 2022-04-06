package hh.kyselypalvelu.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.kyselypalvelu.domain.KyselyRepository;
import hh.kyselypalvelu.domain.Kysymys;
import hh.kyselypalvelu.domain.KysymysRepository;

@Controller
public class KysymysController {

	@Autowired
	private KysymysRepository kysymysRepository;

	private KyselyRepository kyselyRepository;

	@RequestMapping(value = "/lisaakysymys")
	public String lisaaKysymys(Model model) {
		model.addAttribute("kysymys", new Kysymys());
		return "lisaakysymys";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Kysymys kysymys) {
		kysymysRepository.save(kysymys);
		return "redirect:/_____";
	}
}
