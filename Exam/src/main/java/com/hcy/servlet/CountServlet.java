package com.hcy.servlet;

import com.hcy.entity.Classes;
import com.hcy.entity.Count;
import com.hcy.entity.Course;
import com.hcy.entity.Pager;
import com.hcy.service.ClassesService;
import com.hcy.service.CountService;
import com.hcy.service.QuestionService;
import com.hcy.service.serviceImpl.ClassesServiceImpl;
import com.hcy.service.serviceImpl.CountServiceImpl;
import com.hcy.service.serviceImpl.QuestionServiceImple;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class CountServlet
 */
@WebServlet("/CountServlet")
public class CountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int pageSize;
	private String path;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String c_id = request.getParameter("c_id");
		String cls_id = request.getParameter("cls_id");
		String keyword = request.getParameter("keyword");
		String pageNum = request.getParameter("pager");
		Integer currentPage = 1;
		if (pageNum != null) {
			currentPage = Integer.parseInt(pageNum);
		}
		CountService countSev = new CountServiceImpl();
		List<Count> countList = countSev.claculateCount();
		for (Count count : countList) {
			if (!countSev.ifHaveRecore(count.getP_id())) {
				countSev.insertCount(count);
			} else {
				countSev.updateCount(count);
			}
		}
		Pager<Count> pager = countSev.findAllCount(currentPage, pageSize, c_id, cls_id, keyword);
		QuestionService questionService = new QuestionServiceImple();
		List<Course> courseList = questionService.findAllCourseType();
		request.setAttribute("courseList", courseList);
		ClassesService classesSev = new ClassesServiceImpl();
		List<Classes> classesList = classesSev.findAllClasses();
		request.setAttribute("classesList", classesList);
		request.setAttribute("pager", pager);
		request.getRequestDispatcher("/jsp/teacher/test_count_list.jsp").forward(request, response);

	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);

		path = config.getServletContext().getContextPath();
		String pSize = config.getServletContext().getInitParameter("pageSize");

		pageSize = Integer.parseInt(pSize);
	}

}
