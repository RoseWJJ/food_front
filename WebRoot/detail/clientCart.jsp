<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/detail/style/css/index.css" />
	<script type="text/javascript">
		/** // É¾³ý²ËÆ·Ïî
		function removeSorder(node) {
			var gid = node.lang;
			window.location.href = "/wirelessplatform/sorder.html?method=removeSorder&gid="+gid;
		}
		
		// ÐÞ¸Ä²ËÆ·ÏîÊýÁ¿
		function alterSorder(node) {
			var snumber = node.value;
			var gid = node.lang;
			window.location.href = "/wirelessplatform/sorder.html?method=alterSorder&gid="+gid+"&snumber="+snumber;
		}
		*/
		// ÏÂµ¥
		function genernateOrder() {
			window.location.href = "clientOrderList.html";
		}
	</script>
</head>

<body style="text-align: center">
	<div id="all">
		<div id="menu">
			<!-- ²Í³µdiv -->
			<div id="count">
				<table align="center" width="100%">
					<tr height="40">
				 		<td align="center" width="20%">菜名</td>
				 		<td align="center" width="20%">单价</td>
				 		<td align="center" width="20%">数量</td>
				 		<td align="center" width="20%">小计</td>
				 		<td align="center" width="20%">操作</td>
				 	</tr>
				 	<c:forEach var="i" items="${sessionScope}">
				 		<c:if test="${fn:startsWith(i.key,'cart_')}">
					<tr height="60">
					 		<td align="center" width="20%">${pageScope.i.value.fname}</td>
					 		<td align="center" width="20%">￥${i.value.price}</td>
					 		<td align="center" width="20%">
					 			<input type="text" value="${pageScope.i.value.count}" size="3" lang="3" onblur="alterSorder(this)"/>
					 		</td>
					 		<td align="center" width="20%">${pageScope.i.value.count*pageScope.i.value.price}</td>
					 		<td align="center" width="20%">
					 			<input type="button" value="删除" class="btn_next" lang="3" onclick="window.location='${pageContext.request.contextPath}/FoodCart?flage=delete&cid=${pageScope.i.key}'" />
					 		</td>
				 	</tr>
				 	</c:if>
				</c:forEach>
					<tr>
						<td colspan="6" align="right">总计:
							<span style="font-size:36px;">&yen;&nbsp;</span>
							<label
								id="counter" style="font-size:36px"></label>
						</td>
					</tr>
					<tr>
						<td colspan="6" style="margin-left: 100px; text-align: center;"align="right">
							<input type="hidden" name="bId" value="">
									<input type="button" value="下单 " class="btn_next" onclick="window.location='${pageContext.request.contextPath}/FoodCart?flage=order'" />
						</td>
					</tr>
				</table>
			</div>
		</div>

		<!-- ÓÒ±ß²ËÏµÁÐ±í£¬²ËÆ·ËÑË÷¿ò  -->
		<jsp:include page="include.jsp"></jsp:include>
	</div>
</body>
</html>
