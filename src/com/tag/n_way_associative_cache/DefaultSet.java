package com.tag.n_way_associative_cache;

import com.tag.replacement_algorithm.ReplacementAlgorithm;
import java.util.Deque;
import java.util.LinkedList;

/**
 * The DefaultSet utilizes Java's LinkedList to iteract with the cache
 * Provides an O(N) way to interact with the cache. N representing the number of elements in the set. 
 * 
 * @author quantumDrop
 * @param <K> Generic type to represent Key's
 * @param <V> Generic type to represent Value's
 */
public class DefaultSet<K,V> extends CacheSet<K,V> implements LRUSupport<K, V>, MRUSupport<K, V>{
    private Deque<Block<K,V>> blocks;
    
    /**
     * DefaultSet Constructor
     * @param numWays The number of ways the cache is split into.
     * @param repAlg The replacement algorithm used for the cache.
     */
    public DefaultSet(Integer numWays, ReplacementAlgorithm repAlg){
        super(numWays, repAlg);
        blocks = new LinkedList<>();
    }
    
    /**
     * Updates the cache structure to maintain time ascending order
     * 
     * @param block Key-Value pair that exists as a row within a set of a cache
     * @see Block
     */
    @Override
    public void updateCacheStructure(Block<K,V> block){
        if(block != null){
            blocks.remove(block);  //<--Javas default doubly linked list implementation causes this removal to take O(N) time.
            blocks.addFirst(block);//<--By adding to the beginning of our deque we maintain an order by time (ascending).
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
        blocks.addFirst(block);
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
        return this.blocks.size() >= numWays;
    }
    
    /**
     * @return the block structure for this set (Used for testing)
     */
    public Deque<Block<K,V>> getBlocks(){
        return this.blocks;
    }

    /**
     * Overrides the MRUSupport interface.
     * @return The Block that was removed
     */
    @Override
    public Block<K, V> removeLast() {
        return blocks.removeLast();
    }

    /**
     * Overrides the MRUSupport interface.
     * @return The Block that was removed
     */
    @Override
    public Block<K, V> removeFirst() {
        return blocks.removeFirst();
    }
    
}
