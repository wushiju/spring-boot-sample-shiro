<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户管理</title>
</head>
<body>
    <ul>
        <shiro:hasPermission name="add"><li>增加</li></shiro:hasPermission>
        <shiro:hasPermission name="delete"><li>删除</li></shiro:hasPermission>
        <shiro:hasPermission name="update"><li>修改</li></shiro:hasPermission>
        <shiro:hasPermission name="query"><li>查询</li></shiro:hasPermission>
    </ul>
        <a href="${pageContext.request.contextPath }/logOut">点我注销</a>
</body>
</html>