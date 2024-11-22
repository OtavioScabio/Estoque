package com.example.estoque.model;

public class FornecedorNacional extends Fornecedor {
    private String cnpj;
    private String estado;

    public FornecedorNacional(int id, String nome, String cnpj, String estado) {
        super(id, nome);
        this.cnpj = cnpj;
        this.estado = estado;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
