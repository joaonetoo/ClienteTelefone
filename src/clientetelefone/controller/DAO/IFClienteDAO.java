/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetelefone.controller.DAO;

import clientetelefone.model.Cliente;
import clientetelefone.model.Telefone;
import java.sql.SQLException;
import java.util.List;

public interface IFClienteDAO {
    public void createCliente(Cliente c) throws SQLException;
    public void updateCliente(Cliente c, int pk) throws SQLException;
    public List<Cliente> getClientes() throws SQLException; //read
    public Cliente findClienteByPK(int pk) throws SQLException;
    public void deleteCliente(Cliente c) throws SQLException;
    public List<Telefone> listarTodosTelefonesCliente(int pk) throws SQLException;
    
}
