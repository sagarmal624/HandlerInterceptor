<%@ page language="java"
    contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<head>
<style>
.error {
   color: red;
}
</style>
</head>
<body>

<h3> Login Page <h3>
<br/>
<form action="login" method="post" >
<pre>
         Email address <input type="text" name="emailAddress" />
              Password <input type="password" name="password" />
                        <input type="submit" value="Submit" />
                        <div class = "error">${error}</div>
</pre>
</form>
</body>
</html>