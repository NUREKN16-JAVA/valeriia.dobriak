package ua.nure.kn.dobriak.usermanagement.gui;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
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
        JTable table = (JTable) find(JTable.class, "userTable");
        assertEquals(0, table.getRowCount());
        
        JButton addButton = (JButton) find(JButton.class, "addButton");
        getHelper().enterClickAndLeave(new MouseEventData(this, addButton));

        find(JPanel.class, "addPanel");

        JTextField firstNamefield = (JTextField) find(JTextField.class, "firstNameField");
        JTextField lastNamefield = (JTextField) find(JTextField.class, "lastNameField");
        JTextField dateOfBirth = (JTextField) find(JTextField.class, "dateOfBirthField");
        JButton okButton = (JButton) find(JButton.class, "okButton");
        find(JButton.class, "cancelButton");

        getHelper().sendString(new StringEventData(this, firstNamefield, "John"));
        getHelper().sendString(new StringEventData(this, lastNamefield, "Doe"));

        String date = "1960-02-05";
        getHelper().sendString(new StringEventData(this, dateOfBirth, date));

        getHelper().enterClickAndLeave(new MouseEventData(this, okButton));

        find(JPanel.class, "browsePanel");
        table = (JTable) find(JTable.class, "userTable");
        assertEquals(1, table.getRowCount());
    }
}
