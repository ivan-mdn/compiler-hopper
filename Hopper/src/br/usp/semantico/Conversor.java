package br.usp.semantico;

import br.usp.estrutura.Pilha;

public abstract class Conversor {
    
    public static int hex2Int(String hex) {
        return Integer.valueOf(hex, 16).intValue();
    }
    
    public static String int2Hex(int inteiro) {
        String retorno = Integer.toHexString(inteiro);
        while(retorno.length() < 4)
            retorno = "0" + retorno;
        return retorno;        
    }
    
    public static String[] notacaoPolonesa(String expressao[]){
        
        Pilha pilha = new Pilha();
        String resultado[];
        resultado = new String[50];
        int i,j;                        
        i = 0;
        j = 0;
        
        for(int contador=0; contador<expressao.length; contador++) {
            if(expressao[i].equals(")") && !pilha.pilhaVazia()){
                resultado[j] = (String) pilha.desempilha();
                j++;
            }else if(expressao[i].equals("+") || expressao[i].equals("-") ||
                    expressao[i].equals("*") || expressao[i].equals("/")){
                pilha.empilha(expressao[i]);
            } else if(!expressao[i].equals("(")){
                resultado[j] = expressao[i];
                j++;
            }
            
            if(i>0){//se encontrar * ou / eles possuem prioridade, entao desempilha!!
                if((expressao[i-1].equals("*") || expressao[i-1].equals("/")) &&
                        !expressao[i].equals("(")){
                    resultado[j] = (String) pilha.desempilha();
                    j++;
                }
            }
            i++;
        }
        
        //se nao acabar com fecha parenteses!!
        while(!pilha.pilhaVazia()){
            resultado[j] = (String) pilha.desempilha();
            j++;
        }                
        int tamanhoRetorno = 0;
        for(j=0;resultado[j]!=null;j++)
            tamanhoRetorno++;
                
        return zipVetor(resultado);
    }
    
    public static String[] notacaoPolonesaBooleana(String expressao[]){
        
        Pilha pilha = new Pilha();
        String resultado[];
        resultado = new String[50];
        int i,j;                        
        i = 0;
        j = 0;
        
        for(int contador=0; contador<expressao.length; contador++) {
            if(expressao[i].equals(")")){
                resultado[j] = (String) pilha.desempilha();
                j++;
            } else if(expressao[i].equals(Token.IGUAL_IGUAL) || expressao[i].equals(Token.EXCLAMACAO_IGUAL) ||
                     expressao[i].equals(Token.MAIOR) || expressao[i].equals(Token.MAIOR_IGUAL) ||
                     expressao[i].equals(Token.MENOR) || expressao[i].equals(Token.MENOR_IGUAL) ||
                     expressao[i].equals(Token.ECOMERCIAL_ECOMERCIAL) || expressao[i].equals(Token.BVERTICAL_BVERTICAL)) {
                pilha.empilha(expressao[i]);
            } else if(!expressao[i].equals("(")){
                resultado[j] = expressao[i];
                j++;
            }
            
            if(i>0){//se encontrar * ou / eles possuem prioridade, entao desempilha!!
                if((expressao[i-1].equals(Token.ECOMERCIAL_ECOMERCIAL) || expressao[i-1].equals(Token.BVERTICAL_BVERTICAL)) &&
                        !expressao[i].equals("(")){
                    resultado[j] = (String) pilha.desempilha();
                    j++;
                }
            }
            i++;
        }
        
        //se nao acabar com fecha parenteses!!
        while(!pilha.pilhaVazia()){
            resultado[j] = (String) pilha.desempilha();
            j++;
        }                
        int tamanhoRetorno = 0;
        for(j=0;resultado[j]!=null;j++)
            tamanhoRetorno++;
                
        return zipVetor(resultado);
    }
    
    public static String[] zipVetor(String[] vetor) {
        int tamanho = 0;
        for(int contador=0; contador<vetor.length && vetor[contador]!=null; contador++) {
            tamanho++;
        }
        String[] retorno = new String[tamanho];
        for(int contador=0; contador<tamanho; contador++) {
            retorno[contador] = vetor[contador];
        }
        return retorno;
    }
    
}
