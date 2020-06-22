package Collection;

import java.util.Map;
import java.util.Random;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Collection;

/**
 *
 * 	entry数组 加 entry是链表
 * 	{@linkplain HashMap#table}
 * 	{@linkplain HashMap#entrySet}
 * 	HashSet依赖于HashMap，它实际上是通过HashMap实现的。HashSet中的元素是无序的。
 *  TreeSet依赖于TreeMap，它实际上是通过TreeMap实现的。TreeSet中的元素是有序的。
 *
 * void                 clear()
 * Object               clone()
 * boolean              containsKey(Object key)
 * boolean              containsValue(Object value)
 * Set<Entry<K, V>>     entrySet()
 * V                    get(Object key)
 * boolean              isEmpty()
 * Set<K>               keySet()
 * V                    put(K key, V value)
 * void                 putAll(Map<? extends K, ? extends V> map)
 * V                    remove(Object key)
 * int                  size()
 * Collection<V>        values()
 *
 *   (01) 通过迭代器entrySet().iterator()去遍历key、value，参考实现函数：标准方式 比第四种的优势在于过程中可remove
 *  	{@linkplain #iteratorHashMapByEntryset(HashMap)}
 *
 *   (02) 通过keySet()去遍历key、value，参考实现函数：
 *  	iteratorHashMapByKeyset()
 *
 *   (03) 通过values()去遍历value，参考实现函数：
 *    	iteratorHashMapJustValues()
 *
 *   (04) 增强型forentrySet()参考实现函数：  最快
 *   	 {@linkplain #iteratorHashMapByKeyset2(HashMap)}
 *
 *	hashtable已不使用
 * @author qq491
 */
public class HashMapTest {

	public static void main(String[] args) {
		int val = 0;
		String key = null;
		Integer value = null;
		Random r = new Random();
		HashMap map = new HashMap();

		for (int i=0; i<120; i++) {
			// 随机获取一个[0,100)之间的数字
			val = r.nextInt(100);
			key = String.valueOf(val);
			value = r.nextInt(5);
			// 添加到HashMap中
			map.put(key, value);
			System.out.println(" key:"+key+" value:"+value);
		}
		// 通过entrySet()遍历HashMap的key-value
		iteratorHashMapByEntryset(map) ;

		// 通过keySet()遍历HashMap的key-value
		iteratorHashMapByKeyset(map) ;

		// 单单遍历HashMap的value
		iteratorHashMapJustValues(map);

		// 单单遍历2HashMap的value
		iteratorHashMapByKeyset2(map);
	}

	/**
	 * 通过entry set遍历HashMap
	 * 效率高!
	 * nice
	 */
	private static void iteratorHashMapByEntryset(HashMap map) {
		// 记录开始时间
		long start = System.currentTimeMillis();
		if (map == null)
			return ;

		System.out.println("\niterator HashMap By entryset");
		String key = null;
		Integer integ = null;
		Iterator iter = map.entrySet().iterator();
		while(iter.hasNext()) {
			Map.Entry entry = (Map.Entry)iter.next();

			key = (String)entry.getKey();
			integ = (Integer)entry.getValue();
			System.out.println(key+" -- "+integ.intValue());
		}
		// 记录结束时间
		long end = System.currentTimeMillis();
		long interval = end - start;
		System.out.println("1:: " + interval+" ms");
	}

	/**
	 * 通过keyset来遍历HashMap
	 * 效率低!
	 */
	private static void iteratorHashMapByKeyset(HashMap map) {
		// 记录开始时间
		long start = System.currentTimeMillis();
		if (map == null)
			return ;

		System.out.println("\niterator HashMap By keyset");
		String key = null;
		Integer integ = null;
		Iterator iter = map.keySet().iterator();
		while (iter.hasNext()) {
			key = (String)iter.next();
			integ = (Integer)map.get(key);
			System.out.println(key+" -- "+integ.intValue());
		}
		// 记录结束时间
		long end = System.currentTimeMillis();
		long interval = end - start;
		System.out.println("2:: " + interval+" ms");
	}


	/**
	 * 遍历HashMap的values
	 */
	private static void iteratorHashMapJustValues(HashMap map) {
		// 记录开始时间
		long start = System.currentTimeMillis();
		if (map == null)
			return ;

		System.out.println("\niterator HashMap Just Values");
		Collection c = map.values();
		Iterator iter= c.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
			//iter.next();
		}
		// 记录结束时间
		long end = System.currentTimeMillis();
		long interval = end - start;
		System.out.println("3:: " + interval+" ms");
	}

	private static void iteratorHashMapByKeyset2(HashMap<String,Integer> map) {
		// 记录开始时间
		long start = System.currentTimeMillis();
		if (map == null)
			return ;

		System.out.println("\niterator HashMap By keyset2");
		for (Entry<String, Integer> entry : map.entrySet()) {
			String key = entry.getKey();
			int value = entry.getValue();
			System.out.println(key + "," + value);
		}
		// 记录结束时间
		long end = System.currentTimeMillis();
		long interval = end - start;
		System.out.println("4:: " + interval+" ms");
	}
}


