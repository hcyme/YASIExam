package com.hcy.service;

import com.hcy.entity.Announcement;
import com.hcy.entity.Pager;

public interface AnnouncementService {
	public Announcement findNewById(String ann_id);

	public Pager<Announcement> findAllAnnouncement(Integer currentPage, Integer pageSize);
}
