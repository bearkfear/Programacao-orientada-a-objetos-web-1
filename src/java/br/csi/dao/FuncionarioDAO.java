package br.csi.dao;

import br.csi.model.Funcionario;
import br.csi.model.Usuario;
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

    /**
     * Metodo responsável por armazenar e criar uma ocorrencia de entidade no
     * banco de dados cria um usuario e um funcionario associando-os com chave
     * estrangeira
     *
     *
     * @param funcionario
     * @return
     */
    public boolean create(Funcionario funcionario) {
        funcionario.setId(new UsuarioDAO().create(funcionario.getNome(), funcionario.getSenha(), funcionario.getEmail()));
        if (funcionario.getId() < 1) {
            return false;
        }

        // salvar usuario 
        try (Connection conn = new ConnectionFactory().getConexao()) {
            String SQL = "INSERT INTO funcionario (id_usuario, salario) Values (?,?);  ";
            PreparedStatement pre = conn.prepareStatement(SQL);
            pre.setInt(1, funcionario.getId());
            pre.setFloat(2, funcionario.getSalario());
            if (pre.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException e) {

        }
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
        try (Connection conn = new ConnectionFactory().getConexao()) {
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
        }

        return null;
    }

    /**
     * Faz atualização no banco de dados nas entidades funcionario e usuario
     *
     * Caso tenha feito o update com sucesso retorna TRUE caso tenha feito o
     * update sem exito retorna FALSE
     *
     * @param funcionario
     * @return
     */
    public boolean update(Funcionario funcionario) {

        try (Connection conn = new ConnectionFactory().getConexao()) {

            String SQL = "SELECT id_usuario FROM funcionario WHERE funcionario.id = ?";
            PreparedStatement pre = conn.prepareStatement(SQL);
            // consulta do id do usuario
            pre.setInt(1, funcionario.getId());
            ResultSet rs = pre.executeQuery();
            rs.next();
            int idUsuario = rs.getInt("id_usuario");

            if (idUsuario < 1) {
                return false;
            }

            Usuario usuario = new Usuario();
            usuario.setNome(funcionario.getNome());
            usuario.setEmail(funcionario.getEmail());
            usuario.setSenha(funcionario.getSenha());
            usuario.setId(idUsuario);

            if (new UsuarioDAO().update(usuario) == false) {
                return false;
            }

            SQL = "UPDATE funcionario SET salario = ? WHERE id = ?";
            pre.setFloat(1, funcionario.getSalario());
            pre.setInt(2, funcionario.getId());

            if (pre.executeUpdate() < 1) {
                return false;
            } else {
                return true;
            }

        } catch (SQLException ex) {
        }

        return false;
    }

    /**
     *
     * Deleta o funcionario no banco de dados e o seu respectivo usuario
     * associado por chave estrangeira
     *
     * Caso tenha exito TRUE caso não tenha exito FALSE
     *
     * @param id
     * @return
     */
    public boolean delete(int id) {

        try (Connection conn = new ConnectionFactory().getConexao()) {
            String SQL = "SELECT id_usuario FROM funcionario WHERE funcionario.id = ?";
            PreparedStatement pre = conn.prepareStatement(SQL);
            // consulta do id do usuario
            pre.setInt(1, id);
            ResultSet rs = pre.executeQuery();
            rs.next();
            int idUsuario = rs.getInt("id_usuario");

            if (idUsuario < 1) {
                return false;
            }

            // Remove funcionario com base no id
            SQL = "DELETE FROM FUNCIONARIO WHERE funcionario.id = ?";
            pre = conn.prepareStatement(SQL);
            pre.setInt(1, id);
            if (pre.executeUpdate() < 1) {
                return false;
            }

            // Remove Usuario
            return new UsuarioDAO().delete(idUsuario);

        } catch (SQLException ex) {

        }

        return false;
    }
}
