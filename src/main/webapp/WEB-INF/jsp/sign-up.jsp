<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration page</title>
<link href="resources/css/style.css" type="text/css" rel="stylesheet" />
</head>
<body>

<form id="sign-up-Form" action="MyController" method="post">
	<input type="hidden" name="command" value="sign-up">
	<h1>Create account</h1>
	<input id="name" type="text" placeholder="Name" name ="name" minlength="2" maxlength="25"/>
	<span class="error" aria-live="polite"></span>
	<br />
	<input id="surname" type="text" placeholder="Surname" name ="surname" minlength="2" maxlength="25"/>
	<span class="error" aria-live="polite"></span>
	<br />
	<input id="email" type="email" placeholder="Email" name ="email" minlength="8" maxlength="50"/>
	<span class="error" aria-live="polite"></span>
	<br />
	<input id="password" type="password" placeholder="Password" name ="password" minlength="10" maxlength="25"/>
	<span class="error" aria-live="polite"></span>
	<br />
	<input id="confirm-password" placeholder="Confirm password" name ="confirm-password" type="password" />
	<span class="error" aria-live="polite"></span>
	<br />
	<div id="buttons">
	<input type="submit" value="SIGN UP"/>
	</div>
	<h5>Already have an account? <a href="MyController?command=GO_TO_SIGN_IN_PAGE">Sign in</a></h5>
	<h5>Or you can go back to <a href="MyController?command=GO_TO_INDEX_PAGE">main page</a></h5>
	
</form>
	<script src="resources/js/sign-up-validation.js"></script>
</body>
</html>