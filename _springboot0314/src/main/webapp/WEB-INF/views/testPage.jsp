<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2023-03-14
  Time: 오전 7:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:page>
    <jsp:attribute name="header">
        <h1>이것은 헤더</h1>
    </jsp:attribute>
    <jsp:attribute name="footer">
        <p id="copyright">Copyright 1927, Future Bits When There Be Bits Inc.</p>
    </jsp:attribute>
    <jsp:body>
        <h2>이것은 바디</h2>
    </jsp:body>
</t:page>
