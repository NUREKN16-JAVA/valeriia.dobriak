package ua.nure.kn.dobriak.usermanagement;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

class UserTest {

	@Before
	void setUp() throws Exception {
	}
	
	private static final String FULL_NAME = "Ivanov Ivan";
	private static final String FIRST_NAME = "Ivan";
	private static final String LAST_NAME = "Ivanov";
	
	private static final int YEAR_OF_BIRTH = 1999;
	private static final int CURRENT_YEAR = 2018;
	
	private static final int AGE = CURRENT_YEAR - YEAR_OF_BIRTH;
	private static final int DAY_OF_BIRTH = 23;
	private static final int MONTH_OF_BIRTH = 9;
	private User user = new User();
	
	@Test void testGetAge( ) {
		LocalDate dateOfBirth = LocalDate.of(YEAR_OF_BIRTH, MONTH_OF_BIRTH, DAY_OF_BIRTH);
		user.setDateOfBirth(dateOfBirth);
		assertEquals(AGE, user.getAge());
	}
	
	// In case if current year is a Birth Year
	@Test void testGetAgeForCurrentYearBirth() {
		LocalDate dateOfBirth = LocalDate.of(2018, 10, 24);
		user.setDateOfBirth(dateOfBirth);
		assertEquals(0, user.getAge());
	}
	
	// In case if Birth Year is year ago
	@Test void testGetAgeYearAgo() {
		LocalDate dateOfBirth = LocalDate.now().minusYears(1);
		user.setDateOfBirth(dateOfBirth);
		assertEquals(1, user.getAge());
	}
	
	// In case if birthday was month ago
	@Test void testGetAgeMonthBefore() {
		LocalDate dateOfBirth = LocalDate.now().minusMonths(1);
		user.setDateOfBirth(dateOfBirth);
		assertEquals(0, user.getAge());
	}
	
	// In case if birthday was year after YEAR_OF_BIRTH constant
	@Test void testGetAgeInYearAfter() {
		LocalDate dateOfBirth = LocalDate.of(YEAR_OF_BIRTH + 1, MONTH_OF_BIRTH, DAY_OF_BIRTH);
		user.setDateOfBirth(dateOfBirth);
		assertEquals(AGE - 1, user.getAge());
	}
	
	// In case if birthday was year after YEAR_OF_BIRTH constant
	@Test void testGetAgeInYearBefore() {
		LocalDate dateOfBirth = LocalDate.of(YEAR_OF_BIRTH - 1, MONTH_OF_BIRTH, DAY_OF_BIRTH);
		user.setDateOfBirth(dateOfBirth);
		assertEquals(AGE + 1, user.getAge());
	}
	
	@Test void getFullName() {
		user.setFirstName(FIRST_NAME);
		user.setLastName(LAST_NAME);
		assertEquals(FULL_NAME, user.getFullName());
	}

}
