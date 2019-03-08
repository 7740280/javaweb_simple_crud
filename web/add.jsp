<%--
  Created by IntelliJ IDEA.
  User: jiee
  Date: 2019-03-06
  Time: 22:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/product" method="post">
    <input type="hidden" name="method" value="add">
    商品名称: <input type="text" name="pname"> <br>
    市场价格: <input type="text" name="market_price"> <br>
    商城价格: <input type="text" name="shop_price"> <br>
    商品描述: <textarea name="pdesc" id="" cols="30" rows="10"></textarea> <br>
    <input type="submit">
</form>
</body>
</html>
