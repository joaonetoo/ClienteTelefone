/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetelefone.test;

import clientetelefone.controller.DAO.TelefoneDAOImpl;
import clientetelefone.model.Telefone;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

public class jUnitTelefoneTeste {
    private static Telefone telefone;
    private static Telefone telefone2;
    private static Telefone telefone3;
    public jUnitTelefoneTeste() {
    }
    
    @BeforeClass
    public static void setUp() {
        try {
            TelefoneDAOImpl.getInstance().criarTabelaTelefone();
        } catch (SQLException ex) {
            Logger.getLogger(jUnitTelefoneTeste.class.getName()).log(Level.SEVERE, null, ex);
        }
        telefone = new Telefone(55,83,"998472949",1);
        telefone2 = new Telefone(55,83,"996571794",2);
        telefone3 = new Telefone(55,83,"996326575",3);
    }
    
    @Test
    public void criarTelefone() {
        try {
            assertEquals(1, TelefoneDAOImpl.getInstance().createTelefone(telefone));
            assertEquals(1, TelefoneDAOImpl.getInstance().createTelefone(telefone2));
            assertEquals(1, TelefoneDAOImpl.getInstance().createTelefone(telefone3));
            
            telefone.setNumero("988170868");
            assertEquals(1, TelefoneDAOImpl.getInstance().createTelefone(telefone));
        } catch (SQLException ex) {
            Logger.getLogger(jUnitTelefoneTeste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void atualizarTelefone() {
        telefone.setNumero("988339964");
        telefone3.setNumero("912345678");
        try {
            TelefoneDAOImpl.getInstance().updateTelefone(telefone, 1);
            TelefoneDAOImpl.getInstance().updateTelefone(telefone3, 3);
        } catch (SQLException ex) {
            Logger.getLogger(jUnitTelefoneTeste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void readTelefones() {
        try {
            System.out.println(TelefoneDAOImpl.getInstance().getTelefones());
        } catch (SQLException ex) {
            Logger.getLogger(jUnitTelefoneTeste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void findTelefoneByID() {
        try {
            System.out.println(TelefoneDAOImpl.getInstance().findTelefoneByPK(1));
            System.out.println(TelefoneDAOImpl.getInstance().findTelefoneByPK(2));
            System.out.println(TelefoneDAOImpl.getInstance().findTelefoneByPK(3));
            System.out.println(TelefoneDAOImpl.getInstance().findTelefoneByPK(4));
        } catch (SQLException ex) {
            Logger.getLogger(jUnitTelefoneTeste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void deleteTelefone() {
        try {
            TelefoneDAOImpl.getInstance().deleteTelefone(telefone);
        } catch (SQLException ex) {
            Logger.getLogger(jUnitTelefoneTeste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
