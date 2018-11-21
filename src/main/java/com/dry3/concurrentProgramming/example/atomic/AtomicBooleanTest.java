package com.dry3.concurrentProgramming.example.atomic;

import com.dry3.concurrentProgramming.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/18
 * @time 13:47
 */

@Slf4j
@ThreadSafe
public class AtomicBooleanTest {

    private static int clientTotal = 5000;

    private static int threadTotal = 200;

    private static AtomicBoolean isHandle = new AtomicBoolean(false);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    compareAndSet();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("atomicBoolean : {}", isHandle);

    }

    private static void compareAndSet() {
        if (isHandle.compareAndSet(false, true)) {
            log.info("Change is Success1 : {}", isHandle.get());
        }
    }


}
