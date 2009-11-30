package br.usp.semantico;

/**
 *
 * @author Bruno Grisi
 */
public class Token {
    
    /*===============================================*/
    /* CLASSIFICA��O DOS TOKENS - RETORNOS DO LEXICO */
    /*===============================================*/
    public final static String IGUAL                = "=";
    public final static String PONTO_E_VIRGULA      = ";";
    public final static String VIRGULA              = ",";
    public final static String ABRE_CHAVES          = "{";
    public final static String FECHA_CHAVES         = "}";
    public final static String ABRE_COLCHETES       = "[";
    public final static String FECHA_COLCHETES      = "]";
    public final static String ABRE_PARENTESES      = "(";
    public final static String FECHA_PARENTESES     = ")";
    public final static String EXCLAMACAO           = "!";
    public final static String MAIS                 = "+";
    public final static String MENOS                = "-";
    public final static String BARRA                = "/";
    public final static String ASTERISCO            = "*";
    public final static String ECOMERCIAL           = "&";
    public final static String BVERTICAL            = "|";
    public final static String MAIOR                = ">";
    public final static String MENOR                = "<";
    public final static String MAIOR_IGUAL          = ">=";
    public final static String MENOR_IGUAL          = "<=";
    public final static String IGUAL_IGUAL          = "==";
    public final static String EXCLAMACAO_IGUAL     = "!=";
    public final static String ECOMERCIAL_ECOMERCIAL= "&&";
    public final static String BVERTICAL_BVERTICAL  = "||";
    public final static String NUMERO               = "NUMERO";
    public final static String IDENTIFICADOR        = "IDENTIFICADOR";
    public final static String FIM_DE_ARQUIVO       = "FIM_DE_ARQUIVO";
    public final static String ERRO                 = "ERRO";
    
    private int posicao;
    private String tipo;
    private String nome;
    
    private int bloco;
    private int nivel;
    private int idNivel;
    
    private boolean inicioExpressao = false;
    private boolean fimExpressao = false;
    private String observacao = "null";
    
    public final static String DECLARACAO_FUNCAO = "DECLARACAO_FUNCAO";
    public final static String FIM_PARAMETROS_FUNCAO = "FIM_PARAMETROS_FUNCAO";
    public final static String FIM_FUNCAO = "FIM_FUNCAO";
    public final static String SCAN = "SCAN";
    public final static String PRINT = "PRINT";
    public final static String ATRIBUICAO = "ATRIBUICAO";
    public final static String ID_INICIALIZADO = "ID_INICIALIZADO";
    public final static String CHAMADA_FUNCAO = "CHAMADA_FUNCAO";
    public final static String IF = "IF";
    public final static String WHILE = "WHILE";
    public final static String DECLARACAO = "DECLARACAO";
    public final static String FIM_DECLARACAO = "FIM_DECLARACAO";
    public final static String DIVISOR = "DIVISOR";
    public final static String FIM_PARAMETROS = "FIM_PARAMETROS";
    public final static String FIM_WHILE_PARAMETROS = "FIM_WHILE_PARAMETROS";
    public final static String FIM_IF_PARAMETROS = "FIM_IF_PARAMETROS";
    public final static String FIM_IF = "FIM_IF";
    public final static String FIM_ELSIF = "FIM_ELSIF";
    public final static String FIM_ELSE = "FIM_ELSE";
    public final static String FIM_WHILE = "FIM_WHILE";
    public final static String FIM_GENERICO = "FIM_GENERICO";
    
    public Token(String tipo, String nome) {
        this.tipo = tipo;
        this.nome = nome;
    }
    
    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public int getPosicao() {
        return posicao;
    }
    public String getNome() {
        return nome;
    }
    public String getTipo() {
        return tipo;
    }

    public void setBloco(int bloco){
        this.bloco = bloco;
    }
    public void setNivel(int nivel){
        this.nivel = nivel;
    }
    public void setIdNivel(int idNivel){
        this.idNivel = idNivel;
    }
    public int getBloco(){
        return bloco;
    }
    public int getNivel(){
        return nivel;
    }
    public int getIdNivel(){
        return idNivel;
    }
    
    public void setInicioExpressao(boolean inicioExpressao){
        this.inicioExpressao = inicioExpressao;
    }
    public void setFimExpressao(boolean fimExpressao){
        this.fimExpressao = fimExpressao;
    }
    public void setObservacao(String observacao){
        this.observacao = observacao;
    }
    public boolean getInicioExpressao(){
        return inicioExpressao;
    }
    public boolean getFimExpressao(){
        return fimExpressao;
    }
    public String getObservacao(){
        return observacao;
    }
    
}
