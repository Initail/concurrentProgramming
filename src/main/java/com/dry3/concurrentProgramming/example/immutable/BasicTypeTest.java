package com.dry3.concurrentProgramming.example.immutable;

import com.dry3.concurrentProgramming.annotations.NotRecommend;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * final 修饰符进行修饰
 *
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/18
 * @time 9:47
 */

@Slf4j
@NotRecommend
public class BasicTypeTest {

    public final static Integer COUNT = 1;

    public final static String NAME = "Le";

    public final static Map<Integer, Integer> map = Maps.newHashMap();
    //final修饰的对象不能给他指向一个新的引用, 可以改变原引用中的值

    static {
        map.put(1, 2);
        map.put(2, 3);
        map.put(3, 4);
    }

    public static void main(String[] args) {
//        COUNT = 2;
//        NAME = "yuan";
//        map = Maps.newHashMap();
        map.put(1, 3);
        log.info("{}", map.get(1));

    }

}
