package com.dry3.concurrentProgramming.example.singleton;

import com.dry3.concurrentProgramming.annotations.Recommend;
import com.dry3.concurrentProgramming.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 枚举模式
 *      相比懒汉模式能保证线程的安全性
 *      相比饿汉模式能保证只在第一次调用暴露的方法时才进行初始化, 后续调用不进行初始化保证资源的合理利用
 * 在调用内部枚举类时候JVM可以保证只执行一次初始化类的操作
 *
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/19
 * @time 8:55
 */
@Slf4j
@ThreadSafe
@Recommend
public class EnumerationMode {

    private EnumerationMode() {

    }

    public static EnumerationMode getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;
        private EnumerationMode enumerationMode;

        //JVM保证这个方法只执行一次
        Singleton() {
            enumerationMode = new EnumerationMode();

        }

        public EnumerationMode getInstance() {
            return enumerationMode;
        }


    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5000; i++) {
            executorService.execute(() -> {
                log.info("{}", getInstance().toString());
            });
        }
        executorService.shutdown();
    }
}
