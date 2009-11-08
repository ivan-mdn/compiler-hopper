package br.usp.maquinaestados;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author nathalia
 */
public class MaquinaEstados {

    private ArrayList<Transicao> tabelaTransicoes = new ArrayList<Transicao>();
    private ArrayList tabelaEstadosAceitacao = new ArrayList();
    private int estadoAtual;
    private int estadoInicial;

    public int getEstadoInicial() {
        return estadoInicial;
    }

    public void setEstadoInicial(int estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public ArrayList getTabelaEstadosAceitacao() {
        return tabelaEstadosAceitacao;
    }

    public void setTabelaEstadosAceitacao(ArrayList tabelaEstadosAceitacao) {
        this.tabelaEstadosAceitacao = tabelaEstadosAceitacao;
    }

    public MaquinaEstados(ArrayList<Transicao> tabela, int estadoInicial, ArrayList tabelaEstadosAceitacao) {
        setTabelaTransicoes(tabela);
        setEstadoInicial(estadoInicial);
        setTabelaEstadosAceitacao(tabelaEstadosAceitacao);
    }

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

    public String transita(String valorLido)
    {
        boolean achou = false;
        String acao = "";
        Iterator<Transicao> itTransicao = tabelaTransicoes.iterator();

        while(itTransicao.hasNext() && !achou)
        {
            Transicao transicao = itTransicao.next();
            if(transicao.getEstadoAtual() == estadoAtual)
                if(transicao.getSimbolo().equals(valorLido) || transicao.getSimbolo().equals("other"))
                {
                    System.out.println("Estado Atual: " + estadoAtual);
                    int proximoEstado = transicao.getProximoEstado();
                    System.out.println("Próximo Estado: " + proximoEstado);
                    if(tabelaEstadosAceitacao.contains(proximoEstado))
                    {
                        setEstadoAtual(estadoInicial);
                    }
                    else
                    {
                        setEstadoAtual(proximoEstado);
                    }
                    acao = transicao.getAcao();
                    System.out.println("Mudou para o Estado " + estadoAtual);
                    achou = true;
                }
        }
        return acao;
    }

    public boolean isEstadoAceitacao(){
        return tabelaEstadosAceitacao.contains(estadoAtual);
    }

}
