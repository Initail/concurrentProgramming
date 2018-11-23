package com.dry3.concurrentProgramming.example.concurrent;

import com.dry3.concurrentProgramming.annotations.ThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * HashMap => concurrentHashMap
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/21
 * @time 21:40
 */
@ThreadSafe
@Slf4j
public class ConcurrentHashMapTest {

    private static int threadTotal = 2;

    private static int clientTotal = 5000000;

    private static Map<Integer, Integer> map = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        Long start = System.currentTimeMillis();
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    concurrentHashMapPut(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size: {},time : {}秒", map.size(), (System.currentTimeMillis() - start) / 1000f);
    }

    private static void concurrentHashMapPut(int i) {
        map.put(i, i);
    }
}
