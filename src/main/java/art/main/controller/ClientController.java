package art.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	
}
