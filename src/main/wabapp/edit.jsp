<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="user" class="ua.nure.kn.dobriak.usermanagement.User" scope="session" />
<html>
<head>
    <title>User management. Edit</title>
</head>
<body>
    <form action="<%= request.getContextPath() %>/edit" method="post">
        <input type="hidden" name="id" value="$(user.id)">
        First Name <input type="text" name="firstName" value="$(user.firstName)"><br>
        Last Name <input type="text" name="lastName" value="$(user.lastName)"><br>
        Date of Birth <input type="text" name="dateOfBirth" value="$(user.dateOfBirth)"><br>
        <input type="submit" name="okButton" value="Ok">
        <input type="submit" name="cancelButton" value="Cancel">
    </form>
    <c:if test="$(requestScope.error) != null">
        <script>
            alert('$(requestScope.error)')
        </script>
    </c:if>
</body>
</html>
