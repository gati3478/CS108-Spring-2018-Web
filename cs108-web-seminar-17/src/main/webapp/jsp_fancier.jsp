<%@ page import="java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Insert title here</title>
</head>
<body>
<%
    java.util.Date now = new java.util.Date();

    String comment = "";

    Calendar cal = Calendar.getInstance();
    cal.setTime(now);

    switch (cal.get(Calendar.MONTH)) {
        case 0:
        case 1:
            comment = "very cold";
            break;
        case 2:
        case 3:
        case 4:
            comment = "getting warmer";
            break;
        case 5:
        case 6:
        case 7:
            comment = "summer!";
            break;
        case 8:
            comment = "school time";
            break;
        case 9:
            comment = "getting colder";
            break;
        case 10:
            comment = "Thanksgiving";
            break;
        case 11:
            comment = "break time";
            break;
    }
    ;

    out.println("It is " + comment);

%>

</body>
</html>