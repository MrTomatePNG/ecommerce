package com.mrtomate.ecommerce.service;

import com.mrtomate.ecommerce.dto.CriarUsuarioDTO;
import com.mrtomate.ecommerce.dto.UsuarioDTO;
import com.mrtomate.ecommerce.exception.EmailJaRegistradoException;
import com.mrtomate.ecommerce.exception.UsuarioNaoEncontradoException;
import com.mrtomate.ecommerce.models.Usuario;
import com.mrtomate.ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public UsuarioDTO criar(CriarUsuarioDTO dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new EmailJaRegistradoException("Email já registrado");
        }

        Usuario usuario = new Usuario(
            dto.getNome(),
            dto.getEmail(),
            dto.getSenha()
        );
        usuario.setEndereco(dto.getEndereco());

        Usuario salvo = repository.save(usuario);
        return converterParaDTO(salvo);
    }

    public UsuarioDTO buscarPorId(Long id) {
        Usuario usuario = repository
            .findById(id)
            .orElseThrow(() ->
                new UsuarioNaoEncontradoException("Usuário não encontrado")
            );
        return converterParaDTO(usuario);
    }

    public UsuarioDTO buscarPorEmail(String email) {
        Usuario usuario = repository
            .findByEmail(email)
            .orElseThrow(() ->
                new UsuarioNaoEncontradoException("Usuário não encontrado")
            );
        return converterParaDTO(usuario);
    }

    public UsuarioDTO atualizar(Long id, CriarUsuarioDTO dto) {
        Usuario usuario = repository
            .findById(id)
            .orElseThrow(() ->
                new UsuarioNaoEncontradoException("Usuário não encontrado")
            );

        usuario.setNome(dto.getNome());
        usuario.setEndereco(dto.getEndereco());
        usuario.setSenha(dto.getSenha());

        Usuario atualizado = repository.save(usuario);
        return converterParaDTO(atualizado);
    }

    public void deletar(Long id) {
        Usuario usuario = repository
            .findById(id)
            .orElseThrow(() ->
                new UsuarioNaoEncontradoException("Usuário não encontrado")
            );
        repository.delete(usuario);
    }

    private UsuarioDTO converterParaDTO(Usuario usuario) {
        return new UsuarioDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getEndereco()
        );
    }
}
