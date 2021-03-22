package top.uaian.algorithm.leecode;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

/**
 * 这是双向链表的反转。。
 */
public class Leecode206_0 {

    @Data
    @AllArgsConstructor
    static class Entry<T>{
        private T value;
        private Entry<T> preEntry;
        private Entry<T> suffixEntry;


        //不重写会抛出SOF
        //https://blog.csdn.net/qq_31433709/article/details/95187801
        @Override
        public String toString() {
            return "Entry{" +
                    "value=" + value +
                    ", preEntry=" + Optional.ofNullable(preEntry).map(item -> item.getValue()) +
                    ", suffixEntry=" + Optional.ofNullable(suffixEntry).map(item -> item.getValue()) +
                    '}';
        }
    }

    public static void main(String[] args) {
        Entry<Integer> five = new Entry<Integer>(5, null, null);
        Entry<Integer> four = new Entry<Integer>(4, null, five);
        Entry<Integer> three = new Entry<Integer>(3, null, four);
        Entry<Integer> two = new Entry<Integer>(2, null, three);
        Entry<Integer> one = new Entry<Integer>(1, null, two);
        two.setPreEntry(one);
        three.setPreEntry(two);
        four.setPreEntry(three);
        five.setPreEntry(four);
//        iteratorEntrys(one);
        ReverseEntrys(one);
        iteratorEntrys(five);

    }

    private static void ReverseEntrys(Entry<Integer> curEntry) {
        while (true){
            if(curEntry != null){
                Entry<Integer> preEntry = curEntry.getPreEntry();
                Entry<Integer> suffixEntry = curEntry.getSuffixEntry();
                curEntry.setSuffixEntry(preEntry);
                curEntry.setPreEntry(suffixEntry);
                curEntry = curEntry.getPreEntry();
            }else {
                break;
            }
        }

    }

    private static void iteratorEntrys(Entry<Integer> curEntry) {
        if(curEntry != null){
            System.out.println(curEntry.getValue());
            iteratorEntrys(curEntry.getSuffixEntry());
        }

    }
}
