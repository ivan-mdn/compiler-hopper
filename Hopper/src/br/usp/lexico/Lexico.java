package br.usp.lexico;

import br.usp.maquinaestados.MaquinaEstados;
import br.usp.maquinaestados.Transicao;
import java.util.ArrayList;

/**
 *
 * @author nathalia
 */
public class Lexico {
    private static ArrayList<Transicao> tabelaTransicoes = new ArrayList<Transicao>();
    private static ArrayList tabelaEstadosAceitacao = new ArrayList();
    private static int estadoInicial = 0;
    private static MaquinaEstados maquina = new MaquinaEstados(tabelaTransicoes, estadoInicial, tabelaEstadosAceitacao);

    public Lexico() {
        setTabelaTransicoes();
        setTabelaEstadosAceitacao();
        maquina.setTabelaTransicoes(tabelaTransicoes);
        maquina.setTabelaEstadosAceitacao(tabelaEstadosAceitacao);
        maquina.setEstadoInicial(estadoInicial);
    }

    public static ArrayList getTabelaEstadosAceitacao() {
        return tabelaEstadosAceitacao;
    }

    public static void setTabelaEstadosAceitacao() {
        tabelaEstadosAceitacao.add(11);
        tabelaEstadosAceitacao.add(12);
        tabelaEstadosAceitacao.add(13);
        tabelaEstadosAceitacao.add(14);
        tabelaEstadosAceitacao.add(16);
        tabelaEstadosAceitacao.add(17);
    }

    public static int getEstadoInicial() {
        return estadoInicial;
    }

    public static void setEstadoInicial(int estadoInicial) {
        Lexico.estadoInicial = estadoInicial;
    }

    public static MaquinaEstados getMaquina() {
        return maquina;
    }

    public static void setMaquina(MaquinaEstados maquina) {
        Lexico.maquina = maquina;
    }

    public static ArrayList<Transicao> getTabelaTransicoes() {
        return tabelaTransicoes;
    }

    public static void setTabelaTransicoes() {
        tabelaTransicoes.add(new Transicao(0,  10, "<", "nada"));
        tabelaTransicoes.add(new Transicao(0,  14, "=", "nada"));
        tabelaTransicoes.add(new Transicao(0,  15, ">", "nada"));
        tabelaTransicoes.add(new Transicao(10, 11, "=", "nada"));
        tabelaTransicoes.add(new Transicao(10, 12, ">", "nada"));
        tabelaTransicoes.add(new Transicao(10, 13, "other", "nada"));
        tabelaTransicoes.add(new Transicao(15, 16, "=", "nada"));
        tabelaTransicoes.add(new Transicao(15, 17, "other", "nada"));
    }
}
