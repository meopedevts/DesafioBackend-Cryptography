package br.com.meopedevts.cryptography.domain;

public record TransactionDTO(
        Long id,
        String userDocument,
        String creditCardToken,
        Long creditValue
) {
}
