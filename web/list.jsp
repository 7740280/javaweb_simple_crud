<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" align="center">
    <tr>
        <th>id</th>
        <th>名称</th>
        <th>市场价</th>
        <th>销售价</th>
        <th>展示图</th>
        <th>参数</th>
        <th>商品描述</th>
        <th>编辑</th>
        <th>删除</th>
    </tr>
    <c:if test="${empty list}">
        <tr>
            <td colspan="7">没有找到商品</td>
        </tr>
    </c:if>
    <c:if test="${!empty list}">
        <c:forEach items="${list}" var="pro">
            <tr>
                <td>${pro.pid}</td>
                <td>${pro.pname}</td>
                <td>${pro.market_price}</td>
                <td>${pro.shop_price}</td>
                <td>${pro.pimage}</td>
                <td>${pro.pdate}</td>
                <td>${pro.pdesc}</td>
                <td><a href="${pageContext.request.contextPath}/product?method=udp&id=${pro.pid}">编辑</a></td>
                <td><a href="${pageContext.request.contextPath}/product?method=del&id=${pro.pid}">删除</a></td>
            </tr>
        </c:forEach>
    </c:if>
</table>
</body>
</html>
