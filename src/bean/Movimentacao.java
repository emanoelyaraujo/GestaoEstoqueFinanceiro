package bean;

import java.util.Date;

public class Movimentacao {

    private int movimentacaoId;
    private int produtoId;
    private String produto;
    private int pessoaId;
    private String nome;
    private String tipo;
    private Date dataMovimentacao;
    private String status;
    private int quantidadeMovimentacao;
    private double valor;

    public Movimentacao() {
    }

    public Movimentacao(int movimentacaoId, int produtoId, String produto, int pessoaId,
            String nome, String tipo, Date dataMovimentacao, String status, int quantidadeMovimentacao, double valor) {
        this.movimentacaoId = movimentacaoId;
        this.produtoId = produtoId;
        this.produto = produto;
        this.pessoaId = pessoaId;
        this.nome = nome;
        this.tipo = tipo;
        this.dataMovimentacao = dataMovimentacao;
        this.status = status;
        this.quantidadeMovimentacao = quantidadeMovimentacao;
        this.valor = valor;
    }

    public int getMovimentacaoId() {
        return movimentacaoId;
    }

    public void setMovimentacaoId(int movimentacaoId) {
        this.movimentacaoId = movimentacaoId;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(int pessoaId) {
        this.pessoaId = pessoaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getDataMovimentacao() {
        return dataMovimentacao;
    }

    public void setDataMovimentacao(Date dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantidadeMovimentacao() {
        return quantidadeMovimentacao;
    }

    public void setQuantidadeMovimentacao(int quantidadeMovimentacao) {
        this.quantidadeMovimentacao = quantidadeMovimentacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
