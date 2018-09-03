/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.csi.dao;
import br.csi.model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Enrico
 */
public class FuncionarioDAO {
    
    /**
     * Não está funcionando, está retornando nullPointerException
     * 
     * 
     * Tem que fazer correções 
     * 
     * 
     * 
     */
    public static void main(String[] args) {
        Funcionario f = new FuncionarioDAO().read(2);
        System.out.println("Salario: " + f.getSalario());
    }
    
    
    public boolean create (Funcionario funcionario) {
        return false;
    }
    
    public Funcionario read (int id) {
        try (Connection conn = new ConectaDB_Postgress().getConexao()) {
            String SQL = "SELECT * FROM funcionario WHERE funcionario.id_usuario = usuario.id AND funcionario.id = ?";
            PreparedStatement pre = conn.prepareStatement(SQL);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                
                Funcionario funcionario = new Funcionario();
                funcionario.setEmail(rs.getString("email"));
                funcionario.setSenha(rs.getString("senha"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setSalario(rs.getFloat("salario"));
                return funcionario;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
}
