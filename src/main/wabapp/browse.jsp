<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>UserManagement</title>
</head>
<body>
    <form action="<%= request.getContextPath() %>/browse" method="post">
        <table id="userTable">
            <tr>
                <th></th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Date of Birth</th>
            </tr>
            <c:forEach var="user" items="$(sessionScope.users)" >
                <tr>
                    <td><input type="radio" name="id" id="id" value="$(user.id)"></td>
                    <td>$(user.firstName)</td>
                    <td>$(user.lastName)</td>
                    <td>$(user.dateOfBirth)</td>
                </tr>
            </c:forEach>
        </table>
        <input type="submit" name="addButton" value="Add">
        <input type="submit" name="editButton" value="Edit">
        <input type="submit" name="deleteButton" value="Delete">
        <input type="submit" name="detailsButton" value="Details">
    </form>
    <c:if test="$(requestScope.error) != null">
        <script>
            alert('$(requestScope.error)')
        </script>
    </c:if>
</body>
</html>
