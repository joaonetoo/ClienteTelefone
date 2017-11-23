/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetelefone.controller.DAO;

import clientetelefone.controller.connection.Conexao;
import clientetelefone.model.Telefone;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TelefoneDAOImpl implements IFTelefoneDAO {
    private volatile static TelefoneDAOImpl uniqueInstance;
    private Conexao conexao;
    
    private TelefoneDAOImpl() {
        this.conexao = new Conexao();
    }
    
    public void criarTabelaTelefone() throws SQLException{
        this.conexao.conectar();
        String sql = "CREATE TABLE IF NOT EXISTS Telefone (TelefoneID SERIAL,"
                + "ddi int NOT NULL,"
                + "ddd int NOT NULL,"
                + "numero varchar(30) NOT NULL,"
                + "ClienteID int NOT NULL,"
                + "PRIMARY KEY(TelefoneID),"
                + "FOREIGN KEY(ClienteID) REFERENCES Cliente(ClienteID))";
        PreparedStatement comando = this.conexao.getConexao().prepareStatement(sql);
        comando.executeUpdate();
        this.conexao.close();
    }

    @Override
    public int createTelefone(Telefone t) throws SQLException {
        this.conexao.conectar();
        String sql = "INSERT INTO telefone VALUES (default,?,?,?,?)";
        PreparedStatement comando = conexao.getConexao().prepareStatement(sql);
        comando.setInt(1, t.getDdi());
        comando.setInt(2, t.getDdd());
        comando.setString(3, t.getNumero());
        comando.setInt(4, t.getClienteid());
        int result = comando.executeUpdate();
        this.conexao.close();
        return result;
    }
    
    @Override
    public List<Telefone> findTelefoneByClientePK(int clientepk) throws SQLException{
        this.conexao.conectar();
        Telefone t = null;
        List<Telefone> telefones = null;
        String sql = "SELECT * FROM Telefone WHERE clienteid = ?";
        PreparedStatement comando = this.conexao.getConexao().prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE);
        comando.setInt(1, clientepk);
        ResultSet result = comando.executeQuery();
        telefones = toList(result);
        this.conexao.close();
        return telefones;
    }

    @Override
    public int updateTelefone(Telefone t, int pk) throws SQLException {
        this.conexao.conectar();
        String sql = "UPDATE Telefone SET ddi = ?, ddd = ?, numero = ? WHERE telefoneid = ?";
        PreparedStatement comando = this.conexao.getConexao().prepareStatement(sql);
        comando.setInt(1, t.getDdi());
        comando.setInt(2, t.getDdd());
        comando.setString(3, t.getNumero());
        comando.setInt(4, pk);
        int result = comando.executeUpdate();
        this.conexao.close();
        return result;
    }

    @Override
    public List<Telefone> getTelefones() throws SQLException {
        this.conexao.conectar();
        String sql = "SELECT * FROM Telefone";
        PreparedStatement comando = this.conexao.getConexao().prepareStatement(sql);
        List<Telefone> listaTelefones = toList(comando.executeQuery());
        this.conexao.close();
        return listaTelefones;
    }
    
    private List<Telefone> toList(ResultSet result) {
        List<Telefone> listaDeTelefones = new LinkedList<>();
        try {
            while(result.next()) {
                int id = result.getInt(1);
                int ddi = result.getInt(2);
                int ddd = result.getInt(3);
                String numero = result.getString(4);
                Telefone t = new Telefone(id, ddi, ddd, numero);
                listaDeTelefones.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaDeTelefones;
    }


    @Override
    public Telefone findTelefoneByPK(int pk) throws SQLException {
        this.conexao.conectar();
        Telefone t = null;
        String sql = "SELECT * FROM Telefone WHERE telefoneid = ?";
        PreparedStatement comando = this.conexao.getConexao().prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE);
        comando.setInt(1, pk);
        ResultSet result = comando.executeQuery();
        result.first();
        t = new Telefone();
        t.setId(result.getInt(1));
        t.setDdi(result.getInt(2));
        t.setDdd(result.getInt(3));
        t.setNumero(result.getString(4));
        t.setClienteid(result.getInt(5));
        this.conexao.close();
        return t;
    }

    @Override
    public int deleteTelefone(Telefone t) throws SQLException {
        this.conexao.conectar();
        String sql = "DELETE FROM Telefone WHERE telefoneid = ?";
        PreparedStatement comando = this.conexao.getConexao().prepareStatement(sql);
        comando.setInt(1, t.getId());
        int result = comando.executeUpdate();
        this.conexao.close();
        return result;
    }
    
    public static TelefoneDAOImpl getInstance() {
        if(TelefoneDAOImpl.uniqueInstance == null) {
            synchronized(TelefoneDAOImpl.class) {
                if(TelefoneDAOImpl.uniqueInstance == null) {
                    TelefoneDAOImpl.uniqueInstance = new TelefoneDAOImpl();
                }
            }
        }
        return TelefoneDAOImpl.uniqueInstance;
    }
}
