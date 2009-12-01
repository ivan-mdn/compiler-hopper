package br.usp.sintatico;

import br.usp.lexico.Simbolo;
import br.usp.maquinaestados.MaquinaEstados;
import br.usp.maquinaestados.Transicao;
import br.usp.semantico.Semantico;

/**
 *
 * @author nathalia
 */
public class MaquinaAtribuicao extends SubMaquina{

    public MaquinaAtribuicao() {
        estadoInicial = 0;
        tabelaTransicoes.add(new Transicao(0, 1, "identificador", "chamaIdentificador"));
        tabelaTransicoes.add(new Transicao(1, 2, "=", "ignora"));
        tabelaTransicoes.add(new Transicao(2, 3, "(", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(2, 3, "-", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(2, 3, "true", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(2, 3, "false", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(2, 3, "identificador", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(2, 3, "numero", "chamaExpressao"));

        tabelaEstadosAceitacao.add(3);

        maquina = new MaquinaEstados(tabelaTransicoes, estadoInicial, tabelaEstadosAceitacao);
        maquina.setNome("Sintático-Atribuicao");
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
//        else if(acao.equals("chamaIdentificador"))
//        {
//            subMaquina = new MaquinaIdentificador();
//            retorno = subMaquina.processaToken(token);
//        }
//        else if(acao.equals("chamaExpressao"))
//        {
//            subMaquina = new MaquinaExpressao();
//            retorno = subMaquina.processaToken(token);
//        }
//        else
//        {
//            System.out.println("Atribuicao: Não existe ação definida!");
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
        else if(acao.equals("chamaIdentificador"))
        {
			// executa a ação semântica de Atribuição do Lado Esquerdo (identificador)
			semantico.AtribuicaoID(token);

            subMaquina = new MaquinaIdentificador();
            retorno = subMaquina.processaToken(token, semantico);
        }
        else if(acao.equals("chamaExpressao"))
        {
            subMaquina = new MaquinaExpressao();
            retorno = subMaquina.processaToken(token, semantico);

			// executa a ação semântica de Declaração
			semantico.AtribuicaoGeraCodigo();
        }
        else
        {
            System.out.println("Atribuicao: Não existe ação definida!");
        }

        return retorno;

	}
}