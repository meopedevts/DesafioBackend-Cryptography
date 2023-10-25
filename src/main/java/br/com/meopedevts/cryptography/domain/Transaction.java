package br.com.meopedevts.cryptography.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userDocument;
    private String creditCardToken;
    private Long creditValue;

    public Transaction(String userDocument, String creditCardToken, Long creditValue) {
        this.userDocument = userDocument;
        this.creditCardToken = creditCardToken;
        this.creditValue = creditValue;
    }
}
