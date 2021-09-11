package dao;

import bean.Cliente;
import java.sql.ResultSet;
import config.Conexao;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class ClienteDAO {

    // lista todos os clientes
    public ArrayList liste() {
        // declara coleção de objetos vazia
        ArrayList<Cliente> clientes = new ArrayList<>();

        try {

            // efetua a conexão com o BD
            Conexao conn = new Conexao();
            conn.conecte();

            // objeto para manipular a sentença sql 
            Statement st = conn.conexao.createStatement();

            // resultset para a manipulação dos dados
            ResultSet rs = st.executeQuery("select * from v_cliente");

            // cria um objeto para a formatação da data
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            while (rs.next()) {

                // cria um objeto da classe cliente
                Cliente cliente = new Cliente(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        format.parse(rs.getString(7))
                );

                // adiciona o objeto da classe cliente
                clientes.add(cliente);
            }
        } catch (ClassNotFoundException | SQLException | ParseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // retorna a coleção
        return clientes;
    }
    
    public String grave(Cliente cliente){
        // declara a string que será retornada
        String msg = "";
        
        try {
            
            // efetua uma conexão com o BD
            Conexao conn = new Conexao();
            conn.conecte();
            
            // determina a query que será executada 
            String sql = "call p_salve_cliente(?, ?, ?, ?, ?, ?)";
            
            // cria um objeto que substirui a ? 
            PreparedStatement st = conn.conexao.prepareStatement(sql);
            
            st.setInt(1, cliente.getPessoaId());
            st.setString(2, cliente.getNome());
            st.setString(3, cliente.getTipoPessoa());
            st.setString(4, cliente.getCpfCnpj());
            st.setString(5, cliente.getTelefone());
            st.setString(6, cliente.getEmail());
            
            // executa a procedure
            st.executeUpdate();
            
            // recupera a msg do BD
            ResultSet rs = st.getResultSet();
            rs.next(); 
            
            // atribui a variavel msg a mensagem da procedure
            msg = rs.getString(3);
            st.close();
            
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        // retorna a msg da procedure
        return msg;
    }
}
