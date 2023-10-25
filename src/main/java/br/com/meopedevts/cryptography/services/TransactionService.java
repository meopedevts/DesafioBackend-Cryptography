package br.com.meopedevts.cryptography.services;

import br.com.meopedevts.cryptography.domain.Transaction;
import br.com.meopedevts.cryptography.domain.TransactionDTO;
import br.com.meopedevts.cryptography.exceptions.TransactionNotFound;
import br.com.meopedevts.cryptography.exceptions.TransactionNullableException;
import br.com.meopedevts.cryptography.repositories.TransactionRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private EncrypterComponent encrypter;

    @Autowired
    private EntityManager entityManager;
    
    public TransactionDTO createTransaction(TransactionDTO transaction) throws Exception {

        if (transaction.userDocument() == null || transaction.creditCardToken() == null || transaction.creditValue() == null) {
            throw new TransactionNullableException();
        }

        String encryptedUserDocument = this.encrypter.encode(transaction.userDocument());
        String encryptedCreditCardToken = this.encrypter.encode(transaction.creditCardToken());

        Transaction newTransaction = new Transaction(encryptedUserDocument, encryptedCreditCardToken, transaction.creditValue());
        
        Long newId = this.repository.save(newTransaction).getId();

        return new TransactionDTO(newId, transaction.userDocument(), transaction.creditCardToken(), transaction.creditValue());
    }

    public List<Transaction> findAllTransactions() {

        List<Transaction> transactionList = this.repository.findAll();
        return transactionList.stream().map(transaction -> {
            transaction.setUserDocument(this.encrypter.decode(transaction.getUserDocument()));
            transaction.setCreditCardToken(this.encrypter.decode(transaction.getCreditCardToken()));
            return transaction;
        }).collect(Collectors.toList());
    }

    public Transaction updateTransaction(Long id, TransactionDTO transaction) throws Exception {
        Transaction transactionToUpdate = this.repository.findById(id).orElseThrow(TransactionNotFound::new);
        transactionToUpdate.setUserDocument(this.encrypter.encode(transaction.userDocument()));
        transactionToUpdate.setCreditCardToken(this.encrypter.encode(transaction.creditCardToken()));
        transactionToUpdate.setCreditValue(transaction.creditValue());
        this.repository.save(transactionToUpdate);
        return transactionToUpdate;
    }

    public Transaction findTransactionById(Long id) throws Exception {
        Transaction transaction = this.repository.findById(id).orElseThrow(TransactionNotFound::new);
        transaction.setUserDocument(this.encrypter.decode(transaction.getUserDocument()));
        transaction.setCreditCardToken(this.encrypter.decode(transaction.getCreditCardToken()));
        return transaction;
    }

    public Map<String, Object> deleteTransaction(Long id) {
        this.repository.deleteById(id);
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("message", "Transação deletada com sucesso.");
        res.put("status", HttpStatus.OK.value());
        return res;
    }
}
