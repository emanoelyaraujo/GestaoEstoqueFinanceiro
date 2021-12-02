package dao;

import bean.Movimentacao;
import config.Conexao;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class MovimentacaoDAO {

    // lista todas as movimentações do banco de dados (v_movimentacao)
    public ArrayList liste() {

        // declara coleção de objetos de movimentacao vazia
        ArrayList<Movimentacao> movimentacoes = new ArrayList<>();

        try {

            // efetua a conexão com o banco de dados
            Conexao c = new Conexao();
            c.conecte();

            // cria um objeto para manipulação de sentença SQL
            Statement st = c.conexao.createStatement();

            // Resultset para a manipulação dos dados semiestruturados
            ResultSet rs = st.executeQuery("select * from v_movimentacao");

            // cria um objeto para formatação de data
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            // a cada linha retornada pela view v_movimentacao
            while (rs.next()) {

                // cria um objeto da classe movimentação
                Movimentacao movimentacao = new Movimentacao(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5),
                        rs.getString(6),
                        format.parse(rs.getString(7)),
                        rs.getString(8),
                        rs.getInt(9),
                        rs.getDouble(10));

                // adiciona o objeto da classe movimentação à coleção de objetos
                movimentacoes.add(movimentacao);
            }

        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        // retorna a coleção de objetos
        return movimentacoes;
    }

    public String grave(Movimentacao movimentacao) {

        // declara uma string que retornará a mensagem da procedure
        String msg = "";

        try {

            // efetua a conexão com o banco de dados
            Conexao c = new Conexao();
            c.conecte();

            // determina a query que será executada para gravação de cliente no BD
            String sql = "call p_salve_movimentacao(?, ?, ?, ?, ?, ?, ?)";

            // cria um objeto para fazer a substituição das ? pelos atributos da classe
            // movimentacao
            PreparedStatement st = c.conexao.prepareStatement(sql);

            st.setInt(1, movimentacao.getMovimentacaoId());
            st.setInt(2, movimentacao.getProdutoId());
            st.setInt(3, movimentacao.getPessoaId());
            st.setString(4, movimentacao.getTipo());
            st.setString(5, movimentacao.getStatus());
            st.setInt(6, movimentacao.getQuantidadeMovimentacao());
            st.setDouble(7, movimentacao.getValor());

            // executa a procedure montada
            st.executeUpdate();

            // recupera a mensagem do banco de dados em um ResultSet
            ResultSet rs = st.getResultSet();
            rs.next();

            // atribui à variável msg a mensagem retornada pela procedure p_salve_cliente
            msg = rs.getString(3);
            st.close();

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // retorna a mensagem da procedure
        return msg;
    }

    public String remove(int movimentacaoId) {

        // string que irá retornar a msg da procedure
        String msg = "";

        try {

            // efetua uma conexao com o banco
            Conexao conn = new Conexao();
            conn.conecte();

            // determina a query a ser executada no BD
            String sql = "call p_delete_movimentacao(?)";

            // substitui o coringa ?
            PreparedStatement st = conn.conexao.prepareStatement(sql);

            st.setInt(1, movimentacaoId);

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

    public ArrayList<Movimentacao> pesquise(String produto) {
        // coleção de obj da classe movimentacao
        ArrayList<Movimentacao> movimentacoes = new ArrayList<>();

        try {
            // efetua a conexao com o banco
            Conexao c = new Conexao();
            c.conecte();

            // determina a query que lista
            String sql = "select * from v_movimentacao where produto like ? order by movimentacao_id desc";

            // cria um obj para substituir o coringa
            PreparedStatement st = c.conexao.prepareStatement(sql);
            st.setString(1, "%" + produto + "%");

            // executa a procedure e recupera a listagem
            st.executeQuery();
            ResultSet rs = st.getResultSet();

            // obj de formatação
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            while (rs.next()) {
                // cria uma obj da classe movimentacao
                Movimentacao movimentacao = new Movimentacao(
                    rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5), 
                    rs.getString(6), format.parse(rs.getString(7)), rs.getString(8), rs.getInt(9), rs.getDouble(10));

                // adiciona obj á coleção
                movimentacoes.add(movimentacao);
            }

        } catch (ClassNotFoundException | SQLException | ParseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        return movimentacoes;
    }
}
