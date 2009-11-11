package br.usp.sintatico;

import br.usp.lexico.Simbolo;
import br.usp.maquinaestados.MaquinaEstados;
import br.usp.maquinaestados.Transicao;

/**
 *
 * @author nathalia
 */
public class MaquinaIdentificador extends SubMaquina{

    public MaquinaIdentificador() {
        estadoInicial = 0;
        tabelaTransicoes.add(new Transicao(0, 1, "identificador", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "[", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 7, "vazio", "ignora"));
        tabelaTransicoes.add(new Transicao(2, 3, "numero", "ignora"));
        tabelaTransicoes.add(new Transicao(3, 4, "]", "ignora"));
        tabelaTransicoes.add(new Transicao(4, 5, "[", "ignora"));
        tabelaTransicoes.add(new Transicao(4, 7, "vazio", "ignora"));
        tabelaTransicoes.add(new Transicao(5, 6, "numero", "ignora"));
        tabelaTransicoes.add(new Transicao(6, 7, "]", "ignora"));

        tabelaEstadosAceitacao.add(7);

        maquina = new MaquinaEstados(tabelaTransicoes, estadoInicial, tabelaEstadosAceitacao);
    }

    public boolean trataToken(Simbolo token)
    {
        boolean retorno = false;

        String acao = maquina.transita(token.getTipo());
        if(acao.equals("ignora"))
        {

        }
        else
        {
            System.out.println("Não existe ação definida!");
        }

        return retorno;
    }
}
