package art.main.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaintingRepository extends JpaRepository<Painting, Long>{
	
	List<Painting> OrderByPriceAsc();
	List<Painting> OrderByPriceDesc();
	
	List<Painting> OrderByYearAsc();
	List<Painting> OrderByYearDesc();
	

}
