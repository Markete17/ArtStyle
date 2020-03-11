package art.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import art.main.database.Painting;
import art.main.database.PaintingRepository;

@Controller
public class PaintingController {
	
	@Autowired
	private PaintingRepository paintingRepository;
	
	@GetMapping("/paintings")
	public String loadPaintings(Model model) {
		model.addAttribute("paintings",paintingRepository.findAll());
		return "painting";
	}
	
	@GetMapping("/")
	public String loadHome() {
		return "index";
	}
	
	@PostMapping("/added_painting")
	public String newPainting(Painting painting) {
		paintingRepository.save(painting);
		return "painting_form";
	}

}
