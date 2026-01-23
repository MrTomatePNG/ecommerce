package com.mrtomate.ecommerce.controller;

import com.mrtomate.ecommerce.dto.ProdutoDTO;
import com.mrtomate.ecommerce.service.ProdutoService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
  
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoDTO> criar(
        @Valid @RequestBody ProdutoDTO dto
    ) {
        ProdutoDTO produto = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscar(@PathVariable Long id) {
        ProdutoDTO produto = service.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listar() {
        List<ProdutoDTO> produtos = service.listarTodos();

        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProdutoDTO>> buscarPorNome(
        @RequestParam String nome
    ) {
        List<ProdutoDTO> produtos = service.buscarPorNome(nome);
        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizar(
        @PathVariable Long id,
        @Valid @RequestBody ProdutoDTO dto
    ) {
        ProdutoDTO produto = service.atualizar(id, dto);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
