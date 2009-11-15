package br.usp.sintatico;

import br.usp.lexico.Simbolo;
import br.usp.maquinaestados.MaquinaEstados;
import br.usp.maquinaestados.Transicao;

/**
 *
 * @author nathalia
 */
public class MaquinaCondicao extends SubMaquina{

    public MaquinaCondicao() {
        estadoInicial = 0;

        tabelaTransicoes.add(new Transicao(0, 1, "(", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(0, 1, "-", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(0, 1, "true", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(0, 1, "false", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(0, 1, "identificador", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(0, 1, "numero", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(0, 3, "not", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "<", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, ">", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "<=", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, ">=", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "==", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "<>", "ignora"));
        tabelaTransicoes.add(new Transicao(2, 4, "(", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(2, 4, "-", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(2, 4, "true", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(2, 4, "false", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(2, 4, "identificador", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(2, 4, "numero", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(3, 4, "true", "ignora"));
        tabelaTransicoes.add(new Transicao(3, 4, "false", "ignora"));
        tabelaTransicoes.add(new Transicao(3, 4, "(", "ignora"));
        tabelaTransicoes.add(new Transicao(3, 4, "(", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(3, 4, "-", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(3, 4, "true", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(3, 4, "false", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(3, 4, "identificador", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(3, 4, "numero", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(3, 4, "not", "chamaCondicao"));

        tabelaEstadosAceitacao.add(4);

        maquina = new MaquinaEstados(tabelaTransicoes, estadoInicial, tabelaEstadosAceitacao);
        maquina.setNome("Sintático-Condicao");
    }

    public boolean trataToken(Simbolo token) {
        boolean retorno = false;

        String acao = maquina.transita(token.getTipo());
        if(acao.equals("ignora"))
        {
            return true;
        }
        else if(acao.equals("chamaExpressao"))
        {
            subMaquina = new MaquinaExpressao();
            retorno = subMaquina.processaToken(token);
        }
        else if(acao.equals("chamaCondicao"))
        {
            subMaquina = new MaquinaCondicao();
            retorno = subMaquina.processaToken(token);
        }
        else
        {
            System.out.println("Condicao: Não existe ação definida!");
        }

        return retorno;
    }

}
