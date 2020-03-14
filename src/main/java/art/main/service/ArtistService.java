package art.main.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import art.main.database.Artist;
import art.main.database.ArtistRepository;
import art.main.database.Painting;

@Service
public class ArtistService {

	@Autowired
	private ArtistRepository artistRepository;
		
	public List<Artist> filterBy(String name, String surname, String nif, String country, Integer min_year, Integer max_year,
			String address, String email, String phone) {
		
		List<Artist> artists = new LinkedList<>();
		
		//Aun por hacer
		
		return artists;
		
	}
}
