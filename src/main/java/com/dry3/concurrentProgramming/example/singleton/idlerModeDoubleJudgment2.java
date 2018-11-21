package com.dry3.concurrentProgramming.example.singleton;

import com.dry3.concurrentProgramming.annotations.NotRecommend;
import com.dry3.concurrentProgramming.annotations.ThreadSafe;

/**
 * 懒汉模式 => 双重监测机制
 * 单例实例在第一次使用时进行创建
 *
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/18
 * @time 22:16
 */

@ThreadSafe
@NotRecommend
public class idlerModeDoubleJudgment2 {

    // 私有构造函数
    private idlerModeDoubleJudgment2() {

    }

    // 单例对象 使用volatile关键字保证对象变换的可见性
    private volatile static idlerModeDoubleJudgment2 instance = null;

    // 工厂模式获取对象
    public static idlerModeDoubleJudgment2 getInstance() {
        if (instance == null) {     // 1
            synchronized (idlerModeDoubleJudgment2.class) { // 同步锁
                if (instance == null) {
                    instance = new idlerModeDoubleJudgment2(); // 2
                }
            }
        }
        return instance;
    }
}
