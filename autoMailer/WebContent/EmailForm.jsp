<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AutoMailer</title>
</head>
<body>
<h1>Auto Mailer</h1>
<form action = "EmailServlet"method ="post">
 <table border="0" width="35%" align="center">
         
            <tr>
                <td width="50%">Recipient address </td>
                <td><input type="text" name="emailAddress" size="50"/></td>
            </tr>
            <tr>
                <td>Subject </td>
                <td><input type="text" name="subject" size="50"/></td>
            </tr>
          	<tr>
                <td>Message </td>
                <td><textarea rows="10" cols="39" name="messageText"></textarea> </td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Send"/></td>
            </tr>
          
        </table>
</form>
</body>
</html>