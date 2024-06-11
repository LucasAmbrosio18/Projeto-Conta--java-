package view;

import javax.swing.*;
import dao.BillDao;
import model.Bill;
import model.User;

import java.awt.*;
import java.awt.event.*;

public class BillRegistrationScreen extends JFrame implements ActionListener {
  private JTextField dateField;
  private JTextField costField;
  private JButton registerButton;
  private JButton cancelButton;
  private User loggedInUser;

  public BillRegistrationScreen(User user) {
    this.loggedInUser = user;
    setTitle("Payment Registration");
    setSize(600, 400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);

    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.anchor = GridBagConstraints.WEST;
    constraints.insets = new Insets(10, 10, 10, 10);

    JLabel dateLabel = new JLabel("Date:");
    JLabel costLabel = new JLabel("Cost:");

    dateField = new JTextField(20);
    costField = new JTextField(20);

    registerButton = new JButton("Register");
    cancelButton = new JButton("Cancel");

    constraints.gridx = 0;
    constraints.gridy = 0;
    panel.add(dateLabel, constraints);

    constraints.gridx = 1;
    panel.add(dateField, constraints);

    constraints.gridx = 0;
    constraints.gridy = 1;
    panel.add(costLabel, constraints);

    constraints.gridx = 1;
    panel.add(costField, constraints);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    buttonPanel.add(cancelButton);
    buttonPanel.add(registerButton);

    constraints.gridx = 0;
    constraints.gridy = 3;
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
      registerPayment();
    } else if (e.getSource() == cancelButton) {
      new BillsListScreen(this.loggedInUser);
      dispose();
    }
  }

  private void registerPayment() {
    Bill bill = new Bill();
    BillDao billDao = new BillDao();
    bill.setDate(dateField.getText());
    bill.setCost(Double.parseDouble(costField.getText()));

    billDao.create(bill);
    JOptionPane.showMessageDialog(this, "Payment registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

    clearFields();
  }

  private void clearFields() {
    dateField.setText("");
    costField.setText("");
  }
}
