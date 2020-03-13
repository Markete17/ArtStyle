package art.main.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping("/productSortBy")
	public String sortBy(Model model,@RequestParam String value) {
		switch(value) {
		case "PriceDesc":
			model.addAttribute("paintings", this.paintingRepository.OrderByPriceDesc());
			break;
		case "PriceAsc":
			model.addAttribute("paintings", this.paintingRepository.OrderByPriceAsc());
			break;
		case "YearDesc":
			model.addAttribute("paintings", this.paintingRepository.OrderByYearDesc());
			break;
		case "YearAsc":
			model.addAttribute("paintings", this.paintingRepository.OrderByYearAsc());
			break;
		}
		return "painting";
	}
	
	@PostMapping("/added_painting")
	public String newPainting(Painting painting) {
		paintingRepository.save(painting);
		return "painting_form";
	}

}
