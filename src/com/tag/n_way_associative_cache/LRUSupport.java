package com.tag.n_way_associative_cache;
/**
 * Interface to allow the user to interact with the NWayAssociativeCache's LRU implementation.
 * @author quantumDrop
 */
public interface LRUSupport<K,V>{    
    public Block<K,V> removeLast();
}
