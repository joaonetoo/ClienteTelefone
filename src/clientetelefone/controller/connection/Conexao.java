/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetelefone.controller.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static Connection connection = null;
    private final String DRIVER = "org.postgresql.Driver";
    private final String DBNAME = "clientetelefone";
    private final String URL = "jdbc:postgresql://localhost:5432/"+DBNAME;
    private final String USERNAME = "postgres";
    private final String PASSWORD = "";
    
    public Conexao() {}
    
    public boolean conectar() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return true;
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Connection getConexao() {
        return connection;
    }
    
    public boolean close() {
        try {
            connection.close();
            return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
