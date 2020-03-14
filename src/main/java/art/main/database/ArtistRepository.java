package art.main.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long>{
	
	Artist findByEmail(String email);
	
	List<Artist> OrderByNameAsc();
	List<Artist> OrderByNameDesc();
	
	List<Artist> OrderBySurnameAsc();
	List<Artist> OrderBySurnameDesc();
	
	List<Artist> OrderByYearAsc();
	List<Artist> OrderByYearDesc();
	
	List<Artist> OrderByCountryAsc();
	List<Artist> OrderByCountryDesc();
	
	List<Artist> OrderByNIFAsc();
	List<Artist> OrderByNIFDesc();
	
	List<Artist> OrderByEmailAsc();
	List<Artist> OrderByEmailDesc();
	
	
	List<Painting> findByNameAndSurnameIgnoreCase(String name, String surname);
	List<Painting> findByNameIgnoreCase(String name);
	List<Painting> findBySurnameIgnoreCase(String surname);
	List<Painting> findByNIF(String nif);
	List<Painting> findByCountryIgnoreCase(String country);
	List<Painting> findByYearLessThanEqual(int year);
	List<Painting> findByYearGreaterThanEqual(int year);
	List<Painting> findByAddressContainingIgnoreCase(String address);
	List<Painting> findByEmailIgnoreCase(String email);
	List<Painting> findByPhoneContainingIgnoreCase(String phone);
	
}
