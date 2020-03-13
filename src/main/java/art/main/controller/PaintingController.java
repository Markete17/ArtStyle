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

import art.main.database.Artist;
import art.main.database.ArtistRepository;
import art.main.database.Client;
import art.main.database.ClientRepository;
import art.main.database.Painting;
import art.main.database.PaintingRepository;
import art.main.database.PaintingService;

@Controller
public class PaintingController {
	
	@Autowired
	private PaintingRepository paintingRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ArtistRepository artistRepository;
	
	@Autowired
	private PaintingService paintingService;
	
	@GetMapping("/paintings")
	public String loadPaintings(Model model) {
		model.addAttribute("paintings",paintingRepository.findAll());
		model.addAttribute("a",artistRepository);
		
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
	
	@RequestMapping("/filterPainting")
	public String filterPaintings(Model model, @RequestParam(defaultValue ="150.0") double width, @RequestParam(defaultValue = "150.0") double height, @RequestParam (defaultValue = "0")int min_price,
			@RequestParam(defaultValue = "1000") int max_price) {
		model.addAttribute("width", width);
		model.addAttribute("height", height);
		model.addAttribute("min_price", min_price);
		model.addAttribute("max_price", max_price);
		model.addAttribute("paintings",this.paintingService.filterBy(width,height,min_price,max_price));	
		return "painting";
	}
	
	@GetMapping("/paintings/")
	public String showPainting(Model model,@RequestParam Long id) {
		Painting p=paintingRepository.findById(id).get();
		model.addAttribute("painting",p);
		return "painting_profile";
		
	}
	
	@PostMapping("/painting_update/")
	public String updatePainting(Model model,@RequestParam Long id,Painting painting) {
		Painting p=this.paintingRepository.findById(id).get();
		update(p,painting);
		return "redirect:/paintings";
	}
	
	@GetMapping("/painting_buy/")
	public String buyPainting(Model model,@RequestParam Long id) {
		//Client c=clientRepository.findByEmail(email);
		Painting p=paintingRepository.findById(id).get();
		p.setSold(true);
		//p.setClient(c);
		paintingRepository.save(p);
		return "redirect:/paintings";
	}
	
	private void update(Painting p, Painting painting) {
		p.setDate(painting.getDate());
		p.setDescription(painting.getDescription());
		p.setHeight(painting.getHeight());
		p.setWidth(painting.getWidth());
		p.setPrice(painting.getPrice());
		p.setTitle(painting.getTitle());
		p.setYear(painting.getYear());
		this.paintingRepository.save(p);
		
	}


	@PostMapping("/added_painting")
	public String newPainting(Painting painting,@RequestParam String email) {
		Artist a=artistRepository.findByEmail(email);
		painting.setAutor(a);
		paintingRepository.save(painting);
		return "redirect:/paintings";
	}

}
