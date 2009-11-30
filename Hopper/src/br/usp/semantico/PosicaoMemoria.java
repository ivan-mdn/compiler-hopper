package br.usp.semantico;

public class PosicaoMemoria {
    
    private int endereco;
    private int operacao;
    private String rotuloOperando;    
    private int operando;
    private String rotulo;
    
    public PosicaoMemoria(String rotulo, String operacao, String rotuloOperando) {
        this.endereco = 0;
        this.operacao = Conversor.hex2Int(operacao);
        this.rotuloOperando = rotuloOperando;
        this.rotulo = rotulo;
    }               
    
    public void setEndereco(String endereco) {
        this.endereco = Conversor.hex2Int(endereco);
    }

    public void setOperacao(String operacao) {
        this.operacao = Conversor.hex2Int(operacao);
    }

    public void setRotuloOperando(String rotuloOperando) {
        this.rotuloOperando = rotuloOperando;
    }

    public void setOperando(String operando) {
        this.operando = Conversor.hex2Int(operando);
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getEndereco() {
        return Conversor.int2Hex(endereco);
    }

    public String getOperacao() {
        return Conversor.int2Hex(operacao).substring(3).toUpperCase();
    }

    public String getRotuloOperando() {
        return rotuloOperando;
    }

    public String getOperando() {
        return Conversor.int2Hex(operando).substring(1,4);
    }

    public String getRotulo() {
        return rotulo;
    }
    
	@Override
    public String toString() {
        String retorno;
        retorno = getEndereco() + "\t" + getOperacao() + "\t" + getOperando() + "\t\t" + getRotulo();        
        return retorno;
    }
}
