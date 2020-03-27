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
	
	
	List<Artist> findByNameIgnoreCase(String name);
	List<Artist> findBySurnameIgnoreCase(String surname);
	List<Artist> findByNIF(String nif);
	List<Artist> findByCountryIgnoreCase(String country);
	List<Artist> findByYearLessThanEqual(int year);
	List<Artist> findByYearGreaterThanEqual(int year);
	List<Artist> findByAddressContainingIgnoreCase(String address);
	List<Artist> findByEmailIgnoreCase(String email);
	List<Artist> findByPhoneContainingIgnoreCase(String phone);
	
}
