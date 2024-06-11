package view;

import javax.swing.*;

import dao.FeedbackDao;
import model.Bill;
import model.Feedback;
import model.User;

import java.awt.*;

public class AddFeedbackScreen extends JFrame {
  private Bill bill;
  private JTextArea feedbackTextArea;
  private User loggedInUser;

  public AddFeedbackScreen(Bill bill, User user) {
    this.bill = bill;
    this.loggedInUser = user;
    setTitle("Add Feedback");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(400, 300);
    setLocationRelativeTo(null);

    JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JLabel titleLabel = new JLabel("Add Feedback");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
    mainPanel.add(titleLabel, BorderLayout.NORTH);

    feedbackTextArea = new JTextArea(10, 30);
    JScrollPane scrollPane = new JScrollPane(feedbackTextArea);
    mainPanel.add(scrollPane, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    JButton cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(e -> dispose());
    buttonPanel.add(cancelButton);

    JButton createButton = new JButton("Create");
    createButton.addActionListener(e -> createFeedback());
    buttonPanel.add(createButton);

    mainPanel.add(buttonPanel, BorderLayout.SOUTH);

    add(mainPanel);

    setVisible(true);
  }

  private void createFeedback() {
    String feedbackText = feedbackTextArea.getText();
    Feedback feedback = new Feedback();
    feedback.setBill(bill);
    feedback.setUser(loggedInUser);
    feedback.setComment(feedbackText);
    new FeedbackDao().create(feedback);

    new BillFeedbackScreen(bill, loggedInUser);
    dispose();
  }
}
