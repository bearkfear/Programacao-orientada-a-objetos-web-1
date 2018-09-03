package br.csi.dao;


/**
 * importação de java.Connection referencia a classe Connection
 * Será importante para retornar um tipo de conexão 
*/
import java.sql.Connection;

/**
 * Importação da classe DriverManager referente 
 
 */
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaDB_Postgress {
    
    /**
     * Classe para conexão com banco de dados postgres
     * 
     * 
     * Declaração das constantes estáticas para o driver, 
     * url, 
     * usuário do banco de dados e 
     * a senha
    */
    
    // DRIVER
    private static final String DRIVER = "org.postgresql.Driver";
    
    // URL
    private static final String URL = "jdbc:postgresql://localhost:5432/rotas2018";
    
    // USUARIO
    private static final String USER = "admin";
    
    // SENHA DO USUARIO
    private static final String PASSWORD = "0000";
    
    
    
    public Connection getConexao() {
        Connection conn = null;

        try {
            Class.forName(DRIVER); // escolhe o banco apartir do driver
            conn = DriverManager.getConnection(URL , USER, PASSWORD); // conecta no banco e recebe uma conexão
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;

    }
}
