package art.main.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import art.main.database.Client;
import art.main.database.ClientRepository;
import art.main.database.Painting;
import art.main.database.PaintingRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private PaintingRepository paintingRepository;
	
	public List<Client> filterBy(String name, String surname, String nif, String address, String email, String phone, String painting) {
		
		List<Client> clients = new LinkedList<>();
		boolean enter = true;
				
		if (painting.isEmpty() && name.isEmpty() && surname.isEmpty() && nif.isEmpty() && 
				address.isEmpty() && email.isEmpty() && phone.isEmpty()) {
			return clientRepository.findAll();
		}
		
		if (!name.isEmpty() && !surname.isEmpty()) {
			enter = false;
			clients = clientRepository.findByNameAndSurnameIgnoreCase(name, surname);
		} else if (!name.isEmpty()) {
			enter = false;
			clients = clientRepository.findByNameIgnoreCase(name);
		} else if (!surname.isEmpty()) {
			enter = false;
			clients = clientRepository.findBySurnameIgnoreCase(surname);
		}
		
		if (!nif.isEmpty()) {
			Client client = clientRepository.findByNIF(nif);
			
			if (client == null) {
				clients = new LinkedList<>();
			} else if (clients.isEmpty() && enter) {
				enter = false;
				clients.add(client);
			}
		}
				
		if (!address.isEmpty()) {
			if (clients.isEmpty() && enter) {
				enter = false;
				clients = clientRepository.findByAddressContainingIgnoreCase(address);
			} else {
				clients.retainAll(clientRepository.findByAddressContainingIgnoreCase(address));
			}
		}
		
		if (!email.isEmpty()) {
			if (clients.isEmpty() && enter) {
				enter = false;
				clients = clientRepository.findByEmailIgnoreCase(email);
			} else {
				clients.retainAll(clientRepository.findByEmailIgnoreCase(email));
			}
		}
		
		if (!phone.isEmpty()) {
			if (clients.isEmpty() && enter) {
				enter = false;
				clients = clientRepository.findByPhoneContaining(phone);
			} else {
				clients.retainAll(clientRepository.findByPhoneContaining(phone));
			}
		}
		
		if (!painting.isEmpty()) {
			List<Painting> clientPainting = paintingRepository.findByTitleIgnoreCase(painting);
			List<Client> auxClients = new LinkedList<Client>();
			
			for (Painting p : clientPainting) {
				if (p.getClient() != null) {
						auxClients.add(p.getClient());
				}
			}
			
			if (clients.isEmpty() && enter) {
				clients = auxClients;
			} else {
				clients.retainAll(auxClients);
			}
		}
		
		return clients;
		
	}
}
