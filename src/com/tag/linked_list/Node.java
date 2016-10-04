package com.tag.linked_list;

/**
 * Node class which contains a reference to the node in front of it (next) the node behind it (prev) and the data.
 * @author quantumDrop
 * @param <D> Generic type D to represent data.
 */
public class Node<D> {
    private D data;
    private Node<D> next;
    private Node<D> prev;
    
    /**
     * Node constructor
     * @param data The data for this particular node.
     * @param next The node in front of this one that it's connected to.
     * @param prev The node behind this one that it's connected to.
     */
    public Node(D data, Node<D> next, Node<D> prev){
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
    
    /**
     * 
     * @return The data for the node.
     */
    public D getData() {
        return data;
    }

    /**
     * Sets the data for the node
     * @param data The data for the node
     */
    public void setData(D data) {
        this.data = data;
    }

    /**
     * 
     * @return The node in front of this one that it's connected to.
     */
    public Node<D> getNext() {
        return next;
    }

    /**
     * Sets the node in front of this one that its connected to
     * @param next The node in front of this one that its connected to
     */
    public void setNext(Node<D> next) {
        this.next = next;
    }

    /**
     * 
     * @return The node behind this one that its connected to
     */
    public Node<D> getPrev() {
        return prev;
    }

    /**
     * Sets the node behind this one that its connected to
     * @param prev The node behind this one that its connected to
     */
    public void setPrev(Node<D> prev) {
        this.prev = prev;
    }
}
