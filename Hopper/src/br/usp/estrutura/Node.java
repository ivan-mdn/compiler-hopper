package br.usp.estrutura;

public class Node {    
    
    private Object object;
    private int position;
    public Node next;
    
    public Node(Object newObject, int position) {
        object = newObject;
        this.position = position;
        next = null;
    }            
    
    public Node(Object newObject) {
        object = newObject;
        next = null;
    }
    
    public Object getObject() {
        return object;
    }
    
    public void setObject(Object newObject) {
        object = newObject;
    }        
    
    public int getPosition() {
        return position;
    }
}
