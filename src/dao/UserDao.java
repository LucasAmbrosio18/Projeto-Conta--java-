package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDao extends DAO {
    public UserDao(){
        super();
    }
    
    public boolean create (User user) {
      String sql = "INSERT INTO USERS " +
      "(USERNAME, EMAIL, NAME, PASSWORD, PHONE, ISADM) " +
      "VALUES (?, ?, ?, ?, ?, ?)";
      
      try {
        PreparedStatement ps = conexao.prepareStatement(sql);
        ps.setString(1, user.getUsername());
        ps.setString(2, user.getEmail());
        ps.setString(3, user.getName());
        ps.setString(4, user.getPassword());
        ps.setString(5, user.getPhone());
        ps.setBoolean(6, false);
        ps.execute();
        ps.close();
        return true;
      } catch (Exception e) {
        System.out.println("ERRO AO CRIAR USUÁRIO " + e.getMessage());
      }
      return false;
    }
    public User findByEmail (String email) {
        String sql = "SELECT * FROM USERS WHERE EMAIL = ?";
        User user = new User();

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("Email"));
                user.setName(rs.getString("Name"));
                user.setPhone(rs.getString("Phone"));
                user.setAdm(rs.getBoolean("isAdm"));
                user.setId(rs.getInt("Id"));
            }
            ps.close();
            
        } catch (Exception e) {
            System.out.println("ERRO AO BUSCAR USUÁRIO PELO Email " + e.getMessage());
        }
        return user;
    } 
    public boolean update (int id, User updatedUser) {
        String sql = "UPDATE USERS SET " + 
        "USERNAME = ?, EMAIL = ?, PASSWORD = ?, NAME = ?, PHONE = ? " +
        "WHERE ID = ? ";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, updatedUser.getUsername());
            ps.setString(2, updatedUser.getEmail());
            ps.setString(3, updatedUser.getPassword());
            ps.setString(4, updatedUser.getName());
            ps.setString(5, updatedUser.getPhone());
            ps.setInt(6, id );
            ps.execute();
            ps.close();
            return true;
        } catch (Exception e) {
            System.out.println("ERRO AO ATUALIZAR O USUÁRIO " + e.getMessage());
        }
        return false;
    }
    public boolean delete (int id) {
        String sql = "DELETE FROM USERS WHERE ID = ?";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
            return true;
        } catch (Exception e) {
            System.out.println("ERRO AO DELETAR O USUÁRIO " + e.getMessage());
        }
        return false;
    }

    public List<User> findAll(){
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM USERS ";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("Email"));
                user.setName(rs.getString("Name"));
                user.setPhone(rs.getString("Phone"));
                user.setAdm(rs.getBoolean("isAdm"));
                user.setId(rs.getInt("Id"));
                userList.add(user); 
            }
            ps.close(); 
        } catch (Exception e) {
            System.out.println("ERRO AO LISTAR TODOS OS USUÁRIOS " + e.getMessage());
        }
        return userList;
    }

    public boolean login(String email, String password) {
        String sql = "SELECT * FROM USERS WHERE email = ? AND password = ?";
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        }
        catch (SQLException e) {
            System.out.println("ERRO AO FAZER LOGIN " + e.getMessage());
        }
        return false;
    }
    
}
