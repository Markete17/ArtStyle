package art.database;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String surname;
	private String NIF;
	
	private int year;
	private String country;
	private String address;
	private String email;
	private String phone;
	
	@OneToMany
	private List<Client> clients;
	
	@OneToMany(mappedBy = "autor")
	private List<Painting> paintings;

	public Autor(String name, String surname, String NIF, int year, String country, String address,
			String email, String phone) {
		this.name = name;
		this.surname = surname;
		this.NIF = NIF;
		this.year = year;
		this.country = country;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.clients=new LinkedList<>();
	}
	
	public List<Painting> getPaintings() {
		return paintings;
	}

	public void setPaintings(List<Painting> paintings) {
		this.paintings = paintings;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getNIF() {
		return NIF;
	}
	public void setNIF(String nIF) {
		this.NIF = nIF;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public List<Client> getClients() {
		return clients;
	}
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	
}
