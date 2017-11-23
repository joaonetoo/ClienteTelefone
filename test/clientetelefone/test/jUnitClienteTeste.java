/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetelefone.test;

import clientetelefone.controller.DAO.ClienteDAOImpl;
import clientetelefone.model.Cliente;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class jUnitClienteTeste {
    private static Cliente cliente;
    private static Cliente cliente2;
    private static Cliente cliente3;
    
    public jUnitClienteTeste() {
    }
    
    @BeforeClass
    public static void setUp() {
        try {
            ClienteDAOImpl.getInstance().criarTabelaCliente();
        } catch (SQLException ex) {
            Logger.getLogger(jUnitClienteTeste.class.getName()).log(Level.SEVERE, null, ex);
        }
        cliente = new Cliente("Leonardo de Araujo Leitao",
                "Avenida Joao Mauricio",
                "05295631427");
        cliente2 = new Cliente("Gabriel Araujo Marques", "Rua Numerisman de Andrade Carneiro", "11029618410");
        cliente3 = new Cliente("Joao Antonio Leite dos Santos Neto", "Rua Rosa Lima dos Santos", "10734640451");
    }
    
    @Test
    public void criarCliente() {
        try {
            ClienteDAOImpl.getInstance().createCliente(cliente);
            ClienteDAOImpl.getInstance().createCliente(cliente2);
            ClienteDAOImpl.getInstance().createCliente(cliente3);
        } catch (SQLException ex) {
            Logger.getLogger(jUnitClienteTeste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void atualizarCliente() {
        cliente.setEndereco("Avenida Epitacio Pessoa");
        cliente2.setEndereco("Rua Torontes");
        cliente3.setCpf("1234567890");
        try {
            ClienteDAOImpl.getInstance().updateCliente(cliente, 1);
            ClienteDAOImpl.getInstance().updateCliente(cliente2, 2);
            ClienteDAOImpl.getInstance().updateCliente(cliente3, 3);
        } catch (SQLException ex) {
            Logger.getLogger(jUnitClienteTeste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void getClientes() {
        try {
            System.out.println(ClienteDAOImpl.getInstance().getClientes());
        } catch (SQLException ex) {
            Logger.getLogger(jUnitClienteTeste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void findClienteByPK() {
        try {
            System.out.println(ClienteDAOImpl.getInstance().findClienteByPK(1));
            System.out.println(ClienteDAOImpl.getInstance().findClienteByPK(2));
            System.out.println(ClienteDAOImpl.getInstance().findClienteByPK(3));
        } catch (SQLException ex) {
            Logger.getLogger(jUnitClienteTeste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void deletarCliente() {
        try {
            ClienteDAOImpl.getInstance().deleteCliente(cliente);
        } catch (SQLException ex) {
            Logger.getLogger(jUnitClienteTeste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void listarTodosTelefonesDeUmCliente() {
        try {
            System.out.println(ClienteDAOImpl.getInstance().listarTodosTelefonesCliente(1));
        } catch (SQLException ex) {
            Logger.getLogger(jUnitClienteTeste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
