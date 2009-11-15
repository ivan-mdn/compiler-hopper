package br.usp.sintatico;

import br.usp.lexico.Simbolo;
import br.usp.maquinaestados.MaquinaEstados;
import br.usp.maquinaestados.Transicao;

/**
 *
 * @author nathalia
 */
public class MaquinaTermo extends SubMaquina{

    public MaquinaTermo() {
        estadoInicial = 0;
        tabelaTransicoes.add(new Transicao(0, 1, "(", "chamaFator"));
        tabelaTransicoes.add(new Transicao(0, 1, "-", "chamaFator"));
        tabelaTransicoes.add(new Transicao(0, 1, "true", "chamaFator"));
        tabelaTransicoes.add(new Transicao(0, 1, "false", "chamaFator"));
        tabelaTransicoes.add(new Transicao(0, 1, "identificador", "chamaFator"));
        tabelaTransicoes.add(new Transicao(0, 1, "numero", "chamaFator"));
        tabelaTransicoes.add(new Transicao(1, 2, "*", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "/", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "%", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 3, "other", "devolve"));
        tabelaTransicoes.add(new Transicao(2, 1, "(", "chamaFator"));
        tabelaTransicoes.add(new Transicao(2, 1, "-", "chamaFator"));
        tabelaTransicoes.add(new Transicao(2, 1, "true", "chamaFator"));
        tabelaTransicoes.add(new Transicao(2, 1, "false", "chamaFator"));
        tabelaTransicoes.add(new Transicao(2, 1, "identificador", "chamaFator"));
        tabelaTransicoes.add(new Transicao(2, 1, "numero", "chamaFator"));

        tabelaEstadosAceitacao.add(3);

        maquina = new MaquinaEstados(tabelaTransicoes, estadoInicial, tabelaEstadosAceitacao);
        maquina.setNome("Sintático-Termo");
    }

    public boolean trataToken(Simbolo token) {
        boolean retorno = false;

        String acao = maquina.transita(token.getTipo());
        if(acao.equals("ignora"))
        {
            return true;
        }
        else if(acao.equals("devolve"))
        {
            return false;
        }
        else if(acao.equals("chamaFator"))
        {
            subMaquina = new MaquinaFator();
            retorno = subMaquina.processaToken(token);
        }
        else
        {
            System.out.println("Termo: Não existe ação definida!");
        }

        return retorno;
    }

}
