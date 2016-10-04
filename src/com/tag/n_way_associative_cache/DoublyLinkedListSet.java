package com.tag.n_way_associative_cache;

import com.tag.linked_list.DoublyLinkedList;
import com.tag.linked_list.Node;
import com.tag.replacement_algorithm.ReplacementAlgorithm;
import java.util.HashMap;

/**
 * The DoublyLinkedListSet provides an O(1) way to interact with the cache.
 * 
 * @author quantumDrop
 * @param <K> Generic type to represent Key's
 * @param <V> Generic type to represent Value's
 */
public class DoublyLinkedListSet<K,V> extends CacheSet<K,V> implements LRUSupport, MRUSupport{
    private DoublyLinkedList<Block<K,V>> blocks;
    private HashMap<K, Node<Block<K,V>>> nodeMap;
    
    /**
     * DoublyLinkedListSet Constructor
     * @param numWays The number of ways the cache is split into.
     * @param repAlg The replacement algorithm used for the cache.
     */
    public DoublyLinkedListSet(Integer numWays, ReplacementAlgorithm repAlg){
        super(numWays, repAlg);
        blocks = new DoublyLinkedList<>();
        nodeMap = new HashMap<>();
    }
    
    /**
     * Updates the cache structure
     * 
     * @param block Key-Value pair that exists as a row within a set of a cache
     * @see Block
     */
    @Override
    public void updateCacheStructure(Block<K,V> block){
        if(block != null){
            Node<Block<K,V>> nodeBlockReference = nodeMap.get(block.getKey());
            blocks.removeFast(nodeBlockReference); //<--My doubly linked list implementation causes this removal to take O(1) time ;). 
            nodeMap.remove(nodeBlockReference);
//            blocks.remove(block);  //<--Javas default doubly linked list implementation causes this removal to take O(N) time.
            blocks.addFirst(nodeBlockReference);//<--By adding to the beginning of our doubly linked list we maintain an order by time (ascending).
        }
    }
    
    /**
     * The add function adds the arguments key and value as a block within the set called upon.
     * 
     * @param block Key-Value pair that exists as a row within a set of a cache
     * @see Block
     */
    @Override
    public void add(Block<K,V> block){
        Node<Block<K,V>> nodeBlock = new Node<>(block, null, null);
        blocks.addFirst(nodeBlock);//<--By adding to the beginning of our deque we maintain an order by time (ascending).
        
        nodeMap.put(block.getKey(), nodeBlock);
    }
    
    /**
     * Replaces a value in our cache. This is called when the Cache is full. 
     * 
     * @param block
     * @return The block that the replacement algorithm removed. CacheSet uses this to remove it from the map.
     */
    @Override
    public Block<K,V> replace(Block<K,V> block){
        Block<K,V> blockToRemove = this.repAlg.replacementAlgorithm(this, block);
        return blockToRemove;
    }
    
    @Override
    public Boolean isFull(){
        return nodeMap.size() >= numWays;
//        return this.blocks.size() >= numWays;
    }
    
    /**
     * Adds to the beginning of the list.
     * @param n 
     */
    public void addFirst(Node<Block<K,V>> n){
        blocks.addFirst(n);
    }
    /**
     * Adds to the end of the list.
     * @param n 
     */
    public void addLast(Node<Block<K,V>> n){
        blocks.addLast(n);
    }
    
    /**
     * @return The size of the map.
     */
    public Integer size(){
        return nodeMap.size();
    }
    
    /**
     * @return the block structure for this set (Used for testing)
     */
    public DoublyLinkedList<Block<K, V>> getBlocks(){
        return this.blocks;
    }

    /**
     * Overrides the LRUSupport interface.
     * @return the Block that was removed.
     */
    @Override
    public Block<K,V> removeLast() {
        return this.blocks.removeLast();
    }

    /**
     * Overrides the MRUSupport interface.
     * @return The Block that was removed
     */
    @Override
    public Block<K,V> removeFirst() {
        return this.blocks.removeFirst();
    }
    
}
