package br.usp.sintatico;

import br.usp.lexico.Simbolo;
import br.usp.maquinaestados.MaquinaEstados;
import br.usp.maquinaestados.Transicao;

/**
 *
 * @author nathalia
 */
public class MaquinaIteracao extends SubMaquina{

    public MaquinaIteracao() {
        estadoInicial = 0;
        tabelaTransicoes.add(new Transicao(0, 1, "while", "ignora"));
        tabelaTransicoes.add(new Transicao(0, 7, "for", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "(", "ignora"));
        tabelaTransicoes.add(new Transicao(2, 3, "(", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(2, 3, "-", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(2, 3, "true", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(2, 3, "false", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(2, 3, "identificador", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(2, 3, "numero", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(2, 3, "not", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(3, 4, ")", "ignora"));
        tabelaTransicoes.add(new Transicao(4, 5, "do", "ignora"));
        tabelaTransicoes.add(new Transicao(5, 5, "if", "chamaCondicional"));
        tabelaTransicoes.add(new Transicao(5, 5, "while", "chamaIteracao"));
        tabelaTransicoes.add(new Transicao(5, 5, "for", "chamaIteracao"));
        tabelaTransicoes.add(new Transicao(5, 6, "int", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(5, 6, "float", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(5, 6, "string", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(5, 6, "boolean", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(5, 6, "input", "chamaEntrada"));
        tabelaTransicoes.add(new Transicao(5, 6, "output", "chamaSaida"));
        tabelaTransicoes.add(new Transicao(5, 6, "identificador", "chamaAtribuicao"));
        tabelaTransicoes.add(new Transicao(5, 15, "endwhile", "ignora"));
        tabelaTransicoes.add(new Transicao(6, 5, ";", "ignora"));
        tabelaTransicoes.add(new Transicao(7, 8, "(", "ignora"));
        tabelaTransicoes.add(new Transicao(8, 81, "identificador", "chamaAtribuicao"));
        tabelaTransicoes.add(new Transicao(81, 9, ";", "ignora"));
        tabelaTransicoes.add(new Transicao(9, 91, "(", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(9, 91, "-", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(9, 91, "true", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(9, 91, "false", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(9, 91, "identificador", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(9, 91, "numero", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(9, 91, "not", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(91, 10, ";", "ignora"));
        tabelaTransicoes.add(new Transicao(10, 11, "identificador", "chamaAtribuicao"));
        tabelaTransicoes.add(new Transicao(11, 12, ")", "ignora"));
        tabelaTransicoes.add(new Transicao(12, 13, "beginfor", "ignora"));
        tabelaTransicoes.add(new Transicao(13, 13, "if", "chamaCondicional"));
        tabelaTransicoes.add(new Transicao(13, 13, "while", "chamaIteracao"));
        tabelaTransicoes.add(new Transicao(13, 13, "for", "chamaIteracao"));
        tabelaTransicoes.add(new Transicao(13, 14, "int", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(13, 14, "float", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(13, 14, "string", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(13, 14, "boolean", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(13, 14, "input", "chamaEntrada"));
        tabelaTransicoes.add(new Transicao(13, 14, "output", "chamaSaida"));
        tabelaTransicoes.add(new Transicao(13, 14, "identificador", "chamaAtribuicao"));
        tabelaTransicoes.add(new Transicao(14, 13, ";", "ignora"));
        tabelaTransicoes.add(new Transicao(13, 15, "endfor", "ignora"));

        tabelaEstadosAceitacao.add(15);

        maquina = new MaquinaEstados(tabelaTransicoes, estadoInicial, tabelaEstadosAceitacao);
        maquina.setNome("Sintático-Iteracao");
    }

    public boolean trataToken(Simbolo token)
    {
        boolean retorno = false;

        String acao = maquina.transita(token.getTipo());
        if(acao.equals("ignora"))
        {
            return true;
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
        else if(acao.equals("chamaCondicao"))
        {
            subMaquina = new MaquinaCondicao();
            retorno = subMaquina.processaToken(token);
        }
        else
        {
            System.out.println("Iteracao: Não existe ação definida!");
        }

        return retorno;
    }
}
