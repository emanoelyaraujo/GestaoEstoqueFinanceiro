package dao;

import bean.Produto;
import java.sql.PreparedStatement;
import config.Conexao;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ProdutoDAO {

    // método que lista todos os produtos na tabela produto
    public ArrayList liste() {

        // coleção de objetos da classe produto
        ArrayList<Produto> produtos = new ArrayList<>();

        try {

            // efetua a conexão com o banco 
            Conexao conn = new Conexao();
            conn.conecte();

            // objeto para minipular sentenças SQL
            Statement st = conn.conexao.createStatement();

            // declara a sentença de listagem
            String sql = "select * from produto order by descricao";

            // retorna os dados para um resultset
            ResultSet result = st.executeQuery(sql);

            while (result.next()) {

                // cria um objeto da classe produto
                Produto produto = new Produto(
                        result.getInt(1),
                        result.getString(2),
                        result.getDouble(3),
                        result.getDouble(4),
                        result.getInt(5)
                );

                // adiciona i objeto na coleção
                produtos.add(produto);
            }

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // retorna a coleção de objetos
        return produtos;
    }

    // método de insere ou altera um registro
    public String grave(Produto produto) {

        // declara a string de retorno da mensagem do BD
        String msg = "";

        // tratamento de exceção
        try {

            // efetua a conexão com o banco
            Conexao conn = new Conexao();
            conn.conecte();

            // determina a query que grava produto
            String sql = "call p_salve_produto(?, ?, ?, ?, ?)";

            // cria um objeto que faz a substituição das ? pelos atributos
            PreparedStatement st = conn.conexao.prepareStatement(sql);

            st.setInt(1, produto.getProdutoId());
            st.setString(2, produto.getDescricao());
            st.setDouble(3, produto.getPrecoCusto());
            st.setDouble(4, produto.getPrecoVenda());
            st.setInt(5, produto.getQuantidade());

            // executa a procedure com todos os valores
            st.executeUpdate();

            ResultSet result = st.getResultSet();
            result.next();

            msg = result.getString(3);
            st.close();

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // retorna a mensagem da procedure
        return msg;
    }

    // método que remove registros 
    public String remova(int produtoId) {

        // declara a string que receberá a msg da procedure
        String msg = "";

        try {
            // efetua a conexao com o banco
            Conexao conn = new Conexao();
            conn.conecte();

            // determina a query que remove 
            String sql = "call p_delete_produto(?)";

            // cria um objeto que ira substituir a ?
            PreparedStatement st = conn.conexao.prepareStatement(sql);

            // substitui o ? pelo argumento
            st.setInt(1, produtoId);

            // executa a procedure e recepera a msg
            st.executeUpdate();
            ResultSet result = st.getResultSet();
            result.next();
            msg = result.getString(3);

            // fecha a conexao com o banco
            st.close();

        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // retorna a msg da procedure
        return msg;
    }
    
    public ArrayList<Produto> pesquise(String descricao){
        
        // coleção de objetos da classe produto
        ArrayList<Produto> produtos = new ArrayList<>();
        
        try {
            // efetua a conexao com o banco
            Conexao conn =new Conexao();
            conn.conecte();
            
            // determina a query que lista os produtos
            String sql = "select * from produto where descricao like ? order by descricao";
            
            // cria um objeto para substituir o ?
            PreparedStatement st = conn.conexao.prepareStatement(sql);
            
            // substitui o ? pelo argumento descricao
            st.setString(1, "%" + descricao + "%");
            
            // executa a procedure e recupera os produtos
            st.executeQuery();
            ResultSet result = st.getResultSet();   
            
            while (result.next()) {
                
                // cria um objeto da classe produtos
                Produto produto = new Produto (
                        result.getInt(1),
                        result.getString(2),
                        result.getDouble(3),
                        result.getDouble(4),
                        result.getInt(5)
                );
                
                // adiciona o objeto a coleção 
                produtos.add(produto);
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return produtos;
    }
}
