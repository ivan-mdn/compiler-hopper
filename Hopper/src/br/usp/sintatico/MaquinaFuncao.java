package br.usp.sintatico;

import br.usp.lexico.Simbolo;
import br.usp.maquinaestados.MaquinaEstados;
import br.usp.maquinaestados.Transicao;

/**
 *
 * @author nathalia
 */
public class MaquinaFuncao extends SubMaquina{

    public MaquinaFuncao() {
        estadoInicial = 0;
        tabelaTransicoes.add(new Transicao(0, 1, "function", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "float", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "int", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "boolean", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "string", "ignora"));
        tabelaTransicoes.add(new Transicao(2, 3, "identificador", "chamaIdentificador"));
        tabelaTransicoes.add(new Transicao(3, 4, "(", "ignora"));
        tabelaTransicoes.add(new Transicao(4, 5, "int", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(4, 5, "float", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(4, 5, "string", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(4, 5, "boolean", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(5, 6, ",", "ignora"));
        tabelaTransicoes.add(new Transicao(5, 7, ")", "ignora"));
        tabelaTransicoes.add(new Transicao(6, 4, "vazio", "ignora"));
        tabelaTransicoes.add(new Transicao(7, 8, "beginfunction", "ignora"));
        tabelaTransicoes.add(new Transicao(8, 9, "int", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(8, 9, "float", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(8, 9, "string", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(8, 9, "boolean", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(8, 9, "input", "chamaEntrada"));
        tabelaTransicoes.add(new Transicao(8, 9, "output", "chamaSaida"));
        tabelaTransicoes.add(new Transicao(8, 9, "if", "chamaCondicional"));
        tabelaTransicoes.add(new Transicao(8, 9, "while", "chamaIteracao"));
        tabelaTransicoes.add(new Transicao(8, 9, "for", "chamaIteracao"));
        tabelaTransicoes.add(new Transicao(8, 9, "identificador", "chamaAtribuicao"));
        tabelaTransicoes.add(new Transicao(9, 8, "vazio", "ignora"));
        tabelaTransicoes.add(new Transicao(8, 10, "return", "ignora"));
        tabelaTransicoes.add(new Transicao(10, 11, "(", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(10, 11, "-", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(10, 11, "true", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(10, 11, "false", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(10, 11, "identificador", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(10, 11, "numero", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(11, 12, ";", "ignora"));
        tabelaTransicoes.add(new Transicao(12, 13, "endfunction", "ignora"));

        tabelaEstadosAceitacao.add(13);

        maquina = new MaquinaEstados(tabelaTransicoes, estadoInicial, tabelaEstadosAceitacao);
    }

    public boolean trataToken(Simbolo token)
    {
        boolean retorno = false;

        String acao = maquina.transita(token.getTipo());
        if(acao.equals("ignora"))
        {

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
        else if(acao.equals("chamaExpressao"))
        {
            subMaquina = new MaquinaExpressao();
            retorno = subMaquina.processaToken(token);
        }
        else
        {
            System.out.println("Não existe ação definida!");
        }

        return retorno;
    }
}
