package art.main.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	List<Client> OrderByNameAsc();
	List<Client> OrderByNameDesc();
	
	List<Client> OrderBySurnameAsc();
	List<Client> OrderBySurnameDesc();
	
	List<Client> OrderByNIFAsc();
	List<Client> OrderByNIFDesc();
	
	List<Client> OrderByAddressAsc();
	List<Client> OrderByAddressDesc();
	
	List<Client> OrderByEmailAsc();
	List<Client> OrderByEmailDesc();
	
	List<Client> OrderByPhoneAsc();
	List<Client> OrderByPhoneDesc();
	
	
	List<Client> findByNameIgnoreCase(String name);
	List<Client> findBySurnameIgnoreCase(String surname);
	Client findByNIF(String nif);
	List<Client> findByAddressContainingIgnoreCase(String address);
	List<Client> findByEmailIgnoreCase(String email);
	List<Client> findByPhoneContaining(String phone);
	
}
