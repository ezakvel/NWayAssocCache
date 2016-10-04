package com.tag.exceptions;

/**
 *
 * @author quantumDrop
 */
public class CacheSizeConstructionException extends Exception {
    public CacheSizeConstructionException() { 
        super(); 
    }
    public CacheSizeConstructionException(String msg) { 
        super(msg); 
    }
    public CacheSizeConstructionException(String msg, Throwable cause) { 
        super(msg, cause); 
    }
    public CacheSizeConstructionException(Throwable cause) { 
        super(cause); 
    }
}
