package br.usp.sintatico;

import br.usp.estrutura.Pilha;
import br.usp.lexico.Simbolo;
import br.usp.maquinaestados.MaquinaEstados;
import br.usp.maquinaestados.Transicao;
import br.usp.semantico.Semantico;

/**
 *
 * @author nathalia
 */
public class MaquinaIdentificador extends SubMaquina{

    public MaquinaIdentificador() {
        estadoInicial = 0;
        tabelaTransicoes.add(new Transicao(0, 1, "identificador", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "[", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 7, "other", "devolve"));
        tabelaTransicoes.add(new Transicao(1, 8, "(", "ignora"));
        tabelaTransicoes.add(new Transicao(2, 3, "numero", "ignora"));
        tabelaTransicoes.add(new Transicao(3, 4, "]", "ignora"));
        tabelaTransicoes.add(new Transicao(4, 5, "[", "ignora"));
        tabelaTransicoes.add(new Transicao(4, 7, "other", "devolve"));
        tabelaTransicoes.add(new Transicao(5, 6, "numero", "ignora"));
        tabelaTransicoes.add(new Transicao(6, 7, "]", "ignora"));
        tabelaTransicoes.add(new Transicao(8, 9, "(", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(8, 9, "-", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(8, 9, "true", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(8, 9, "false", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(8, 9, "identificador", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(8, 9, "numero", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(9, 8, ",", "ignora"));
        tabelaTransicoes.add(new Transicao(9, 7, ")", "ignora"));

        tabelaEstadosAceitacao.add(7);

        maquina = new MaquinaEstados(tabelaTransicoes, estadoInicial, tabelaEstadosAceitacao);
        maquina.setNome("Sintático-Identificador");
    }

//    public boolean trataToken(Simbolo token)
//    {
//        boolean retorno = false;
//
//        String acao = maquina.transita(token.getTipo());
//        if(acao.equals("ignora"))
//        {
//			return true;
//        }
//        else if(acao.equals("devolve"))
//        {
//            return false;
//        }
//        else
//        {
//            System.out.println("Identificador: Não existe ação definida!");
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
        else
        {
            System.out.println("Identificador: Nao existe acao definida!");
        }

        return retorno;
	}
}
