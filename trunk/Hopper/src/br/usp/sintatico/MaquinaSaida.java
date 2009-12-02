package br.usp.sintatico;

import br.usp.lexico.Simbolo;
import br.usp.maquinaestados.MaquinaEstados;
import br.usp.maquinaestados.Transicao;
import br.usp.semantico.Semantico;

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
        maquina.setNome("Sintático-Saida");
    }

//    public boolean trataToken(Simbolo token)
//    {
//        boolean retorno = false;
//
//        String acao = maquina.transita(token.getTipo());
//        if(acao.equals("ignora"))
//        {
//            return true;
//        }
//        else if(acao.equals("chamaExpressao"))
//        {
//            subMaquina = new MaquinaExpressao();
//            retorno = subMaquina.processaToken(token);
//        }
//        else
//        {
//            System.out.println("Saida: Não existe ação definida!");
//        }
//
//        return retorno;
//    }

	public boolean trataToken(Simbolo token, Semantico semantico) {
        boolean retorno = false;

        String acao = maquina.transita(token.getTipo());
        if(acao.equals("ignora"))
        {
            return true;
        }
        else if(acao.equals("chamaExpressao"))
        {
            subMaquina = new MaquinaExpressao();
            retorno = subMaquina.processaToken(token, semantico);
        }
        else
        {
//            System.out.println("Saida: Não existe ação definida!");
        }

        return retorno;
	}
}
