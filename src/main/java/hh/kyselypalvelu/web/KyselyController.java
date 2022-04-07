package hh.kyselypalvelu.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import hh.kyselypalvelu.domain.Kysely;
import hh.kyselypalvelu.domain.KyselyRepository;
import hh.kyselypalvelu.domain.Kysymys;
import hh.kyselypalvelu.domain.KysymysRepository;

@CrossOrigin
@Controller
public class KyselyController {

	@Autowired
	private KyselyRepository kyselyRepository;

	@Autowired
	private KysymysRepository kysymysRepository;

	// REST-palvelu: näytä kaikki kyselyt
	@CrossOrigin
	@GetMapping("/kaikki")
	public @ResponseBody List<Kysymys> kyselytjakysymyksetRest() {
		return (List<Kysymys>) kysymysRepository.findAll();
	}
	
	// REST-palvelu: näytä kaikki kyselyt
	@CrossOrigin
	@GetMapping("/kyselyt")
	public @ResponseBody List<Kysely> kyselylistaRest() {
		return (List<Kysely>) kyselyRepository.findAll();
	}
	
<<<<<<< HEAD
	@GetMapping("/kyselyt/{id}")
	public @ResponseBody List<Kysely> kyselyKysymyksetRest() {
		return (List<Kysely>) kyselyRepository.findAll();
	}
	
	//REST-palvelu: näytä kirja id:n perusteella
	@GetMapping("/kyselylista{id}")
	public @ResponseBody Optional<Kysely> findKyselyRest(@PathVariable("id") Long id) {
		return kyselyRepository.findById(id);
=======
	//REST-palvelu: näytä kysely id:n perusteella
	@CrossOrigin
	@GetMapping("/kyselyt/{id}")
	public @ResponseBody Optional<Kysely> findKyselyByIdRest(@PathVariable("id") Long kyselyId) {
		return kyselyRepository.findById(kyselyId);
	}
	
	//REST-palvelu: näytä tietyn kyselyn kysymykset
	@CrossOrigin
	@GetMapping("/kyselyt/{id}/kysymykset")
	public @ResponseBody List<Kysymys> findKysymyksetByKyselyIdRest(@PathVariable("id") Long kyselyId) {
		return kyselyRepository.findById(kyselyId).get().getKysymykset();
>>>>>>> ac801b305e393a4109f81b5010adc6bfcfbe0679
	}
	
	// näytä kaikki kyselyt
	@GetMapping("/kyselylista")
	public String getKyselyt(Model model) {
		model.addAttribute("kyselyt", kyselyRepository.findAll());
		return "kyselylista";
	}

	// lisää uusi kysely
	@RequestMapping("/kyselylista/add")
	public String lisaaKysely(Model model) {
		model.addAttribute("kysely", new Kysely());
		return "lisaakysely";
	}

	// tallenna kysely
	@PostMapping("/kyselylista/save")
	public String tallennaKysely(Kysely kysely) {
		kyselyRepository.save(kysely);
		return "redirect:/kyselylista";
	}

	// muokkaa kyselyä
	@RequestMapping("/kyselylista/edit/{id}")
	public String muokkaaKyselya(@PathVariable("id") Long kyselyId, Model model) {
		model.addAttribute("kyselyId", kyselyId);
		model.addAttribute("kysely", kyselyRepository.findById(kyselyId).get());
		model.addAttribute("kysymykset", kyselyRepository.findById(kyselyId).get().getKysymykset());
		model.addAttribute("kysymys", new Kysymys());
		return "lisaakysymyksia";
	}

	// lisää kysymys
	@PostMapping("/kyselylista/edit/{id}/save")
	public String tallennaKysymys(@PathVariable("id") Long kyselyId, Kysymys kysymys) {
		kysymysRepository.save(kysymys);
		return "redirect:/kyselylista/edit/{id}";
	}
}