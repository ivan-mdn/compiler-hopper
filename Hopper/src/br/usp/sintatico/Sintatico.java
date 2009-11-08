package br.usp.sintatico;

import br.usp.lexico.Simbolo;


/**
 *
 * @author nathalia
 */
public class Sintatico {

    MaquinaPrograma maquinaPrograma = new MaquinaPrograma();

    public void processaToken(Simbolo token)
    {
        if(maquinaPrograma.processaToken(token))
        {
            //proximo token
        }
        else
        {
            if(maquinaPrograma.maquina.isEstadoAceitacao())
            {
                //cabou legal
            }
            else
            {
                //deu erro no programa
            }
        }
    }

}
