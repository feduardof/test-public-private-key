package com.example;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.RSAKeyGenParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

public class CriptografaAssincronaUtil {

  private byte[] buf = new byte[1024];

  public DadosChave gerarChaves() throws Exception {
    KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
    kpg.initialize(new RSAKeyGenParameterSpec(1024, RSAKeyGenParameterSpec.F4));
    KeyPair par = kpg.generateKeyPair();

    DadosChave dados = new DadosChave();
    dados.privateKey = par.getPrivate();
    dados.publicKey = par.getPublic();
    return dados;
  }


  public void criptografar(Key chave, InputStream in, OutputStream out) throws Exception {
    Cipher cifra = Cipher.getInstance("RSA");
    cifra.init(Cipher.ENCRYPT_MODE, chave);
    CipherOutputStream cipherOut = new CipherOutputStream(out, cifra);

    int numLido = 0;
    while ((numLido = in.read(buf)) >= 0) {
        cipherOut.write(buf, 0, numLido);
    }
    cipherOut.close();
  }

  public void descriptografar(Key chave, InputStream in, OutputStream out) throws Exception {
    Cipher cifra = Cipher.getInstance("RSA");
    cifra.init(Cipher.DECRYPT_MODE, chave);
    CipherInputStream cipherIn = new CipherInputStream(in, cifra);

    int numLido = 0;
    while ((numLido = cipherIn.read(buf)) >= 0) {
        out.write(buf, 0, numLido);
    }
    cipherIn.close();
    out.close();
  }
  
}
