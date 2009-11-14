package br.usp.sintatico;

import br.usp.lexico.Simbolo;


/**
 *
 * @author nathalia
 */
public class Sintatico {

    MaquinaPrograma maquinaPrograma = new MaquinaPrograma();

    public boolean processaToken(Simbolo token)
    {
        if(maquinaPrograma.processaToken(token))
        {
            //proximo token
            return true;
        }
        else
        {
            if(maquinaPrograma.maquina.isEstadoAceitacao())
            {
                //cabou legal
                return false;
            }
            else
            {
                //deu erro no programa
                System.out.println("Deu pau no sintático");
                return false;
            }
        }
    }

}
