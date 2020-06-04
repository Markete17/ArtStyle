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

import art.main.database.Client;
import art.main.database.ClientRepository;
import art.main.database.Painting;
import art.main.service.ClientService;

@Controller
public class ClientController {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping("/clients")
	public String loadPaintings(Model model, HttpSession session) {
		model.addAttribute("clients",clientRepository.findAll());
		session.setAttribute("clients", clientRepository.findAll());
		model.addAttribute("default", true);
		
		return "client";
	}
	
	@PostMapping("/added_client")
	public String newPainting(Client client) {
		client.setPaintings(new LinkedList<Painting>());
		clientRepository.save(client);
		return "redirect:/clients";
	}	
	
	@GetMapping("/clients/")
	public String showClient(Model model,@RequestParam Long id) {
		Client client=clientRepository.findById(id).get();
		model.addAttribute("client",client);
		return "client_profile";
	}
	
	@RequestMapping("/clientSortBy")
	public String sortBy(Model model,@RequestParam String value, HttpSession session) {

		List<Client> sortedClients = new LinkedList<>();

		switch (value) {
		case "NameAsc":
			sortedClients = clientRepository.OrderByNameAsc();
			model.addAttribute("option1", true);
			break;
		case "NameDesc":
			sortedClients = clientRepository.OrderByNameDesc();
			model.addAttribute("option2", true);
			break;
		case "SurnameAsc":
			sortedClients = clientRepository.OrderBySurnameAsc();
			model.addAttribute("option3", true);
			break;
		case "SurnameDesc":
			sortedClients = clientRepository.OrderBySurnameDesc();
			model.addAttribute("option4", true);
			break;
		case "NifDesc":
			sortedClients = clientRepository.OrderByNIFDesc();
			model.addAttribute("option5", true);
			break;
		case "NifAsc":
			sortedClients = clientRepository.OrderByNIFAsc();
			model.addAttribute("option6", true);
			break;	
		case "AddressAsc":
			sortedClients = clientRepository.OrderByAddressAsc();
			model.addAttribute("option7", true);
			break;
		case "AddressDesc":
			sortedClients = clientRepository.OrderByAddressDesc();
			model.addAttribute("option8", true);
			break;
		case "EmailAsc":
			sortedClients = clientRepository.OrderByEmailAsc();
			model.addAttribute("option9", true);
			break;
		case "EmailDesc":
			sortedClients = clientRepository.OrderByEmailDesc();
			model.addAttribute("option10", true);
			break;
		case "PhoneDesc":
			sortedClients = clientRepository.OrderByPhoneAsc();
			model.addAttribute("option11", true);
			break;
		case "PhoneAsc":
			sortedClients = clientRepository.OrderByPhoneDesc();
			model.addAttribute("option12", true);
			break;
		default:
			sortedClients = clientRepository.findAll();
			model.addAttribute("default", true);
		}

		model.addAttribute("painting", session.getAttribute("painting"));
		model.addAttribute("name", session.getAttribute("name"));
		model.addAttribute("surname", session.getAttribute("surname"));
		model.addAttribute("nif", session.getAttribute("nif"));
		model.addAttribute("address", session.getAttribute("address"));
		model.addAttribute("email", session.getAttribute("email"));
		model.addAttribute("phone", session.getAttribute("phone"));

		sortedClients.retainAll((Collection<?>) session.getAttribute("clients"));
		model.addAttribute("clients", sortedClients);
		
		return "client";
	}
	
	@PostMapping("/client_update/")
	public String updateClient(Model model,Client client,@RequestParam Long id) {
			
		update(client,id);
		
		return "redirect:/clients";
		
	}
	
	@RequestMapping("/filterClient")
	public String filterPaintings(Model model, @RequestParam String painting, @RequestParam String name, 
			@RequestParam String surname, @RequestParam String nif, @RequestParam String address, 
			@RequestParam String email, @RequestParam String phone, HttpSession session) {

		model.addAttribute("painting", painting);
		session.setAttribute("paintings", painting);
		
		model.addAttribute("name", name);
		session.setAttribute("name", name);
		
		model.addAttribute("surname", surname);
		session.setAttribute("surname", surname);
		
		model.addAttribute("nif", nif);
		session.setAttribute("nif", nif);		
						
		model.addAttribute("address", address);
		session.setAttribute("address", address);
		
		model.addAttribute("email", email);
		session.setAttribute("email", email);
		
		model.addAttribute("phone", phone);
		session.setAttribute("phone", phone);
		
		model.addAttribute("default", true);
		
		List<Client> clients = this.clientService.filterBy(name, surname, nif, address, email, phone, painting);
		
		model.addAttribute("clients", clients);
		session.setAttribute("clients", clients);
		
		return "client";
	}
	
	private void update(Client client,Long id) {
		
		Client c = clientRepository.findById(id).get();
		c.setAddress(client.getAddress());
		c.setEmail(client.getEmail());
		c.setName(client.getName());
		c.setNIF(client.getNIF());
		c.setSurname(client.getSurname());
		c.setPhone(client.getPhone());
		clientRepository.save(c);
		
	}
}
