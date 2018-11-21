package com.dry3.concurrentProgramming.unsafe;

import com.dry3.concurrentProgramming.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * SimpleDateFormat使用
 * 使用SimpleDateFormat的parse()方法时会出现线程不安全情况, 需要在方法里面单独创建转换对象
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/21
 * @time 14:47
 */
@Slf4j
@ThreadSafe
public class DataFormatTest {

    private static int threadTotal = 200;

    private static int clientTotal = 5000;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        Long start = System.currentTimeMillis();
        for (int i = 1; i <= clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    dataFormat(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("time : {}秒", (System.currentTimeMillis() - start) / 1000f);
    }

    private static void dataFormat(int i) {
        //simpleDateFormat.parse("20181112");
        log.info("{},{}", i, simpleDateFormat.format(new Date()));
    }
}
