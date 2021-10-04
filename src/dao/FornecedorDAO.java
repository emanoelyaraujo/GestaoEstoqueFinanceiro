package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import bean.Fornecedor;
import config.Conexao;

public class FornecedorDAO {

    // lista todos os fornecedores
    public ArrayList liste() {

        // declara a coleção de objetos vazia
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();

        try {

            // efetua a conexão com o banco
            Conexao conn = new Conexao();
            conn.conecte();

            // objeto para manipular a sentença sql
            Statement st = conn.conexao.createStatement();

            // manipula os dados
            ResultSet rs = st.executeQuery("select * from v_fornecedor");

            // cria um objeto para formatar data
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            while (rs.next()) {
                
                // cria um objeto da classe fornecedor
                Fornecedor fornecedor = new Fornecedor(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    format.parse(rs.getString(7))
                );

                // adiciona o obj da classe fornecedor
                fornecedores.add(fornecedor);
            }

        } catch (ClassNotFoundException | SQLException | ParseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return fornecedores;
    }
}
