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
        ManageUsers manageUser = new ManageUsers();
        Long userId = manageUser.create(user);
        assertNotNull(userId);
        manageUser.destroy(Math.toIntExact(userId));
    }

    public void testUpdate() {
        User user = new User(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH);
        ManageUsers manageUser = new ManageUsers();
        user.setId(manageUser.create(user));
        User updatedUser = new User(OTHER_FIRST_NAME, OTHER_LAST_NAME, OTHER_DATE_OF_BIRTH);
        manageUser.update(user.getId(), updatedUser);
        updatedUser = manageUser.find(user.getId());
        assertNotSame(user.getFullName(), updatedUser.getFullName());
        assertNotSame(user.getAge(), updatedUser.getAge());
        manageUser.destroy(user.getId());
    }

    public void testFind() {
        User user = new User(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH);
        ManageUsers manageUser = new ManageUsers();
        user.setId(manageUser.create(user));
        User testedUser = manageUser.find(user.getId());
        assertEquals(user.getId(), testedUser.getId());
        manageUser.destroy(user.getId());
    }

    public void testFindAll() {
        ManageUsers manageUser = new ManageUsers();
        Integer firstUserId = manageUser.create(new User(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH));
        Integer secondUserId = manageUser.create(new User(OTHER_FIRST_NAME, OTHER_LAST_NAME, OTHER_DATE_OF_BIRTH));
        List users = manageUser.findAll();
        assertEquals(new Long(users.size()), manageUser.countAll());
        manageUser.destroy(firstUserId);
        manageUser.destroy(secondUserId);
    }

    public void testDestroy() {
        ManageUsers manageUser = new ManageUsers();
        Integer userId = manageUser.create(new User(FIRST_NAME, LAST_NAME, DATE_OF_BIRTH));
        Long countBefore = manageUser.countAll();
        manageUser.destroy(userId);
        Long countAfter = manageUser.countAll();
        assertTrue(countBefore.intValue() != countAfter.intValue());
    }
}
