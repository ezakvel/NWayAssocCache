package com.tag.replacement_algorithm;

import com.tag.exceptions.CacheSizeConstructionException;
import com.tag.n_way_associative_cache.Block;
import com.tag.n_way_associative_cache.DefaultSet;
import com.tag.n_way_associative_cache.DoublyLinkedListSet;
import com.tag.n_way_associative_cache.NWayAssociativeCache;
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
public class LRUTest {
    private NWayAssociativeCache<Integer, Integer> myLRUDefaultCache;
    private LRU lru;
    
    public LRUTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws CacheSizeConstructionException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        lru = new LRU();
        myLRUDefaultCache = new NWayAssociativeCache<>(null, 1, 1, lru);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void LRUReplacementAlgorithmTest(){
        Integer key = 1, value = 10;
        myLRUDefaultCache.add(key, value);
        value = 11;
        myLRUDefaultCache.add(key, value);
        
        assertTrue(findBlockInSet(key, value));
        
        Integer valInCache = myLRUDefaultCache.get(key);
        assertTrue(valInCache.equals(value));
    }
    
    public boolean findBlockInSet(Integer key, Integer value){
        int setId = Math.abs(key.hashCode()%myLRUDefaultCache.getNumSets());
        DefaultSet<Integer, Integer> set = (DefaultSet) myLRUDefaultCache.getCache().get(setId);
        for(Block<Integer,Integer> block: set.getBlocks()){
            if(Objects.equals(block.getKey(), key)){
                return true;
            }
        }
        return false;
    }
}
