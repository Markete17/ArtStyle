package art.main.controller;

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
import art.main.service.ArtistService;

@Controller
public class ArtistController {

	@Autowired
	private ArtistRepository artistRepository;
	
	@Autowired
	private ArtistService artistService;
	
	@GetMapping("/artists")
	public String loadPaintings(Model model) {
		model.addAttribute("artists",artistRepository.findAll());
		return "artist";
	}
	
	@PostMapping("/added_artist")
	public String newPainting(Artist artist) {
		artistRepository.save(artist);
		return "artist_form";
	}
	
	@GetMapping("/artists/")
	public String showClient(Model model,@RequestParam Long id) {
		Artist artist=artistRepository.findById(id).get();
		model.addAttribute("artist",artist);
		return "artist_profile";

	}
	
	@PostMapping("/artist_update/")
	public String updateArtist(Model model,Artist artist,@RequestParam Long id) {
			
		update(artist,id);
		
		return "redirect:/artists";
		
	}
	
	@RequestMapping("/artistSortBy")
	public String sortBy(Model model,@RequestParam String value) {
		switch(value) {
		case "Name":
			model.addAttribute("artists", this.artistRepository.OrderByName());
			break;
		case "Surname":
			model.addAttribute("artists", this.artistRepository.OrderBySurname());
			break;
		case "Year":
			model.addAttribute("artists", this.artistRepository.OrderByYear());
			break;
		case "Country":
			model.addAttribute("artists", this.artistRepository.OrderByCountry());
			break;
		}
		return "artist";
	}
	
	@RequestMapping("/filterArtist")
	public String filterPaintings(Model model, @RequestParam(defaultValue ="") String name, @RequestParam(defaultValue = "") String surname,@RequestParam(defaultValue = "") String country) {
		model.addAttribute("name", name);
		model.addAttribute("surname", surname);
		model.addAttribute("country",country);
		model.addAttribute("artists",this.artistService.filterBy(name,surname,country));	
		return "artist";
	}
	
	private void update(Artist artist,Long id) {
		
		Artist a=artistRepository.findById(id).get();
		a.setAddress(artist.getAddress());
		a.setCountry(artist.getCountry());
		a.setEmail(artist.getEmail());
		a.setName(artist.getName());
		a.setNIF(artist.getNIF());
		a.setPhone(artist.getPhone());
		a.setSurname(artist.getSurname());
		a.setYear(artist.getYear());
		artistRepository.save(a);	
	}
	
}
