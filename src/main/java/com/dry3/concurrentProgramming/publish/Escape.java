package com.dry3.concurrentProgramming.publish;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/18
 * @time 17:58
 */

@Slf4j
public class Escape {

    private static String[] states = {"a", "b", "c"};

    public Escape() {
        new InnerClass();
    }

    private class InnerClass {

        public InnerClass() {
            log.info("{}", Arrays.toString(states));
        }
    }

    public static void main(String[] args) {
        new Escape();
    }
}
