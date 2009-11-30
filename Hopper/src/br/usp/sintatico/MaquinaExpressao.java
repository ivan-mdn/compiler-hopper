package br.usp.sintatico;

import br.usp.lexico.Simbolo;
import br.usp.maquinaestados.MaquinaEstados;
import br.usp.maquinaestados.Transicao;
import br.usp.semantico.Semantico;

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
        tabelaTransicoes.add(new Transicao(1, 3, "other", "devolve"));
        tabelaTransicoes.add(new Transicao(2, 1, "(", "chamaTermo"));
        tabelaTransicoes.add(new Transicao(2, 1, "-", "chamaTermo"));
        tabelaTransicoes.add(new Transicao(2, 1, "true", "chamaTermo"));
        tabelaTransicoes.add(new Transicao(2, 1, "false", "chamaTermo"));
        tabelaTransicoes.add(new Transicao(2, 1, "identificador", "chamaTermo"));
        tabelaTransicoes.add(new Transicao(2, 1, "numero", "chamaTermo"));

        tabelaEstadosAceitacao.add(3);

        maquina = new MaquinaEstados(tabelaTransicoes, estadoInicial, tabelaEstadosAceitacao);
        maquina.setNome("Sintático-Expressao");
    }

//    public boolean trataToken(Simbolo token) {
//        boolean retorno = false;
//
//        String acao = maquina.transita(token.getTipo());
//        if(acao.equals("ignora"))
//        {
//            return true;
//        }
//        else if(acao.equals("devolve"))
//        {
//            return false;
//        }
//        else if(acao.equals("chamaTermo"))
//        {
//            subMaquina = new MaquinaTermo();
//            retorno = subMaquina.processaToken(token);
//        }
//        else
//        {
//            System.out.println("Expressao: Não existe ação definida!");
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
        else if(acao.equals("devolve"))
        {
            return false;
        }
        else if(acao.equals("chamaTermo"))
        {
            subMaquina = new MaquinaTermo();
            retorno = subMaquina.processaToken(token, semantico);
        }
        else
        {
            System.out.println("Expressao: Não existe ação definida!");
        }

        return retorno;
	}

}
