package com.tag.replacement_algorithm;

import com.tag.exceptions.CacheSizeConstructionException;
import com.tag.n_way_associative_cache.Block;
import com.tag.n_way_associative_cache.DefaultSet;
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
public class MRUTest {
    private NWayAssociativeCache<Integer, Integer> myMRUCache;
    private MRU mru;
    
    public MRUTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws CacheSizeConstructionException, InstantiationException, IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
        mru = new MRU();
        myMRUCache = new NWayAssociativeCache<>(null, 1, 1, mru);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void MRUReplacementAlgorithmTest(){
        Integer key = 1, value = 10;
        myMRUCache.add(key, value);
        value = 11;
        myMRUCache.add(key, value);

        assertTrue(findBlockInSet(key, value));

        Integer valInCache = myMRUCache.get(key);
        assertTrue(valInCache.equals(value));
    }
    
    public boolean findBlockInSet(Integer key, Integer value){
        int setId = Math.abs(key.hashCode()%myMRUCache.getNumSets());
        DefaultSet<Integer, Integer> set = (DefaultSet) myMRUCache.getCache().get(setId);
        for(Block<Integer,Integer> block: set.getBlocks()){
            if(Objects.equals(block.getKey(), key)){
                return true;
            }
        }
        return false;
    }
}
