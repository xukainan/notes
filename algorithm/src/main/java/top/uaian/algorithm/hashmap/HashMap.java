package top.uaian.algorithm.hashmap;

import lombok.Data;

/**
 * description:  <br>
 * date: 2021/1/20 15:41 <br>
 * author: xukainan <br>
 * version: 1.0 <br>
 */
public class HashMap<K,V> implements Map<K, V>{

    private final int INIT_CAPACITY = 16;

    Node[] array= new Node[INIT_CAPACITY];

    @Override
    public void put(K k, V v) {
        int hash = simpleHashCode(k);
        int index = hash % INIT_CAPACITY;
        Node curNode = array[index];
        if(curNode == null){
            array[index] = new MapNodeFactory().create( k,  v,  hash, null);
        }else {
            if(curNode.k == k || k.equals(curNode.k)){
                curNode.setV(v);
            }else {
                array[index] = new MapNodeFactory().create( k,  v,  hash, curNode);
            }
        }

    }

    @Override
    public V get(K k) {
         int hash = simpleHashCode(k);
         int index = hash % INIT_CAPACITY;
         Node curNode = array[index];
         if(curNode != null){
             return getVal(curNode, k);
         }
         return null;
    }

    private V getVal(Node curNode, K k) {
        if(curNode.k == k || k.equals(curNode.k)){
            return (V) curNode.v;
        }else{
            getVal(curNode.next, k);
        }
        return null;
    }

    @Override
    public int simpleHashCode(K k) {
        return k.hashCode();
    }


    class Node<K, V> implements Map.Entry<K, V>{
        private K k;
        private V v;
        private int hashCode;
        private Node next;

        public K getK() {
            return k;
        }

        public void setK(K k) {
            this.k = k;
        }

        public V getV() {
            return v;
        }

        public void setV(V v) {
            this.v = v;
        }

        public int getHashCode() {
            return hashCode;
        }

        public void setHashCode(int hashCode) {
            this.hashCode = hashCode;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    class MapNodeFactory<K, V>{
        private Node create(K k, V v, int hash, Node next){
            Node node = new Node();
            node.setHashCode(hash);
            node.setK(k);
            node.setV(v);
            node.setNext(next);
            return node;
        }
    }
}
