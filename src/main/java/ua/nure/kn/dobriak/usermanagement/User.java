package ua.nure.kn.dobriak.usermanagement;

import java.time.Period;
import java.time.LocalDate;

public class User {
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private Long id;
	
	public User() {}
	public User(Long id, String firstName, String lastName, LocalDate dateOfBirth) {
		this.id = id;
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
}

