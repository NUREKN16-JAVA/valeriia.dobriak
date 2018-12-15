package ua.nure.kn.dobriak.usermanagement.gui;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;

import javax.swing.*;
import java.awt.*;

public class MainFrameTest extends JFCTestCase {
    private MainFrame mainFrame;

    protected void setUp() throws Exception {
        super.setUp();
        setHelper(new JFCTestHelper());
        mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    protected void tearDown() throws Exception {
        mainFrame.setVisible(false);
        getHelper().cleanUp(this);
        super.tearDown();
    }

    private Component find(Class componentClass, String name) {
        NamedComponentFinder finder;
        finder = new NamedComponentFinder(componentClass, name);
        finder.setWait(0);
        Component component = finder.find(mainFrame, 0);
        assertNotNull("Could not find component '" + name + "'" + component);
        return component;
    }

    public void testBrowseControlls() {
        find(JPanel.class, "browsePanel");
        find(JButton.class, "addButton");
        find(JButton.class, "editButton");
        find(JButton.class, "deleteButton");
        find(JButton.class, "detailsButton");
        JTable table = (JTable) find(JTable.class, "userTable");
        assertEquals(4, table.getColumnCount());
        assertEquals("ID", table.getColumnName(0));
        assertEquals("First Name", table.getColumnName(1));
        assertEquals("Last Name", table.getColumnName(2));
        assertEquals("Date of Birth", table.getColumnName(3));
    }

    public void testAddUser() {
        JButton addButton = (JButton) find(JButton.class, "addButton");
        getHelper().enterClickAndLeave(new MouseEventData(this, addButton));

        find(JPanel.class, "addPanel");

        find(JTextField.class, "firstNameField");
        find(JTextField.class, "lastNameField");
        find(JTextField.class, "dateOfBirthField");
        find(JButton.class, "submitButton");
        find(JButton.class, "cancelButton");
    }
}
