package com.example;

<<<<<<< HEAD
=======
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.spec.RSAKeyGenParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;

>>>>>>> 87f50bbdd5e8de5a8c52410b679007d47e8ca0d6
/**
 * Hello world!
 */
public final class App {

    private App() {
    }

    /**
     * Says hello to the world.
     *
     * @param args The arguments of the program.
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
      
        String sk = CriptografiaUtil.gerarChave();
      
        String mensagem = "Mensagem que será cifrada";
<<<<<<< HEAD

        String mbt = CriptografiaUtil.criptografar(sk, mensagem);
        System.out.println(new String(mbt));
        String bts = new String(CriptografiaUtil.descriptografar(sk, mbt));
        System.out.println(new String(bts));


=======

        String mbt = CriptografiaUtil.criptografar(sk, mensagem);
        System.out.println(new String(mbt));
        String bts = new String(CriptografiaUtil.descriptografar(sk, mbt));
        System.out.println(new String(bts));


>>>>>>> 87f50bbdd5e8de5a8c52410b679007d47e8ca0d6
    }

}
