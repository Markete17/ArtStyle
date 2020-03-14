package art.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import art.main.database.Painting;
import art.main.database.PaintingRepository;

@Service
public class PaintingService {

	
	@Autowired
	private PaintingRepository paintingRepository;
	
	public List<Painting> filterBy(double width, double height, int min_price, int max_price) {
		List<Painting> paintings = this.paintingRepository.findAll();
		List<Painting> aux = new ArrayList<>();
		for (int i = 0; i < paintings.size(); i++) {
			if(paintings.get(i).getWidth()<= width && paintings.get(i).getHeight()<= height && paintings.get(i).getPrice()>=min_price && paintings.get(i).getPrice()<=max_price ) {
				aux.add(paintings.get(i));
			}
		}
		return aux;
	}
	
}
