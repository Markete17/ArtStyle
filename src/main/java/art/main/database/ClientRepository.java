package art.main.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	List<Client> OrderByNameAsc();
	List<Client> OrderByNameDesc();
	
	List<Client> OrderBySurnameAsc();
	List<Client> OrderBySurnameDesc();
}
