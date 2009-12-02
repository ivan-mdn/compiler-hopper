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
public class MaquinaDeclaracao extends SubMaquina{

    public MaquinaDeclaracao() {
        estadoInicial = 0;
        tabelaTransicoes.add(new Transicao(0, 1, "string", "ignora"));
        tabelaTransicoes.add(new Transicao(0, 1, "float", "ignora"));
        tabelaTransicoes.add(new Transicao(0, 1, "int", "ignora"));
        tabelaTransicoes.add(new Transicao(0, 1, "boolean", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "identificador", "chamaIdentificador"));

        tabelaEstadosAceitacao.add(2);

        maquina = new MaquinaEstados(tabelaTransicoes, estadoInicial, tabelaEstadosAceitacao);
        maquina.setNome("Sintático-Declaracao");
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
//			subMaquina = new MaquinaIdentificador();
//            retorno = subMaquina.processaToken(token);
//        }
//        else
//        {
//            System.out.println("Declaração: Não existe ação definida!");
//        }
//
//        return retorno;
//    }

	public boolean trataToken(Simbolo token, Semantico semantico) {
        boolean retorno = false;

        String acao = maquina.transita(token.getTipo());
        if(acao.equals("ignora")) {
            return true;
        }
        else if(acao.equals("chamaIdentificador")) {
			// executa ação semântica
			try {
			if (!semantico.DeclaracaoID(token))
				System.out.println("Erro no semantico!");
			}
			catch(Exception e){}

			subMaquina = new MaquinaIdentificador();
            retorno = subMaquina.processaToken(token, semantico);
        }
        else {
//            System.out.println("Declaração: Não existe ação definida!");
        }

        return retorno;
	}
}