package br.com.meopedevts.cryptography.controller;

import br.com.meopedevts.cryptography.exceptions.TransactionNotFound;
import br.com.meopedevts.cryptography.exceptions.TransactionNullableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class TransactionControllerAdvice {

    @ExceptionHandler(TransactionNullableException.class)
    private ResponseEntity<Object> handleTransactionNullableException() {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("message", "Os campos não podem ser nulos");
        res.put("status", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(TransactionNotFound.class)
    private ResponseEntity<Object> handleTransactionNotFound() {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("message", "Transação não encontrada.");
        res.put("status", HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }
}
