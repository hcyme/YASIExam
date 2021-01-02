package com.hcy.service;

import com.hcy.entity.Count;
import com.hcy.entity.Pager;

import java.util.List;

public interface CountService {
	public List<Count> claculateCount();

	public void insertCount(Count count);

	public void updateCount(Count count);

	public boolean ifHaveRecore(String p_id);

	public Pager<Count> findAllCount(Integer currentPage, Integer pageSize, String c_id, String cls_id, String keyword);
}
