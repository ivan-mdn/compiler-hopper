package br.usp.semantico;

import br.usp.es.Saida;
import br.usp.estrutura.Pilha;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Memoria extends LinkedList {
            
    private boolean inserirEmSeguida = false;
    private String rotuloMarcador;
    private int contadorRelativo;
    private Pilha pilhaRotuloMarcador;
    private Pilha pilhaContadorRelativo;
    
    public Memoria() {            
        pilhaRotuloMarcador = new Pilha();
        pilhaContadorRelativo = new Pilha();
        contadorRelativo = 0;
    }                
    
    public void setInserirEmSeguida(String rotulo) {
        if(inserirEmSeguida) {
            pilhaRotuloMarcador.empilha(rotuloMarcador);
            pilhaContadorRelativo.empilha(contadorRelativo);
        }
        inserirEmSeguida = true;
        rotuloMarcador = rotulo;
        contadorRelativo = 0;
    }
    public void resetInserirEmSeguida() {
        if(pilhaRotuloMarcador.pilhaVazia()) {
            inserirEmSeguida = false;
            contadorRelativo = 0;
        } else {
            contadorRelativo = (Integer)pilhaContadorRelativo.desempilha();
            rotuloMarcador = (String)pilhaRotuloMarcador.desempilha();
        }
    }
    public void inserir(PosicaoMemoria posicaoMemoria) {                    
        if(inserirEmSeguida) {
            inserirEmSeguida(rotuloMarcador, posicaoMemoria);            
        } else {
            this.add(posicaoMemoria);
        }
    }
    
    private void inserirEmSeguida(String rotulo, PosicaoMemoria posicaoMemoria) {
        Iterator it = this.iterator();
        int posicao = 0;
        while(it.hasNext()) {
            PosicaoMemoria atual = (PosicaoMemoria)it.next();
            posicao++;
            if(atual.getRotulo().equals(rotulo)) {                
                this.add(posicao+contadorRelativo, posicaoMemoria);
                contadorRelativo++;
                return;
            }             
        }
        System.exit(0);
    }
       
     public void imprimir() {
        ListIterator it = this.listIterator();
        System.out.println("**********CONTEUDO MEMORIA**********");
        System.out.println("END\tOP\tOPERANDO\tROTULO");
        while(it.hasNext()) {     
            PosicaoMemoria posicaoMemoria = (PosicaoMemoria)it.next();
            System.out.println(posicaoMemoria);            
            
        }
    }         
     
     public void imprimirMVNes() {
        Saida saida = new Saida("C:/mvn/teste.mvn");
        saida.delete();
        ListIterator it = this.listIterator();
        System.out.println("********** CONTEUDO MEMORIA EM MVN **********");
        while(it.hasNext()) {     
            PosicaoMemoria posicaoMemoria = (PosicaoMemoria)it.next();
            System.out.println(posicaoMemoria.getEndereco() + " " + posicaoMemoria.getOperacao() + posicaoMemoria.getOperando());
            saida.write(posicaoMemoria.getEndereco() + " " + posicaoMemoria.getOperacao() + posicaoMemoria.getOperando());
        }
     }
     
     public String getEnderecoDeRotulo(String rotulo) {
         Iterator it = this.iterator();         
         while(it.hasNext()) {
             PosicaoMemoria posicaoAtual = (PosicaoMemoria)it.next();
             if(posicaoAtual.getRotulo().equals(rotulo))
                 return posicaoAtual.getEndereco();
         }         
         return null;
     }
}
