package br.usp.sintatico;

import br.usp.lexico.Simbolo;
import br.usp.maquinaestados.MaquinaEstados;
import br.usp.maquinaestados.Transicao;
import br.usp.semantico.Semantico;

/**
 *
 * @author nathalia
 */
public class MaquinaPrograma extends SubMaquina{

    public MaquinaPrograma() {
        estadoInicial = 0;
        tabelaTransicoes.add(new Transicao(0, 1, "program", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 1, "function", "chamaFuncao"));
        tabelaTransicoes.add(new Transicao(1, 1, "if", "chamaCondicional"));
        tabelaTransicoes.add(new Transicao(1, 1, "while", "chamaIteracao"));
        tabelaTransicoes.add(new Transicao(1, 1, "for", "chamaIteracao"));
        tabelaTransicoes.add(new Transicao(1, 3, "int", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(1, 3, "float", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(1, 3, "string", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(1, 3, "boolean", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(1, 3, "input", "chamaEntrada"));
        tabelaTransicoes.add(new Transicao(1, 3, "output", "chamaSaida"));
        tabelaTransicoes.add(new Transicao(1, 3, "identificador", "chamaAtribuicao"));
        tabelaTransicoes.add(new Transicao(1, 4, "end", "ignora"));
        tabelaTransicoes.add(new Transicao(3, 1, ";", "ignora"));

        tabelaEstadosAceitacao.add(4);

        maquina = new MaquinaEstados(tabelaTransicoes, estadoInicial, tabelaEstadosAceitacao);
        maquina.setNome("Sintático-Programa");
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
//        else if(acao.equals("chamaFuncao"))
//        {
//            subMaquina = new MaquinaFuncao();
//            retorno = subMaquina.processaToken(token);
//        }
//        else if(acao.equals("chamaDeclaracao"))
//        {
//            subMaquina = new MaquinaDeclaracao();
//            retorno = subMaquina.processaToken(token);
//        }
//        else if(acao.equals("chamaEntrada"))
//        {
//            subMaquina = new MaquinaEntrada();
//            retorno = subMaquina.processaToken(token);
//        }
//        else if(acao.equals("chamaSaida"))
//        {
//            subMaquina = new MaquinaSaida();
//            retorno = subMaquina.processaToken(token);
//        }
//        else if(acao.equals("chamaCondicional"))
//        {
//            subMaquina = new MaquinaCondicional();
//            retorno = subMaquina.processaToken(token);
//        }
//        else if(acao.equals("chamaIteracao"))
//        {
//            subMaquina = new MaquinaIteracao();
//            retorno = subMaquina.processaToken(token);
//        }
//        else if(acao.equals("chamaAtribuicao"))
//        {
//            subMaquina = new MaquinaAtribuicao();
//            retorno = subMaquina.processaToken(token);
//        }
//        else
//        {
//            System.out.println("Programa: Não existe ação definida!");
//        }
//
//        return retorno;
//    }

	    public boolean trataToken(Simbolo token, Semantico semantico) {
        boolean retorno = false;

        String acao = maquina.transita(token.getTipo());
        if(acao.equals("ignora"))
        {
			// executo a ação semântica de incialização do processamento
			if(token.getNome().equals("program")) {
				semantico.Inicializar();
			}

			// executo a ação semântica de finalização do processamento
			if(token.getNome().equals("end")) {
				semantico.Encerrar();
			}
            return true;
        }
        else if(acao.equals("chamaFuncao"))
        {
            subMaquina = new MaquinaFuncao();
            retorno = subMaquina.processaToken(token, semantico);
        }
        else if(acao.equals("chamaDeclaracao"))
        {
            subMaquina = new MaquinaDeclaracao();
            retorno = subMaquina.processaToken(token, semantico);
        }
        else if(acao.equals("chamaEntrada"))
        {
            subMaquina = new MaquinaEntrada();
            retorno = subMaquina.processaToken(token, semantico);
        }
        else if(acao.equals("chamaSaida"))
        {
            subMaquina = new MaquinaSaida();
            retorno = subMaquina.processaToken(token, semantico);
        }
        else if(acao.equals("chamaCondicional"))
        {
            subMaquina = new MaquinaCondicional();
            retorno = subMaquina.processaToken(token, semantico);
        }
        else if(acao.equals("chamaIteracao"))
        {
            subMaquina = new MaquinaIteracao();
            retorno = subMaquina.processaToken(token, semantico);
        }
        else if(acao.equals("chamaAtribuicao"))
        {
            subMaquina = new MaquinaAtribuicao();
            retorno = subMaquina.processaToken(token, semantico);
        }
        else
        {
            System.out.println("Programa: Não existe ação definida!");
        }

        return retorno;
    }
}
