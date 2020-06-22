package algorithm.sort.heap_sort.java;

/**
 * 堆排序：Java
 *	 *复杂度O(N*lgN)
 *	 不稳定
 * @author skywang
 * @date 2014/03/11
 */

public class HeapSort {
	public static void show(int[] a){
		for (int i=0; i<a.length; i++)
			System.out.printf("%d ", a[i]);
		System.out.printf("\n");
	}

	/**
	 * @author tianchuan
	 * 结构优化后的堆算法
	 * 遍历次数明了
	 * n*lgn
	 * @param a 待排数组
	 * @param n a.length
	 */
	public static void heapSortTc(int[] a, int n) {
		int i,j,tmp;
		//每轮建立一个大小为N的最大堆，然后把最大值从堆中剔除
		for (j = n ; j > 0; j--) {
			//遍历堆的每个非叶子节点 ,以每个非叶子节点(必须为最末端的非叶子节点)为开始，向下调整堆
			for (i = j / 2 - 1; i >= 0; i--){
				maxHeapTc(a, i, j);
			}
			tmp = a[0];
			a[0] = a[j-1];
			a[j-1] = tmp;
			show(a);

		}
	}
	/**
	 * 结构优化后的向下调整算法
	 * @param start 非叶节点下标
	 * @param end 堆的大小
	 */
	public static void maxHeapTc(int[] a, int start, int end) {
		int l = 2*start + 1;
		int tmp = a[start];

		// 左右两孩子中选择较大者
		if (a[l] < a[l+1] && l+1< end) {
			l++;
		}
		// 交换值
		if (tmp < a[l]) {
			a[start] = a[l];
			a[l] = tmp;
		}
	}
	/**
	 * (最大)堆的向下调整算法
	 *
	 * 注：数组实现的堆中，第N个节点的左孩子的索引值是(2N+1)，右孩子的索引是(2N+2)。
	 *     其中，N为数组下标索引值，如数组中第1个数对应的N为0。
	 *
	 * 参数说明：
	 *     a -- 待排序的数组
	 *     start -- 被下调节点的起始位置(一般为0，表示从第1个开始)
	 *     end   -- 截至范围(一般为数组中最后一个元素的索引)
	 */
	public static void maxHeapDown(int[] a, int start, int end) {
		int c = start;			// 当前(current)节点的位置
		int l = 2*c + 1;		// 左(left)孩子的位置
		int tmp = a[c];			// 当前(current)节点的大小

		for (; l <= end; c=l,l=2*l+1) {
			// "l"是左孩子，"l+1"是右孩子
			if ( l < end && a[l] < a[l+1])
				l++;		// 左右两孩子中选择较大者，即m_heap[l+1]
			if (tmp >= a[l])
				break;		// 调整结束
			else {			// 交换值
				a[c] = a[l];
				a[l]= tmp;
			}
		}
	}

	/**
	 * 堆排序(从小到大)
	 *
	 * 参数说明：
	 *     a -- 待排序的数组
	 *     n -- 数组的长度
	 */
	public static void heapSortAsc(int[] a, int n) {
		int i,tmp;

		// 从(n/2-1) --> 0逐次遍历。遍历之后，得到的数组实际上是一个(最大)二叉堆。
		for (i = n / 2 - 1; i >= 0; i--)
			maxHeapDown(a, i, n-1);

		// 从最后一个元素开始对序列进行调整，不断的缩小调整的范围直到第一个元素
		for (i = n - 1; i > 0; i--) {
			// 交换a[0]和a[i]。交换后，a[i]是a[0...i]中最大的。
			tmp = a[0];
			a[0] = a[i];
			a[i] = tmp;
			// 调整a[0...i-1]，使得a[0...i-1]仍然是一个最大堆。
			// 即，保证a[i-1]是a[0...i-1]中的最大值。
			maxHeapDown(a, 0, i-1);
		}
	}

	/**
	 * (最小)堆的向下调整算法
	 *
	 * 注：数组实现的堆中，第N个节点的左孩子的索引值是(2N+1)，右孩子的索引是(2N+2)。
	 *     其中，N为数组下标索引值，如数组中第1个数对应的N为0。
	 *
	 * 参数说明：
	 *     a -- 待排序的数组
	 *     start -- 被下调节点的起始位置(一般为0，表示从第1个开始)
	 *     end   -- 截至范围(一般为数组中最后一个元素的索引)
	 */
	public static void minHeapDown(int[] a, int start, int end) {
		int c = start;			// 当前(current)节点的位置
		int l = 2*c + 1;		// 左(left)孩子的位置
		int tmp = a[c];			// 当前(current)节点的大小

		for (; l <= end; c=l,l=2*l+1) {
			// "l"是左孩子，"l+1"是右孩子
			if ( l < end && a[l] > a[l+1])
				l++;		// 左右两孩子中选择较小者
			if (tmp <= a[l])
				break;		// 调整结束
			else {			// 交换值
				a[c] = a[l];
				a[l]= tmp;
			}
		}
	}

	/**
	 * 堆排序(从大到小)
	 *
	 * 参数说明：
	 *     a -- 待排序的数组
	 *     n -- 数组的长度
	 */
	public static void heapSortDesc(int[] a, int n) {
		int i,tmp;

		// 从(n/2-1) --> 0逐次遍历每。遍历之后，得到的数组实际上是一个最小堆。
		for (i = n / 2 - 1; i >= 0; i--)
			minHeapDown(a, i, n-1);

		// 从最后一个元素开始对序列进行调整，不断的缩小调整的范围直到第一个元素
		for (i = n - 1; i > 0; i--) {
			// 交换a[0]和a[i]。交换后，a[i]是a[0...i]中最小的。
			tmp = a[0];
			a[0] = a[i];
			a[i] = tmp;
			// 调整a[0...i-1]，使得a[0...i-1]仍然是一个最小堆。
			// 即，保证a[i-1]是a[0...i-1]中的最小值。
			minHeapDown(a, 0, i-1);
		}
	}

	public static void main(String[] args) {
		int i;
		int a[] = {20,30,90,40,70,110,60,10,100,50,80};

		System.out.printf("before sort:");
		for (i=0; i<a.length; i++)
			System.out.printf("%d ", a[i]);
		System.out.printf("\n");

		heapSortTc(a, a.length);			// 田川
		//heapSortAsc(a, a.length);			// 升序排列
		//heapSortDesc(a, a.length);		// 降序排列

		System.out.printf("after  sort:");
		for (i=0; i<a.length; i++)
			System.out.printf("%d ", a[i]);
		System.out.printf("\n");
	}
}
