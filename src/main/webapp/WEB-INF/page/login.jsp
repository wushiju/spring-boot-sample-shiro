<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common.jsp"%>

<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<title>后台管理系统</title>
<link rel="stylesheet" type="text/css"
	href="<%=contextPath %>/static/css/denglu.css" />
<link rel="stylesheet" type="text/css"
	href="<%=contextPath %>/static/css/public.css" />
<script type="text/javascript" src="/static/js/jquery.SuperSlide.2.1.js"></script>

<style>
.carousel {
	width: 750px;
	height: 430px;
	float: left;
}
/* 本例子css */
.focusBox {
	margin: 0 auto;
	position: relative;
	width: 750px;
	height: 330px;
	margin-top: 50px;
	padding: 5px;
	background: #f5f5f5;
	overflow: hidden;
}

.focusBox .hd {
	height: 22px;
	position: absolute;
	z-index: 1;
	bottom: 10px;
	right: 10px;
	overflow: hidden;
}

.focusBox .hd ul {
	float: right;
	overflow: hidden;
}

.focusBox .hd li {
	width: 22px;
	height: 22px;
	line-height: 22px;
	text-align: center;
	background: #999;
	color: #fff;
	font-family: Arial;
	float: left;
	margin: 0 1px;
	display: inline;
	-webkit-border-radius: 22px;
	-moz-border-radius: 22px;
	border-radius: 22px;
	cursor: pointer;
}

.focusBox .hd li.on {
	background: #c00;
}

.focusBox .bd li {
	vertical-align: middle;
}

.focusBox .bd li img {
	width: 750px;
	height: 330px;
	display: block;
	background: url(static/img/denglu/loading.gif) center center no-repeat;
}
</style>

<script type="text/javascript">

// 	function login(){
// 		$.ajax({ //使用ajax与服务器异步交互
// 	        url:"Login?s="+new Date().getTime(), //后面加时间戳，防止IE辨认相同的url，只从缓存拿数据
// 	        type:"POST",
// 	        data: $('#form').serialize(), 
// 	        dataType:"json", //接收返回的数据方式为json
// 	        error:function(XMLHttpRequest,textStatus,errorThrown){
// 	            alert("网络错误！");
// 	        }, //错误提示
// 	        success:function(data){ //data为交互成功后，后台返回的数据
// 	        	if(data.flag){
// 	        		window.location.href="main.html";
// 	        	}else{
// 	        		alert("信息填写错误，登录失败！");
// 	        	}
// 	        }
// 	    });
// 	}


	function doLogin(){
		var name = $("#name").val();
		var pass = $("#password").val();
		if(!$.trim(name)){
			alert("登录用户名不能为空！");
			return;
		}
		if(!$.trim(pass)){
			alert("登录密码不能为空！");
			return;
		}
		var param = {};
		param.userName = name;
		param.password = pass;
		
		console.log(param);
		
        $.ajax({
            url: "/user/login.do",    //向springboot请求数据的url
            type: "post",
            data: param,
//             dataType: "json",
            success: function (data) {
                if ("0000" == data.retCode) {
	                location.href = "/main/index";
                } else {
                	alert(data.retMsg);
                }
            },
            error:function() {
            	console.log("error...");
            }
        });			
	}
		
// 		$(document).ready(function(){
// 			$("#name").keyup(function(e){
// 				var curKey = e.which;
// 				if(curKey == 13){
// 					$("#password").focus();
// 					return;
// 				}
// 			});
			
// 			$("#password").keyup(function(e){
// 				var curKey = e.which;
// 				if(curKey == 13){
// 					doLogin();
// 					return;
// 				}
// 			});
// 		});
		</script>

</head>
<body>
	<!-- 整体内容开始 -->
	<div class="denglu">
		<!--第一部分内容开始-->
		<div class="denglu-first1"></div>
		<!--第一部分内容结束-->

		<!--第二部分内容开始-->
		<div class="denglu-frist2">
			<div class="denglu-first2-content">
				<div class="denglu-first2-content-top">
					<div class="carousel">
						<div class="focusBox">
							<div class="bd">
								<ul>
									<li><a target="_blank" href="#"><img _src="/static/img/denglu/bg1.jpg" src="/static/img/denglu/3.png" /></a></li>
									<li><a target="_blank" href="#"><img _src="/static/img/denglu/bg2.jpg" src="/static/img/denglu/3.png" /></a></li>
									<li><a target="_blank" href="#"><img _src="/static/img/denglu/bg3.jpg" src="/static/img/denglu/3.png" /></a></li>
								</ul>
							</div>
							<div class="hd">
								<ul></ul>
							</div>
						</div>
						<script type="text/javascript">
							jQuery(".focusBox").slide({ titCell:".hd ul", mainCell:".bd ul", effect:"top", autoPlay:true, autoPage:true, switchLoad:"_src" });
							</script>
					</div>

					<div class="denglu-top-contents">
						<div class="denglu-top-contents-top"></div>
						<div class="denglu-top-contents-bottom">
							<div class="contents-bottom-neirong">
								<div class="contents-bottom-neirong-first1">
									<div class="hasMoreTab" style="margin: 0 auto">
										<div class="hd">
											<ul>
												<li class="li1">管理员登录</li>
												<!-- <li>商家用户登录入口</li> -->
											</ul>
										</div>
										<div class="bd">
<!-- 											<form id="loginForm" method ="post"  action ="/user/login.do"> -->
												<div class="conWrap">
													<div class="con">
														<div class="yonghuming">
															<input type="text" id="name" name="name" value=""
																placeholder="用户名" class="shurukuang" />
														</div>
														<div class="yonghuming-mima">
															<input type="password" id="password" name="password"
																value="" placeholder="密码" class="shurukuang" />
														</div>
														<div class="xieyi"></div>
														<a href="javascript:doLogin()">
															<div class="denglu1">
																<p>登录</p>
															</div>
														</a>
														<div class="zhuce">
														</div>
													</div>
												</div>
<!-- 											</form> -->
										</div>
										<script type="text/javascript">
											jQuery(".hasMoreTab").slide({ mainCell:".conWrap", targetCell:".more a", effect:"fold"});
											</script>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--第二部分内容结束-->
		</div>
		<!--登录尾部内容开始-->
		<div class="denglu-footer">
			<div class="denglu-footer-content">
				现代金融控股（成都）有限公司 版权所有 2015-2016 蜀ICP备1111111111111号&nbsp;&nbsp; <a
					href="#">关于我们</a>&nbsp; |&nbsp; <a href="#">帮助中心</a> &nbsp;|&nbsp;
				<a href="#">网站合作</a>&nbsp;|&nbsp; <a href="#">版权说明</a>&nbsp;|&nbsp;
				<a href="#">诚聘英才</a>&nbsp;|&nbsp; <a href="#">联系我们</a>
			</div>
		</div>
		<!--登录尾部内容结束-->
	</div>
</body>
</html>
