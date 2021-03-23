<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Mayray
  Date: 17.03.2021
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello!</title>
</head>
<body>
<form action="./FrontController" method="post">
<div>
    <h1>Rooms</h1>
</div>
<div>
    <table>
        <tr>
            <td>RoomId</td>
            <td>RoomName</td>
            <td>RoomCountry</td>
            <td>Lamp is on</td>
        </tr>
        <c:forEach var="lamp" items="${lamps}">
            <tr>
                <td>${lamp.id}</td>
                <td>${lamp.roomName}</td>
                <td>${lamp.lampCountry}</td>
                <td>${lamp.work}</td>
                <td><button type="submit" name="button" value="light${lamp.id}">light on/off</button></td>
            </tr>
        </c:forEach>
    </table>
</div>
</form>
</body>
</html>
