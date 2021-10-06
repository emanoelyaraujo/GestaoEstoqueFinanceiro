package dao;

import java.sql.PreparedStatement;
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

            // objeto para manipular a sent ença sql
            Statement st = conn.conexao.createStatement();

            // manipula os dados
            ResultSet rs = st.executeQuery("select * from v_fornecedor");

            // cria um objeto para formatar data
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            while (rs.next()) {

                // cria um objeto da classe fornecedor
                Fornecedor fornecedor = new Fornecedor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), format.parse(rs.getString(7)));

                // adiciona o obj da classe fornecedor
                fornecedores.add(fornecedor);
            }

        } catch (ClassNotFoundException | SQLException | ParseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return fornecedores;
    }

    public String grave(Fornecedor fornecedor) {

        // declara a string que será retornada
        String msg = "";

        try {

            // efetua uma conexão com o BD
            Conexao conn = new Conexao();
            conn.conecte();

            // determina a query que será executada
            String sql = "call p_salve_fornecedor(?, ?, ?, ?, ?, ?)";

            // cria um objeto que substirui a ?
            PreparedStatement st = conn.conexao.prepareStatement(sql);

            st.setInt(1, fornecedor.getPessoaId());
            st.setString(2, fornecedor.getNome());
            st.setString(3, fornecedor.getTipoPessoa());
            st.setString(4, fornecedor.getCpfCnpj());
            st.setString(5, fornecedor.getTelefone());
            st.setString(6, fornecedor.getEmail());

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

    public String remove(int pessoaId) {

        // string que irá retornar a msg da procedure
        String msg = "";

        try {

            // efetua uma conexao com o banco
            Conexao conn = new Conexao();
            conn.conecte();

            // determina a query a ser executada no BD
            String sql = "call p_delete_fornecedor(?)";

            // substitui o coringa ?
            PreparedStatement st = conn.conexao.prepareStatement(sql);

            st.setInt(1, pessoaId);

            // executa a procedure
            st.executeUpdate();

            // recupera a msg da procedure
            ResultSet result = st.getResultSet();
            result.next();

            msg = result.getString(3);

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return msg;
    }

    public ArrayList<Fornecedor> pesquise(String nome) {

        // declara a coleção de objetos
        ArrayList<Fornecedor> fornecedores = new ArrayList<>();

        try {

            // efetua uma conexao com o bd
            Conexao conn = new Conexao();
            conn.conecte();

            // determina a query de busca
            String sql = "select * from v_fornecedor where nome like ? order by nome";

            // substitui o coringa ?
            PreparedStatement st = conn.conexao.prepareStatement(sql);

            // substitui a ? pelo argumento nome
            st.setString(1, "%" + nome + "%");

            // executa a query
            st.executeQuery();
            ResultSet rs = st.getResultSet();

            // cria um obj para formatar data
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            while (rs.next()) {

                // cria um objeto da classe fornecedor
                Fornecedor fornecedor = new Fornecedor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), format.parse(rs.getString(7)));

                // adiciona o objeto da classe cliente
                fornecedores.add(fornecedor);
            }

        } catch (ClassNotFoundException | SQLException | ParseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return fornecedores;
    }
}
