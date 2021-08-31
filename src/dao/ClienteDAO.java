package dao;

import bean.Cliente;
import java.sql.ResultSet;
import config.Conexao;
import java.sql.SQLException;
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
}
