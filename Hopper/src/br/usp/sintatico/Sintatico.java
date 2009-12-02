package br.usp.sintatico;

import br.usp.lexico.Simbolo;
import br.usp.semantico.Semantico;
import java.util.Hashtable;

/**
 *
 * @author nathalia, Bruno Grisi
 */
public class Sintatico {

    MaquinaPrograma maquinaPrograma = new MaquinaPrograma();
	Semantico semantico;
	Hashtable tabelaSimbolos;

	public Sintatico(Hashtable tabelaSimbolos) {
		this.tabelaSimbolos = tabelaSimbolos;
		this.semantico = new Semantico(tabelaSimbolos);
	}

    public boolean processaToken(Simbolo token)
    {
        if(maquinaPrograma.processaToken(token, semantico))
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
				//deu erro no Sintático
				if (this.semantico.getErroSintatico() == 0) {		// POG!!! para as saídas não acumularem "erros repetidos"
					System.out.println("Erro no sintatico!");
					this.getSemantico().setErroSintatico(1);
				}
                return false;
            }
        }
    }

	public Semantico getSemantico() {
        return semantico;
    }

    public void setSemantico(Semantico semantico) {
        this.semantico = semantico;
    }

}
