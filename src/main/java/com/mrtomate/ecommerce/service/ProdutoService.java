package com.mrtomate.ecommerce.service;

import com.mrtomate.ecommerce.dto.ProdutoDTO;
import com.mrtomate.ecommerce.exception.ProdutoNaoEncontradoException;
import com.mrtomate.ecommerce.models.Produto;
import com.mrtomate.ecommerce.repository.ProdutoRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public ProdutoDTO criar(ProdutoDTO dto) {
        Produto produto = new Produto(
            dto.getNome(),
            dto.getDescricao(),
            dto.getPreco(),
            dto.getEstoque()
        );
        Produto salvo = repository.save(produto);
        return converterParaDTO(salvo);
    }

    public ProdutoDTO buscarPorId(long id) {
        Produto produto = repository
            .findById(id)
            .orElseThrow(() ->
                new ProdutoNaoEncontradoException("Produto nao encontrado")
            );
        return converterParaDTO(produto);
    }

    public List<ProdutoDTO> listarTodos() {
        return repository
            .findAll()
            .stream()
            .map(this::converterParaDTO)
            .collect(Collectors.toList());
    }

    public List<ProdutoDTO> buscarPorNome(String nome) {
        return repository
            .findByNomeContainingIgnoreCase(nome)
            .stream()
            .map(this::converterParaDTO)
            .collect(Collectors.toList());
    }

    public ProdutoDTO atualizar(Long id, ProdutoDTO dto) {
        Produto produto = repository
            .findById(id)
            .orElseThrow(() ->
                new ProdutoNaoEncontradoException("Produto nao encontrado")
            );

        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setEstoque(dto.getEstoque());

        Produto atualizado = repository.save(produto);
        return converterParaDTO(atualizado);
    }

    public void deletar(Long id) {
        Produto produto = repository
            .findById(id)
            .orElseThrow(() ->
                new ProdutoNaoEncontradoException("Produto nao encontrado")
            );

        repository.delete(produto);
    }

    public ProdutoDTO converterParaDTO(Produto produto) {
        return new ProdutoDTO(
            produto.getId(),
            produto.getNome(),
            produto.getDescricao(),
            produto.getPreco(),
            produto.getEstoque()
        );
    }
}
