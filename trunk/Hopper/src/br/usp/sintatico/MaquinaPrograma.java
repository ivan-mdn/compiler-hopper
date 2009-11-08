package br.usp.sintatico;

import br.usp.lexico.Simbolo;
import br.usp.maquinaestados.MaquinaEstados;
import br.usp.maquinaestados.Transicao;
import java.util.ArrayList;

/**
 *
 * @author nathalia
 */
public class MaquinaPrograma extends SubMaquina{

    private static ArrayList<Transicao> tabelaTransicoes = new ArrayList<Transicao>();
    private static ArrayList tabelaEstadosAceitacao = new ArrayList();
    private static int estadoInicial = 0;

    public MaquinaPrograma() {
        tabelaTransicoes.add(new Transicao(0, 1, "program", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "function", "chamaFuncao"));
        tabelaTransicoes.add(new Transicao(1, 3, "int", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(1, 3, "float", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(1, 3, "string", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(1, 3, "boolean", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(1, 3, "input", "chamaEntrada"));
        tabelaTransicoes.add(new Transicao(1, 3, "output", "chamaSaida"));
        tabelaTransicoes.add(new Transicao(1, 3, "if", "chamaCondicional"));
        tabelaTransicoes.add(new Transicao(1, 3, "while", "chamaIteracao"));
        tabelaTransicoes.add(new Transicao(1, 3, "for", "chamaIteracao"));
        tabelaTransicoes.add(new Transicao(1, 3, "identificador", "chamaIdentificador"));
        tabelaTransicoes.add(new Transicao(1, 4, "end", "ignora"));
        tabelaTransicoes.add(new Transicao(2, 1, "vazio", "ignora"));
        tabelaTransicoes.add(new Transicao(3, 1, "vazio", "ignora"));

        tabelaEstadosAceitacao.add(4);

        maquina = new MaquinaEstados(tabelaTransicoes, estadoInicial, tabelaEstadosAceitacao);
    }

    public boolean trataToken(Simbolo token)
    {
        boolean retorno = false;

        String acao = maquina.transita(token.getTipo());
        if(acao.equals("ignora"))
        {

        }
        else if(acao.equals("chamaFuncao"))
        {
            subMaquina = new MaquinaFuncao();
            retorno = subMaquina.processaToken(token);
        }
        else if(acao.equals("chamaDeclaracao"))
        {
            subMaquina = new MaquinaDeclaracao();
            retorno = subMaquina.processaToken(token);
        }
        else if(acao.equals("chamaEntrada"))
        {
            subMaquina = new MaquinaEntrada();
            retorno = subMaquina.processaToken(token);
        }
        else if(acao.equals("chamaSaida"))
        {
            subMaquina = new MaquinaSaida();
            retorno = subMaquina.processaToken(token);
        }
        else if(acao.equals("chamaCondicional"))
        {
            subMaquina = new MaquinaCondicional();
            retorno = subMaquina.processaToken(token);
        }
        else if(acao.equals("chamaIteracao"))
        {
            subMaquina = new MaquinaIteracao();
            retorno = subMaquina.processaToken(token);
        }
        else if(acao.equals("chamaIdentificador"))
        {
            subMaquina = new MaquinaIdentificador();
            retorno = subMaquina.processaToken(token);
        }
        else
        {
            System.out.println("Não existe ação definida!");
        }

        return retorno;
    }


}
