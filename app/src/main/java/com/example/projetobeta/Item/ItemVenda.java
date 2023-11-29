package com.example.projetobeta.Item;
import com.example.projetobeta.Produto.Produto;

public class ItemVenda {

    private Produto produto;
    private int quantidade;

    public ItemVenda(Produto produto, int quantidadeItem) {
        this.produto = produto;
        this.quantidade = quantidadeItem;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidadeItem() {
        return quantidade;
    }

    public void setQuantidadeItem(int quantidadeItem) {
        this.quantidade = quantidadeItem;
    }

    public double getSubtotal() {
        return produto.getValor() * quantidade;
    }

    // Incluído o método para obter o ID do produto
    public long getIdProduto() {
        return produto.getIdProduto();
    }

    @Override
    public String toString() {
        // Modificado para incluir o ID do produto
        return "ID: " + getIdProduto() + " - " + produto.getDescricao() + " - Quantidade: " + quantidade + " - Subtotal: R$ " + getSubtotal();
    }
}

