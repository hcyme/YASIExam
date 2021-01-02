package com.hcy.service;

import com.hcy.entity.Pager;

public interface PagerService<T> {
	public Pager<T> findFromModel(int pageNum, int pageSize, StringBuffer sql, Class entity);
}
