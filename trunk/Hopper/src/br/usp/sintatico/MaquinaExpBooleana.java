package br.usp.sintatico;

import br.usp.lexico.Simbolo;
import br.usp.maquinaestados.MaquinaEstados;
import br.usp.maquinaestados.Transicao;

/**
 *
 * @author nathalia
 */
public class MaquinaExpBooleana extends SubMaquina{

    public MaquinaExpBooleana() {
        estadoInicial = 0;
        //tabelaTransicoes.add(new Transicao(0, 1, "condicao", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(0, 3, "(", "ignora"));
        tabelaTransicoes.add(new Transicao(0, 5, "true", "ignora"));
        tabelaTransicoes.add(new Transicao(0, 5, "false", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "<", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, ">", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "<=", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, ">=", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "==", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "<>", "ignora"));
        //tabelaTransicoes.add(new Transicao(2, 5, "condicao", "chamaCondicao"));
        //tabelaTransicoes.add(new Transicao(3, 4, "condicao", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(1, 2, ">=", "ignora"));
        tabelaTransicoes.add(new Transicao(4, 5, ")", "ignora"));

        tabelaEstadosAceitacao.add(5);

        maquina = new MaquinaEstados(tabelaTransicoes, estadoInicial, tabelaEstadosAceitacao);
    }

    public boolean trataToken(Simbolo token) {
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
        else if(acao.equals("chamaAtribuicao"))
        {
            subMaquina = new MaquinaAtribuicao();
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
