package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Obtém conexão com o banco de dados
    public static Connection obtemConexao() throws SQLException {
        return DriverManager
                .getConnection("jdbc:mysql://localhost/phpmyadmin/sql.php?server=1&db=trabalho&table=pais&pos=0/pais?user=root&password=99449107Gui");
    }

}
