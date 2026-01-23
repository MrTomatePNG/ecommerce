package com.mrtomate.ecommerce.service;

import com.mrtomate.ecommerce.dto.CriarPedidoDTO;
import com.mrtomate.ecommerce.dto.ItemCarrinhoDTO;
import com.mrtomate.ecommerce.dto.ItemPedidoDTO;
import com.mrtomate.ecommerce.dto.PedidoDTO;
import com.mrtomate.ecommerce.exception.EstoqueInsuficienteException;
import com.mrtomate.ecommerce.exception.PedidoNaoEncontradoException;
import com.mrtomate.ecommerce.exception.ProdutoNaoEncontradoException;
import com.mrtomate.ecommerce.exception.UsuarioNaoEncontradoException;
import com.mrtomate.ecommerce.models.ItemPedido;
import com.mrtomate.ecommerce.models.Pedido;
import com.mrtomate.ecommerce.models.Produto;
import com.mrtomate.ecommerce.models.Usuario;
import com.mrtomate.ecommerce.repository.ItemPedidoRepository;
import com.mrtomate.ecommerce.repository.PedidoRepository;
import com.mrtomate.ecommerce.repository.ProdutoRepository;
import com.mrtomate.ecommerce.repository.UsuarioRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Transactional
    public PedidoDTO criar(CriarPedidoDTO dto) {
        Usuario usuario = usuarioRepository
            .findById(dto.getUsuarioId())
            .orElseThrow(() ->
                new UsuarioNaoEncontradoException("usuario nao encontrado")
            );

        BigDecimal total = BigDecimal.ZERO;

        List<ItemPedido> itens = new java.util.ArrayList<>();
        for (ItemCarrinhoDTO itemDTO : dto.getItens()) {
            Produto produto = produtoRepository
                .findById(itemDTO.getProdutoId())
                .orElseThrow(() ->
                    new ProdutoNaoEncontradoException("Produto nao encontrado")
                );

            if (produto.getEstoque() < itemDTO.getQuantidade()) {
                throw new EstoqueInsuficienteException(
                    "Produto" + produto.getNome() + " com estoque insuficiente"
                );
            }

            BigDecimal subtotal = produto
                .getPreco()
                .multiply(new BigDecimal(itemDTO.getQuantidade()));
            total = total.add(subtotal);

            ItemPedido item = new ItemPedido(
                null,
                produto,
                itemDTO.getQuantidade(),
                produto.getPreco()
            );
            itens.add(item);
            produto.setEstoque(produto.getEstoque() - item.getQuantidade());
            produtoRepository.save(produto);
        }
        Pedido pedido = new Pedido(usuario, total);
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("pendente");
        Pedido pedidoSalvo = pedidoRepository.save(pedido);

        for (ItemPedido item : itens) {
            item.setPedido(pedidoSalvo);
            itemPedidoRepository.save(item);
        }

        return converterParaDTO(pedidoSalvo);
    }

    public PedidoDTO buscarPorId(Long id) {
        Pedido pedido = pedidoRepository
            .findById(id)
            .orElseThrow(() ->
                new PedidoNaoEncontradoException("PEdido nao encontrado")
            );
        return converterParaDTO(pedido);
    }

    public List<PedidoDTO> buscarPorUsuario(Long usuarioId) {
        usuarioRepository
            .findById(usuarioId)
            .orElseThrow(() ->
                new UsuarioNaoEncontradoException("usuario nao encontrado")
            );

        return pedidoRepository
            .findByUsuarioId(usuarioId)
            .stream()
            .map(this::converterParaDTO)
            .collect(Collectors.toList());
    }

    @Transactional
    public PedidoDTO atualizarStatus(Long id, String novoStatus) {
        Pedido pedido = pedidoRepository
            .findById(id)
            .orElseThrow(() ->
                new PedidoNaoEncontradoException("Pedido nao encontrado")
            );

        pedido.setStatus(novoStatus);
        Pedido atualizado = pedidoRepository.save(pedido);
        return converterParaDTO(atualizado);
    }

    private PedidoDTO converterParaDTO(Pedido pedido) {
        PedidoDTO dto = new PedidoDTO(
            pedido.getId(),
            pedido.getUsuario().getId(),
            pedido.getData(),
            pedido.getStatus(),
            pedido.getTotal()
        );

        List<ItemPedidoDTO> itensDTO = pedido
            .getItens()
            .stream()
            .map(item ->
                new ItemPedidoDTO(
                    item.getProduto().getId(),
                    item.getProduto().getNome(),
                    item.getQuantidade(),
                    item.getPrecoUnitario()
                )
            )
            .collect(Collectors.toList());

        dto.setItens(itensDTO);
        return dto;
    }
}
