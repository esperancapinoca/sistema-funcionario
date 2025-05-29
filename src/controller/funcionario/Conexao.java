package controller.funcionario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
	private Connection connection;
    public  Connection conectar() throws SQLException {
       
    	/* try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            String url = "jdbc:sqlserver://ESPERANCA22PINO" + 
                        ";databaseName=BDfuncionario" + 
                        ";integratedSecurity=true" +
                        ";trustServerCertificate=true";
            
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver JDBC não encontrado", e);
        }*/
    	
    	try {
			return this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBfuncionario", "root", "");
			
		} catch (SQLException  e) {
			System.out.println(e.getMessage());
			return null;
		}
    }
}