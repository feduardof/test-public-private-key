package com.example;

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
        System.out.println("Hello World!");

        App app = new App();
        DadosChave chaves = app.gerarChaves();
        DadosChave novaChave = new DadosChave();
        novaChave.setPrivate(chaves.getPrivate());
        novaChave.setPublic(chaves.getPublic());

        ByteArrayInputStream msg = new ByteArrayInputStream(
                "Aqui vai uma mensagem longa para identifica se vai ocorrer conforme o eserado \n\n\n\b12312312"
                        .getBytes());
        ByteArrayOutputStream msgC = new ByteArrayOutputStream();
        app.criptografar(novaChave.privateKey, msg, msgC);

        System.out.println(msgC.toString());

        ByteArrayOutputStream msgD = new ByteArrayOutputStream();
        ByteArrayInputStream msgBC = new ByteArrayInputStream(msgC.toByteArray());
        app.descriptografar(novaChave.publicKey, msgBC, msgD);

        System.out.println(msgD.toString());

    }

    public DadosChave gerarChaves() throws Exception {
        // Cria um gerador de chaves para o RSA
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        // Inicializa o tamanho da chave e seu expoente público
        kpg.initialize(new RSAKeyGenParameterSpec(1024, RSAKeyGenParameterSpec.F4));
        // Gera um par de chaves
        KeyPair par = kpg.generateKeyPair();

        DadosChave dados = new DadosChave();
        dados.privateKey = par.getPrivate();
        dados.publicKey = par.getPublic();
        return dados;
    }

    private byte[] buf = new byte[1024];

    public void criptografar(Key chave, InputStream in, OutputStream out) throws Exception {
        // Obtém uma instância da criptografia RSA
        Cipher cifra = Cipher.getInstance("RSA");
        // Inicializa o RSA com o modo de criptografia e a chave que será usada
        cifra.init(Cipher.ENCRYPT_MODE, chave);
        // Cria um stream que recebe uma entrada descriptografada para criptografar
        CipherOutputStream cipherOut = new CipherOutputStream(out, cifra);

        // Criptografa o conteúdo da entrada (in) e guarda o resultado na saída (out).
        int numLido = 0;
        while ((numLido = in.read(buf)) >= 0) {
            cipherOut.write(buf, 0, numLido);
        }
        cipherOut.close();
    }

    public void descriptografar(Key chave, InputStream in, OutputStream out) throws Exception {
        // Obtém uma instância da criptografia RSA
        Cipher cifra = Cipher.getInstance("RSA");
        // Inicializa o RSA com o modo de descriptografia e a chave que será usada
        cifra.init(Cipher.DECRYPT_MODE, chave);
        // Cria um stream que recebe uma entrada criptografada para descriptografar
        CipherInputStream cipherIn = new CipherInputStream(in, cifra);

        // Descriptografa o conteúdo da entrada (in) e guarda o resultado na saída
        // (out).
        int numLido = 0;
        while ((numLido = cipherIn.read(buf)) >= 0) {
            out.write(buf, 0, numLido);
        }
        cipherIn.close();
        out.close();
    }

}
