package br.usp.lexico;

/**
 *
 * @author nathalia, Bruno Grisi
 */
public class Simbolo {

    private int codigo;
    private String nome;
    private String tipo;						// valores possíveis: IDENTIFICADOR, NUMERO, PALAVRA_RESERVADA.toString() (seu próprio nome)
	private String categoria;					// valores possíveis: FUNCAO, VETOR, PARAMETRO, VARIAVEL
    private boolean declarado = false;			// verifica se o símbolo já foi declarado

	public final static String FUNCAO		= "FUNCAO   ";
    public final static String VETOR		= "VETOR    ";
    public final static String PARAMETRO	= "PARAMETRO";
    public final static String VARIAVEL		= "VARIAVEL";

    public Simbolo(int codigo, String nome, String tipo) {
        this.codigo = codigo;
        this.nome = nome;
        this.tipo = tipo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

	public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public boolean getDeclarado() {
        return declarado;
    }

    public void setDeclarado() {
        this.declarado = true;
    }
}
