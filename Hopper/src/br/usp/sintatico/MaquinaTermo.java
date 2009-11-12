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
        tabelaTransicoes.add(new Transicao(1, 2, "+", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "-", "ignora"));
        tabelaTransicoes.add(new Transicao(3, 2, "(", "chamaFator"));
        tabelaTransicoes.add(new Transicao(3, 2, "-", "chamaFator"));
        tabelaTransicoes.add(new Transicao(3, 2, "true", "chamaFator"));
        tabelaTransicoes.add(new Transicao(3, 2, "false", "chamaFator"));
        tabelaTransicoes.add(new Transicao(3, 2, "identificador", "chamaFator"));
        tabelaTransicoes.add(new Transicao(3, 2, "numero", "chamaFator"));

        tabelaEstadosAceitacao.add(1);

        maquina = new MaquinaEstados(tabelaTransicoes, estadoInicial, tabelaEstadosAceitacao);
    }

    public boolean trataToken(Simbolo token) {
        boolean retorno = false;

        String acao = maquina.transita(token.getTipo());
        if(acao.equals("ignora"))
        {

        }
        else if(acao.equals("chamaFator"))
        {
            subMaquina = new MaquinaFator();
            retorno = subMaquina.processaToken(token);
        }
        else
        {
            System.out.println("Não existe ação definida!");
        }

        return retorno;
    }

}
