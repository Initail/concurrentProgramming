package com.dry3.concurrentProgramming.example.immutable;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * Collections.unmodifiableXXX()方法将常用集合类的改变操作全部置空做抛异常处理
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/20
 * @time 18:56
 */
@Slf4j
public class CollectionUnmodifiableTest {

    public static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 3);
        map.put(2, 4);
        map.put(3, 5);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1, 2);
        log.info("{}", map);
    }

}
