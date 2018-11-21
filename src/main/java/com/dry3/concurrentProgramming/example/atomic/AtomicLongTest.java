package com.dry3.concurrentProgramming.example.atomic;

import com.dry3.concurrentProgramming.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/18
 * @time 9:47
 */

@Slf4j
@ThreadSafe
public class AtomicLongTest {

    private static int threadTotal = 50;

    private static int clientTotal = 5000;


    private static AtomicLong atomicLong = new AtomicLong();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    incrementAndGet();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("atomicLong:{}", atomicLong.get());
    }

    private static void incrementAndGet() {
        atomicLong.incrementAndGet();
    }
}
