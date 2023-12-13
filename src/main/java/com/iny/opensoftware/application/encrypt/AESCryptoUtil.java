package com.iny.opensoftware.application.encrypt;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;

public class AESCryptoUtil{

        public static SecretKey getKey() throws Exception {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            return secretKey;
        }


        public static IvParameterSpec getIv() {
            byte[] iv = new byte[16];
            new SecureRandom().nextBytes(iv);
            return new IvParameterSpec(iv);
        }
}
