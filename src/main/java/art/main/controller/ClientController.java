package art.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import art.main.database.Client;
import art.main.database.ClientRepository;

@Controller
public class ClientController {

	@Autowired
	private ClientRepository clientRepository;
	
	@GetMapping("/clients")
	public String loadPaintings(Model model) {
		model.addAttribute("clients",clientRepository.findAll());
		return "client";
	}
	
	@PostMapping("/added_client")
	public String newPainting(Client client) {
		clientRepository.save(client);
		return "client_form";
	}	
	
	@GetMapping("/clients/")
	public String showClient(Model model,@RequestParam Long id) {
		Client client=clientRepository.findById(id).get();
		model.addAttribute("client",client);
		return "client_profile";
		
	}
	
	@RequestMapping("/clientSortBy")
	public String sortBy(Model model,@RequestParam String value) {
		switch(value) {
		case "NameAsc":
			model.addAttribute("clients", this.clientRepository.OrderByNameDesc());
			break;
		case "NameDesc":
			model.addAttribute("clients", this.clientRepository.OrderByNameAsc());
			break;
		case "SurnameAsc":
			model.addAttribute("clients", this.clientRepository.OrderBySurnameDesc());
			break;
		case "SurnameDesc":
			model.addAttribute("clients", this.clientRepository.OrderBySurnameAsc());
			break;
		}
		return "client";
	}
	
	@PostMapping("/client_update/")
	public String updateClient(Model model,Client client,@RequestParam Long id) {
			
		update(client,id);
		
		return "redirect:/clients";
		
	}
	
	private void update(Client client,Long id) {
		
		Client c=clientRepository.findById(id).get();
		c.setAddress(client.getAddress());
		c.setEmail(client.getEmail());
		c.setName(client.getName());
		c.setNIF(client.getNIF());
		c.setSurname(client.getSurname());
		c.setPhone(client.getPhone());
		clientRepository.save(c);
		
	}
}
