package com.tag.n_way_associative_cache;

import com.tag.replacement_algorithm.LRU;
import com.tag.replacement_algorithm.ReplacementAlgorithm;
import java.util.HashMap;

/**
 * This CacheSet abstract class allows the user to create their own data structure for the sets within the cache.
 * A map is created which maps Keys to Block locations so that it may be (optionally) utilized within a users custom set structure.
 * 
 * @author quantumDrop
 * @param <K> Generic type to represent Key's
 * @param <V> Generic type to represent Value's
 */
public abstract class CacheSet<K,V> {
    protected final Integer numWays;
    protected ReplacementAlgorithm<K,V> repAlg;
    private HashMap<K,Block<K,V>> blockLocationMap;
    
    /**
     *
     * @param numWays The number of ways the cache was split. (aka the set size)
     * @param repAlg the replacement algorithm for this set
     */
    public CacheSet(Integer numWays, ReplacementAlgorithm repAlg){
        this.numWays = numWays;
        this.repAlg = (repAlg != null) ? repAlg : new LRU();
        blockLocationMap = new HashMap<>();
    }
    
    /**
     * The get function returns the value associated with the key argument.
     * 
     * @param key The key of generic type K to access the correct set id
     * @return The value associated with the key provided. If the key does not exist within the cache the <code>null</code> is returned.
     */
    public V get(K key){
        if(blockLocationMap.containsKey(key)){
            Block<K,V> block = blockLocationMap.get(key);
            updateCacheStructure(block);
            return block.getValue();
        }
        return null;
    }
    
    /**
     * 
     * @param key Generic type to represent Key's
     * @param value Generic type to represent Value's 
     */
    public void add(K key, V value){
        Block<K,V> blockToAdd = new Block(key, value);
        this.replaceIfFull(blockToAdd); //check if the cache is full. 
        this.updateMap(key, value);    //adds new value to map.
    }
    
    /**
     * 
     * @param key Generic type to represent Key's
     * @param value Generic type to represent Value's 
     */
    public void updateMap(K key, V value){
        Block<K,V> blockToAdd;
        
        if(blockLocationMap.containsKey(key)){  //if the key exists in the map
            blockToAdd = blockLocationMap.get(key); //get the ref to the block to update
            blockToAdd.setValue(value); //update the blocks value. 
            this.updateCacheStructure(blockToAdd); //updates element in data structure.
        } else { // if the key does not exist in the map
            blockToAdd = new Block(key,value);  //create new block
            blockLocationMap.put(key, blockToAdd);
            this.add(blockToAdd); //adds new element to data structure.
        }
    }

    /**
     *
    * @param block Key-Value pair that exists as a row within a set of a cache
     * @see Block
     */
    public void replaceIfFull(Block<K,V> block){
        if(this.isFull()){
            Block<K,V> blockToRemove = this.replace(block); //Removes the element from the structure. Returns the block that was removed so that it can be removed from the map as well.
            blockLocationMap.remove(blockToRemove.getKey()); //removes element from map.
        }
    }
    
    /**
     * @return True if this set is full, False otherwise.
     */
    public abstract Boolean isFull();
    /**
     * The add function adds the arguments key and value as a block within the set called upon.
     * 
     * @param block Key-Value pair that exists as a row within a set of a cache
     * @see Block
     */
    public abstract void add(Block<K,V> block);
    /**
     * Replaces a value in our cache. This is called when the Cache is full. 
     * 
     * @param block
     * @return The block that the replacement algorithm removed.
     */
    public abstract Block<K,V> replace(Block<K,V> block);
    /**
     * Updates the cache structure.
     * The DefaultSet and DoublyLinkedListSet use this to maintain the time ascending order. 
     * User implementations may use this to maintain their own order.
     * 
     * @param block Key-Value pair that exists as a row within a set of a cache
     * @see Block
     */
    public abstract void updateCacheStructure(Block<K,V> block);
}
