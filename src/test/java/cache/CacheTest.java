package cache;

import evictionpolicy.LRUEviction;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.HashMapStorage;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {

    Cache<Integer, Integer> cache;
    @BeforeEach
    public void initializeCache(){
        cache = new CacheFactory<Integer, Integer>().defaultCache(3);
    }
    @Test
    void testPutGetAndEviction() {
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        // eviction should take place on addition of 4 since capacity is full. 1 should be evicted
        cache.put(4, 4);
        assertNull(cache.get(1));
        assertEquals(cache.get(2), 2);
        // this makes the LRU 3->4->2 so next eviction should remove 3 instead of 2
        cache.put(1, 1);
        assertNull(cache.get(3));
        assertEquals(cache.get(1), 1);
    }
    @Test
    void testIfCacheCapacityIsNegative(){
        cache = new CacheFactory<Integer, Integer>().defaultCache(-1);
        assertThrows(RuntimeException.class, () -> {
            cache.put(1, 1);
        });
    }
}