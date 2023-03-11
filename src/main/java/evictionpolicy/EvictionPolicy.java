package evictionpolicy;

public interface EvictionPolicy<Key> {
    public Key evictKey();
    public void keyAccessed(Key key);
}
