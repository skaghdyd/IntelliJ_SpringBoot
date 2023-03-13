<%--
  Created by IntelliJ IDEA.
  User: NHY
  Date: 2023-03-10
  Time: 오후 12:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:sampleLayout title="My page">
    <jsp:attribute name="script_area">
        <script>
            function hello() {
                alert("Hello World");
            }
        </script>
    </jsp:attribute>
    <jsp:body>
        <button onclick="hello();">Hello There</button>
    </jsp:body>
</t:sampleLayout>
