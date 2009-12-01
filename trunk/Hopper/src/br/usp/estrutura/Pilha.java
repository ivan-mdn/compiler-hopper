package br.usp.estrutura;

public class Pilha {
    
    private Elemento topo;
    
    /** Creates a new instance of Pilha */
    public Pilha() {
        topo = null;
    }
    
    public void empilha(Object elemento) {
        
        Elemento novoElemento = new Elemento(elemento);
        
        if (pilhaVazia()) {
            novoElemento.prox = null;
        } else {//Inserções sempre feitas no topo
            novoElemento.prox = topo;
        }
        topo = novoElemento;        
    }
    
    public Object desempilha(){
        Elemento elementoTopo = topo;
        topo = elementoTopo.prox;        
        return elementoTopo.getElemento();
    }
    
    public boolean pilhaVazia(){
        if(topo==null)
            return true;
        else
            return false;
    }
    
    public void esvaziaPilha(){
        topo = null;
    }

	public Object getTopo() {
        return topo.getElemento();
    }

    public void setTopo(Elemento topo) {
        this.topo = topo;
    }
}
