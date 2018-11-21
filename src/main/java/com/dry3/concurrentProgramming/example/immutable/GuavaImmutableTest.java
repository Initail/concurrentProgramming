package com.dry3.concurrentProgramming.example.immutable;

import com.dry3.concurrentProgramming.annotations.ThreadSafe;
import com.google.common.collect.*;
import lombok.extern.slf4j.Slf4j;

/**
 * Guava调用ImmutableXXX来实现不可变对象
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/20
 * @time 18:56
 */
@Slf4j
@ThreadSafe
public class GuavaImmutableTest {

    public static ImmutableMap map = ImmutableMap.builder().put(1, 2).put(2, 3).put(3, 4).build();

    public static ImmutableList list = ImmutableList.of(1, 2, 3, 4, 5, 5, 6);

    public static ImmutableSet set = ImmutableSet.copyOf(list);

    public static void main(String[] args) {
       /*  成员变量是不可变得  所以显示方法已废弃
        map.put(1, 3);
        list.add(6);
        set.add(5);*/
        log.info("{}", map);
    }

}
