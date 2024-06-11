package view;

import javax.swing.*;
import dao.UserDao;
import model.User;

import java.awt.*;
import java.awt.event.*;

public class LoginScreen extends JFrame implements ActionListener {
  private JTextField emailField;
  private JPasswordField passwordField;
  private JButton loginButton;
  private JButton cancelButton;
  private JButton registerButton;

  public LoginScreen() {
    setTitle("Login");
    setSize(600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.anchor = GridBagConstraints.CENTER;
    constraints.insets = new Insets(10, 10, 10, 10);

    JLabel emailLabel = new JLabel("Email:");
    JLabel passwordLabel = new JLabel("Password:");

    emailField = new JTextField(20);
    passwordField = new JPasswordField(20);
    loginButton = new JButton("Login");
    cancelButton = new JButton("Cancel");
    registerButton = new JButton("Register");

    constraints.gridx = 0;
    constraints.gridy = 0;
    panel.add(emailLabel, constraints);

    constraints.gridx = 1;
    panel.add(emailField, constraints);

    constraints.gridx = 0;
    constraints.gridy = 1;
    panel.add(passwordLabel, constraints);

    constraints.gridx = 1;
    panel.add(passwordField, constraints);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(cancelButton);
    buttonPanel.add(loginButton);
    buttonPanel.add(registerButton);

    constraints.gridx = 0;
    constraints.gridy = 2;
    constraints.gridwidth = 2;
    panel.add(buttonPanel, constraints);

    add(panel);
    setVisible(true);

    loginButton.addActionListener(this);
    cancelButton.addActionListener(this);
    registerButton.addActionListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    UserDao userDao = new UserDao();
    if (e.getSource() == loginButton) {
      String email = emailField.getText();
      String password = new String(passwordField.getPassword());

      if (userDao.login(email, password)) {
        User loggedInUser = userDao.findByEmail(email);
        new BillsListScreen(loggedInUser);
        dispose();
      } else {
        JOptionPane.showMessageDialog(this, "Incorrect Email or Password. Try Again", "Try Again",
            JOptionPane.INFORMATION_MESSAGE);
      }
    } else if (e.getSource() == registerButton) {
      new RegisterScreen();
      dispose();
    } else if (e.getSource() == cancelButton) {
      System.exit(0);
    }
  }
}
