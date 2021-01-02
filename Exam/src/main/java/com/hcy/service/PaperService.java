package com.hcy.service;

import com.hcy.entity.Pager;
import com.hcy.entity.Paper;
import com.hcy.entity.SubAnswer;

public interface PaperService {
	public Paper findPaperById(String pid);

	public Pager<Paper> findAllPaper(Integer currentPage, Integer pageSize, String c_id, String keyword);

	public Pager<Paper> findPaperByClassesId(Integer currentPage, Integer pageSize, String classesId);

	public void saveSubAnswer(SubAnswer answer);

	public void saveScore(String sc_id, String pid, String sid, Float score, String cid, String pname, String sname,
                          String cls_id, String cls_name);
}
