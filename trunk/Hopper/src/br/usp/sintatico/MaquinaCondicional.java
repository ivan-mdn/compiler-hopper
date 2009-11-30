package br.usp.sintatico;

import br.usp.lexico.Simbolo;
import br.usp.maquinaestados.MaquinaEstados;
import br.usp.maquinaestados.Transicao;
import br.usp.semantico.Semantico;

/**
 *
 * @author nathalia
 */
public class MaquinaCondicional extends SubMaquina{

    public MaquinaCondicional() {
        estadoInicial = 0;
        tabelaTransicoes.add(new Transicao(0, 1, "if", "ignora"));
        tabelaTransicoes.add(new Transicao(1, 2, "(", "ignora"));
        tabelaTransicoes.add(new Transicao(2, 3, "(", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(2, 3, "-", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(2, 3, "true", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(2, 3, "false", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(2, 3, "identificador", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(2, 3, "numero", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(2, 3, "not", "chamaCondicao"));
        tabelaTransicoes.add(new Transicao(3, 4, ")", "ignora"));
        tabelaTransicoes.add(new Transicao(4, 5, "then", "ignora"));
        tabelaTransicoes.add(new Transicao(5, 5, "if", "chamaCondicional"));
        tabelaTransicoes.add(new Transicao(5, 5, "while", "chamaIteracao"));
        tabelaTransicoes.add(new Transicao(5, 5, "for", "chamaIteracao"));
        tabelaTransicoes.add(new Transicao(5, 5, "int", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(5, 6, "float", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(5, 6, "string", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(5, 6, "boolean", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(5, 6, "input", "chamaEntrada"));
        tabelaTransicoes.add(new Transicao(5, 6, "output", "chamaSaida"));
        tabelaTransicoes.add(new Transicao(5, 6, "identificador", "chamaAtribuicao"));
        tabelaTransicoes.add(new Transicao(5, 9, "endif", "ignora"));
        tabelaTransicoes.add(new Transicao(5, 7, "else", "ignora"));
        tabelaTransicoes.add(new Transicao(6, 5, ";", "ignora"));
        tabelaTransicoes.add(new Transicao(7, 7, "if", "chamaCondicional"));
        tabelaTransicoes.add(new Transicao(7, 7, "while", "chamaIteracao"));
        tabelaTransicoes.add(new Transicao(7, 7, "for", "chamaIteracao"));
        tabelaTransicoes.add(new Transicao(7, 8, "int", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(7, 8, "float", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(7, 8, "string", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(7, 8, "boolean", "chamaDeclaracao"));
        tabelaTransicoes.add(new Transicao(7, 8, "input", "chamaEntrada"));
        tabelaTransicoes.add(new Transicao(7, 8, "output", "chamaSaida"));
        tabelaTransicoes.add(new Transicao(7, 8, "identificador", "chamaAtribuicao"));
        tabelaTransicoes.add(new Transicao(8, 7, ";", "ignora"));
        tabelaTransicoes.add(new Transicao(7, 9, "endif", "ignora"));

        tabelaEstadosAceitacao.add(9);

        maquina = new MaquinaEstados(tabelaTransicoes, estadoInicial, tabelaEstadosAceitacao);
        maquina.setNome("Sintático-Condicional");
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
//        else if(acao.equals("chamaAtribuicao"))
//        {
//            subMaquina = new MaquinaAtribuicao();
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
//        else if(acao.equals("chamaIdentificador"))
//        {
//            subMaquina = new MaquinaIdentificador();
//            retorno = subMaquina.processaToken(token);
//        }
//        else if(acao.equals("chamaCondicao"))
//        {
//            subMaquina = new MaquinaCondicao();
//            retorno = subMaquina.processaToken(token);
//        }
//        else
//        {
//            System.out.println("Condicional: Não existe ação definida!");
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
        else if(acao.equals("chamaAtribuicao"))
        {
            subMaquina = new MaquinaAtribuicao();
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
        else if(acao.equals("chamaIdentificador"))
        {
            subMaquina = new MaquinaIdentificador();
            retorno = subMaquina.processaToken(token, semantico);
        }
        else if(acao.equals("chamaCondicao"))
        {
            subMaquina = new MaquinaCondicao();
            retorno = subMaquina.processaToken(token, semantico);
        }
        else
        {
            System.out.println("Condicional: Não existe ação definida!");
        }

        return retorno;
	}
}
