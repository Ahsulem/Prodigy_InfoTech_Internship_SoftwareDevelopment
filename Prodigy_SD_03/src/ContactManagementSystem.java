import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ContactManagementSystem extends JFrame {
    private ArrayList<Contact> contacts;
    private JTextField nameField, phoneField, emailField;
    private JTextArea contactListArea;
    private JButton addButton, viewButton, editButton, deleteButton;

    public ContactManagementSystem() {
        contacts = new ArrayList<>();
        setTitle("Contact Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(50, 50, 50)); // Dark background

        initComponents();
    }

    private void initComponents() {
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(30, 30, 30));
        JLabel titleLabel = new JLabel("Contact Management System");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBackground(new Color(50, 50, 50));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        nameField = new JTextField(20);
        JLabel phoneLabel = new JLabel("Phone:");
        phoneLabel.setForeground(Color.WHITE);
        phoneField = new JTextField(20);
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailField = new JTextField(20);

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(phoneLabel);
        inputPanel.add(phoneField);
        inputPanel.add(emailLabel);
        inputPanel.add(emailField);

        addButton = createStyledButton("Add Contact");
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.WEST);

        // Contact List Area
        contactListArea = new JTextArea();
        contactListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(contactListArea);
        add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(50, 50, 50));

        viewButton = createStyledButton("View Contacts");
        editButton = createStyledButton("Edit Contact");
        deleteButton = createStyledButton("Delete Contact");

        buttonPanel.add(viewButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        addButton.addActionListener(e -> addContact());
        viewButton.addActionListener(e -> viewContacts());
        editButton.addActionListener(e -> editContact());
        deleteButton.addActionListener(e -> deleteContact());
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(0, 102, 204));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }

    private void addContact() {
        String name = nameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
            contacts.add(new Contact(name, phone, email));
            clearFields();
            JOptionPane.showMessageDialog(this, "Contact added successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Please fill all fields!");
        }
    }

    private void viewContacts() {
        contactListArea.setText("");
        for (Contact contact : contacts) {
            contactListArea.append(contact.toString() + "\n\n");
        }
    }

    private void editContact() {
        String name = JOptionPane.showInputDialog(this, "Enter the name of the contact to edit:");
        if (name != null && !name.isEmpty()) {
            for (Contact contact : contacts) {
                if (contact.getName().equalsIgnoreCase(name)) {
                    String newPhone = JOptionPane.showInputDialog(this, "Enter new phone number:", contact.getPhone());
                    String newEmail = JOptionPane.showInputDialog(this, "Enter new email:", contact.getEmail());
                    if (newPhone != null && newEmail != null) {
                        contact.setPhone(newPhone);
                        contact.setEmail(newEmail);
                        JOptionPane.showMessageDialog(this, "Contact updated successfully!");
                        return;
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Contact not found!");
        }
    }

    private void deleteContact() {
        String name = JOptionPane.showInputDialog(this, "Enter the name of the contact to delete:");
        if (name != null && !name.isEmpty()) {
            for (Contact contact : contacts) {
                if (contact.getName().equalsIgnoreCase(name)) {
                    contacts.remove(contact);
                    JOptionPane.showMessageDialog(this, "Contact deleted successfully!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Contact not found!");
        }
    }

    private void clearFields() {
        nameField.setText("");
        phoneField.setText("");
        emailField.setText("");
    }

    private class Contact {
        private String name, phone, email;

        public Contact(String name, String phone, String email) {
            this.name = name;
            this.phone = phone;
            this.email = email;
        }

        public String getName() { return name; }
        public String getPhone() { return phone; }
        public String getEmail() { return email; }
        public void setPhone(String phone) { this.phone = phone; }
        public void setEmail(String email) { this.email = email; }

        @Override
        public String toString() {
            return "Name: " + name + "\nPhone: " + phone + "\nEmail: " + email;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ContactManagementSystem cms = new ContactManagementSystem();
            cms.setVisible(true);
        });
    }
}