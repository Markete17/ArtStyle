package art.main.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long>{
	
	List<Artist> OrderByName();
	List<Artist> OrderBySurname();
	List<Artist> OrderByYear();
	List<Artist> OrderByCountry();
}
