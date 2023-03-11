package cache;

import evictionpolicy.EvictionPolicy;
import exceptions.NotFoundException;
import exceptions.StorageFullException;
import storage.Storage;

public class Cache<Key, Value> {
    private Storage<Key, Value> storage;
    private EvictionPolicy<Key> evictionPolicy;

    public Cache(Storage<Key, Value> storage, EvictionPolicy<Key> evictionPolicy) {
        this.storage = storage;
        this.evictionPolicy = evictionPolicy;
    }

    public void put(Key key, Value value){
        try{
            storage.put(key, value);
            evictionPolicy.keyAccessed(key);
        } catch (StorageFullException exception){
            System.out.println(exception.toString());
            Key keyToEvict =  evictionPolicy.evictKey();
            if(keyToEvict == null){
                throw new RuntimeException("storage is full but no key to evict");
            }
            storage.delete(keyToEvict);
            storage.put(key, value);
            evictionPolicy.keyAccessed(key);
        }
    }

    public Value get(Key key){
        try{
            Value value = storage.get(key);
            evictionPolicy.keyAccessed(key);
            return value;
        }catch(NotFoundException exception){
            System.out.println(exception.toString());
            return null;
        }
    }
}
