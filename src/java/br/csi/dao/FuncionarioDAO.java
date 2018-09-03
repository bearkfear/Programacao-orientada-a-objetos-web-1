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
 * Classe FuncionarioDAO que conecta com banco de dados como CRUD
 *
 *
 * @author Enrico
 */

/*

Tabela no banco de dados 

CREATE TABLE funcionario(
    id serial, 
    id_usuario int,
    salario float,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    PRIMARY KEY (ID)
)
 */
public class FuncionarioDAO {

    public static void main(String[] args) {
        Funcionario f = new FuncionarioDAO().read(2);
        System.out.println("Salario: " + f.getSalario());
    }

    
    
    /**
     * Metodo responsável por armazenar e criar uma ocorrencia de entidade no banco de dados
     * 
     * 
     * @param funcionario
     * @return 
     */
    public boolean create(Funcionario funcionario) {
        return false;
    }

    /**
     * Metodo leitura de um funcionario, le as informações do funcionario no
     * banco de dados e retorna o funcionario
     *
     * Utiliza o codigo de identificação do usuario para retornar as informações
     *
     * @param id
     * @return 
     */
    public Funcionario read(int id) {
        try (Connection conn = new ConectaDB_Postgress().getConexao()) {
            String SQL = "SELECT * FROM funcionario, usuario WHERE funcionario.id_usuario = usuario.id AND funcionario.id = ?";
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
