package art.main.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import art.main.database.*;

@Controller
public class DataInsert implements CommandLineRunner {

	@Autowired
	private ArtistRepository autorRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private PaintingRepository paintRepository;

	@Override
	public void run(String... args) throws Exception {

		/*
		 * public Autor(String name, String surname, String NIF, int year, String
		 * country, String address, String email, String phone) {
		 */
		Artist a0 = new Artist("Jose", "Perez", "22222222J", 1972, "Ecuador", "Calle Ledesma, 20, 4.º bis",
				"perez@outlook.com", "684574364");
		Artist a1 = new Artist("Vincent", "Van Goh", "58954754P", 1980, "Netherlands", "C/ Florida, 19, 3.º  izda.",
				"vangoh@outlook.com", "485902841");
		Artist a2 = new Artist("Rosa", "Rodriguez", "57945545X", 1993, "Spain", "Manuel Iradier, 7, 3.º, dpto. 2", "rose@outlook.com",
				"772539541");
		Artist a3 = new Artist("Steven", "Nielsen", "26854754A", 1975, "Germany", "Av. Gasteiz, 10, 2.º A", "steven@outlook.com",
				"479275713");
		Artist a4 = new Artist("Pierre", "Diarra", "55876652P", 1985, "France", "Ctra. Gasteiz-Irun, km 5", "oueri@outlook.com",
				"333058617");
		Artist a5 = new Artist("Walker", "Diana", "75454555Z", 1985, "England", "Plaza Vicente Goikoetxea, 1, 1.º, 1.ª ofic.", "dwalker@outlook.com",
				"389120465");

		/**
		 * public Client(String name, String surname, String nIF, String address, String
		 * email, String phone) {
		 */
		Client c0 = new Client("Fernando", "Lopez", "11111111H", "Plza. Constitución, 3, ático 9", "fl@gmail.com", "275893471");
		Client c1 = new Client("Marcos", "Ruiz", "56556554A", "Avda Valencia 25", "mr@gmail.com", "493785291");
		Client c2 = new Client("Jose Luis", "Sierra", "25655554X", "Calle Higuereta, 4, 7.º B", "jose@gmail.com", "267490314");
		Client c3 = new Client("Enrique", "Maji", "99554564B", "Desarrollos Industriales, S. A.", "maji@gmail.com", "250859757");
		Client c4 = new Client("Daniel", "Ruiz", "96315665E", "7.º B, 69521 Rota,", "delegado@gmail.com", "266806943");
		Client c5 = new Client("Diego", "Montoto", "58866554G", "Polígono Industrial Tizona", "diego@gmail.com",
				"787267398");

		/**
		 * public Painting(String title, int year, float height, float width, int price)
		 * {
		 */
			
		Date d0 = Date.valueOf("2010-01-12");
		Date d1 = Date.valueOf("2017-02-23");
		Date d2 = Date.valueOf("2016-03-14");
		Date d3 = Date.valueOf("2017-07-17");
		Date d4 = Date.valueOf("2015-05-04");
		Date d5 = Date.valueOf("2018-01-30");

		
		Painting p0 = new Painting("Laurales", "Sobre un fondo de paisaje vaporoso, donde se resalta la figura de esta mujer," +
				" cuya enigmática sonrisa constituye lo más atractivo del cuadro, consiguió su más alta aspiración: plasmar el" +
				"alma humana.", 2007, 26.88, 30.52, 3000);
		p0.setDate(d0);
		p0.setSold(true);
		c0.getPaintings().add(p0);
		Painting p1 = new Painting("Lago verde", " Sus colores estridentes, sus rotundas pinceladas y sus retorcidos trazos lo" +
				" convierten en una de las más realistas expresiones de la angustia y del dolor del ser humano.", 1990, 60.55, 60.45, 120);
		p1.setDate(d1);
		p1.setSold(true);
		c1.getPaintings().add(p1);
		Painting p7 = new Painting("Girasoles", "Las pinturas muestran girasoles en todas las etapas de su vida, desde plenamente en "
				+ " flor hasta que se marchitan.", 2010, 20.75, 100.65, 1200);
		c2.getPaintings().add(p7);
		p7.setSold(true);
		p7.setDate(d2);
		Painting p2 = new Painting("Caballos", "Una serie de caballos en familia mirando la montaña que tienen a su izquierda.", 1999, 56.75, 160.45, 150);
		c3.getPaintings().add(p2);
		p2.setSold(true);
		p2.setDate(d3);
		Painting p8 = new Painting("Creative paint", "Los relojes, como la memoria, se han reblandecido por el paso del tiempo." +
				" Son relojes perfectamente verosímiles que siguen marcando la hora (supuestamente en torno a la seis de la tarde). ", 2011, 60.75, 70.57, 520);
		c4.getPaintings().add(p8);
		p8.setSold(true);
		p8.setDate(d4);
		Painting p3 = new Painting("The mountains", "Y ahí estaba frente a ella: sintiendo su cálida mirada, sus ebúrneas manos de seda" +
				" sosteniendo entre las mías, toscas y burdas, aquel rosario de cristalinas y verdes cuentas, engarzadas" +
				" con dorados y brillantes.", 1990, 57.75, 15.45, 10);
		c5.getPaintings().add(p3);
		p3.setSold(true);
		p3.setDate(d5);
		Painting p9 = new Painting("Anuel", "Y el día de mi regreso, entré a mi casa sola como el desierto, pero en la que, sin embargo, sentía la algarabía de la fiesta," +
				" del canto para romper la piñata, tan solo al entrar a la empolvada cocina.", 2018, 5.75, 10.55, 10);
		Painting p4 = new Painting("Der legen", "Es increíble cómo pasa el tiempo. Estoy en la calle de mi escuela y todo es diferente." +
				" Ahí sigue la escuela. Las rejas abiertas por donde entrabamos cuando era tarde, ahora son fríos muros " +
				" casi inexpugnables.", 1980, 85.20, 100.45, 90);
		Painting p10 = new Painting("Steven Hay", "La papelería de Paty, donde comprábamos refrescos y papelería," +
				" ahora es sólo una cortina pintarrajeada y polvorienta.", 2001, 50.75, 100.85, 20);
		Painting p5 = new Painting("Motche", "La casa de doña Chona ahora es un edificio de departamentos. La otra" +
				" papelería aún está, pero ya no la atiende la viejecita que me vendía las plumas, ahora está una" +
				" jovencita que parece ser su nieta.", 1987, 75.15, 16.35, 18);
		Painting p11 = new Painting("Snakes", "Del árbol donde nos sentábamos a platicar y a tomar refrescos," +
				" ahora sólo queda el tronco que sirve como banco a un anciano quien también mira nostálgico" +
				" la vieja escuela.", 2009, 50.75, 26.45, 15);
		Painting p6 = new Painting("The birds", "Viéndolo bien ese viejecito, es aquel que era mi maestro de matemáticas," +
				" el que me castigó por dejar un escarabajo en su escritorio, pero a pesar de eso, me dejó alta calificación" +
				" por ayudar a mis compañeros.", 1998, 25.75, 30.45, 165);
		Painting p12 = new Painting("Flowers", "El también mira los pocos restos que quedan de nuestros recuerdos;" +
				" quizá en su nostalgia, recuerda mucho más y ve mucho menos que lo que yo puedo añorar.", 2020, 15.75, 100.55, 32);
		
		p0.setClient(c0);
		p1.setClient(c1);
		p7.setClient(c2);
		p2.setClient(c3);
		p8.setClient(c4);
		p3.setClient(c5);
		
		clientRepository.save(c0);
		clientRepository.save(c1);
		clientRepository.save(c2);
		clientRepository.save(c3);
		clientRepository.save(c4);
		clientRepository.save(c5);
		
		a0.getPaintings().add(p0);
		a1.getPaintings().add(p4);
		a2.getPaintings().add(p1);
		a2.getPaintings().add(p2);
		a4.getPaintings().add(p12);
		a1.getPaintings().add(p5);
		a2.getPaintings().add(p7);
		a3.getPaintings().add(p10);
		a5.getPaintings().add(p6);
		a2.getPaintings().add(p9);
		a3.getPaintings().add(p11);
		a5.getPaintings().add(p3);
		a5.getPaintings().add(p8);
		
		autorRepository.save(a0);
		autorRepository.save(a1);
		autorRepository.save(a2);
		autorRepository.save(a3);
		autorRepository.save(a4);
		autorRepository.save(a5);
		
		p0.setAutor(a0);
		p1.setAutor(a2);
		p2.setAutor(a2);
		p3.setAutor(a5);
		p4.setAutor(a1);
		p5.setAutor(a1);
		p6.setAutor(a5);
		p7.setAutor(a2);
		p8.setAutor(a5);
		p9.setAutor(a2);
		p10.setAutor(a3);
		p11.setAutor(a3);
		p12.setAutor(a4);
		
		paintRepository.save(p0);
		paintRepository.save(p1);
		paintRepository.save(p4);
		paintRepository.save(p7);
		paintRepository.save(p10);
		paintRepository.save(p2);
		paintRepository.save(p5);
		paintRepository.save(p8);
		paintRepository.save(p11);
		paintRepository.save(p3);
		paintRepository.save(p6);
		paintRepository.save(p9);
		paintRepository.save(p12);
	
	}

}
