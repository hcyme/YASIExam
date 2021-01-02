package com.hcy.service;

import com.hcy.entity.Classes;

import java.util.List;

public interface ClassesService {
	public List<Classes> findAllClasses();

	public Classes findClasses(String cls_id);
}
