package utils;

import java.util.Random;

public final class TextoUtil {
    public static String criaTextoRandomico(Integer tamanhoTexto) {
        String caracteres = "   abcdefghijklm   nopqrstuvxwyz   ";
        Random random = new Random();
        String resultado = "";

        while (resultado.length() < tamanhoTexto) {
            char caractereRandom = caracteres.charAt(random.nextInt(caracteres.length()));
            resultado += caractereRandom;
        }
        return resultado;
    }
}
