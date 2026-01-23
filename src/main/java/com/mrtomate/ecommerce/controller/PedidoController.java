package com.mrtomate.ecommerce.controller;

import com.mrtomate.ecommerce.dto.CriarPedidoDTO;
import com.mrtomate.ecommerce.dto.PedidoDTO;
import com.mrtomate.ecommerce.service.PedidoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping
    public ResponseEntity<PedidoDTO> criar(
        @Valid @RequestBody CriarPedidoDTO dto
    ) {
        PedidoDTO pedido = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> buscar(@PathVariable Long id) {
        PedidoDTO pedido = service.buscarPorId(id);
        return ResponseEntity.ok(pedido);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PedidoDTO>> buscarPorUsuario(
        @PathVariable Long usuarioId
    ) {
        List<PedidoDTO> pedidos = service.buscarPorUsuario(usuarioId);
        return ResponseEntity.ok(pedidos);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PedidoDTO> atualizarStatus(
        @PathVariable Long id,
        @RequestParam String status
    ) {
        PedidoDTO pedido = service.atualizarStatus(id, status);

        return ResponseEntity.ok(pedido);
    }
}
