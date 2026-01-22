package com.mrtomate.ecommerce.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class CriarPedidoDTO {

    @NotNull(message = "ID do usuário é obrigatório")
    private Long usuarioId;

    @NotEmpty(message = "Pedido deve ter pelo menos um item")
    private List<ItemCarrinhoDTO> itens;

    // Getters e Setters
    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<ItemCarrinhoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemCarrinhoDTO> itens) {
        this.itens = itens;
    }
}
