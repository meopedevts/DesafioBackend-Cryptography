package br.com.meopedevts.cryptography.controller;

import br.com.meopedevts.cryptography.domain.Transaction;
import br.com.meopedevts.cryptography.domain.TransactionDTO;
import br.com.meopedevts.cryptography.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping()
    private ResponseEntity<TransactionDTO> createTransaction(@RequestBody TransactionDTO transaction) throws Exception {
        TransactionDTO newTransaction = this.service.createTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTransaction);
    }

    @GetMapping()
    private ResponseEntity<List<Transaction>> findAllTransactions() {
        List<Transaction> transactions = this.service.findAllTransactions();
        return ResponseEntity.status(HttpStatus.OK).body(transactions);
    }

    @GetMapping("/search/{id}")
    private ResponseEntity<Transaction> searchTransactionById(@PathVariable(value = "id") String id) throws Exception {
        Transaction transaction = this.service.findTransactionById(Long.valueOf(id));
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Transaction> updateTransaction(@PathVariable(value = "id") String id, @RequestBody TransactionDTO transaction) throws Exception {
        Transaction updatedTransaction = this.service.updateTransaction(Long.valueOf(id), transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedTransaction);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Object> deleteTransaction(@PathVariable(value = "id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.service.deleteTransaction(Long.valueOf(id)));
    }
}
