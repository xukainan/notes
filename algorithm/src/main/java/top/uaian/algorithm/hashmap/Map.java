package top.uaian.algorithm.hashmap;

public interface Map<K,V> {

    void put(K k, V v);
    V get(K k);
    int simpleHashCode(K k);

    interface Entry<K, V>{

    }

}
