package art.controller;

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
		
		autorRepository.save(a1);autorRepository.save(a2);
		autorRepository.save(a3);autorRepository.save(a4);
		
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
		
		Painting p1=new Painting("Rosales", 1990, 60.55, 60.45, 120);Painting p7=new Painting("Lago verde", 2010, 20.75, 100.65, 1200);
		Painting p2=new Painting("Caballos", 1999, 56.75, 160.45, 150);Painting p8=new Painting("Creative paint", 2011, 60.75, 70.57, 520);
		Painting p3=new Painting("The mountains", 1990, 57.75, 15.45, 10);Painting p9=new Painting("Anuel", 2018, 5.75, 10.55, 10);
		Painting p4=new Painting("Der legen", 1980, 85.20, 100.45, 90);Painting p10=new Painting("Steven Hay", 2001, 50.75, 100.85, 20);
		Painting p5=new Painting("Motche", 1987, 75.15, 16.35, 18);Painting p11=new Painting("Snakes", 2009, 50.75, 26.45, 15);
		Painting p6=new Painting("The birds", 1998, 25.75, 30.45, 165);Painting p12=new Painting("Flowers", 2020, 15.75, 100.55, 32);
	}

}
