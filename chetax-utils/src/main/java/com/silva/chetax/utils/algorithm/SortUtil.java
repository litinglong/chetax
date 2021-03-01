package com.silva.chetax.utils.algorithm;


public class SortUtil {

	
	/* 打印数据 */
	public static String print(int[] data) {
		StringBuilder builder = new StringBuilder();
		String result = null;
		for (int i = 0; i < data.length; i++) {
			builder.append(data[i] + ",");
			if((i+1) == data.length) {
				result = builder.substring(0, builder.lastIndexOf(","));
			}
		}
		return result;
	}
	
	/**
	 * 冒泡排序算法
	 * @param data
	 */
	public static void sortByBubbling(int[] data) {
		int l = data.length;
		for (int j = 0; j < l; j++) {
			// 外循环控制排序次数
			for (int i = 1; i < l - j; i++) {
				// 内循环比较大小交换位置，循环一次即可将当前列表中最大的一个排到列表的末尾，所以下次排序的列表可以排除掉末尾的元素（n-j即为减长操作），直到待排序的列表长度变为0为止
				int a = data[i - 1];
				int b = data[i];
				if (a - b > 0) {
					data[i - 1] = b;
					data[i] = a;
				}
			}
		}

	}

	/**
	 * 插入排序算法
	 * @param data
	 */
	public static void sortByInsert(int[] data) {
		// 循环取元素来插入
		for (int i = 0; i < data.length; i++) {
			// i为当前需要插入的元素的下标
			int di = data[i];
			for (int j = 0; j < i; j++) {
				// 在0到i之间查找符合条件的元素进行插入替换
				int dj = data[j];
				if (di < dj) {
					data[j] = di;
					data[i] = dj;
					// 更新ai的值
					di = data[i];
				}
			}
		}
	}
	/**
	 * 快速排序算法
	 * @param data
	 */
	public static void sortByFast(int[] data) {
		int start = 0; 
		int end = data.length-1;
		doSortByFast(data, start, end);
	}
	
	public static void doSortByFast(int[] data, int start, int end) {
		// 找一个基准值
		int baseData = data[start];
		// 将所有比基准值小的数放置到基准值左侧，将所有比基准值大的数放置到基准值右侧
		int curbaseDataIndex = start;
		int right = end;
		int left = start;
		while(left < right) {
			// 从右往左找一个比基准值小的数
			for (int i = end; i > start; i--) {
				int di = data[i];
				if(data[i] <= baseData) {
					data[i] = baseData;
					data[start] = di;
					curbaseDataIndex = i;
					right = i;
					break;
				}
			}
			// 从左往右找一个比基准值大的数
			for (int i = start; i < end; i++) {
				int di = data[i];
				if(data[i] > baseData) {
					data[i] = baseData;
					data[curbaseDataIndex] = di;
					curbaseDataIndex = i;
					left = i;
					break;
				}
			}
		}
		// 对基准值左边的无序列表重复上述操作
		doSortByFast(data, start, left);
		// 对基准值右边的无序列表重复上述操作
		doSortByFast(data, right, end);
	}
	/* 希尔排序算法 */

	/* 归并排序算法 */

	/* 桶排序算法 */

	/* 基数排序算法 */

}
