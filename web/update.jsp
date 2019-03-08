<%--
  Created by IntelliJ IDEA.
  User: jiee
  Date: 2019-03-08
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/product?method=edit" method="post">
    <input type="hidden" name="pid" value="${product.pid}">
    商品名称 : <input type="text" name="pname" value="${product.pname}"> <br>
    市场价 : <input type="text" name="market_price" value="${product.market_price}"><br>
    销售价 : <input type="text" name="shop_price" value="${product.shop_price}"><br>
    日期 : <input type="text" name="pdate" value="${product.pdate}"> <br>
    描述: <textarea name="pdesc" id="" cols="30" rows="10"><br>
                ${product.pdesc}
            </textarea><br>

    <input type="submit">
</form>

</body>
</html>
