package br.usp.estrutura;

public class Table {    
    private Node first, last;
    private Node iterator;
    private int lastPosition;
                
    public Table() {
        first = null;
        last = null;
        lastPosition = 0;
    }
    
    public boolean isEmpty() {
        if(first!=null && last!=null && first.getObject()!=null)
            return false;
        else
            return true;
    }
    
    public void add(Object newObject) {
        Node newNode = new Node(newObject, lastPosition);
        lastPosition++;
        Node temp = first;
        if (isEmpty()) {                        
            last = newNode;
            first = newNode;            
        } else {            
            last.next = newNode;
            last = newNode;  
        }                             
    }    

    
    public boolean contains(Object testObject) {        
        NodeIterator iterador = this.iterator();
        while(iterador.hasNext()) {
            if(iterador.next().equals(testObject))
                return true;
        }
        return false;        
    }
    
    public Object getElement(int position) {
        Node node = first;
        for(int i=0; i<position; i++) {
            node = node.next;
        }
        try {
            return node.getObject(); 
        } catch(Exception e) {
            return null;
        }
        
    }
    
    public void setElement(Object object, int position) {
        Node node = first;               
        for(int i=0; i<position; i++) {
            node = node.next;
        }
        node.setObject(object);
    }
    
    public void remove(int position){
//        System.out.println("$$$$$$$$$$$$$$$$$$$$$$posicao a ser removida: " + position);
        Node node = first;               
          for(int i=0; i<position-2; i++) {
            node = node.next;
        }
        node.next=null;
        last=node;
        lastPosition--;
    }
    
    public NodeIterator iterator() {
        return new NodeIterator(first);
    }      
    
    public int getSize() {
        return lastPosition;       
    }
    
}
