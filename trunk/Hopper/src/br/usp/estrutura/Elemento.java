package br.usp.estrutura;

public class Elemento {
    public Object elemento;
    public Elemento prox;
    
    public Elemento(Object elemento) {        
        this.setElemento(elemento);
        prox = null;
    }

    public Object getElemento() {
        return elemento;
    }

    public void setElemento(Object elemento) {
        this.elemento = elemento;
    }
            
}
