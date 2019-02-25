package com.winjean.utils;

import java.util.ArrayList;
import java.util.List;

public class CollectionUtil {
	/**
	 * 按指定大小，分隔集合
	 * 
	 * @param source
	 * @param n
	 *            每个list的大小
	 * @return
	 */
	public static <T> List<List<T>> splitList(List<T> source, int n) {
		List<List<T>> result = new ArrayList<List<T>>();
		int left = source.size() % n; // (先计算出余数)
		int number = source.size() / n; // 然后是商
		for (int i = 0; i < number; i++) {
			List<T> value = null;
			value = source.subList(i * n, (i + 1) * n);
			result.add(value);
		}
		if (left > 0) {
			List<T> leftList = source.subList(number * n, source.size());
			result.add(leftList);
		}
		return result;
	}

}
