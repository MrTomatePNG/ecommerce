package com.mrtomate.ecommerce.dto;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private String endereco;

    public UsuarioDTO() {}

    public UsuarioDTO(
        final Long id,
        final String nome,
        final String email,
        final String endereco
    ) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(final String endereco) {
        this.endereco = endereco;
    }
}
