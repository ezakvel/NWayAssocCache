package com.tag.replacement_algorithm;

import com.tag.n_way_associative_cache.Block;
import com.tag.n_way_associative_cache.CacheSet;
import com.tag.n_way_associative_cache.MRUSupport;

/**
 * Most Recently Used (MRU) replacement algorithm.
 * @author quantumDrop
 * @param <K> Generic type to represent Key's
 * @param <V> Generic type to represent Value's
 */
public class MRU<K,V> implements ReplacementAlgorithm<K,V>{

    /**
     * Most recently used replacement algorithm which maintains the caches time sensitive order by removing the most recently added item from the cache by removing the first element. Then adding the new item to the beginning of the cache.
     * This should never return null.
     * 
     * @param set The set the MRU should be performed on.
     * @param block Key-Value pair that exists as a row within a set of a cache.
     * @return The Block that was replaced. 
     * @see Block
     */
    @Override
    public Block<K,V> replacementAlgorithm(CacheSet set, Block<K,V> block) {
        if(set instanceof MRUSupport){
            MRUSupport mruSet = (MRUSupport) set;
            Block<K,V> b = mruSet.removeFirst();
            return b;
        } else {
            return null;
        }
    }

}
