package com.dry3.concurrentProgramming.example.unsafe;

import com.dry3.concurrentProgramming.annotations.NotThreadSafe;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * HashMap是线程不安全的
 *
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/21
 * @time 15:35
 */

@Slf4j
@NotThreadSafe
public class HashMapTest {

    private static int threadTotal = 200;

    private static int clientTotal = 5000;

    private static HashMap<Integer, Integer> map = Maps.newHashMap();

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
                    mapPut(count);
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

    private static void mapPut(int i) {
        map.put(i, i);
    }
}
