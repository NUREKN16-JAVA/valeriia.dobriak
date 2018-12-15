package ua.nure.kn.dobriak.usermanagement.gui;

import ua.nure.kn.dobriak.usermanagement.User;
import ua.nure.kn.dobriak.usermanagement.db.ManageUsers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddPanel extends JPanel implements ActionListener {
    private final MainFrame parent;
    private JPanel buttonPanel;
    private JPanel fieldPanel;
    private JButton cancelButton;
    private JButton submitButton;
    private JTextField dateOfBirthField;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private Color bgColor = Color.WHITE;

    public AddPanel(MainFrame parent) {
        this.parent = parent;
        initialize();
    }

    private void initialize() {
        this.setName("addPanel");
        this.setLayout(new BorderLayout());
        this.add(getFieldPanel(), BorderLayout.NORTH);
        this.add(getButtonPanel(), BorderLayout.SOUTH);

    }

    private JPanel getButtonPanel() {
        if (buttonPanel == null) {
            buttonPanel = new JPanel();
            buttonPanel.add(getSubmitButton(), null);
            buttonPanel.add(getCancelButton(), null);
        }
        return buttonPanel;
    }

    private JPanel getFieldPanel() {
        if (fieldPanel == null) {
            fieldPanel = new JPanel();
            fieldPanel.setLayout(new GridLayout(3, 2));
            addLabeledField(fieldPanel, "First Name", getFirstNameField());
            addLabeledField(fieldPanel, "Last Name", getLastNameField());
            addLabeledField(fieldPanel, "Date of Birth", getDateOfBirthField());
        }
        return fieldPanel;
    }

    private void addLabeledField(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setLabelFor(textField);
        panel.add(label);
        panel.add(textField);
    }

    private JButton getSubmitButton() {
        if (submitButton == null) {
            submitButton = new JButton();
            submitButton.setText("Добавить");
            submitButton.setName("okButton");
            submitButton.setActionCommand("submit");
            submitButton.addActionListener(this);
        }
        return submitButton;
    }

    private JButton getCancelButton() {
        if (cancelButton == null) {
            cancelButton = new JButton();
            cancelButton.setText("Отменить");
            cancelButton.setName("cancelButton");
            cancelButton.setActionCommand("cancel");
            cancelButton.addActionListener(this);
        }
        return cancelButton;
    }

    private JTextField getFirstNameField() {
        if (firstNameField == null) {
            firstNameField = new JTextField();
            firstNameField.setName("firstNameField");
        }
        return firstNameField;
    }

    private JTextField getLastNameField() {
        if (lastNameField == null) {
            lastNameField = new JTextField();
            lastNameField.setName("lastNameField");
        }
        return lastNameField;
    }

    private JTextField getDateOfBirthField() {
        if (dateOfBirthField == null) {
            dateOfBirthField = new JTextField();
            dateOfBirthField.setName("dateOfBirthField");
        }
        return dateOfBirthField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ("submit".equalsIgnoreCase(e.getActionCommand())) {
            User user = new User();
            user.setFirstName(getFirstNameField().getText());
            user.setLastName(getLastNameField().getText());
            LocalDate date;
            try {
                date = LocalDate.parse(getDateOfBirthField().getText());
            } catch (DateTimeParseException el) {
                getDateOfBirthField().setBackground(Color.RED);
                return;
            }
            user.setDateOfBirth(date);
            Integer userId = ManageUsers.create(user);
            if (userId == null) {
                JOptionPane.showMessageDialog(this, "User was not created", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        clearFields();
        this.setVisible(false);
        parent.showBrowsePanel();
    }

    private void clearFields() {
        getFirstNameField().setText("");
        getLastNameField().setText("");
        getDateOfBirthField().setText("");
        getFirstNameField().setBackground(bgColor);
        getLastNameField().setBackground(bgColor);
        getDateOfBirthField().setBackground(bgColor);
    }
}
