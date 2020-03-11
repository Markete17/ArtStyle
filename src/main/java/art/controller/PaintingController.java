package art.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import art.database.*;

@Controller
public class PaintingController {
	
	@Autowired
	private PaintingRepository paintingRepository;
	
	@GetMapping("/paintings")
	public String loadPage(Model model) {
		model.addAttribute("paintings",paintingRepository.findAll());
		return "painting";
	}
	
	@PostMapping("/paint_success")
	public String newPainting(Painting painting) {
		paintingRepository.save(painting);
		return "paintingform";
	}

}
