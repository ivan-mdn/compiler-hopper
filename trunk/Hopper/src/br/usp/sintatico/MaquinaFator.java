package br.usp.sintatico;

import br.usp.lexico.Simbolo;
import br.usp.maquinaestados.MaquinaEstados;
import br.usp.maquinaestados.Transicao;
import br.usp.semantico.Semantico;
import br.usp.semantico.Token;

/**
 *
 * @author nathalia
 */
public class MaquinaFator extends SubMaquina{

    public MaquinaFator() {
        estadoInicial = 0;

        tabelaTransicoes.add(new Transicao(0, 4, "numero", "ignora"));
        tabelaTransicoes.add(new Transicao(0, 4, "true", "ignora"));
        tabelaTransicoes.add(new Transicao(0, 4, "false", "ignora"));
        tabelaTransicoes.add(new Transicao(0, 4, "identificador", "chamaIdentificador"));
        tabelaTransicoes.add(new Transicao(0, 1, "(", "ignora"));
        tabelaTransicoes.add(new Transicao(0, 3, "-", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "(", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(1, 2, "-", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(1, 2, "true", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(1, 2, "false", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(1, 2, "identificador", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(1, 2, "numero", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(2, 4, ")", "ignora"));
        tabelaTransicoes.add(new Transicao(3, 4, "(", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(3, 4, "-", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(3, 4, "true", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(3, 4, "false", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(3, 4, "identificador", "chamaExpressao"));
        tabelaTransicoes.add(new Transicao(3, 4, "numero", "chamaExpressao"));

        tabelaEstadosAceitacao.add(4);

        maquina = new MaquinaEstados(tabelaTransicoes, estadoInicial, tabelaEstadosAceitacao);
        maquina.setNome("Sintático-Fator");
    }

//    public boolean trataToken(Simbolo token) {
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
//            System.out.println("Fator: Não existe ação definida!");
//        }
//
//        return retorno;
//    }

	public boolean trataToken(Simbolo token, Semantico semantico) {
        boolean retorno = false;

        String acao = maquina.transita(token.getTipo());
        if(acao.equals("ignora"))
        {
			// ABRE_PARENTESES
			if(token.getNome().equals(Token.ABRE_PARENTESES)) {
				semantico.ExpressaoEmpilhaOperadores(token);
			}

			// FECHA_PARENTESES
			if(token.getNome().equals(Token.FECHA_PARENTESES)) {
				semantico.ExpressaoX5();
			}

			// MENOS
			if(token.getNome().equals(Token.MENOS)) {
				semantico.ExpressaoX7();
			}

			// FECHA_PARENTESES
			if(token.getNome().equals(Token.FECHA_PARENTESES)) {
				semantico.ExpressaoX5();
			}

			// NUMERO
			if(semantico.ehNumero(token.getNome())) {
				semantico.ExpressaoEmpilhaOperandos(token);
			}

            return true;
        }
        else if(acao.equals("chamaIdentificador"))
        {
            subMaquina = new MaquinaIdentificador();
            retorno = subMaquina.processaToken(token, semantico);
        }
        else if(acao.equals("chamaExpressao"))
        {
            subMaquina = new MaquinaExpressao();
            retorno = subMaquina.processaToken(token, semantico);
        }
        else
        {
            System.out.println("Fator: Não existe ação definida!");
        }

        return retorno;
	}

}
