/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.csi.dao;

import br.csi.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Enrico
 */
public class UsuarioDAO {

    public boolean create(Usuario usuario) {

        try (Connection conn = new ConectaDB_Postgress().getConexao()) {
            PreparedStatement pre = conn.prepareStatement("Insert into usuario (email, nome, senha) values (?,?,?)");
            pre.setString(1, usuario.getNome());
            pre.setString(2, usuario.getSenha());
            pre.setString(3, usuario.getSenha());

            if (pre.executeUpdate() > 0) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Usuario read(int id) {
        Usuario u = new Usuario();
        try (Connection conn = new ConectaDB_Postgress().getConexao()) {

            String SQL = "SELECT * from usuario "
                    + "where id = ?";
            PreparedStatement pre = conn.prepareStatement(SQL);
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            while (rs.next()) {
                //Usuario u = new Usuario();
                u.setNome(rs.getString("nome"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                //return u;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return u;
    }

    public boolean update(Usuario usuario) {

        try (Connection conn = new ConectaDB_Postgress().getConexao()) {
            PreparedStatement pre = conn.prepareStatement("UPDATE usuario set nome = ?, email = ?, senha = ? where id = ?");
            pre.setString(1, usuario.getNome());
            pre.setString(2, usuario.getEmail());
            pre.setString(3, usuario.getSenha());
            pre.setInt(4, usuario.getId());
            if (pre.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {

        // conexão
        // SQL
        // PreparedStatement
        // ExecuteUpdate
        try (Connection conn = new ConectaDB_Postgress().getConexao()) {
            PreparedStatement pre = conn.prepareStatement("Delete from usuario where id = ?");
            pre.setInt(1, id);
            if (pre.executeUpdate() > 0) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Usuario> getUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        try (Connection conn = new ConectaDB_Postgress().getConexao()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM USUARIO");

            while (rs.next()) {
                Usuario us = new Usuario();
                us.setId(rs.getInt("id"));
                us.setNome(rs.getString("nome"));
                us.setEmail(rs.getString("email"));
                us.setSenha(rs.getString("senha"));

                usuarios.add(us);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

}
