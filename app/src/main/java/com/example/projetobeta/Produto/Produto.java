package com.example.projetobeta.Produto;

public class Produto {
    private long idProduto;
    private String descricao;
    private Integer quantidade;
    private Double valor;

    public Produto(long idProduto, String descricao, Integer quantidade, Double valor) {
        this.idProduto = idProduto;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public Produto(String descricao, Integer quantidade, Double valor) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long id) {
        this.idProduto = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return idProduto + " - " + descricao + " - " + quantidade + " - " + valor;
    }
}
