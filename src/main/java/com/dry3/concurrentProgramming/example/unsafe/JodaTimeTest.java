package com.dry3.concurrentProgramming.example.unsafe;

import com.dry3.concurrentProgramming.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Joda-Time使用
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/21
 * @time 14:47
 */
@Slf4j
@ThreadSafe
public class JodaTimeTest {

    private static int threadTotal = 200;

    private static int clientTotal = 5000;

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMMdd");

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
                    dateTimeFormatter(count);
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

    private static void dateTimeFormatter(int i) {
        //simpleDateFormat.parse("20181112");
        log.info("{},{}", i, DateTime.parse("20181121", dateTimeFormatter).toDate());
    }
}
