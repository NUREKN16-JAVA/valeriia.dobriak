package ua.nure.kn.dobriak.usermanagement.db;

import junit.framework.TestCase;

import ua.nure.kn.dobriak.usermanagement.User;

import java.time.LocalDate;
import java.util.List;

public class ManageUsersTest extends TestCase {
    private String FIRST_NAME = "Valeria";
    private String LAST_NAME = "Dobriak";
    private LocalDate DATE_OF_BIRTH = LocalDate.of(1998, 9, 30);

    private String OTHER_FIRST_NAME = "AnotherName";
    private String OTHER_LAST_NAME = "AnotherLastName";
    private LocalDate OTHER_DATE_OF_BIRTH = LocalDate.of(1988, 6, 4);

    public void testCreate() {
        User user = new User(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH);
        Integer userId = ManageUser.create(user);
        assertNotNull(userId);
        ManageUser.destroy(userId);
    }

    public void testFind() {
        User user = new User(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH);
        user.setId(ManageUser.create(user));
        User testedUser = ManageUser.find(user.getId());
        assertEquals(user.getId(), testedUser.getId());
        ManageUser.destroy(user.getId());
    }

    public void testFindAll() {
        Integer firstUserId = ManageUser.create(new User(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH));
        Integer secondUserId = ManageUser.create(new User(OTHER_FIRST_NAME, OTHER_LAST_NAME, OTHER_DATE_OF_BIRTH));
        List users = ManageUser.findAll();
        assertEquals(new Long(users.size()), ManageUser.countAll());
        ManageUser.destroy(firstUserId);
        ManageUser.destroy(secondUserId);
    }

    public void testDestroy() {
        Integer userId = ManageUser.create(new User(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH));
        Long countBefore = ManageUser.countAll();
        ManageUser.destroy(userId);
        Long countAfter = ManageUser.countAll();
        assertTrue(countBefore.intValue() != countAfter.intValue());
    }
}
