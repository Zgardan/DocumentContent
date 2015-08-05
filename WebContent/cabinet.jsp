<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Creare cabinet</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/javaScript.js"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h1 class="description">Creeaza</h1>
	<div class="box-table">
		<form name="myForm" action="ControllerServlet" method=get>
			<table>
				<tr>
					<td><label>Nume cabinet :</label></td>
					<td><input type="text" name="cabinet_name"
						onblur="return validateName(this)"><span id="numeError"></span><br></td>
				</tr>
				<tr>
					<td><label>Tip aplicat :</label></td>
					<td><input type="text" name="owner_name"
						onblur="return validateOwner(this)"><span id="ownerError"></span><br>
				<tr>
				<tr>
					<td colspan="2"><input type="submit" name="salveazaCab"
						value="Salveaza" onclick="return validateFormCreare()"> <input
						type="submit" name="anuleazaCab" value="Anuleaza"></td>
			</table>
		</form>
	</div>
</body>
</html>