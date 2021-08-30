package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    
    // armazena a conexão ativa com o banco de dados
    public Connection conexao = null;
    
    // efetua a conexão com o banco de dados
    public void conecte() throws ClassNotFoundException, SQLException{
        
        // string com informações de conexão com o BD
        String url = "jdbc:mysql://localhost:3306/pooii?autoReconnect=true";
        
        // determina usuário e senha de acesso ao SGBD
        String usuario = "root";
        String senha = "";
        
        // registra o driver de conexão 
        Class.forName("com.mysql.jdbc.Driver");
    
        // atribui a conexão com o BD ao atributo conexão
        this.conexao = DriverManager.getConnection(url, usuario, senha);
    }
}
