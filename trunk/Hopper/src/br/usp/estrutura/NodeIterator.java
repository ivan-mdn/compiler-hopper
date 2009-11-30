package br.usp.estrutura;

public class NodeIterator {
    
    private Node current;
    private Node first;
    
    public NodeIterator(Node node) {        
        first = node;
        current = new Node(new Object());
        current.next = node;
    }
    
    public boolean hasNext() {
        return current.next!=null;
    }
    
    public Object next() {        
        current = current.next;
        return current.getObject();
    }
    
    public void reset() {
        current = new Node(new Object());
        current.next = first;
    }
}
