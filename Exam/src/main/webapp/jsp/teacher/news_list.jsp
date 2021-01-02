<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page
	import="java.util.*, com.hcy.dao.daoImpl.*, com.hcy.entity.*,com.hcy.service.*,com.hcy.service.serviceImpl.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>"></base>
<meta charset="utf-8">
<title>亚斯在线考试系统|消息中心</title>
<link rel="stylesheet" type="text/css" href="css/teacher_common.css" />
<link rel="stylesheet" type="text/css" href="css/teacher_main.css" />
<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
</head>
<script type="text/javascript">
	$(function() {
		var skipBtn = $("#skip");
		var startBtn = $("#startPage");
		var upBtn = $("#upPage");
		var nextBtn = $("#nextPage");
		var endBtn = $("#endPage");

		//跳转页面
		function skipPage(page) {
			with (document.getElementById("page")) {
				method = "post";
				action = "jsp/teacher/news_list.jsp?pager=" + page;
				submit();
			}
		}

		skipBtn.click(function() {
			var page = document.getElementById("skipPage").value;
			skipPage(page);
		});

		startBtn.click(function() {
			var page = '${pager.currentPage}';
			skipPage(page);
		});
		upBtn.click(function() {
			var page = Number('${pager.currentPage}') - 1;
			if (page != 0) {
				skipPage(page);
			} else {
				skipPage(1);
			}
		});
		nextBtn.click(function() {
			var page = Number('${pager.currentPage}') + 1;
			if (page > '${pager.totalPage}') {
				skipPage('${pager.currentPage}');
			} else {
				skipPage(page);
			}
		});
		endBtn.click(function() {
			var page = '${pager.totalPage}';
			skipPage(page);
		});

	});
</script>
<body>
	<%
		AnnouncementService adi = new AnnouncementServiceImpl();
		Announcement ann = new Announcement();
		String num = request.getParameter("pager");
		if ("" != num && null != num) {
			Pager<Announcement> pager = adi.findAllAnnouncement(Integer.parseInt(num), 5);
		}
		Pager<Announcement> pager = adi.findAllAnnouncement(1, 5);
		request.setAttribute("pager", pager);
	%>
	<div class="topbar-wrap white">
		<div class="topbar-inner clearfix">
			<div class="topbar-logo-wrap clearfix">
				<ul class="navbar-list clearfix">
					<li><a href="jsp/teacher/index.jsp">亚斯在线考试系统</a></li>
				</ul>
			</div>
			<div class="top-info-wrap">
				<ul class="top-info-list clearfix">
					<li><a href="jsp/teacher/index.jsp">你好！${tea_name }</a></li>
					<li><a href="jsp/teacher/modify_password.jsp">修改密码</a></li>
					<li><a href="QuitServlet?status=teacher">退出</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="container clearfix">
		<div class="sidebar-wrap">
			<div class="sidebar-title">
				<h1>菜单</h1>
			</div>
			<div class="sidebar-content">
				<ul class="sidebar-list">
					<li>
						<ul class="sub-menu">
							<li><a href="jsp/teacher/index.jsp"><i class="icon-font">&#xe012;</i>个人中心</a>
							</li>
							<li class="on"><a href="jsp/teacher/news_list.jsp"><i
									class="icon-font">&#xe012;</i>消息中心</a></li>
							<li><a
								href="servlet/ManageQuestion.do?flag=find&type=0&pager=1&manager=teacher"><i
									class="icon-font">&#xe008;</i>试题管理</a></li>
							<li><a href="PaperManageServlet?operation=update"><i
									class="icon-font">&#xe005;</i>考试管理</a></li>
							<li><a
								href="Class_List_BrowseServlet?updateclassform=updateClassForm"><i
									class="icon-font">&#xe005;</i>班级管理</a></li>
							<li><a
								href="servlet/ScoreCorrectServlet.do?operation=toScoreCorrect"><i
									class="icon-font">&#xe005;</i>批改试卷</a></li>
							<li><a href="CountServlet"><i class="icon-font">&#xe005;</i>成绩统计</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
		<!--/sidebar-->
		<div class="main-wrap">

			<div class="crumb-wrap">
				<div class="crumb-list">
					<i class="icon-font"></i> <a href="jsp/teacher/index.jsp">首页</a><span
						class="crumb-step">&gt;</span><span class="crumb-name">消息中心</span>
				</div>
			</div>
			<div class="search-wrap">
				<div class="search-content">
					<form action="#" method="post">
						<table class="search-tab">
							<tr>
								<%--<th width="70">关键字:</th>
								<td><input class="common-text" placeholder="关键字"
									name="keyword" value="" id="" type="text"></td>
								<td><input class="btn btn-primary btn2" name="sub"
									value="查询" type="submit"></td>--%>
							</tr>
						</table>
					</form>
				</div>
			</div>
			<div class="result-wrap">
				<form name="myform" id="myform" method="post">
					<div class="result-title">
						<div class="result-list">
							<a href="jsp/teacher/news_add.jsp"><i class="icon-font"></i>发布消息</a>
							<%--<a id="batchDel" href="javascript:void(0)"><i
								class="icon-font"></i>批量删除</a> <a id="updateOrd"
								href="javascript:void(0)"><i class="icon-font"></i>更新排序</a>--%>
						</div>
					</div>
					<div class="result-content">
						<table class="result-tab" width="100%">
							<tr>
								<th class="tc" width="5%"><input class="allChoose" name=""
									type="checkbox"></th>
								<th width="5%">ID</th>
								<th width="20%">标题</th>
								<th width="30%">内容</th>
								<th width="20%">有效时间</th>
								<th width="7%">发布人</th>
								<th width="10%">操作</th>
							</tr>
							<c:if test="${! empty pager }">
								<c:if
									test="${! empty pager.getDateList() && pager.getDateList().size() >0 }">
									<c:forEach var="ann" items="${pager.getDateList() }"
										varStatus="v">
										<tr>
											<td class="tc"><input name="id[]" value="59"
												type="checkbox"></td>
											<td>${v.index+1 }</td>
											<td>${ann.a_title}</td>
											<td>${ann.a_content}</td>
											<td>${ann.a_startTime}--${ann.a_endTime}</td>
											<td>${ann.a_author}</td>
											<td><a class="link-update"
												href="DoUpdateAnnouncementServlet?operation=toUpdate&ann_id=${ann.a_id}">修改</a>
												<a class="link-del ss-del-btn" data-id="${ann.a_id}">删除</a>
											</td>
										</tr>
									</c:forEach>
								</c:if>
							</c:if>
						</table>
						<c:if
							test="${ empty pager.getDateList() || pager.getDateList().size() == 0 }">
									没有任何公告信息</c:if>
					</div>
				</form>
				<jsp:include page="/jsp/page.jsp"></jsp:include>
			</div>
		</div>
		<!--/main-->
	</div>
</body>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/news.js"></script>
</html>