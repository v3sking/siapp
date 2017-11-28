package com.lhy.si.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionUtil {
	
	/**
	 * 字典排序
	 * @param list
	 * @return
	 */
	public static List<String> dictSort(List<String> list){
		Collections.sort(list, new SpellComparator());
		return  list;
	}
	
	/**
	 *  移除list重复元素
	 * @param list
	 * @return
	 */
	public static <T> List<T> removeRepeatElement(List<T> list){
		Map<T,T> mp = new HashMap<T,T>();
		for (T string : list) {
			mp.put(string, string);
		}
		Set<T> keySet = mp.keySet();
		List<T> l = new ArrayList<T>();
		for (T s : keySet) {
			l.add(s);
		}
		return l;
	}
	
}


/**
 * 汉字拼音排序比较器
 */
class SpellComparator implements Comparator {
 public int compare(Object o1, Object o2) {
  try {
   // 取得比较对象的汉字编码，并将其转换成字符串
   String s1 = new String(o1.toString().getBytes("GB2312"), "ISO-8859-1");
   String s2 = new String(o2.toString().getBytes("GB2312"), "ISO-8859-1");
   // 运用String类的 compareTo（）方法对两对象进行比较
   return s1.compareTo(s2);
  } catch (Exception e) {
   e.printStackTrace();
  }
  return 0;
 }
 
	
}
