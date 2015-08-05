<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="java.sql.*, java.io.*, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="docSingleBean" class="model.DocumentSingleBean"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List document</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<%@ include file="header.jsp"%>
<body>
	<h1 class="description">List document</h1>
	<br>
	<div class="box-table">
		<p>${message}</p>
		<form action="ControllerServlet" method=get>
			<input type="hidden" name="cabinet_id"
				value="<%=request.getParameter("cabinet_id")%>">

			<table border="1">
				<thead>
					<tr>
						<th>ID</th>
						<th>Nume Document</th>
						<th>Tip Document</th>
						<th>Tip Continut</th>
						<th>Dimensiune Continut</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${documents}" var="document">
					<jsp:setProperty property="documentId" name="docSingleBean"
						value="${document.documentId}" />
					<jsp:setProperty property="documentName" name="docSingleBean"
						value="${document.documentName}" />
					<jsp:setProperty property="documentType" name="docSingleBean"
						value="${document.documentType}" />
					<jsp:setProperty property="creationDate" name="docSingleBean"
						value="${document.creationDate}" />
					<jsp:setProperty property="contentType" name="docSingleBean"
						value="${document.contentType}" />
					<jsp:setProperty property="contentSize" name="docSingleBean"
						value="${document.contentSize}" />
					<jsp:setProperty property="contentPath" name="docSingleBean"
						value="${document.contentPath}" />
					<jsp:setProperty property="cabinetId" name="docSingleBean"
						value="${document.cabinetId}" />
					<tr>
						<td><input type="radio" name="document_id" id="document_id"
							value="<jsp:getProperty property="documentId" name="docSingleBean"/>"><jsp:getProperty
								property="documentId" name="docSingleBean" /></td>
						<td><jsp:getProperty property="documentName"
								name="docSingleBean" /></td>
						<td><jsp:getProperty property="documentType"
								name="docSingleBean" /></td>
						<td><jsp:getProperty property="contentType"
								name="docSingleBean" /></td>
						<td><jsp:getProperty property="contentSize" name="docSingleBean" />
						</td>
					</tr>
					   </c:forEach>
				</tbody>
			</table>
			<input type="submit" name="editareDoc" value="Editare" /> <input
				type="submit" name="stergeDoc" value="Sterge" /> <input
				type="submit" name="adaugaDoc" value="Adauga" /> <input
				type="submit" name="descarcaDoc" value="Descarca" />
		</form>
	</div>
</body>
</html>