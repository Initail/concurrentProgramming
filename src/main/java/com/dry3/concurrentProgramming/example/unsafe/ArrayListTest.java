package com.dry3.concurrentProgramming.example.unsafe;

import com.dry3.concurrentProgramming.annotations.NotThreadSafe;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * ArrayList 是线程不安全的, 而且在ArrayList的add中代码是这样的
 *  public boolean add(E e) {
 *         ensureCapacityInternal(size + 1);  // Increments modCount!!
 *         elementData[size++] = e;
 *         return true;
 *     }
 *     size++操作不是原子性的, 这样操作数组会导致数组越界问题出现
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/21
 * @time 15:35
 */

@Slf4j
@NotThreadSafe
public class ArrayListTest {


    private static int threadTotal = 200;

    private static int clientTotal = 5000;

    private static List<Integer> list = Lists.newArrayList();
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
                    add(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size: {},time : {}秒", list.size(), (System.currentTimeMillis() - start) / 1000f);
    }

    private static void add(int i) {
       list.add(i);
    }
}
