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
    private static MaquinaEstados maquina = new MaquinaEstados(tabelaTransicoes);
    private static int estadoInicial = 0;
}
