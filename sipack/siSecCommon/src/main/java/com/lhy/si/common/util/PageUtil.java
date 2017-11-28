package com.lhy.si.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lhy.si.common.model.Pagation;
import com.lhy.si.common.model.RequestPageParam;

/**
 * 
 * @ClassName: PageUtil
 * @Description: 转化分页对象 ，后续通过注解配置
 * @author luanhy
 * @date 2017年7月7日 下午5:23:57
 * @Copyright: Copyright (c) 2017 wisedu
 */
public class PageUtil {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(PageUtil.class);

	/**
	 * 
	 * @Title: converter 
	 * @Description: list转化分页对象 
	 * @param list
	 * @return ResponsePage<T> 返回类型 
	 * @throws
	 */
	public static <T> Pagation<T> converter(List<T> list) {
		if (list instanceof Page) {
			Page<T> p = (Page<T>) list;
			Pagation<T> page = new Pagation<T>();
			page.setList((ArrayList<T>) p);
			page.setPages(p.getPages());
			page.setTotal(p.getTotal());
			return page;
		} else {
			Pagation<T> page = new Pagation<T>();
			page.setList(list);
			int pages = (list == null || list.isEmpty()) ? 0 : 1;
			page.setPages(pages);
			page.setTotal(list.size());
			return page;
		}
	}
	
	/**
	 * 开启分页 如果为空则不走分页
	 * @param requestPageParam
	 * @return boolean 是否已分页
	 */
	public static boolean startPage(RequestPageParam requestPageParam){
		if(requestPageParam.getPage() != null && requestPageParam.getRows() != null){
			PageHelper.startPage(requestPageParam.getPage(), requestPageParam.getRows());
			return true;
		}
		return false;
	}

}
