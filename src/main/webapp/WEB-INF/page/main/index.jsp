<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页管理</title>

<script type="text/javascript">

	//打开菜单
	function createFrame(url) {
		var s = '<iframe scrolling="auto" frameborder="0"  src="' + '<%=contextPath%>' + url + '" style="width:100%;height:100%;"></iframe>';
		return s;
	}
	
	//在右边center区域打开菜单，新增tab
    function openUrl(text, url) {
		console.log("openUrl:<%=contextPath%>/" + url);
        if ($("#tabs").tabs('exists', text)) {
            $('#tabs').tabs('select', text);
        } else {
            $('#tabs').tabs('add', {
                title : text,
                closable : true,
//                 content结合iframe可以跨域访问
//                 content : createFrame(url)
                //href不能跨域访问
                href:"<%=contextPath%>" + "/" + url
            });
        }
    }
	//右键菜单的具体实现
	function closeTab(menu, type){
		var curTabTitle = $(menu).data("tabTitle");
        var tabs = $("#tabs");
        if (type === "mm-tabclose") {
            tabs.tabs("close", curTabTitle);
            return;
        }
        var allTabs = tabs.tabs("tabs");
        var closeTabsTitle = [];
        
        var homeOpt = allTabs[0].panel("options");
        $.each(allTabs, function () {
            var opt = $(this).panel("options");
            if (opt.closable && opt.title != curTabTitle && type === "mm-tabcloseother") {
                closeTabsTitle.push(opt.title);
            } else if (opt.closable && type === "mm-tabcloseall") {
                closeTabsTitle.push(opt.title);
            } else if (opt.closable && type === "mm-tabcloseright") {
            	//关闭当前右侧的tab
            	var nextall = $('.tabs-selected').nextAll();
            	if(nextall.length > 0){
            		nextall.each(function(i, n) {
            			var t = $('a:eq(0) span', $(n)).text();
            			if(t == homeOpt.title && homeOpt.closable){
            				closeTabsTitle.push(t);
            			}else if(t != homeOpt.title){
            				closeTabsTitle.push(t);
            			}
            		});
            	}
            	
            } else if (opt.closable && type === "mm-tabcloseleft"){
            	//关闭当前左侧的tab
            	var prevAll = $('.tabs-selected').prevAll();
            	if(prevAll.length > 0){
            		prevAll.each(function(i, n) {
            			var t = $('a:eq(0) span', $(n)).text();
            			if(t == homeOpt.title && homeOpt.closable){
            				closeTabsTitle.push(t);
            			}else if(t != homeOpt.title){
            				closeTabsTitle.push(t);
            			}
            		});
            	}
            }
        });
        for (var i = 0; i < closeTabsTitle.length; i++) {
            tabs.tabs("close", closeTabsTitle[i]);
        }
	}
	$(function(){
		//绑定右键菜单
		$("#tabs").tabs({
	        onContextMenu : function (e, title) {
	            e.preventDefault();
	            $('#mm').menu('show', {
	                left : e.pageX,
	                top : e.pageY
	            }).data("tabTitle", title);
	        }
	    });
		
		//绑定右键菜单的onClick事件
	    $("#mm").menu({
	        onClick : function (item) {
	            closeTab(this, item.id);
	        }
	    });
		
	    $('#tt').tree({
	    	onClick: function(node){
				var tree = $('#tt').tree;
				var isLeaf = tree('isLeaf', node.target);
				if(isLeaf){
					//叶子节点跳转
					var url = node.url;
					openUrl(node.text, url);
				}
	    	}
	    });
	});

</script>

</head>

<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height: 60px; padding: 10px">
		<span style="padding-left: 10px; font-size: 16px; text-align: center;">后台管理系统</span>
	</div>

	<div data-options="region:'west',split:true,title:'导航菜单'"
		style="width: 200px; padding: 10px;">
    	<ul id="tt" class="easyui-tree" url="<%=contextPath %>/menu/querAllMenus.do"></ul>
	</div>

	<div data-options="region:'south',border:false"
		style="height: 50px; padding: 10px;">
		<div class="footer">&copy;版权所有 &copy; 现代金融控股（成都）有限公司</div>
	</div>
	
	<div data-options="region:'center',title:'Center'">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="首&nbsp;&nbsp;页" style="padding: 20px; overflow: hidden;"
				id="home">
				<h1>欢迎您登录后台管理系统！</h1>
<!-- 				<hr style="width: 100%;" /> -->
			</div>
		</div>
	</div>
	
	
    <div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>	
</body>
</html>