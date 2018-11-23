package com.dry3.concurrentProgramming.example.concurrent;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

/** TreeSet => ConcurrentSkipListSet
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/21
 * @time 21:40
 */
public class ConcurrentSkipListSetTest {

    public static void forEach(Set<Integer> set) {
        for (Integer item : set) {
            if (item == 3) {
                set.remove(item);
            }
        }
    }

    public static void iterator(Set<Integer> set) {
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i == 3) {
                set.remove(i);
            }

        }
    }


    public static void main(String[] args) {
        Set<Integer> set = new ConcurrentSkipListSet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        iterator(set);
    }
}
