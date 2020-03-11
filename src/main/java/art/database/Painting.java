package art.database;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Painting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String title;
	private String description;
	private int year;
	private double height;
	private double width;
	private int price;
	private Date date;
	private String dateFormat;
	
	@ManyToOne
	private Autor autor;
	
	@ManyToOne
	private Client client;

	public Painting(String title,String description, int year, double height, double width, int price, Date date) {
		super();
		this.title = title;
		this.description=description;
		this.year = year;
		this.height = height;
		this.width = width;
		this.price = price;
		this.date = date;
		this.dateFormat=this.date.toString();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String date) {
		this.dateFormat = date;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

}