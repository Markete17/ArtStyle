package art.main.database;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	
	public List<Client> filterBy(String name,String surname) {
		List<Client> clients = this.clientRepository.findAll();
		List<Client> aux = new ArrayList();
		for(int i=0;i<clients.size();i++) {
			if((clients.get(i).getName().equals(name) || name.equals("")) && (clients.get(i).getSurname().equals(surname) || surname.equals(""))) {
				aux.add(clients.get(i));
			}
		}
		return aux;	
	}
}
