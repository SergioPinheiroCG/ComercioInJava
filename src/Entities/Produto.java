package Entities;

public class Produto {
    private String nome;
    private int codigo;
    private double precoVenda;
    private int estoque;
    private Pessoa vendedor;

    public Produto(String nome, int codigo, double precoVenda, Pessoa vendedor) {
        this.nome = nome;
        this.codigo = codigo;
        this.precoVenda = precoVenda;
        this.vendedor = vendedor;
        this.estoque = 0; // Inicializa o estoque como zero
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public double getPrecoVenda() {
        return precoVenda;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public Pessoa getVendedor() {
        return vendedor;
    }
}
