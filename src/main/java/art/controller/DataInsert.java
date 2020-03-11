package art.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import art.database.*;

@Controller
public class DataInsert implements CommandLineRunner {
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private PaintingRepository paintRepository;

	@Override
	public void run(String... args) throws Exception {
		
		/*
		 * 	public Autor(String name, String surname, String NIF, int year, String country, String address,
			String email, String phone) {
		 */
		Autor a1=new Autor("Vincent","Van Goh","58954754P",1980,"Netherlands","Carried Street","vangoh@outlook.com","684574");
		Autor a2=new Autor("Rosa","Rodriguez","579455454X",1993,"Spain","Rosa Street","rose@outlook.com","778574654");
		Autor a3=new Autor("Steven","Nielsen","6854754A",1975,"Germany","Nielsen Street","steven@outlook.com","654275454");
		Autor a4=new Autor("Pierre","Diarra","55876652P",1985,"France","Diarra Street","oueri@outlook.com","7788564");
		Autor a5=new Autor("Walker","Diana","75454555",1985,"England","Walker Street","dwalker@outlook.com","4457878864");
		
		/**
		 * 	public Client(String name, String surname, String nIF, String address, String email, String phone) {
		 */
		Client c1=new Client("Marcos","Ruiz","656556554A","Avda Valencia 25","mr@gmail.com","66878754");
		Client c2=new Client("Jose Luis","Sierra","25655554X","Avda Valencia 25","jose@gmail.com","7874754");
		Client c3=new Client("Enrique","Maji","99554564B","Avda Valencia 25","maji@gmail.com","6247789");
		Client c4=new Client("Daniel","Ruiz","9631566554E","Avda Valencia 25","delegado@gmail.com","668732345");
		Client c5=new Client("Diego","Montoto","65658866554G","Avda Valencia 25","diego@gmail.com","62424478621");
		
		clientRepository.save(c1);clientRepository.save(c2);clientRepository.save(c3);
		clientRepository.save(c4);clientRepository.save(c5);
		
		/**
		 * public Painting(String title, int year, float height, float width, int price) {
		 */
		
		Date d1=new Date(500000);
		
		Painting p1=new Painting("Rosales","description", 1990, 60.55, 60.45, 120,d1);Painting p7=new Painting("Lago verde","description", 2010, 20.75, 100.65, 1200,d1);
		Painting p2=new Painting("Caballos","description", 1999, 56.75, 160.45, 150,d1);Painting p8=new Painting("Creative paint","description", 2011, 60.75, 70.57, 520,d1);
		Painting p3=new Painting("The mountains","description", 1990, 57.75, 15.45, 10,d1);Painting p9=new Painting("Anuel","description", 2018, 5.75, 10.55, 10,d1);
		Painting p4=new Painting("Der legen","description", 1980, 85.20, 100.45, 90,d1);Painting p10=new Painting("Steven Hay","description", 2001, 50.75, 100.85, 20,d1);
		Painting p5=new Painting("Motche","description", 1987, 75.15, 16.35, 18,d1);Painting p11=new Painting("Snakes","description", 2009, 50.75, 26.45, 15,d1);
		Painting p6=new Painting("The birds","description", 1998, 25.75, 30.45, 165,d1);Painting p12=new Painting("Flowers","description", 2020, 15.75, 100.55, 32,d1);
		
		paintRepository.save(p1);paintRepository.save(p4);paintRepository.save(p7);paintRepository.save(p10);
		paintRepository.save(p2);paintRepository.save(p5);paintRepository.save(p8);paintRepository.save(p11);
		paintRepository.save(p3);paintRepository.save(p6);paintRepository.save(p9);paintRepository.save(p12);
		
		a1.getPaintings().add(p4);a2.getPaintings().add(p1);a2.getPaintings().add(p2);a4.getPaintings().add(p12);
		a1.getPaintings().add(p5);a2.getPaintings().add(p7);a3.getPaintings().add(p10);a5.getPaintings().add(p6);
								  a2.getPaintings().add(p9);a3.getPaintings().add(p11);a5.getPaintings().add(p3);a5.getPaintings().add(p8);
		
		
		autorRepository.save(a1);autorRepository.save(a2);
		autorRepository.save(a3);autorRepository.save(a4);autorRepository.save(a5);
	}

}
