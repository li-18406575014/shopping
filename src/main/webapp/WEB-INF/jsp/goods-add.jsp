<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Title</title>
<link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/dpl.css"
	rel="stylesheet">
<link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/bui.css"
	rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/index.css">
<!--<link rel="stylesheet" href="css/goods-add.css">-->
<link rel="stylesheet" href="<%=request.getContextPath() %>/font/iconfont.css">
</head>
<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
<body>
	<div class="doc-content">
		<ul class="breadcrumb breadcrumb2"></ul>
		<ul class="breadcrumb">
			<li><span class="iconfont icon-shouye"></span> <a href="#">首页</a>
				<span class="divider"><span
					class="iconfont icon-down-trangle1"></span></span></li>
			<li class="active" >商品新增</li>
		</ul>
	</div>

<div class="span95">
    <table cellspacing="0" class="table table-head-bordered table-bordered">
        <a href="<%=request.getContextPath() %>/ItemAddController/goToSkip">
            <div class="s-btn">
                <span style="color: black">新增</span>
            </div>
        </a>
        <thead>
        <tr style="">
            <th>教练图片</th>
            <th>教练名字</th>
            <th>教练介绍</th>
            <!-- <th>序号</th> -->
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${ban}" var="obj">
	        <tr>
	            <td>
	                <img src="<%=request.getContextPath() %>/serverimg/${obj.url}" alt="">
	            </td>
	            <td>${obj.name}</td>
	            <td>${obj.jieshao}</td>
	            <%-- <td>${obj.price}</td> --%>
	            <td><a href="<%=request.getContextPath() %>/ItemAddController/selectId?id=${obj.id}&type=update">编辑</a> <a href="<%=request.getContextPath() %>/ItemAddController/delectId?id=${obj.id}&type=delete">删除</a></td>
	        </tr>
        </c:forEach>
        
        </tbody>
    </table>
</div>


${page}
</body>
<script>


$.ajax({
    type: "POST",//请求方式
    url: "login.php",//地址，就是json文件的请求路径
    dataType: "json",//数据类型可以为 text xml json  script  jsonp
    success: function(result){//返回的参数就是 action里面所有的有get和set方法的参数
        addBox(result);
    }
});
</script>
<script type="text/javascript">
//根据后台传的值选中下拉框
$(function(){
$("#pageSize").val("${number}");
})
//点击分页按钮时触发的事件
function page(i){
//获取到下拉框选中的值
var pageSize = $("#pageSize").find("option:selected").text()
//跳转路径再次查询分页数据
window.location.href="<%=request.getContextPath()%>/ItemAddController/skip?currentPage="+i+"&pageSize="+pageSize+" "
}
</script>
</html>