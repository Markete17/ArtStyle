package art.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import art.main.database.Artist;
import art.main.database.ArtistRepository;

@Service
public class ArtistService {

	@Autowired
	private ArtistRepository artistRepository;
	
	public List<Artist> filterBy(String name,String surname,String country){
		List<Artist> artists = this.artistRepository.findAll();
		List<Artist> aux = new ArrayList<>();
		for(int i=0;i<artists.size();i++) {
			if((artists.get(i).getName().equals(name)||name.equals(""))&&(artists.get(i).getSurname().equals(surname)||surname.equals(""))&&(artists.get(i).getCountry().equals(country)||country.equals(""))) {
				aux.add(artists.get(i));
			}
		}
		return aux;
	}
}
