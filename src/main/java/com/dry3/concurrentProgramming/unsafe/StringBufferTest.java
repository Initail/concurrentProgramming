package com.dry3.concurrentProgramming.unsafe;

import com.dry3.concurrentProgramming.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 *
 * 测试StringBuffer 在多线程运行场景的情况
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/21
 * @time 14:36
 */
@Slf4j
@ThreadSafe
public class StringBufferTest {

    private static int threadTotal = 200;

    private static int clientTotal = 500000;

    private static StringBuffer stringBuffer = new StringBuffer();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        Long start = System.currentTimeMillis();
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    bufferAppend();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception",e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("length : {}, time : {}秒", stringBuffer.length(), (System.currentTimeMillis() - start) / 1000f);
    }

    private static void bufferAppend() {
        stringBuffer.append(1);
    }
}
