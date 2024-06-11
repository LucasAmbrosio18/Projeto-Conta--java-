package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Bill;

public class BillDao extends DAO{
    public BillDao(){
        super();
    }


    public void create (Bill bill) {
        String sql = "INSERT INTO BILLS " +
        "(DATE, COST) " +
        "VALUES (?, ?)";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1,bill.getDate());
            ps.setDouble(2, bill.getCost());
            ps.execute();

        } catch (Exception e) {
            System.out.println("ERRO AO CRIAR CONTA DE GASTOS " + e.getMessage());
        }  
    }
    public void delete (int id) {
        String sql = "DELETE FROM BILLS WHERE ID = ?";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ps.close();

        } catch (Exception e) {
            System.out.println("ERRO AO DELETAR CONTA DE GASTOS " + e.getMessage());
        }
    }

    public void update (int id, Bill updateBill) {
        String sql = "UPDATE BILLS SET " + 
        "DATE = ?, COST = ?" +
        "WHERE ID = ? ";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1,updateBill.getDate());
            ps.setDouble(2, updateBill.getCost());
            ps.setInt(3, id);
            ps.execute();
            ps.close();
            
        } catch (Exception e) {
            System.out.println("ERRO AO ATUALIZAR A CONTA DE GASTOS " + e.getMessage());
        }
    }
    public Bill findById (int id) {
        String sql = "SELECT * FROM BILLS WHERE ID = ?";
        Bill bill = new Bill();
        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                bill.setDate(rs.getString("date"));
                bill.setCost(rs.getDouble("cost"));
                bill.setId(rs.getInt("id"));

            }
            ps.close();
        } catch (Exception e) {
            System.out.println("ERRO AO BUSCAR CONTA PELO ID " + e.getMessage());
        }
        return bill;

    }

    public List<Bill> findAll(){
        List<Bill> billList = new ArrayList<>();
        String sql = "SELECT * FROM BILLS ";

        try {
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setDate(rs.getString("date"));
                bill.setCost(rs.getDouble("cost"));
                bill.setId(rs.getInt("id"));
                billList.add(bill);
            }
            ps.close();
        } catch (Exception e) {
            System.out.println("ERRO AO LISTAR TODAS AS LISTAS " + e.getMessage());
        }
        return billList;
    }
}   

