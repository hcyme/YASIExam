package com.hcy.service;

import com.hcy.entity.Pager;
import com.hcy.entity.Scores;
import com.hcy.entity.SubAnswer;

import java.util.List;

public interface ScoreService {
	public Pager<Scores> findAllScore(Integer currentPage, Integer pageSize, String c_id, String cls_id,
                                      String keyword);

	public List<SubAnswer> findSubAnswer(String sc_id);

	public void correctSubAnswer(String sc_id, Float subScore);
}
