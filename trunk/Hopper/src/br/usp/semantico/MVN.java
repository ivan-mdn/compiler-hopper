package br.usp.semantico;

/**
 *
 * @author Bruno Grisi
 */
public class MVN {
    
    public static String desvioIncondicional() {
        return "0";
    }
    
    public static String desvioAcumuladorZero() {
        return "1";
    }
    
    public static String desvioAcumuladorNegativo() {
        return "2";
    }

    public static String constanteParaAcumulador() {
        return "3";
    }
    
    public static String soma() {
        return "4";
    }
    
    public static String subtracao() {
        return "5";
    }
    
    public static String multiplicacao() {
        return "6";
    }
    
    public static String divisao() {
        return "7";
    }
    
    public static String memoriaParaAcumulador() {
        return "8";
    }
    
    public static String acumuladorParaMemoria() {
        return "9";
    }
    
    public static String desvioParaSubprograma() {
        return "A";
    }
    
    public static String retornoDeSubPrograma() {
        return "B";
    }
    
    public static String parada() {
        return "C";
    }
    
    public static String entrada() { 
        return "D";
    }
    
    public static String saida() {
        return "E";
    }
    
    public static String chamadaSupervisor() {
        return "F";
    }
}
