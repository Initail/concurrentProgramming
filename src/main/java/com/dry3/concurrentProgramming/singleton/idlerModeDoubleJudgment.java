package com.dry3.concurrentProgramming.singleton;

import com.dry3.concurrentProgramming.annotations.NotThreadSafe;

/**
 * 懒汉模式 => 双重监测机制
 * 单例实例在第一次使用时进行创建
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/18
 * @time 22:16
 */

@NotThreadSafe
public class idlerModeDoubleJudgment {

    // 私有构造函数
    private idlerModeDoubleJudgment() {

    }

    // 1.memory = allocate()    分配对象的内存空间
    // 2.ctorInstance()         初始化对象
    // 3.instance = memory      设置Instance指向分配好的内存空间

    // 在多线程情况下有可能发生  JVM和CPU导致的指令乱排序

    // 1.memory = allocate()    分配对象的内存空间
    // 3.instance = memory      设置Instance指向分配好的内存空间
    // 2.ctorInstance()         初始化对象

    // example: 线程A与线程B访问getInstance()方法
    //          当B线程执行完(2)的代码后 指令可能走了 instance = memory 返回了Instance实例对象 但这个时候instance是没有执行初始化的
    //          这时候A线程进来执行代码(1)判断此时instance是有内存空间的 不为null则直接返回对象instance导致和预期不一样

    // 单例对象
    private static idlerModeDoubleJudgment instance = null;

    // 工厂模式获取对象
    public static  idlerModeDoubleJudgment getInstance() {
        if (instance == null) {     // 1
            synchronized (idlerModeDoubleJudgment.class) { //同步锁
                if (instance == null) {
                    instance = new idlerModeDoubleJudgment(); // 2
                }
            }
        }
        return instance;
    }
}
