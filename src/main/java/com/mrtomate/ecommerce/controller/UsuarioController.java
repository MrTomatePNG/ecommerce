package com.mrtomate.ecommerce.controller;

import com.mrtomate.ecommerce.dto.CriarUsuarioDTO;
import com.mrtomate.ecommerce.dto.UsuarioDTO;
import com.mrtomate.ecommerce.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public ResponseEntity<UsuarioDTO> criar(
        @Valid @RequestBody CriarUsuarioDTO dto
    ) {
        UsuarioDTO usuario = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscar(@PathVariable Long id) {
        UsuarioDTO usuario = service.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/email")
    public ResponseEntity<UsuarioDTO> buscarPorEmail(
        @RequestParam String email
    ) {
        UsuarioDTO usuario = service.buscarPorEmail(email);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizar(
        @PathVariable Long id,
        @Valid @RequestBody CriarUsuarioDTO dto
    ) {
        UsuarioDTO usuario = service.atualizar(id, dto);
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
