package com.mrtomate.ecommerce.config;

import com.mrtomate.ecommerce.exception.EmailJaRegistradoException;
import com.mrtomate.ecommerce.exception.EstoqueInsuficienteException;
import com.mrtomate.ecommerce.exception.ProdutoNaoEncontradoException;
import com.mrtomate.ecommerce.exception.UsuarioNaoEncontradoException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handleProdutoNaoEncontrado(
        ProdutoNaoEncontradoException e
    ) {
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handleUsuarioNaoEncontrado(
        UsuarioNaoEncontradoException e
    ) {
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(EstoqueInsuficienteException.class)
    public ResponseEntity<Map<String, String>> handleEstoqueInsuficiente(
        EstoqueInsuficienteException e
    ) {
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(EmailJaRegistradoException.class)
    public ResponseEntity<Map<String, String>> handleEmailJaRegistrado(
        EmailJaRegistradoException e
    ) {
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", e.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(
        MethodArgumentNotValidException e
    ) {
        Map<String, Object> erro = new HashMap<>();
        Map<String, String> campos = new HashMap<>();

        e
            .getBindingResult()
            .getFieldErrors()
            .forEach(error ->
                campos.put(error.getField(), error.getDefaultMessage())
            );

        erro.put("erro", "Validação falhou");
        erro.put("detalhes", campos);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
