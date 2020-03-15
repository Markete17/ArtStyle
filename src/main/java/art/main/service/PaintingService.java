package art.main.service;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import art.main.database.Painting;
import art.main.database.PaintingRepository;

@Service
public class PaintingService {

	
	@Autowired
	private PaintingRepository paintingRepository;

	public List<Painting> filterBy(Double max_width, Double max_height, Double min_width, Double min_height,
			Integer min_year, Integer max_year, Integer min_price, Integer max_price, String sold) {
		
		List<Painting> paintings = new LinkedList<>();
		boolean enter = true;
		
		//Procurar que no pueda poner min > max en cualquier campo
		if ( (min_width != null && max_width != null && min_width > max_width) 
				|| (min_height != null && max_height != null && min_height > max_height) 
				|| (min_year != null && max_year != null && min_year > max_year) 
				|| (min_price != null && max_price != null && min_price > max_price) ) {
			return new LinkedList<>();
		}
		
		if (max_width != null) {
			enter = false;
			paintings = paintingRepository.findByWidthLessThanEqual(max_width);
		}
		
		if (max_height != null) {
			if (paintings.isEmpty()) {
				enter = false;
				paintings = paintingRepository.findByHeightLessThanEqual(max_height);
			} else {
				paintings.retainAll(paintingRepository.findByHeightLessThanEqual(max_height));			
			}
			
		}
		
		if (min_width != null) {
			if (paintings.isEmpty()) {
				enter = false;
				paintings = paintingRepository.findByWidthGreaterThanEqual(min_width);
			} else {
				paintings.retainAll(paintingRepository.findByWidthGreaterThanEqual(min_width));			
			}
			
		}
		
		if (min_height != null) {
			if (paintings.isEmpty()) {
				enter = false;
				paintings = paintingRepository.findByHeightGreaterThanEqual(min_height);
			} else {
				paintings.retainAll(paintingRepository.findByHeightGreaterThanEqual(min_height));
			}
		}
		
		if (min_year != null) {
			if (paintings.isEmpty()) {
				enter = false;
				paintings = paintingRepository.findByYearGreaterThanEqual(min_year);
			} else {
				paintings.retainAll(paintingRepository.findByYearGreaterThanEqual(min_year));
			}
		}
		
		if (max_year != null) {
			if (paintings.isEmpty()) {
				enter = false;
				paintings = paintingRepository.findByYearLessThanEqual(max_year);
			} else {
				paintings.retainAll(paintingRepository.findByYearLessThanEqual(max_year));
			}
		}
		
		if (min_price != null) {
			if (paintings.isEmpty()) {
				enter = false;
				paintings = paintingRepository.findByPriceGreaterThanEqual(min_price);
			} else {
				paintings.retainAll(paintingRepository.findByPriceGreaterThanEqual(min_price));
			}
		}
		
		if (max_price != null) {
			if (paintings.isEmpty()) {
				enter = false;
				paintings = paintingRepository.findByPriceLessThanEqual(max_price);
			} else {
				paintings.retainAll(paintingRepository.findByPriceLessThanEqual(max_price));
			}
			
		}
		
		if (sold.equals("2")) {
			if (paintings.isEmpty() && enter) {
				paintings = paintingRepository.findBySold(true);
			} else {
				paintings.retainAll(paintingRepository.findBySold(true));
			}
		} else if (sold.equals("3")){
			if (paintings.isEmpty() && enter) {
				paintings = paintingRepository.findBySold(false);
			} else {
				paintings.retainAll(paintingRepository.findBySold(false));
			}			
		} else {
			if (paintings.isEmpty() && enter) {
				paintings = paintingRepository.findAll();
			}
		}
			
		return paintings;
	}
}
