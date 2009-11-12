package br.usp.sintatico;

import br.usp.lexico.Simbolo;
import br.usp.maquinaestados.MaquinaEstados;
import br.usp.maquinaestados.Transicao;

/**
 *
 * @author nathalia
 */
public class MaquinaExpressao extends SubMaquina{

    public MaquinaExpressao() {
        estadoInicial = 0;

        tabelaTransicoes.add(new Transicao(0, 1, "(", "chamaTermo"));
        tabelaTransicoes.add(new Transicao(0, 1, "-", "chamaTermo"));
        tabelaTransicoes.add(new Transicao(0, 1, "true", "chamaTermo"));
        tabelaTransicoes.add(new Transicao(0, 1, "false", "chamaTermo"));
        tabelaTransicoes.add(new Transicao(0, 1, "identificador", "chamaTermo"));
        tabelaTransicoes.add(new Transicao(0, 1, "numero", "chamaTermo"));
        tabelaTransicoes.add(new Transicao(1, 2, "+", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "-", "ignora"));
        tabelaTransicoes.add(new Transicao(3, 2, "(", "chamaTermo"));
        tabelaTransicoes.add(new Transicao(3, 2, "-", "chamaTermo"));
        tabelaTransicoes.add(new Transicao(3, 2, "true", "chamaTermo"));
        tabelaTransicoes.add(new Transicao(3, 2, "false", "chamaTermo"));
        tabelaTransicoes.add(new Transicao(3, 2, "identificador", "chamaTermo"));
        tabelaTransicoes.add(new Transicao(3, 2, "numero", "chamaTermo"));

        tabelaEstadosAceitacao.add(1);

        maquina = new MaquinaEstados(tabelaTransicoes, estadoInicial, tabelaEstadosAceitacao);
    }

    public boolean trataToken(Simbolo token) {
        boolean retorno = false;

        String acao = maquina.transita(token.getTipo());
        if(acao.equals("ignora"))
        {

        }
        else if(acao.equals("chamaTermo"))
        {
            subMaquina = new MaquinaTermo();
            retorno = subMaquina.processaToken(token);
        }
        else
        {
            System.out.println("Não existe ação definida!");
        }

        return retorno;
    }

}
