package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import model.Bill;
import model.Feedback;
import model.User;

public class FeedbackDao extends DAO {
    public FeedbackDao(){
        super();
    }

    public void create (Feedback feedback) {
        String sql = "INSERT INTO FEEDBACKS (COMMENT, BILL_ID, USER_ID)" +
        "VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, feedback.getComment());
            ps.setInt(2, feedback.getBill().getId());
            ps.setInt(3, feedback.getUser().getId());
            ps.execute();
            ps.close();
            
        } catch (Exception e) {
            System.out.println("ERRO AO CRIAR FEEDBACK " + e.getMessage());
        }
    }
    
    public void delete (int id) {
        String sql = "DELETE FROM FEEDBACKS WHERE ID = ?";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            System.out.println("ERRO AO DELETAR O FEEDBACK " + e.getMessage());
        }
    }

    public void update (int id, Feedback updatecomment) {
        String sql = "UPDATE FEEDBACKS SET " +
        "COMMENT = ? WHERE ID = ? ";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, updatecomment.getComment());
            ps.setInt(2, id);
            ps.execute();
            ps.close();

        } catch (Exception e) {
            System.out.println("ERRO AO ATUALIZAR O FEEDBACK " + e.getMessage());
        }
    }
    public Feedback findById (int id) {
        String sql = "SELECT " +
        "USERS.ID AS USER_ID, " +
        "USERS.NAME AS USER_NAME, " +
        "FEEDBACKS.ID AS FEEDBACK_ID, " +
        "FEEDBACKS.COMMENT, " +
        "BILLS.ID AS BILL_ID, " +
        "BILLS.DATE AS BILL_DATE " +
        "FROM FEEDBACKS " +
        "INNER JOIN USERS ON FEEDBACKS.USER_ID = USERS.ID " +
        "INNER JOIN BILLS ON FEEDBACKS.BILL_ID = BILLS.ID " +
        "where FEEDBACKS.ID = ?";
        Feedback feedback = new Feedback();
        Bill bill = new Bill();
        User user = new User();
        

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bill.setDate(rs.getString("BILL_DATE"));
                bill.setId(rs.getInt("BILL_ID"));
                user.setName(rs.getString("USER_NAME"));
                user.setId(rs.getInt("USER_ID"));
                feedback.setBill(bill);
                feedback.setUser(user);
                feedback.setId(id);
                feedback.setComment(rs.getString("comment"));

            }
            ps.close();
        } catch (Exception e) {
            System.out.println("ERRO AO BUSCAR FEEDBACK PELO ID " + e.getMessage());
        }
        return feedback;
    }

    public List<Feedback> findByUser (User user) {
        List<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT " +
        "USERS.ID AS USER_ID, " +
        "USERS.NAME AS USER_NAME, " +
        "FEEDBACKS.ID AS FEEDBACK_ID, " +
        "FEEDBACKS.COMMENT, " +
        "BILLS.ID AS BILL_ID, " +
        "BILLS.DATE AS BILL_DATE " +
        "FROM FEEDBACKS " +
        "INNER JOIN USERS ON FEEDBACKS.USER_ID = USERS.ID " +
        "INNER JOIN BILLS ON FEEDBACKS.BILL_ID = BILLS.ID " +
        "where user.id = ?";
        
        
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Feedback feedback = new Feedback();
                Bill bill = new Bill();
                bill.setDate(rs.getString("BILL_DATE"));
                bill.setId(rs.getInt("BILL_ID"));
                user.setName(rs.getString("USER_NAME"));
                user.setId(rs.getInt("USER_ID"));
                feedback.setBill(bill);
                feedback.setUser(user);
                feedback.setId(rs.getInt("FEEDBACK_id"));
                feedback.setComment(rs.getString("comment"));
                feedbackList.add(feedback);
            }
            ps.close();
        } catch (Exception e) {
            System.out.println("ERRO AO BUSCAR FEEDBACK PELO USUÁRIO " + e.getMessage());
        }

        return feedbackList;   
    }
    
    public List<Feedback> findByBill (Bill bill) {
        List<Feedback> feedbackList = new ArrayList<>();
        String sql = "SELECT " +
        "USERS.ID AS USER_ID, " +
        "USERS.NAME AS USER_NAME, " +
        "FEEDBACKS.ID AS FEEDBACK_ID, " +
        "FEEDBACKS.COMMENT, " +
        "BILLS.ID AS BILL_ID, " +
        "BILLS.DATE AS BILL_DATE " +
        "FROM FEEDBACKS " +
        "INNER JOIN USERS ON FEEDBACKS.USER_ID = USERS.ID " +
        "INNER JOIN BILLS ON FEEDBACKS.BILL_ID = BILLS.ID " +
        "where BILLS.ID = ?";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, bill.getId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Feedback feedback = new Feedback();
                User user = new User(); 
                bill.setDate(rs.getString("BILL_DATE"));
                bill.setId(rs.getInt("BILL_ID"));
                user.setName(rs.getString("USER_NAME"));
                user.setId(rs.getInt("USER_ID"));
                feedback.setBill(bill);
                feedback.setUser(user);
                feedback.setId(rs.getInt("FEEDBACK_id"));
                feedback.setComment(rs.getString("comment"));
                feedbackList.add(feedback);
            }
            ps.close();
        } catch (Exception e) {
            System.out.println("ERRO AO BUSCAR FEEDBACK PELA CONTA " + e.getMessage());
        }
        return feedbackList;
    }
    
}
