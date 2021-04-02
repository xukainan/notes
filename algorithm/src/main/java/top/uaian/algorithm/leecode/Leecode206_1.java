package top.uaian.algorithm.leecode;

import lombok.AllArgsConstructor;
import lombok.Data;

public class Leecode206_1 {

    @Data
    @AllArgsConstructor
    static class Entry<T>{
        private T value;
        private Entry<T> next;

        @Override
        public String toString() {
            return "Entry{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    public static void main(String[] args) {
        Entry<Integer> three = new Entry<Integer>(3, null);
        Entry<Integer> two = new Entry<Integer>(2, three);
        Entry<Integer> one = new Entry<Integer>(1, two);
        reverse(one, null);
        iteratorSingleEntry(three);

    }

    private static void iteratorSingleEntry(Entry<Integer> cur) {
        if(cur != null){
            System.out.println(cur.getValue());
            if(cur.getNext() != null){
                cur = cur.getNext();
                iteratorSingleEntry(cur);
            }
        }
    }

    private static void reverse(Entry<Integer> cur, Entry<Integer> next) {
        if (cur != null){
            Entry<Integer> nextCopy = cur.getNext();
            cur.setNext(next);
            reverse(nextCopy, cur);
        }
    }

}
