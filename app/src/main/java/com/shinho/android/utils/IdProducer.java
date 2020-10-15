package com.shinho.android.utils;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用于产生一个在app生命周期内绝对唯一的int 或者String。
 */

public class IdProducer {
    private static AtomicInteger sAtomicInteger = new AtomicInteger();

    /**
     * 创建int类唯一id
     */
    public static int createInt() {
        return sAtomicInteger.getAndIncrement();
    }

    /**
     * 创建String类唯一id
     */
    public static String create() {
        return String.valueOf(sAtomicInteger.getAndIncrement());
    }
}
