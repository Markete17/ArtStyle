package art.main.controller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import art.main.database.Artist;
import art.main.database.ArtistRepository;
import art.main.database.Painting;
import art.main.service.ArtistService;

@Controller
public class ArtistController {

	@Autowired
	private ArtistRepository artistRepository;
	
	@Autowired
	private ArtistService artistService;
	
	@GetMapping("/artists")
	public String loadPaintings(Model model, HttpSession session) {
		model.addAttribute("artists",artistRepository.findAll());
		session.setAttribute("artists", artistRepository.findAll());
		model.addAttribute("default", true);
		
		return "artist";
	}
	
	@PostMapping("/added_artist")
	public String newPainting(Artist artist) {
		artist.setPaintings(new LinkedList<Painting>());
		artistRepository.save(artist);
		return "redirect:/artists";
	}
	
	@GetMapping("/artists/")
	public String showClient(Model model,@RequestParam Long id) {
		Artist artist = artistRepository.findById(id).get();
		model.addAttribute("artist",artist);
		return "artist_profile";
	}
	
	@PostMapping("/artist_update/")
	public String updateArtist(Model model,Artist artist,@RequestParam Long id) {
			
		update(artist,id);
		
		return "redirect:/artists";
		
	}
	
	@RequestMapping("/artistSortBy")
	public String sortBy(Model model, @RequestParam String value, HttpSession session) {
		
		List<Artist> sortedArtists = new LinkedList<>();

		switch (value) {
		case "NameAsc":
			sortedArtists = artistRepository.OrderByNameAsc();
			model.addAttribute("option1", true);
			break;
		case "NameDesc":
			sortedArtists = artistRepository.OrderByNameDesc();
			model.addAttribute("option2", true);
			break;
		case "SurnameAsc":
			sortedArtists = artistRepository.OrderBySurnameAsc();
			model.addAttribute("option3", true);
			break;
		case "SurnameDesc":
			sortedArtists = artistRepository.OrderBySurnameDesc();
			model.addAttribute("option4", true);
			break;
		case "NifDesc":
			sortedArtists = artistRepository.OrderByNIFDesc();
			model.addAttribute("option5", true);
			break;
		case "NifAsc":
			sortedArtists = artistRepository.OrderByNIFAsc();
			model.addAttribute("option6", true);
			break;
		case "YearDesc":
			sortedArtists = artistRepository.OrderByYearDesc();
			model.addAttribute("option7", true);
			break;
		case "YearAsc":
			sortedArtists = artistRepository.OrderByYearAsc();
			model.addAttribute("option8", true);
			break;
		case "CountryAsc":
			sortedArtists = artistRepository.OrderByCountryAsc();
			model.addAttribute("option9", true);
			break;
		case "CountryDesc":
			sortedArtists = artistRepository.OrderByCountryDesc();
			model.addAttribute("option10", true);
			break;
		case "EmailAsc":
			sortedArtists = artistRepository.OrderByEmailAsc();
			model.addAttribute("option11", true);
			break;
		case "EmailDesc":
			sortedArtists = artistRepository.OrderByEmailDesc();
			model.addAttribute("option12", true);
			break;
		default:
			sortedArtists = artistRepository.findAll();
			model.addAttribute("default", true);
		}

		model.addAttribute("name", session.getAttribute("name"));
		model.addAttribute("surname", session.getAttribute("surname"));
		model.addAttribute("nif", session.getAttribute("nif"));
		model.addAttribute("country", session.getAttribute("country"));
		model.addAttribute("min_year", session.getAttribute("min_year"));
		model.addAttribute("max_year", session.getAttribute("max_year"));
		model.addAttribute("address", session.getAttribute("address"));
		model.addAttribute("email", session.getAttribute("email"));
		model.addAttribute("phone", session.getAttribute("phone"));

		sortedArtists.retainAll((Collection<?>) session.getAttribute("artists"));
		model.addAttribute("artists", sortedArtists);

		return "artist";
	}
	
	@RequestMapping("/filterArtist")
	public String filterPaintings(Model model, @RequestParam String name, @RequestParam String surname, @RequestParam String nif, 
			@RequestParam String country, @RequestParam Integer min_year, @RequestParam Integer max_year, @RequestParam String address, 
			@RequestParam String email, @RequestParam String phone, HttpSession session) {

		model.addAttribute("name", name);
		session.setAttribute("name", name);
		
		model.addAttribute("surname", surname);
		session.setAttribute("surname", surname);
		
		model.addAttribute("nif", nif);
		session.setAttribute("nif", nif);		
		
		model.addAttribute("country", country);
		session.setAttribute("country", country);
		
		model.addAttribute("min_year", min_year);
		session.setAttribute("min_year", min_year);
		
		model.addAttribute("max_year", max_year);
		session.setAttribute("max_year", max_year);
		
		model.addAttribute("address", address);
		session.setAttribute("address", address);
		
		model.addAttribute("email", email);
		session.setAttribute("email", email);
		
		model.addAttribute("phone", phone);
		session.setAttribute("phone", phone);
		
		model.addAttribute("default", true);
		
		List<Artist> artists = this.artistService.filterBy(name, surname, nif, country, min_year, max_year, 
				address, email, phone);
		
		model.addAttribute("artists", artists);
		session.setAttribute("artists", artists);
		
		return "artist";
	}
	
	private void update(Artist artist,Long id) {
		
		Artist a = artistRepository.findById(id).get();
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
