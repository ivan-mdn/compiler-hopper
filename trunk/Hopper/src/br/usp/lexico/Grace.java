package br.usp.lexico;

import java.util.ArrayList;

/**
 *
 * @author nathalia
 */
public class Grace {
    private static ArrayList palavrasReservadas = new ArrayList();
    
    public static ArrayList getPalavrasReservadas() {
        return palavrasReservadas;
    }

    public static void setPalavrasReservadas() {
        palavrasReservadas.add("<=");
        palavrasReservadas.add(">=");
        palavrasReservadas.add("<>");
        palavrasReservadas.add("=");
        palavrasReservadas.add("if");
        palavrasReservadas.add("else");
    }
}
