package view;

import javax.swing.*;

import dao.BillDao;
import model.Bill;
import model.User;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class BillsListScreen extends JFrame implements ActionListener {
  private JPanel listPanel;
  private JButton newPaymentButton;
  private JButton logoutButton;
  private User loggedInUser;

  public BillsListScreen(User user) {
    this.loggedInUser = user;
    setTitle("Paid Bills");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(800, 600);
    setLocationRelativeTo(null);

    listPanel = new JPanel();
    listPanel.setLayout(new GridBagLayout());

    BillDao billDao = new BillDao();
    List<Bill> billList = billDao.findAll();

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(10, 10, 10, 10); // Margem de 10 pixels em todos os lados

    for (Bill bill : billList) {
      JPanel card = createCard(bill);
      card.setPreferredSize(new Dimension(200, 100)); // Tamanho fixo para os cartões
      card.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          new BillFeedbackScreen(bill, loggedInUser);
          dispose();
        }
      });
      listPanel.add(card, gbc);
      gbc.gridy++;
    }

    JScrollPane scrollPane = new JScrollPane(listPanel);
    add(scrollPane, BorderLayout.CENTER);

    newPaymentButton = new JButton("New Payment");
    newPaymentButton.addActionListener(this);

    logoutButton = new JButton("Logout");
    logoutButton.addActionListener(this);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    buttonPanel.add(newPaymentButton);
    buttonPanel.add(logoutButton);

    add(buttonPanel, BorderLayout.SOUTH);

    setVisible(true);
  }

  private JPanel createCard(Bill bill) {
    JPanel card = new JPanel();
    card.setLayout(new BorderLayout());

    JLabel dateLabel = new JLabel("Date: " + bill.getDate());
    JLabel costLabel = new JLabel("Cost: $" + bill.getCost());
    JLabel idLabel = new JLabel("ID: " + bill.getId());

    JPanel labelsPanel = new JPanel(new GridLayout(3, 1));
    labelsPanel.add(dateLabel);
    labelsPanel.add(costLabel);
    labelsPanel.add(idLabel);

    card.add(labelsPanel, BorderLayout.CENTER);

    card.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    return card;
  }

  private void addNewPayment() {
    if (this.loggedInUser.isAdm()) {
      new BillRegistrationScreen(loggedInUser);
    } else {
      JOptionPane.showMessageDialog(this, "Permission denied. You do not have authorization to add a new payment.",
          "Permission Denied", JOptionPane.ERROR_MESSAGE);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == newPaymentButton) {
      addNewPayment();
      dispose();
    } else if (e.getSource() == logoutButton) {
      new LoginScreen();
      dispose();
    }
  }
}
