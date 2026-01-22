package com.mrtomate.ecommerce.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoDTO {

    private Long id;
    private Long usuarioId;
    private LocalDateTime data;
    private String status;
    private BigDecimal total;
    private List<ItemPedidoDTO> itens;

    public PedidoDTO() {}

    public PedidoDTO(
        final Long id,
        final Long usuarioId,
        final LocalDateTime data,
        final String status,
        final BigDecimal total
    ) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.data = data;
        this.status = status;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(final Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(final LocalDateTime data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(final BigDecimal total) {
        this.total = total;
    }

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(final List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }
}
