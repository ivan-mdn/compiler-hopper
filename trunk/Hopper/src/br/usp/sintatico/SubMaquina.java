package br.usp.sintatico;

import br.usp.lexico.Simbolo;
import br.usp.maquinaestados.MaquinaEstados;

/**
 *
 * @author nathalia
 */
public abstract class SubMaquina {

    public MaquinaEstados maquina;
    public SubMaquina subMaquina = null;

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
