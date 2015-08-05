<%@page import="java.text.DateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*, javax.servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add document</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<script type="text/javascript" src="js/javaScript.js"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h1 class="description">Add document</h1>
	<div class="box-table">
		<form name="myForm" action="ControllerServlet" method=get
			enctype="multipart/form-data">
			<input type="hidden" name="cabinet_id"
				value="<%=request.getParameter("cabinet_id")%>">
			<table>
				<tr>
					<td>
						<table>
							<tr>
								<td><label>Nume Document</label></td>
								<td><input type="text" name="documentName" onblur="return validateName(this)"><span id="numeError"></span><br></td>
							</tr>
							<tr>
								<td><label>Tip Document</label></td>
								<td><select name="documentType">
										<option value="" selected="selected"></option>
										<option value="Factura">Factura</option>
										<option value="Corespondenta">Corespondenta</option>

								</select></td>
							</tr>
							<tr>
								<%
									Date today = new Date();
									DateFormat defaultDateFormate = DateFormat.getDateInstance();
									DateFormat shortDF = DateFormat.getDateInstance(DateFormat.SHORT);
								%>
								<td><label>Creation Date</label></td>
								<td><input type="text" readonly
									value=<%=shortDF.format(today)%>></td>
							</tr>
							<tr>
								<td colspan="2"><input type="file" name="pathFile"></td>
							</tr>
						</table>
					</td>
					<td>
						<table>
							<tr>
								<td><label>Autori</label></td>
								<td><textarea name="authors"></textarea></td>
							</tr>
							<tr>
								<td><label>Cuvinte Cheie</label></td>
								<td><textarea name="keywords"></textarea></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<input type="submit" name="salveazaDocAdd" value="Salveaza" onclick="return validateForm()"> 
			<input type="submit" name="anuleazaDocAdd" value="Anuleaza" />
		</form>
	</div>
</body>
</html>