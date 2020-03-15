package art.main.database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaintingRepository extends JpaRepository<Painting, Long>{
	
	List<Painting> OrderByTitleAsc();
	List<Painting> OrderByTitleDesc();
	
	List<Painting> OrderByPriceAsc();
	List<Painting> OrderByPriceDesc();
	
	List<Painting> OrderByYearAsc();
	List<Painting> OrderByYearDesc();
	
	List<Painting> findByWidthLessThanEqual(double width);
	List<Painting> findByHeightLessThanEqual(double height);
	
	List<Painting> findByWidthGreaterThanEqual(double width);
	List<Painting> findByHeightGreaterThanEqual(double height);
	
	List<Painting> findByYearLessThanEqual(int year);
	List<Painting> findByYearGreaterThanEqual(int year);
	
	List<Painting> findByPriceLessThanEqual(int price);
	List<Painting> findByPriceGreaterThanEqual(int price);
	
	List<Painting> findBySold(boolean sold);
	
	List<Painting> findByTitleIgnoreCase(String title);

}
