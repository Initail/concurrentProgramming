package com.dry3.concurrentProgramming;

import com.dry3.concurrentProgramming.annotations.ThreadSafe;

/**
 * @author Administrator
 * @email zyl@dry3.cn
 * @date 2018/11/21
 * @time 17:14
 */
@ThreadSafe
public class RequestHolder {

    public static final ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long get() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }
}
