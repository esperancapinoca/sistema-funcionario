package controller.funcionario;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
	private Connection connection;
    public  Connection conectar() throws SQLException {
     	
    	try {
			return this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/DBfuncionario", "root", "");
			
		} catch (SQLException  e) {
			System.out.println(e.getMessage());
			return null;
		}
    }
}