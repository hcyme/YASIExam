package com.hcy.service.serviceImpl;

import com.hcy.entity.Pager;
import com.hcy.jdbc.JdbcQuery;
import com.hcy.jdbc.JdbcUtils;
import com.hcy.service.PagerService;

import java.util.List;

public class PagerServiceImpl<T> implements PagerService<T> {

	@Override
	public Pager<T> findFromModel(int pageNum, int pageSize, StringBuffer sql, Class entity) {
		// TODO Auto-generated method stub
		JdbcQuery querys = JdbcUtils.createNativeQuery(sql.toString(), entity);
		List<T> list = (List<T>) querys.getResultList();
		if (list != null) {
			// 分页查询
			Pager<T> pager = new Pager<>(list, pageNum, pageSize);
			if (pager != null) {
				return pager;
			}
		}
		return null;

	}

}
