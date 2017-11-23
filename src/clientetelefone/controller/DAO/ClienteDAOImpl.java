/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetelefone.controller.DAO;

import clientetelefone.controller.connection.Conexao;
import clientetelefone.model.Cliente;
import clientetelefone.model.Telefone;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAOImpl implements IFClienteDAO {
    private volatile static ClienteDAOImpl uniqueInstance;
    private Conexao conexao;
    
    private ClienteDAOImpl() {
        this.conexao = new Conexao();
    }
    
    public void criarTabelaCliente() throws SQLException{
        this.conexao.conectar();
        String sql = "CREATE TABLE IF NOT EXISTS Cliente (ClienteID SERIAL,"
                + "nome varchar(50) NOT NULL,"
                + "endereco varchar(75) NOT NULL,"
                + "cpf varchar(11)NOT NULL,"
                + "PRIMARY KEY (ClienteID))";
        PreparedStatement comando = this.conexao.getConexao().prepareStatement(sql);
        comando.executeUpdate();
        this.conexao.close();
    }
    
    @Override
    public void createCliente(Cliente c) throws SQLException {
        this.conexao.conectar();
        String sql = "INSERT INTO Cliente VALUES (default,?,?,?)";
        PreparedStatement comando = conexao.getConexao().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        comando.setString(1, c.getNome());
        comando.setString(2, c.getEndereco());
        comando.setString(3, c.getCpf());
        comando.executeUpdate();
        this.conexao.close();
    }

    @Override
    public void updateCliente(Cliente c, int pk) throws SQLException{
        this.conexao.conectar();
        String sql = "UPDATE Cliente SET nome = ?, endereco = ?, cpf = ? WHERE clienteid = ?";
        PreparedStatement comando = this.conexao.getConexao().prepareStatement(sql);
        comando.setString(1, c.getNome());
        comando.setString(2, c.getEndereco());
        comando.setString(3, c.getCpf());
        comando.setInt(4, pk);
        comando.executeUpdate();
        this.conexao.close();
    }

    @Override
    public List<Cliente> getClientes() throws SQLException{
        this.conexao.conectar();
        String sql = "SELECT * FROM Cliente";
        PreparedStatement comando = this.conexao.getConexao().prepareStatement(sql);
        List<Cliente> listaClientes = toList(comando.executeQuery());
        this.conexao.close();
        return listaClientes;
    }
    
    private List<Cliente> toList(ResultSet result) {
        List<Cliente> listaDeClientes = new LinkedList<>();
        try {
            while(result.next()) {
                int id = result.getInt(1);
                String nome = result.getString(2);
                String endereco = result.getString(3);
                String cpf = result.getString(4);
                Cliente c = new Cliente(id, nome, endereco, cpf);
                listaDeClientes.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaDeClientes;
    }

    @Override
    public Cliente findClienteByPK(int pk) throws SQLException{
        this.conexao.conectar();
        Cliente c = new Cliente();
        String sql = "SELECT * FROM Cliente WHERE clienteid = ?";
        PreparedStatement comando = this.conexao.getConexao().prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE);
        comando.setInt(1, pk);
        ResultSet result = comando.executeQuery();
        result.first();
        c.setId(result.getInt(1));
        c.setNome(result.getString(2));
        c.setEndereco(result.getString(3));
        c.setCpf(result.getString(4));
        this.conexao.close();
        return c;
    }

    @Override
    public void deleteCliente(Cliente c) throws SQLException{
        this.conexao.conectar();
        String sql = "DELETE FROM Cliente WHERE clienteid = ?";
        PreparedStatement comando = this.conexao.getConexao().prepareStatement(sql);
        comando.setInt(1, c.getId());
        comando.executeUpdate();
        this.conexao.close();
    }
    
    public static ClienteDAOImpl getInstance() {
        if(uniqueInstance == null) {
            synchronized(ClienteDAOImpl.class) {
                if(uniqueInstance == null) {
                    uniqueInstance = new ClienteDAOImpl();
                }
            }
        }
        return uniqueInstance;
    }

    @Override
    public List<Telefone> listarTodosTelefonesCliente(int pk) throws SQLException {
        this.conexao.conectar();
        String sql = "SELECT * FROM Telefone WHERE clienteid = ?";
        PreparedStatement comando = this.conexao.getConexao().prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, 
                        ResultSet.CONCUR_UPDATABLE);
        comando.setInt(1, pk);
        ResultSet result = comando.executeQuery();
        List<Telefone> telefones = new LinkedList<>();
        while(result.next()) {
            Telefone t = new Telefone();
            t.setId(result.getInt(1));
            t.setDdi(result.getInt(2));
            t.setDdd(result.getInt(3));
            t.setNumero(result.getString(4));
            t.setClienteid(result.getInt(5));
            telefones.add(t);
        }
        this.conexao.close();
        return telefones;
        
        
    }
    
    
}
