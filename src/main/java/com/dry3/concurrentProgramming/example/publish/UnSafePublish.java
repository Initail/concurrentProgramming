package com.dry3.concurrentProgramming.example.publish;

import com.dry3.concurrentProgramming.annotations.NotRecommend;
import com.dry3.concurrentProgramming.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/18
 * @time 17:50
 */
@Slf4j
@NotThreadSafe
@NotRecommend
public class UnSafePublish {

    private static String[] states = {"a", "b", "c"};

    public String[] getStates() {
        return states;
    }

    public static void main(String[] args) {
        UnSafePublish publishTest = new UnSafePublish();
        log.info("states : {}", Arrays.toString(publishTest.getStates()));
        publishTest.getStates()[0] = "d";
        log.info("states : {}", Arrays.toString(publishTest.getStates()));
    }
}
