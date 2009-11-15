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
            System.out.println(subMaquina.maquina.getNome() + ": " + retorno + "/" + subMaquina.maquina.isEstadoAceitacao());
            
            if(retorno)
            {
                if(subMaquina.isEstadoAceitacao())
                {
                    System.out.println(maquina.getNome() + ": Fechou " + subMaquina.maquina.getNome());
                    subMaquina = null;
                }
            }
            else
            {
                if(subMaquina.isEstadoAceitacao())
                {
                    subMaquina = null;
                    if(!maquina.isEstadoAceitacao())
                    {
                        retorno = trataToken(token);
                    }
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

    public boolean isEstadoAceitacao()
    {
        boolean retorno = false;

        if(subMaquina != null)
        {
            retorno = subMaquina.isEstadoAceitacao();
        }
        else
        {
            retorno = maquina.isEstadoAceitacao();
        }

        return retorno;
    }
}
