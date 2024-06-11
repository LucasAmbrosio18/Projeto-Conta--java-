package view;

import javax.swing.*;
import dao.UserDao;
import model.User;

import java.awt.*;
import java.awt.event.*;

public class RegisterScreen extends JFrame implements ActionListener {
  private JTextField usernameField;
  private JTextField emailField;
  private JTextField nameField;
  private JPasswordField passwordField;
  private JTextField phoneField;
  private JButton registerButton;
  private JButton cancelButton;

  public RegisterScreen() {
    setTitle("Register");
    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.insets = new Insets(10, 10, 10, 10);

    JLabel usernameLabel = new JLabel("Username:");
    JLabel emailLabel = new JLabel("Email:");
    JLabel nameLabel = new JLabel("Name:");
    JLabel passwordLabel = new JLabel("Password:");
    JLabel phoneLabel = new JLabel("Phone:");

    usernameField = new JTextField(20);
    emailField = new JTextField(20);
    nameField = new JTextField(20);
    passwordField = new JPasswordField(20);
    phoneField = new JTextField(20);

    registerButton = new JButton("Register");
    cancelButton = new JButton("Cancel");

    constraints.gridx = 0;
    constraints.gridy = 0;
    panel.add(usernameLabel, constraints);

    constraints.gridx = 1;
    panel.add(usernameField, constraints);

    constraints.gridx = 0;
    constraints.gridy = 1;
    panel.add(emailLabel, constraints);

    constraints.gridx = 1;
    panel.add(emailField, constraints);

    constraints.gridx = 0;
    constraints.gridy = 2;
    panel.add(nameLabel, constraints);

    constraints.gridx = 1;
    panel.add(nameField, constraints);

    constraints.gridx = 0;
    constraints.gridy = 3;
    panel.add(passwordLabel, constraints);

    constraints.gridx = 1;
    panel.add(passwordField, constraints);

    constraints.gridx = 0;
    constraints.gridy = 4;
    panel.add(phoneLabel, constraints);

    constraints.gridx = 1;
    panel.add(phoneField, constraints);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(cancelButton);
    buttonPanel.add(registerButton);

    constraints.gridx = 0;
    constraints.gridy = 5;
    constraints.gridwidth = 2;
    panel.add(buttonPanel, constraints);

    add(panel);
    setVisible(true);

    registerButton.addActionListener(this);
    cancelButton.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == registerButton) {
      if (usernameField.getText().equalsIgnoreCase("") ||
          emailField.getText().equalsIgnoreCase("") ||
          nameField.getText().equalsIgnoreCase("") ||
          new String(passwordField.getPassword()).equalsIgnoreCase("") ||
          phoneField.getText().equalsIgnoreCase("")) {
        JOptionPane.showMessageDialog(this, "Registration Failed! Please try again.", "Error",
            JOptionPane.ERROR_MESSAGE);
      } else {

        User user = new User();
        UserDao userDao = new UserDao();
        user.setUsername(usernameField.getText());
        user.setEmail(emailField.getText());
        user.setName(nameField.getText());
        user.setPassword(new String(passwordField.getPassword()));
        user.setPhone(phoneField.getText());

        if (userDao.create(user)) {
          JOptionPane.showMessageDialog(this, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
          new LoginScreen();
          dispose();
        } else {
          JOptionPane.showMessageDialog(this, "Registration Failed! Please try again.", "Error",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    } else if (e.getSource() == cancelButton) {
      new LoginScreen();
      dispose();
    }
  }
}
