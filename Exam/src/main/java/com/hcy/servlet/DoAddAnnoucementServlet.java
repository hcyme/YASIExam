package com.hcy.servlet;

import com.hcy.dao.daoImpl.AnnouncementDaoImpl;
import com.hcy.entity.Announcement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/DoAddAnnoucementServlet")
public class DoAddAnnoucementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Announcement ann = new Announcement();
		String title = request.getParameter("a_title");
		System.out.println(title);
		String content = request.getParameter("a_content");
		System.out.println(content);
		ann.setA_title(title);
		ann.setA_content(content);
//		ann.setA_title(request.getParameter("a_title"));
//		ann.setA_content(request.getParameter("a_content"));
		String author = (String) request.getSession().getAttribute("tea_name");
		System.out.println(author);
		String admin = (String) request.getSession().getAttribute("adm_name");
		System.out.println(admin);
		if (null != author || !"".equals(author)) {
			ann.setA_author(author);
		} else {
			ann.setA_author(admin);
		}
		String start = request.getParameter("startTime");
		System.out.print(start);
		String end = request.getParameter("endTime");
		System.out.print(end);
		ann.setA_startTime(request.getParameter("startTime"));
		ann.setA_endTime(request.getParameter("endTime"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String createTime = sdf.format(date);
		ann.setA_createDate(createTime);

		AnnouncementDaoImpl adi = new AnnouncementDaoImpl();
		System.out.println(adi);
		adi.addAnnouncement(ann);
		request.getRequestDispatcher("/jsp/teacher/news_list.jsp").forward(request, response);
	}

}
