package view;

import javax.swing.*;

import dao.FeedbackDao;
import model.Bill;
import model.Feedback;
import model.User;

import java.awt.*;
import java.util.List;

public class BillFeedbackScreen extends JFrame {
  private Bill bill;
  private List<Feedback> feedbackList;
  private User loggedInUser;

  public BillFeedbackScreen(Bill bill, User user) {
    this.bill = bill;
    this.loggedInUser = user;
    this.feedbackList = new FeedbackDao().findByBill(bill);
    setTitle("Bill Feedbacks");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setSize(600, 400);
    setLocationRelativeTo(null);

    JPanel headerPanel = new JPanel(new BorderLayout());
    headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JLabel titleLabel = new JLabel("Bill Details");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
    headerPanel.add(titleLabel, BorderLayout.NORTH);

    JPanel detailsPanel = new JPanel(new GridLayout(3, 2));
    detailsPanel.add(new JLabel("ID: "));
    detailsPanel.add(new JLabel(String.valueOf(bill.getId())));
    detailsPanel.add(new JLabel("Date: "));
    detailsPanel.add(new JLabel(bill.getDate()));
    detailsPanel.add(new JLabel("Cost: "));
    detailsPanel.add(new JLabel("$" + bill.getCost()));
    headerPanel.add(detailsPanel, BorderLayout.CENTER);

    add(headerPanel, BorderLayout.NORTH);

    JPanel feedbackPanel = new JPanel(new BorderLayout());
    feedbackPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JLabel feedbackLabel = new JLabel("Feedbacks");
    feedbackLabel.setFont(new Font("Arial", Font.BOLD, 16));
    feedbackPanel.add(feedbackLabel, BorderLayout.NORTH);

    JPanel feedbackListPanel = new JPanel(new GridLayout(feedbackList.size(), 1));
    for (Feedback feedback : feedbackList) {
      JLabel feedbackTextLabel = new JLabel(feedback.getComment());
      feedbackListPanel.add(feedbackTextLabel);
    }
    JScrollPane scrollPane = new JScrollPane(feedbackListPanel);
    feedbackPanel.add(scrollPane, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton newFeedbackButton = new JButton("New Feedback");
    newFeedbackButton.addActionListener(e -> addNewFeedback());
    JButton backButton = new JButton("Back");
    backButton.addActionListener(e -> goBack());
    buttonPanel.add(newFeedbackButton);
    buttonPanel.add(backButton);

    feedbackPanel.add(buttonPanel, BorderLayout.SOUTH);

    add(feedbackPanel, BorderLayout.CENTER);

    setVisible(true);
  }

  private void addNewFeedback() {
    new AddFeedbackScreen(bill, loggedInUser);
  }

  private void goBack(){
    new BillsListScreen(loggedInUser);
    dispose();
  }
}
