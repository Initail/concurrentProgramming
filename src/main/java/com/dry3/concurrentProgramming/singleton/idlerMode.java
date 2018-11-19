package com.dry3.concurrentProgramming.singleton;


import com.dry3.concurrentProgramming.annotations.NotThreadSafe;

/**
 *
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/18
 * @time 22:06
 */
@NotThreadSafe
public class idlerMode {


    // 私有构造函数
    private idlerMode() {

    }

    // 单例对象
    private static idlerMode instance = null;

    // 工厂模式获取对象
    public static idlerMode getInstance() {
        if (instance == null) {
            instance = new idlerMode();
        }
        return instance;
    }
}
