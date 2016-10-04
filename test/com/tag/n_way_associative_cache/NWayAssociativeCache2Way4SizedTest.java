/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tag.n_way_associative_cache;

import com.tag.exceptions.CacheSizeConstructionException;
import com.tag.replacement_algorithm.LRU;
import com.tag.replacement_algorithm.MRU;
import com.tag.replacement_algorithm.ReplacementAlgorithm;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author quantumDrop
 */
public class NWayAssociativeCache2Way4SizedTest {
    private NWayAssociativeCache<Integer, Integer> mySmallCache;
    private LRU lru;
    private MRU mru;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws CacheSizeConstructionException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        mySmallCache = new NWayAssociativeCache<>(DoublyLinkedListSet.class, 2,4);
        lru = new LRU();
        mru = new MRU();
    }
    
    @After
    public void tearDown() {
    }
    
        
    @Test(expected=CacheSizeConstructionException.class)
    public void NWayAssociativeCacheNWayGreaterThanDefaultCacheSize() throws CacheSizeConstructionException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
        NWayAssociativeCache<Integer,Integer> newCache = new NWayAssociativeCache<>(null, 35);
    }
    
    @Test(expected=CacheSizeConstructionException.class)
    public void NWayAssociativeCacheNWayGreaterThanCacheSize() throws CacheSizeConstructionException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
        NWayAssociativeCache<Integer,Integer> newCache = new NWayAssociativeCache<>(null, 500, 100);
    }
    
    @Test(expected=CacheSizeConstructionException.class)
    public void NWayAssociativeCacheNegativeInstation() throws CacheSizeConstructionException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
        NWayAssociativeCache<Integer,Integer> newCache = new NWayAssociativeCache<>(null, -1);
    }
    
    @Test
    public void NWayAssociativeCacheTestInitCache() throws InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException{
        mySmallCache.initCache(null, this.mySmallCache.getNumWays(), this.mySmallCache.getRepAlg());
        for(CacheSet<Integer, Integer> set : mySmallCache.getCache()){
            assertTrue(set != null);
        }
    }
    /*** 4 way cache ***/
    /*
     * Init with LRU replacement algorithm.
    */
    @Test
    public void NWayAssociativeCacheTestInitReplacementAlgorithmLRU(){
        mySmallCache.setRepAlg(lru);
        assertTrue(mySmallCache.getRepAlg() instanceof LRU);        
    }
    /*
     * Init with MRU replacement algorithm.
    */
    @Test
    public void NWayAssociativeCacheTestInitReplacementAlgorithmMRU(){
        mySmallCache.setRepAlg(mru);
        assertTrue(mySmallCache.getRepAlg() instanceof MRU);
    }
    /*
     * Init with URA (User defined) replacement algorithm.
     * Tests if what was added is a replacementAlgorithm.
    */
    @Test
    public void NWayAssociativeCacheTestInitReplacementAlgorithmURA(){
        mySmallCache.setRepAlg(lru);
        assertTrue(mySmallCache.getRepAlg() instanceof ReplacementAlgorithm);        
    }
    


    /**
     * Tests the NWayAssociativeCache "add" function
     */    
    @Test
    public void NWayAssociativeCacheTestAdd(){
        Integer key = 1, value = 10;
        mySmallCache.add(key, value);
        
        assertTrue(Objects.equals(mySmallCache.get(key), value));
    }
    
    /**
     * Tests the NWayAssociativeCache "get" function
     */
    @Test
    public void NWayAssociativeCacheTestGet(){
        //add to our cache utilizing LRU (by default). Get does not depend on the replacement algorithm used.
        mySmallCache.add(1, 10);
        Integer key = 1, value = 10;
        //When key exists in cache return associated value
        assertEquals(value, mySmallCache.get(key));
        //When key DNE in cache return expected to be null
        key = 2;
        assertEquals(null, mySmallCache.get(key));
    }  
    
    @Test
    public void overwriteValueTest(){
        mySmallCache.add(1, 1);
        assertTrue(Objects.equals(mySmallCache.get(1), 1));
        mySmallCache.add(1, 2);
        assertFalse(Objects.equals(mySmallCache.get(1), 1));
        assertTrue(Objects.equals(mySmallCache.get(1), 2));
    }
   
    /**
     * Fills a size 32 cache with 32 values
     */
    @Test
    public void fillCacheTest(){
        for(int i=0; i<32; i++){
            Integer value = i * 10;
            mySmallCache.add(i, value);
            
            assertTrue(Objects.equals(mySmallCache.get(i), value));
        }
    }
    /**
     * Fills a 32 filled cache (of size 32) with values (by replacement) for another 100 values.  
     */
    @Test
    public void fillAlreadyFullCacheTest(){
        for(int i=32; i<128; i++){
            Integer value = i * 10;
            mySmallCache.add(i, value);
            assertTrue(Objects.equals(mySmallCache.get(i), value));
        }
    }
}
