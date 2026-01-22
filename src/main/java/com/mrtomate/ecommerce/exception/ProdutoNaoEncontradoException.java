package com.mrtomate.ecommerce.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {

    ProdutoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
