package com.tag.replacement_algorithm;

import com.tag.n_way_associative_cache.Block;
import com.tag.n_way_associative_cache.CacheSet;
import com.tag.n_way_associative_cache.LRUSupport;

/**
 * Least Recently Used (MRU) replacement algorithm.
 * @author quantumDrop
 * @param <K> Generic type to represent Key's
 * @param <V> Generic type to represent Value's
 */
public class LRU<K,V> implements ReplacementAlgorithm<K,V>{
    
    /**
     * Least recently used replacement algorithm which maintains the caches time sensitive order by removing the least recently added item from the cache by removing the last element. Then adding the new item to the beginning of the cache.
     * This should never return null.
     * 
     * @param set The set the LRU should be performed on
     * @param block Key-Value pair that exists as a row within a set of a cache.
     * @return The Block that was replaced. 
     * @see Block
     */
    @Override
    public Block<K,V> replacementAlgorithm(CacheSet set, Block<K,V> block) {
        if(set instanceof LRUSupport){
            LRUSupport lruSet = (LRUSupport) set;
            Block<K,V> b = lruSet.removeLast();
            return b;
        } else {
            return null;
        }
    }
    
}
