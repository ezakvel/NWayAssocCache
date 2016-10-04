package com.tag.n_way_associative_cache;
/**
 * Interface to allow the user to interact with the NWayAssociativeCache's MRU implementation.
 * @author quantumDrop
 */
public interface MRUSupport<K,V>{    
    public abstract Block<K,V> removeFirst();
}
