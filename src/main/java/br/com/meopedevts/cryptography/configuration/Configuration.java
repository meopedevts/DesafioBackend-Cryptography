package br.com.meopedevts.cryptography.configuration;

import br.com.meopedevts.cryptography.services.TransactionService;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public TransactionService userCreditService() {
        return new TransactionService();
    }
}
