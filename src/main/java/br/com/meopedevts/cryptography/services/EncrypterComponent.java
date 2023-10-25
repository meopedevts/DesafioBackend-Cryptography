package br.com.meopedevts.cryptography.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class EncrypterComponent implements PasswordEncoder {

    @Value("${cryptography.password}")
    private String SECRET_KEY;

    @Override
    public String encode(CharSequence rawData) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedDataBytes = cipher.doFinal(rawData.toString().getBytes("UTF-8"));

            return Base64.getEncoder().encodeToString(encryptedDataBytes);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro durante a criptografia dos dados.");
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) { //Sem uso para aplicação
        return false;
    }

    public String decode(String encryptedData) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptedDataBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));
            return new String (decryptedDataBytes, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("Erro durante a descriptografia dos dados.");
        }
    }
}
