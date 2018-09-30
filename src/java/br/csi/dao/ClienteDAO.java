/*
 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.csi.dao;

import br.csi.model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *Classe ClienteDAO é responsável por conectar-se com o banco de dados
 * É o CRUD do Cliente
 * @author Enrico
 */
public class ClienteDAO {

    
    /**
     * Consulta todos os usuários no banco de dados
     * 
     * Retorna uma lista de objetos
     * 
     * @return
     * @throws SQLException 
     * 
     * 
     */
    
    
    public boolean create(Cliente cliente) {
        
        try (Connection conn = new ConnectionFactory().getConexao()){
            String sql = "INSERT INTO cliente ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            
            
            
        } catch (Exception e) {
        }
        
        
        
        return false;
    }
    
    
    public Cliente read (int id) {
        return null;
    }
    
    public boolean update (Cliente cliente) {
        return false;
    }
    
    public boolean delete (int id) {
        
        
        return false;
    }
    
    
    public ArrayList<Cliente> getClientes() throws SQLException {

        ArrayList<Cliente> clientes = new ArrayList<>();
        Connection conn = new ConnectionFactory().getConexao();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * from Cliente");
        while (rs.next()) {
            Cliente cli = new Cliente();
            cli.setId(rs.getInt("id"));
            cli.setEndereco(rs.getString("endereco"));
            cli.setNome(rs.getString("nome"));
            cli.setLatitude(rs.getString("latitude"));
            cli.setLongitude(rs.getString("longitude"));
            
            clientes.add(cli);
        }
        
        

        return clientes;
    }

}
