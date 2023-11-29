package com.example.projetobeta.Cliente;

public class Cliente {

    private long idCliente;
    private String nome;
    private String email;

    public Cliente(long id, String nome, String email) {
        this.idCliente = id;
        this.nome = nome;
        this.email = email;
    }

    public Cliente( String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public long getId() {
        return idCliente;
    }

    public void setId(long id) {
        this.idCliente = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return idCliente + " - " + nome + " - " + email;
    }
}
