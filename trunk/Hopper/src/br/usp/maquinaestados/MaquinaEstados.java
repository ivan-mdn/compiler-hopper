package br.usp.maquinaestados;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author nathalia
 */
public class MaquinaEstados {

    private ArrayList<Transicao> tabelaTransicoes = new ArrayList<Transicao>();
    private int estadoAtual;

    public MaquinaEstados(ArrayList<Transicao> tabela) {
        setTabelaTransicoes(tabela);
    }

    //novoEstado = transitaEstado ( transicoes, estadoAtual, valorLido);
    public ArrayList<Transicao> getTabelaTransicoes() {
        return tabelaTransicoes;
    }

    public void setTabelaTransicoes(ArrayList<Transicao> tabelaTransicoes) {
        this.tabelaTransicoes = tabelaTransicoes;
    }

    public int getEstadoAtual() {
        return estadoAtual;
    }

    public void setEstadoAtual(int estadoAtual) {
        this.estadoAtual = estadoAtual;
    }

    public void transita(int estado, String valorLido)
    {
        setEstadoAtual(estado);
        Iterator<Transicao> itTransicao = tabelaTransicoes.iterator();
        while(itTransicao.hasNext())
        {
            Transicao transicao = itTransicao.next();
            if(transicao.getEstadoAtual() == estadoAtual && transicao.getSimbolo().equals(valorLido))
            {
                System.out.println("Estado Atual: " + estadoAtual);
                setEstadoAtual(transicao.getProximoEstado());
                System.out.println("Mudou para o Estado " + estadoAtual);
            }
        }
    }

}
