package controller.funcionario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.funcionario.Funcionario;

public class ControllerFuncionario {
    
    public static ArrayList<Funcionario> listaFuncionario() throws SQLException { 
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            con = new  Conexao().conectar();
            stmt = con.prepareStatement("SELECT * FROM funcionario");
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                int codigo = rs.getInt("codigo");
                String nome = rs.getString("nome");
                int contacto = rs.getInt("contato");
                String departamento = rs.getString("departamento");
                String generoStr = rs.getString("genero");
                char genero = generoStr != null && !generoStr.isEmpty() ? generoStr.charAt(0) : ' ';
                String estadoCivilStr = rs.getString("estadoCivil");
                char estadoCivil = estadoCivilStr != null && !estadoCivilStr.isEmpty() ? estadoCivilStr.charAt(0) : ' ';
                int diasTrabalhados = rs.getInt("diasTrabalhados");
                double salarioDiario = rs.getDouble("salarioDiario");
                
                funcionarios.add(new Funcionario(codigo, nome, contacto, departamento, 
                                               genero, estadoCivil, diasTrabalhados, salarioDiario));
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
        return funcionarios;
    }
    
    public static void adicionarFuncionario(int codigo, String nome, int contacto, String departamento, 
                                          char genero, char estadoCivil, int diasTrabalhados, double salarioDiario) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            con = new Conexao().conectar();
            stmt = con.prepareStatement("INSERT INTO funcionario VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            
            stmt.setInt(1, codigo);
            stmt.setString(2, nome);
            stmt.setInt(3, contacto);
            stmt.setString(4, departamento);
            stmt.setString(5, String.valueOf(genero));
            stmt.setString(6, String.valueOf(estadoCivil));
            stmt.setInt(7, diasTrabalhados);
            stmt.setDouble(8, salarioDiario);

            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }
    
    public static void actualizarFuncionario(int codigo, String nome, int contacto, String departamento, 
                                           char genero, char estadoCivil, int diasTrabalhados, double salarioDiario) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            con = new Conexao().conectar();
            stmt = con.prepareStatement("UPDATE funcionario SET nome=?, contacto=?, departamento=?, "
                                       + "genero=?, estadoCivil=?, diasTrabalhados=?, salarioDiario=? "
                                       + "WHERE codigo=?");
            
            stmt.setString(1, nome);
            stmt.setInt(2, contacto);
            stmt.setString(3, departamento);
            stmt.setString(4, String.valueOf(genero));
            stmt.setString(5, String.valueOf(estadoCivil));
            stmt.setInt(6, diasTrabalhados);
            stmt.setDouble(7, salarioDiario);
            stmt.setInt(8, codigo);

            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }
    
    public static void removerFuncionario(int codigo) throws SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        
        try {
            con = new Conexao().conectar();
            stmt = con.prepareStatement("DELETE FROM funcionario WHERE codigo=?");
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        } finally {
            if (stmt != null) stmt.close();
            if (con != null) con.close();
        }
    }
}