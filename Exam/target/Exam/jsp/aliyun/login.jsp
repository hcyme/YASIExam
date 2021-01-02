<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Hcybx~
  Date: 2020/12/29
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String name = request.getContextPath();
        System.out.println(name);

    %>
    <title>阿里云短信登录</title>
    <script>
        function reloadCode() {
            var time=new Date().getTime();
            //由于传过去的时间不一样，所以不是同一个请求
            document.getElementById("imageCode").src="<%=request.getContextPath()%>/imageServlet?d="+time;
        }
    </script>
</head>
<body>
<%--<%=request.getContextPath()%>--%>
    <form action="<%=request.getContextPath()%>/codeServlet" method="get">
        用户名：<input type="text" name="username'"/><br>
        密  码：<input type="password" name="password"/><br>
       <%-- <img id="btn" src="IdentifyingCodeServlet" height="38px" onload="btn.disabled=false;"/>
&lt;%&ndash;        <img src="servlet/SMServlet" id="code" onload="btn.disabled=false;" />&ndash;%&gt;
        <input type="button" value="换个图片" onload="reloadImg()" id="btn"><br>--%>
        <img id="imageCode" alt="验证码" src="<%=request.getContextPath()%>/imageServlet" onclick="reloadCode()"><br/>
        <input type="submit" value="提交">
    </form>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"></c:set>
<script>
    var con = "${contextPath}"
    console.log(con);
</script>
</body>
</html>
