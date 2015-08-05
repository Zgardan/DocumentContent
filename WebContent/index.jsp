<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*, java.io.*, java.util.*,model.CabinetBean"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="cabinetBean" class="model.CabinetBean"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Index page</title>
<link rel="stylesheet" type="text/css" href="css/style.css" /> 
</head>
<body>
	<%@ include file="header.jsp" %>
	<h1 class="description">List cabinet</h1>
	<br>
	<div class="box-table">
	<p>${message}</p>
	<form action="ControllerServlet" method=get>
		<table border="1">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nume Cabinet</th>
					<th>Detinator</th>
					<th>Data Crearii</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${cabinets}" var="cabinet">
			<jsp:setProperty property="cabinetId" name="cabinetBean" value="${cabinet.cabinetId}"/>
			<jsp:setProperty property="cabinetName" name="cabinetBean" value="${cabinet.cabinetName}"/>
			<jsp:setProperty property="ownerName" name="cabinetBean" value="${cabinet.ownerName}"/>
			<jsp:setProperty property="creationDate" name="cabinetBean" value="${cabinet.creationDate}"/>
                <tr>
                    <td><input type="radio" name="cabinet_id" id="cabinet_id"
						value="<jsp:getProperty property="cabinetId" name="cabinetBean"/>"><jsp:getProperty property="cabinetId" name="cabinetBean"/></td>
                    <td><jsp:getProperty property="cabinetName" name="cabinetBean"/></td>
                    <td><jsp:getProperty property="ownerName" name="cabinetBean"/></td>
                    <td><jsp:getProperty property="creationDate" name="cabinetBean"/></td>
                </tr>
                </c:forEach>
			</tbody>
		</table>
		<input type="submit" name="stergeCab" value="Sterge" />
		 <input type="submit" name="creeazaCab" value="Creeaza" />
		 <input type="submit" name="acceseazaCab" value="Acceseaza"/>
	</form>
	</div>
</body>
</html>