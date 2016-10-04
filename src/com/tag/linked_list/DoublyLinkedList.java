package com.tag.linked_list;

/**
 * Doubly linked list implementation
 * @author quantumDrop
 * @param <D> Generic type D to represent data.
 */
public class DoublyLinkedList<D> {
    private Node<D> head;
    private Node<D> tail;

    /**
     * Adds the node to the beginning of the linked list
     * @param node Contains generic type D
     * @see Node
     */
    public void addFirst(Node<D> node){
        if(head == null){
            this.head = node;
            this.head.setNext(null);
            this.head.setPrev(null);
            
            this.tail = node;
            this.tail.setNext(null);
            this.tail.setPrev(null);
        } else {
            head.setPrev(node);
            node.setPrev(null);
            node.setNext(head);
            this.head = node;
        }
    }
    
    /**
     * Adds the node to the end of the linked list
     * @param node Contains generic type D
     * @see Node
     */
    public void addLast(Node<D> node){
        if(head == null){
            this.head = node;
            this.head.setNext(null);
            this.head.setPrev(null);
            
            this.tail = node;
            this.tail.setNext(null);
            this.tail.setPrev(null);
        } else {
            node.setPrev(tail);
            node.setNext(null);
            this.tail = node;
        }
    }
    
    /**
     * 
     * @param n The element to be found
     * @return The Node of generic type D that was found. 
     */
    public Node<D> get(D n){
        Node<D> nodeToBeFound = (Node<D>) n;
        Node<D> pointer = head;
        while(pointer.getNext() != null){
            if(pointer.equals(nodeToBeFound)){
                return pointer;
            }
        }
        return null;
    }
    
    /**
     * Removes the Node from the linked list
     * Performs a removal in O(1) time.
     * 
     * @param nodeRef A handle to the node to be removed from the linked list.
    */
    public void removeFast(Node<D> nodeRef){
        if(nodeRef.getPrev() == null && nodeRef.getNext() != null){ //head node
            nodeRef.getNext().setPrev(null);
            this.head = nodeRef.getNext();
            if(this.head.getNext() == null){
                this.tail = this.head;
            }
        } else if(nodeRef.getPrev() != null && nodeRef.getNext() != null){ //normal node
            nodeRef.setNext(nodeRef.getPrev());
            nodeRef.setPrev(nodeRef.getNext());
        } else if(nodeRef.getPrev() != null && nodeRef.getNext() == null){  //tail node
            nodeRef.getPrev().setNext(null);
            this.tail = nodeRef.getPrev();
            if(this.tail.getPrev() == null){
                this.head = this.tail;
            }
        } else { //else single element in list 
            this.head = null;
            this.tail = null;
        }
        
    }
    
    /**
     * @return The first element in the linked list.
     */
    public D removeFirst(){
        Node<D> node;
        
        if(this.head != null && this.head.getNext() != null){ //HEAD node
            node = this.head;
            Node<D> temp = this.head.getNext();
            temp.setPrev(null);
        } else { //single element in list 
            node = this.tail;
            this.head = null;
            this.tail = null;
        }
        
        return node.getData();
    }
    
    /**
     * @return The last element in the linked list.
     */
    public D removeLast(){
        Node<D> node;
        if(this.tail != null && this.tail.getPrev() != null){ //TAIL node
            node = this.tail;
            Node<D> temp = this.tail.getPrev();
            temp.setNext(null);
            this.tail = temp;
        } else { //single element in list 
            node = this.head;
            this.head = null;
            this.tail = null;
        }
        return node.getData();
    }
    /**
     * 
     * @return The head of the list
     */
    public Node<D> getHead() {
        return head;
    }

    /**
     * Sets the head of the list.
     * @param head The head of the list
     */
    public void setHead(Node<D> head) {
        this.head = head;
    }

    /**
     * 
     * @return The tail of the list
     */
    public Node<D> getTail() {
        return tail;
    }

    /**
     * Sets the tail of the list.
     * @param tail The tail of the list.
     */
    public void setTail(Node<D> tail) {
        this.tail = tail;
    }
}
