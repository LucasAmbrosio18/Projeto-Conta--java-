package dao;

import java.sql.Connection;

import connection.Conexao;

public class DAO {
     Connection conexao;

  public DAO() {
    conexao = new Conexao().getConnection();
  }
}

