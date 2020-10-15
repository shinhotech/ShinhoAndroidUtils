package com.shinho.android.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CollectionUtils {

    /**
     * 判断集合是否是不为null且含有某个元素
     */

    public static <T> boolean contain(Collection<T> c, T data) {
        return !isEmpty(c) && c.contains(data);
    }

    /**
     * 集合是否是null或者不含有元素
     */
    public static <T> boolean isEmpty(Collection<T> c) {
        return c == null || c.size() == 0;
    }

    /**
     * Map是否是null或者不含有元素
     */
    public static boolean isEmpty(Map map) {
        return map == null || map.size() == 0;
    }


    /**
     * 移除list里的null元素
     */
    public static <T> List<T> filterEmpty(List<T> items) {
        if (CollectionUtils.isEmpty(items)) return items;
        Iterator<T> iterator = items.iterator();
        for (; iterator.hasNext(); ) {
            T next = iterator.next();
            if (next == null) iterator.remove();
        }
        return items;
    }

    /**
     * 通过值找到map里的键
     */
    public static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        if (!map.containsValue(value)) return null;
        for (Map.Entry<T, E> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * 移除list里某个索引范围的元素
     */
    public static <T> void removeRange(List<T> list, int fromIndex, int endIndex) {
        list.removeAll(new ArrayList<>(list.subList(fromIndex, endIndex)));
    }
}
