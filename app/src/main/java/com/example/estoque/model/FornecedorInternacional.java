package com.example.estoque.model;

public class FornecedorInternacional extends Fornecedor {
    private String pais;
    private String numeroRegistro;

    public FornecedorInternacional(int id, String nome, String pais, String numeroRegistro) {
        super(id, nome);
        this.pais = pais;
        this.numeroRegistro = numeroRegistro;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }
}
