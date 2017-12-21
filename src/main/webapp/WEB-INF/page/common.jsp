<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- c标签：定义项目的路径 -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
String contextPath = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+contextPath+"/";
%>
<!-- easyui默认主题样式 -->
<link rel="stylesheet" type="text/css" href="${ctx}/static/js/jquery-easyui-1.5.3/themes/default/easyui.css">
<!-- easyui图标样式-->
<link rel="stylesheet" type="text/css" href="${ctx}/static/js/jquery-easyui-1.5.3/themes/icon.css">
<!-- easyui颜色样式 -->
<link rel="stylesheet" type="text/css" href="${ctx}/static/js/jquery-easyui-1.5.3/themes/color.css">
<!-- 先引入jQuery核心的js -->
<script type="text/javascript" src="${ctx}/static/js/jquery-easyui-1.5.3/jquery.min.js"></script>
<!-- 在引入easyui的核心的js-->
<script type="text/javascript" src="${ctx}/static/js/jquery-easyui-1.5.3/jquery.easyui.min.js"></script>
<!-- 国际化的js-->
<script type="text/javascript" src="${ctx}/static/js/jquery-easyui-1.5.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/static/js/commons.js"></script>

<link href="${ctx}/static/js/jquery-easyui-1.5.3/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/js/jquery-easyui-1.5.3/icon.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/static/css/default.css" rel="stylesheet" type="text/css" />

