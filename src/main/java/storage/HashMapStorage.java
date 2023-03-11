package storage;

import exceptions.NotFoundException;
import exceptions.StorageFullException;

import java.util.HashMap;
import java.util.Map;

public class HashMapStorage<Key, Value> implements Storage<Key, Value> {
    private Map<Key, Value> hashMap;
    private int capacity;

    public HashMapStorage(int capacity) {
        this.hashMap = new HashMap<>();
        this.capacity = capacity;
    }


    @Override
    public void put(Key key, Value value) throws StorageFullException {
        if(hashMap.size() >= capacity) throw new StorageFullException("storage is full");
        hashMap.put(key, value);
    }

    @Override
    public Value get(Key key) throws NotFoundException {
        if(!hashMap.containsKey(key)) throw new NotFoundException(key + " not found in cache");
        return hashMap.get(key);
    }

    @Override
    public void delete(Key key) throws NotFoundException{
        if(!hashMap.containsKey(key)) throw new NotFoundException("cannot delete key: " + key + "as it is not found in cache");
        hashMap.remove(key);
    }
}
