package com.dry3.concurrentProgramming.example.singleton;

import com.dry3.concurrentProgramming.annotations.NotRecommend;
import com.dry3.concurrentProgramming.annotations.ThreadSafe;

/**
 * 饥汉模式
 * 单例实例在类装载使用时进行创建
 * defect : 在私有构造函数初始化操作很复杂, 并且所装载对象未被使用时候, 会很浪费资源.
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/18
 * @time 21:55
 */
@ThreadSafe
@NotRecommend
public class HungerHanMode {

    //私有化构造函数
    private HungerHanMode() {

    }

    // 单例对象
    private static HungerHanMode instance = new HungerHanMode();

    // 静态工厂方法
    public static HungerHanMode getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        getInstance();
    }
}
