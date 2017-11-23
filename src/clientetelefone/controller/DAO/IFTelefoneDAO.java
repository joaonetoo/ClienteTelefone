/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetelefone.controller.DAO;

import clientetelefone.model.Telefone;
import java.sql.SQLException;
import java.util.List;

public interface IFTelefoneDAO {
    public int createTelefone(Telefone t) throws SQLException;
    public int updateTelefone(Telefone t, int pk) throws SQLException;
    public List<Telefone> getTelefones() throws SQLException; //read
    public Telefone findTelefoneByPK(int pk) throws SQLException;
    public int deleteTelefone(int pk) throws SQLException;
    public List<Telefone> findTelefoneByClientePK(int clientepk) throws SQLException;
}
