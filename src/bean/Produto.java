package bean;

public class Produto {
    // atributos de classe
    private int produtoId;
    private String descricao;
    private double precoCusto;
    private double precoVenda;
    private int quantidade;
    
    // construtor
    public Produto() {}

    public Produto(int produtoId, String descricao, double precoCusto, double precoVenda, int quantidade) {
        this.produtoId = produtoId;
        this.descricao = descricao;
        this.precoCusto = precoCusto;
        this.precoVenda = precoVenda;
        this.quantidade = quantidade;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(double precoCusto) {
        this.precoCusto = precoCusto;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuanridade(int quantidade) {
        this.quantidade = quantidade;
    }
}

