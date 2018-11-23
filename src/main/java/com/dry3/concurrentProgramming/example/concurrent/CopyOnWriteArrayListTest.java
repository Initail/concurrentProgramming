package com.dry3.concurrentProgramming.example.concurrent;

import com.dry3.concurrentProgramming.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ArrayList => CopyOnWriteArrayList
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/21
 * @time 21:40
 */
@ThreadSafe
public class CopyOnWriteArrayListTest {

    public static void forEach(List<Integer> list) {
        for (Integer item : list) {
            if (item == 3) {
                list.remove(item);
            }
        }
    }

    public static void iterator(List<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i == 3) {
                list.remove(i);
            }

        }
    }

    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        iterator(list);
    }
}
