package com.example;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class DadosChave {

    PublicKey publicKey;
    PrivateKey privateKey;

    String getPublic() {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    String getPrivate() {
        return Base64.getEncoder().encodeToString(privateKey.getEncoded());
    }

    void setPrivate(String valor) throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeyFactory kf = KeyFactory.getInstance("RSA"); // or "EC" or whatever

        privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(valor)));
    }

    void setPublic(String valor) throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeyFactory kf = KeyFactory.getInstance("RSA"); // or "EC" or whatever
        publicKey = kf.generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(valor)));
    }

}
