<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page language="java" import="java.sql.*, java.io.*, java.util.*,model.DocumentRepeatingBean"%>
<jsp:useBean id="docSingleBean" class="model.DocumentSingleBean"></jsp:useBean>
<jsp:useBean id="docRepeatingBean" class="model.DocumentRepeatingBean"></jsp:useBean>
<jsp:useBean id="docRepeatingService" class="service.DocumentRepeatingService"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit document</title>
<link rel="stylesheet" type="text/css" href="css/style.css" /> 
<script type="text/javascript" src="js/javaScript.js"></script>
</head>
<%@ include file="header.jsp" %>
<body>
<h1 class="description">Edit document</h1>
<br>
<div class="box-table">
<form name="myForm" action="ControllerServlet" method=get enctype="multipart/form-data">
<input type="hidden" name="document_id" value="<%=request.getParameter("document_id")%>"/>
<input type="hidden" name="cabinet_id" value="<%=request.getParameter("cabinet_id")%>"/>
		<table>
		<tr>
			<td>
				<table>
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
						<td><label>Nume Document</label></td>
						<td><input type="text" onblur="return validateName(this)" name="documentName" value="<jsp:getProperty property="documentName" name="docSingleBean"/>">
						<span id="numeError"></span>
						</td>
					</tr>
					<tr>
						<td><label>Tip Document</label></td>
						<td>
							<select name="documentType">
							<%if ("Factura".equalsIgnoreCase(docSingleBean.getDocumentType())){%>
								<option value="Factura" selected="selected">Factura</option>
								<option value="Corespondenta">Corespondenta</option>
								<%}else if ("Corespondenta".equalsIgnoreCase(docSingleBean.getDocumentType())) {%>
								<option value="Factura" >Factura</option>
								<option value="Corespondenta" selected="selected">Corespondenta</option>
								<%} if(docSingleBean.getDocumentType() == null || "".equalsIgnoreCase(docSingleBean.getDocumentType())){%>
								<option value="" selected="selected"></option>
								<option value="Factura" >Factura</option>
								<option value="Corespondenta">Corespondenta</option>
								<%}%>
							</select>
						</td>
					</tr>
					<tr>
						<td><label>Creation Date</label></td>
						<td><input type="text" readonly value="<jsp:getProperty property="creationDate" name="docSingleBean"/>"></td>
					</tr>
					<tr><td colspan="2"><input type="file" name="pathFile"></td></tr>
					
				</table>
			</td>
			<td>
				<table>
				 
			<% List<DocumentRepeatingBean> documents=new ArrayList<DocumentRepeatingBean>();
			documents = docRepeatingService.getDocRepeatingForEdit(request.getParameter("document_id"));
			String authors="",keywords="";
			for (int i=0; i<documents.size(); i++){
			%>
			<jsp:setProperty property="documentId" name="docRepeatingBean" value="<%=documents.get(i).getDocumentId()%>"/>
			<jsp:setProperty property="docIndex" name="docRepeatingBean" value="<%=documents.get(i).getDocIndex()%>"/>
			<jsp:setProperty property="docAuthors" name="docRepeatingBean" value="<%=documents.get(i).getDocAuthors()%>"/>
			<jsp:setProperty property="docKeywords" name="docRepeatingBean" value="<%=documents.get(i).getDocKeywords()%>"/>
		<%authors+=documents.get(i).getDocAuthors()+"\n";
		keywords+=documents.get(i).getDocKeywords()+"\n";
		}  %>
		<tr>		
						<td><label>Autori</label></td>
						<td><textarea name="authors"><%=authors %></textarea></td>
					</tr>
					<tr>
						<td><label>Cuvinte Cheie</label></td>
						<td><textarea name="keywords"><%=keywords %></textarea></td>
					</tr>
				</table>
			</td>
		</tr>
		</table>
		<input type="submit" name="salveazaDocEdit" value="Salveaza" onclick="return validateForm()"/>
		<input type="submit" name="anuleazaDocEdit" value="Anuleaza" />
	</form>
	</div>
</body>
</html>