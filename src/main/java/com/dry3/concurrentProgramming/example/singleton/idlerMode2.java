package com.dry3.concurrentProgramming.example.singleton;

import com.dry3.concurrentProgramming.annotations.ThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/18
 * @time 22:16
 */
@ThreadSafe
public class idlerMode2 {


    // 私有构造函数
    private idlerMode2() {

    }

    // 单例对象
    private static idlerMode2 instance = null;

    // 工厂模式获取对象
    public static synchronized idlerMode2 getInstance() {
        if (instance == null) {
            instance = new idlerMode2();
        }
        return instance;
    }
}
