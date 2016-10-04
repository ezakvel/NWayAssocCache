package com.tag.n_way_associative_cache;

import com.tag.exceptions.CacheSizeConstructionException;
import com.tag.replacement_algorithm.LRU;
import com.tag.replacement_algorithm.ReplacementAlgorithm;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * The NWayAssociativeCache creates the cache divided into the number of ways passed.
 * @author quantumDrop
 * @param <K> Generic type to represent Key's
 * @param <V> Generic type to represent Value's
 */
public class NWayAssociativeCache<K,V> {
    private final int numWays;   //number of ways for our n-way associative cache
    private final int numSets; //number of sets per cache
    private ReplacementAlgorithm repAlg; //the replacement algorithm to be used (i.e. LRU, MRU, other)
    private List<CacheSet<K,V>> cache;
    
    /**
     * Constructs an N-Way Associative Cache with the default parameters
     * 
     * @param setClass Client will pass null for default set class or their own custom set implementation
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.NoSuchMethodException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public NWayAssociativeCache(Class setClass) throws InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
        int cacheSize = 32;
        this.numWays = 1;
        this.numSets = cacheSize / this.numWays;
        this.repAlg = new LRU();
        
        initCache(setClass, numWays, this.repAlg);
        
    }
    
    /**
     * Constructs an N-Way Associative Cache with the default parameters
     * 
     * @param numWays The number of ways a set should be divided.
     * @param setClass Client will pass null for default set class or their own custom set implementation
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.NoSuchMethodException
     * @throws java.lang.reflect.InvocationTargetException
     * @throws com.tag.exceptions.CacheSizeConstructionException
     */   
    public NWayAssociativeCache(Class setClass, int numWays) throws CacheSizeConstructionException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
        int cacheSize = 32;
        if(numWays<=cacheSize && numWays > 0){
            this.numWays = numWays;
            this.numSets = cacheSize / this.numWays;
            this.repAlg = new LRU();

            initCache(setClass, numWays, this.repAlg);
        } else {
            throw new CacheSizeConstructionException("Cannot instantiate cache with parameters specified.");
        }
        

    }
    
    /**
     * Constructs an N-Way Associative Cache with the default parameters found in the configuration file.
     * 
     * @param numWays The number of ways a set should be divided.
     * @param cacheSize The cache's size
     * @param setClass Client will pass null for default set class or their own custom set implementation
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.NoSuchMethodException
     * @throws java.lang.reflect.InvocationTargetException
     * @throws com.tag.exceptions.CacheSizeConstructionException
     */
    public NWayAssociativeCache(Class setClass, int numWays, int cacheSize) throws CacheSizeConstructionException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
        if(numWays<=cacheSize && numWays > 0 && cacheSize > 0){
            this.numWays = numWays;
            this.numSets = cacheSize / numWays;
            this.repAlg = new LRU();
            
            initCache(setClass, numWays, this.repAlg);
        } else {
            throw new CacheSizeConstructionException("Cannot instantiate cache with parameters specified.");
        }
        

    }

    /**
     * Constructs an N-Way Associative Cache
     * 
     * @param numWays The number of ways a set should be divided.
     * @param cacheSize The cache's size
     * @param replacementAlgorithm The replacement algorithm that should be used for this cache
     * @param setClass Client will pass null for default set class or their own custom set implementation
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.NoSuchMethodException
     * @throws java.lang.reflect.InvocationTargetException
     * @throws com.tag.exceptions.CacheSizeConstructionException
     */
    public NWayAssociativeCache(Class setClass, int numWays, int cacheSize, ReplacementAlgorithm replacementAlgorithm) throws CacheSizeConstructionException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
        if(numWays<=cacheSize && numWays > 0 && cacheSize > 0){
            this.numWays = numWays;
            this.numSets = cacheSize / numWays;
            this.repAlg = replacementAlgorithm;

            initCache(setClass, numWays, replacementAlgorithm);
        } else {
            throw new CacheSizeConstructionException("Cannot instantiate cache with parameters specified.");
        }
        
    }

    /**
     * Initializes the cache as a new CacheSet object of Blocks (with generic key values) representing sets.
     * 
     * @param numWays The number of ways a set should be divided.
     * @param repAlg The replacement algorithm that should be used for this cache
     * @param c Client will pass null for default set class or their own custom set implementation
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.NoSuchMethodException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public final void initCache(Class c, int numWays, ReplacementAlgorithm repAlg) throws InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
        if(c == null){ c = DefaultSet.class; }
        
        this.cache = new ArrayList<>();
        for(int i=0; i<numSets; i++){
            CacheSet<K,V> newSet = (CacheSet<K,V>) c.getDeclaredConstructor(Integer.class, ReplacementAlgorithm.class).newInstance(numWays, repAlg);
            this.cache.add(newSet);
        }
    }
    
    /**
     * 
     * @param key Generic type to represent Key
     * @return V Generic type to represent value found
     */
    public V get(K key){
        int setId = Math.abs(key.hashCode()%numSets);
        return this.cache.get(setId).get(key);
    }
    
    /**
     * 
     * @param key Generic type to represent Key's
     * @param value Generic type to represent Value's 
     */
    public void add(K key, V value){
        int setId = Math.abs(key.hashCode()%numSets);
        CacheSet<K, V> set = this.cache.get(setId);
        set.add(key, value);
    }
    
    
    /**
     * @return The number of ways a set can be divided
     */
    public int getNumWays() {
        return numWays;
    }

    /**
     * @return The replacement algorithm for this cache.
     */
    public ReplacementAlgorithm getRepAlg() {
        return repAlg;
    }

    /** 
     * @param repAlg the replacement algorithm for this cache.
     */
    public void setRepAlg(ReplacementAlgorithm repAlg) {
        this.repAlg = repAlg;
    }
    
    /**
     * @return The replacement algorithm for this cache.
     */
    public List<CacheSet<K,V>> getCache() {
        return this.cache;
    }

    /** 
     * @param repAlg the replacement algorithm for this cache.
     */
    public void setCache(ReplacementAlgorithm repAlg) {
        this.repAlg = repAlg;
    }

    /**
     * @return The number of sets for this cache.
     */
    public int getNumSets() {
        return numSets;
    }
    
    /**
     * @return The total cache size
     */
    public int getCacheSize(){
        return this.numSets * this.numWays;
    }
    
    
}
