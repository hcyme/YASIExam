package com.hcy.service.serviceImpl;

import com.hcy.entity.Classes;
import com.hcy.jdbc.JdbcQuery;
import com.hcy.jdbc.JdbcUtils;
import com.hcy.service.ClassesService;

import java.util.List;

public class ClassesServiceImpl implements ClassesService {

	@Override
	public List<Classes> findAllClasses() {
		// TODO Auto-generated method stub
		String sql = "select * from classes";
		JdbcQuery querys = JdbcUtils.createNativeQuery(sql, Classes.class);
		List<Classes> classesList = (List<Classes>) querys.getResultList();
		return classesList;
	}

	@Override
	public Classes findClasses(String cls_id) {
		// TODO Auto-generated method stub
		String sql = "select * from classes where cls_id='" + cls_id + "'";
		JdbcQuery querys = JdbcUtils.createNativeQuery(sql, Classes.class);
		Classes classes = querys.getBean();
		return classes;
	}

}
