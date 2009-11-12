package br.usp.sintatico;

import br.usp.lexico.Simbolo;
import br.usp.maquinaestados.MaquinaEstados;
import br.usp.maquinaestados.Transicao;

/**
 *
 * @author nathalia
 */
public class MaquinaSaida extends SubMaquina{

    public MaquinaSaida() {
        estadoInicial = 0;
        tabelaTransicoes.add(new Transicao(0, 1, "output", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "(", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(1, 2, "-", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(1, 2, "true", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(1, 2, "false", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(1, 2, "identificador", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(1, 2, "numero", "chamaExpressao"));

        tabelaEstadosAceitacao.add(2);

        maquina = new MaquinaEstados(tabelaTransicoes, estadoInicial, tabelaEstadosAceitacao);
    }

    public boolean trataToken(Simbolo token)
    {
        boolean retorno = false;

        String acao = maquina.transita(token.getTipo());
        if(acao.equals("ignora"))
        {

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
