package com.dry3.concurrentProgramming.example.unsafe;

import com.dry3.concurrentProgramming.annotations.NotThreadSafe;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * HashSet是线程不安全的
 *
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/21
 * @time 15:35
 */

@Slf4j
@NotThreadSafe
public class HashSetTest {


    private static int threadTotal = 200;

    private static int clientTotal = 5000;

    private static HashSet<Integer> set = Sets.newHashSet();

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
                    setAdd(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size: {},time : {}秒", set.size(), (System.currentTimeMillis() - start) / 1000f);
    }

    private static void setAdd(int i) {
        set.add(i);
    }
}
