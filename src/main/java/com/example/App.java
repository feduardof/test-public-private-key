package com.example;

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

        String mbt = CriptografiaUtil.criptografar(sk, mensagem);
        System.out.println(new String(mbt));
        String bts = new String(CriptografiaUtil.descriptografar(sk, mbt));
        System.out.println(new String(bts));


    }

}
