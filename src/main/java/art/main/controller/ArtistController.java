package art.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import art.main.database.Artist;
import art.main.database.ArtistRepository;

@Controller
public class ArtistController {

	@Autowired
	private ArtistRepository artistRepository;
	
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
	
}
