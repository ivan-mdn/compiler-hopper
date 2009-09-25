package br.usp.maquinaestados;

/**
 *
 * @author nathalia
 */
public class Transicao {

    public Transicao(int estadoAtual, int proximoEstado, String simbolo, String acao) {
        this.estadoAtual = estadoAtual;
        this.proximoEstado = proximoEstado;
        this.simbolo = simbolo;
        this.acao = acao;
    }

    private int estadoAtual;
    private int proximoEstado;
    private String simbolo;
    private String acao;

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public int getEstadoAtual() {
        return estadoAtual;
    }

    public void setEstadoAtual(int estadoAtual) {
        this.estadoAtual = estadoAtual;
    }

    public int getProximoEstado() {
        return proximoEstado;
    }

    public void setProximoEstado(int proximoEstado) {
        this.proximoEstado = proximoEstado;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

}
