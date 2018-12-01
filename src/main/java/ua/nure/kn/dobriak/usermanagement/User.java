package ua.nure.kn.dobriak.usermanagement;

import javax.persistence.*;
import java.time.Period;
import java.time.LocalDate;

@Entity
@Table(name="USERS")
public class User {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="birthday")
	private LocalDate dateOfBirth;
	
	public User() {}
	public User(String firstName, String lastName, LocalDate dateOfBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFullName() {
		return getLastName() + " " + getFirstName();
	}
	
	public Object getAge() {
		LocalDate todayDate = LocalDate.now();
		return Period.between(getDateOfBirth(), todayDate).getYears();
	}

	public void clone(User user) {
		firstName = user.firstName;
		lastName = user.lastName;
		dateOfBirth = user.dateOfBirth;
	}
}

