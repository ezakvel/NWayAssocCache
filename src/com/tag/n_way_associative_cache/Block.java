package com.tag.n_way_associative_cache;

/**
 * Key-Value pair that exists as a row within a set of a cache
 * 
 * @author quantumDrop
 * @param <K> Generic type to represent Key's
 * @param <V> Generic type to represent Value's 
 */
public class Block <K,V>{
        private K key;
        private V value;
        
        /**
         * Constructs a new Block with arguments given. 
         * @param key The key of generic type K to access the correct set id
         * @param value The value of generic type V for the block.
         */
        public Block(K key, V value){
            this.key = key;
            this.value = value;
        }

        /**
         * @return the key of generic type K for the block.
         */
        public K getKey() {
            return key;
        }

        /**
         * @param key The key of generic type K to access the correct set id
         */
        public void setKey(K key) {
            this.key = key;
        }

        /**
         * @return the value of generic type V for the current block.
         */
        public V getValue() {
            return value;
        }

        /**
         * @param value The value of generic type V for the block.
         */
        public void setValue(V value) {
            this.value = value;
        }

}
