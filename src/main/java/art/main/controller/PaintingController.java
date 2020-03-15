package art.main.controller;

import java.util.Calendar;
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
import art.main.database.Client;
import art.main.database.ClientRepository;
import art.main.database.Painting;
import art.main.database.PaintingRepository;
import art.main.service.PaintingService;

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
	public String loadPaintings(Model model, HttpSession session) {
		model.addAttribute("paintings", paintingRepository.findAll());
		session.setAttribute("paintings", paintingRepository.findAll());
		model.addAttribute("default", true);

		return "painting";
	}

	@GetMapping("/")
	public String loadHome() {
		return "index";
	}

	@RequestMapping("/productSortBy")
	public String sortBy(Model model, @RequestParam String value, HttpSession session) {

		List<Painting> sortedPaintings = new LinkedList<>();

		switch (value) {
		case "PriceDesc":
			sortedPaintings = paintingRepository.OrderByPriceDesc();
			model.addAttribute("option1", true);
			break;
		case "PriceAsc":
			sortedPaintings = paintingRepository.OrderByPriceAsc();
			model.addAttribute("option2", true);
			break;
		case "YearDesc":
			sortedPaintings = paintingRepository.OrderByYearDesc();
			model.addAttribute("option3", true);
			break;
		case "YearAsc":
			sortedPaintings = paintingRepository.OrderByYearAsc();
			model.addAttribute("option4", true);
			break;
		case "TitleAsc":
			sortedPaintings = paintingRepository.OrderByTitleAsc();
			model.addAttribute("option5", true);
			break;
		case "TitleDesc":
			sortedPaintings = paintingRepository.OrderByTitleDesc();
			model.addAttribute("option6", true);
			break;
		default:
			sortedPaintings = paintingRepository.findAll();
			model.addAttribute("default", true);
		}

		model.addAttribute("max_width", session.getAttribute("max_width"));
		model.addAttribute("max_height", session.getAttribute("max_height"));
		model.addAttribute("min_width", session.getAttribute("min_width"));
		model.addAttribute("min_height", session.getAttribute("min_height"));
		model.addAttribute("min_year", session.getAttribute("min_year"));
		model.addAttribute("max_year", session.getAttribute("max_year"));
		model.addAttribute("min_price", session.getAttribute("min_price"));
		model.addAttribute("max_price", session.getAttribute("max_price"));
		model.addAttribute("checked1", session.getAttribute("checked1"));
		model.addAttribute("checked2", session.getAttribute("checked2"));
		model.addAttribute("checked3", session.getAttribute("checked3"));

		sortedPaintings.retainAll((Collection<?>) session.getAttribute("paintings"));
		model.addAttribute("paintings", sortedPaintings);

		return "painting";
	}

	@RequestMapping("/filterPainting")
	public String filterPaintings(Model model, @RequestParam Double max_width, @RequestParam Double max_height,
			@RequestParam Double min_width, @RequestParam Double min_height, @RequestParam Integer min_year,
			@RequestParam Integer max_year, @RequestParam Integer min_price, @RequestParam Integer max_price,
			@RequestParam(defaultValue = "1") String sold, HttpSession session) {

		model.addAttribute("max_width", max_width);
		session.setAttribute("max_width", max_width);

		model.addAttribute("max_height", max_height);
		session.setAttribute("max_height", max_height);

		model.addAttribute("min_width", min_width);
		session.setAttribute("min_width", min_width);

		model.addAttribute("min_height", min_height);
		session.setAttribute("min_height", min_height);

		model.addAttribute("min_year", min_year);
		session.setAttribute("min_year", min_year);

		model.addAttribute("max_year", max_year);
		session.setAttribute("max_year", max_year);

		model.addAttribute("min_price", min_price);
		session.setAttribute("min_price", min_price);

		model.addAttribute("max_price", max_price);
		session.setAttribute("max_price", max_price);

		model.addAttribute("default", true);

		List<Painting> paintings = this.paintingService.filterBy(max_width, max_height, min_width, min_height, min_year,
				max_year, min_price, max_price, sold);

		model.addAttribute("paintings", paintings);
		session.setAttribute("paintings", paintings);

		if (sold.equals("1")) {
			model.addAttribute("checked1", true);
			session.setAttribute("checked1", true);
			session.setAttribute("checked2", false);
			session.setAttribute("checked3", false);
		} else if (sold.equals("2")) {
			model.addAttribute("checked2", true);
			session.setAttribute("checked2", true);
			session.setAttribute("checked1", false);
			session.setAttribute("checked3", false);
		} else if (sold.equals("3")) {
			model.addAttribute("checked3", true);
			session.setAttribute("checked3", true);
			session.setAttribute("checked1", false);
			session.setAttribute("checked2", false);
		}

		return "painting";
	}

	@GetMapping("/paintings/")
	public String showPainting(Model model, @RequestParam Long id) {
		Painting p = paintingRepository.findById(id).get();
		model.addAttribute("painting", p);
		return "painting_profile";

	}

	@PostMapping("/painting_update/")
	public String updatePainting(Model model, @RequestParam Long id, Painting painting) {
		Painting p = this.paintingRepository.findById(id).get();
		update(p, painting);
		return "redirect:/paintings";
	}

	@PostMapping("/paintings/sold/")
	public String buyPainting(Model model, @RequestParam Long id, @RequestParam String nif, HttpSession session) {
		Client client = clientRepository.findByNIF(nif);

		if (client == null) {
			model.addAttribute("not_found", true);
			model.addAttribute("id_painting", id);
			model.addAttribute("paintings", paintingRepository.findAll());
			session.setAttribute("paintings", paintingRepository.findAll());
			model.addAttribute("a", artistRepository);

			model.addAttribute("default", true);

			return "painting";
		} else {
			Painting p = paintingRepository.findById(id).get();
			p.setSold(true);
			p.setClient(client);
			p.setDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
			client.getPaintings().add(p);
			clientRepository.save(client);
			paintingRepository.save(p);
		}
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
	public String newPainting(Painting painting, @RequestParam String email) {
		Artist artist = artistRepository.findByEmail(email);
		painting.setAutor(artist);
		artist.getPaintings().add(painting);
		artistRepository.save(artist);
		paintingRepository.save(painting);
		return "redirect:/paintings";
	}

}
