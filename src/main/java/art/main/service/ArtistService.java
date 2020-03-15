package art.main.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import art.main.database.Artist;
import art.main.database.ArtistRepository;

@Service
public class ArtistService {

	@Autowired
	private ArtistRepository artistRepository;
		
	public List<Artist> filterBy(String name, String surname, String nif, String country, Integer min_year, Integer max_year,
			String address, String email, String phone) {
		
		List<Artist> artists = new LinkedList<>();
		
		if (min_year != null && max_year != null && min_year > max_year) {
			return new LinkedList<>();
		}
		
		if (name.isEmpty() && surname.isEmpty() && nif.isEmpty() && country.isEmpty() && min_year == null && max_year == null && 
				address.isEmpty() && email.isEmpty() && phone.isEmpty()) {
			return artistRepository.findAll();
		}
		
		if (!name.isEmpty() && !surname.isEmpty()) {
			artists = artistRepository.findByNameAndSurnameIgnoreCase(name, surname);
		} else if (!name.isEmpty()) {
			artists = artistRepository.findByNameIgnoreCase(name);
		} else if (!surname.isEmpty()) {
			artists = artistRepository.findBySurnameIgnoreCase(surname);
		}
		
		if (!nif.isEmpty()) {
			if (artists.isEmpty()) {
				artists = artistRepository.findByNIF(nif);
			} else {
				artists.retainAll(artistRepository.findByNIF(nif));
			}
		}
		
		if (!country.isEmpty()) {
			if (artists.isEmpty()) {
				artists = artistRepository.findByCountryIgnoreCase(country);
			} else {
				artists.retainAll(artistRepository.findByCountryIgnoreCase(country));
			}
		}
		
		if (min_year != null) {
			if (artists.isEmpty()) {
				artists = artistRepository.findByYearGreaterThanEqual(min_year);
			} else {
				artists.retainAll(artistRepository.findByYearGreaterThanEqual(min_year));
			}
		}
		
		if (max_year != null) {
			if (artists.isEmpty()) {
				artists = artistRepository.findByYearLessThanEqual(max_year);
			} else {
				artists.retainAll(artistRepository.findByYearLessThanEqual(max_year));
			}
		}
		
		if (!address.isEmpty()) {
			if (artists.isEmpty()) {
				artists = artistRepository.findByAddressContainingIgnoreCase(address);
			} else {
				artists.retainAll(artistRepository.findByAddressContainingIgnoreCase(address));
			}
		}
		
		if (!email.isEmpty()) {
			if (email.isEmpty()) {
				artists = artistRepository.findByEmailIgnoreCase(email);
			} else {
				artists.retainAll(artistRepository.findByEmailIgnoreCase(email));
			}
		}
		
		if (!phone.isEmpty()) {
			if (artists.isEmpty()) {
				artists = artistRepository.findByPhoneContainingIgnoreCase(phone);
			} else {
				artists.retainAll(artistRepository.findByPhoneContainingIgnoreCase(phone));
			}
		}
		
		return artists;
		
	}
}
