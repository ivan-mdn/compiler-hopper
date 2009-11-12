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
        tabelaTransicoes.add(new Transicao(0, 1, "(", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(0, 1, "-", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(0, 1, "true", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(0, 1, "false", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(0, 1, "identificador", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(0, 1, "numero", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(0, 1, "not", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(0, 3, "(", "ignora"));
        tabelaTransicoes.add(new Transicao(0, 5, "true", "ignora"));
        tabelaTransicoes.add(new Transicao(0, 5, "false", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "<", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, ">", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "<=", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, ">=", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "==", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "<>", "ignora"));
        tabelaTransicoes.add(new Transicao(2, 5, "(", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(2, 5, "-", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(2, 5, "true", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(2, 5, "false", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(2, 5, "identificador", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(2, 5, "numero", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(2, 5, "not", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(3, 4, "(", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(3, 4, "-", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(3, 4, "true", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(3, 4, "false", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(3, 4, "identificador", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(3, 4, "numero", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(3, 4, "not", "chamaCondicao"));
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
        else if(acao.equals("chamaCondicao"))
        {
            subMaquina = new MaquinaCondicao();
            retorno = subMaquina.processaToken(token);
        }
        else
        {
            System.out.println("Não existe ação definida!");
        }

        return retorno;
    }

}
