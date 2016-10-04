package com.tag.replacement_algorithm;

import com.tag.n_way_associative_cache.Block;
import com.tag.n_way_associative_cache.CacheSet;

/**
 * Interface used to create replacement algorithms utilizing the same cache structure. 
 * 
 * @author quantumDrop
 * @param <K> Generic type to represent Key's
 * @param <V> Generic type to represent Value's
 */
public interface ReplacementAlgorithm<K,V>{
    public Block<K,V> replacementAlgorithm(CacheSet set, Block<K,V> block);
}
