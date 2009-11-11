package br.usp.sintatico;

import br.usp.lexico.Simbolo;
import br.usp.maquinaestados.MaquinaEstados;
import br.usp.maquinaestados.Transicao;
import java.util.ArrayList;

/**
 *
 * @author nathalia
 */
public abstract class SubMaquina {

    public MaquinaEstados maquina;
    public SubMaquina subMaquina = null;
    public ArrayList<Transicao> tabelaTransicoes = new ArrayList<Transicao>();
    public ArrayList tabelaEstadosAceitacao = new ArrayList();
    public int estadoInicial;

    public abstract boolean trataToken(Simbolo token);

    public boolean processaToken(Simbolo token)
    {
        boolean retorno = false;

        if(subMaquina != null)
        {
            retorno = subMaquina.processaToken(token);

            if(!retorno)
            {
                if(subMaquina.maquina.isEstadoAceitacao())
                {
                    subMaquina = null;
                    retorno = true;
                }
                else
                {
                    System.out.println("Nao processou token e nao eh estado de aceitacao.");
                }
            }
        }
        else
        {
            retorno = trataToken(token);
        }

        return retorno;
    }
}
