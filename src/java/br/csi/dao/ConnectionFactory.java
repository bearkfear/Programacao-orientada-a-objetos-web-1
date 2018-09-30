package br.csi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * Classe para conexão com banco de dados PostgreSQL
 *
 * @author BearkFear
 */
public class ConnectionFactory {

    // DRIVER
    private static final String DRIVER = "org.postgresql.Driver";

    // URL
    private static final String URL = "jdbc:postgresql://localhost:5432/rotas2018";

    // USUARIO
    private static final String USER = "postgres";

    // SENHA DO USUARIO
    private static final String PASSWORD = "1234";

    /**
     *
     * Metodo que retorna conexão com banco de dados Retorna uma Connection
     *
     * @return
     */
    public Connection getConexao() {
        Connection conn = null;

        try {
            Class.forName(DRIVER); // escolhe o banco apartir do driver
            conn = DriverManager.getConnection(URL, USER, PASSWORD); // conecta no banco e recebe uma conexão
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;

    }
}
