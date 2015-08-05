package service;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DocumentSingleBean;
import utility.ConnectDB;

public class DocumentSingleService {
	ConnectDB connectDB = new ConnectDB();
	
	public List<DocumentSingleBean> getCabinetDoc(String id) throws SQLException, IOException {
		List<DocumentSingleBean> documents = new ArrayList<DocumentSingleBean>();
		connectDB.connect();
		ResultSet rs = connectDB.showCabinetDocuments(id);
		while (rs.next()) {
			DocumentSingleBean document = new DocumentSingleBean();
			document.setDocumentId(rs.getInt("document_id"));
			document.setDocumentName(rs.getString("document_name"));
			document.setDocumentType(rs.getString("document_type"));
			document.setCreationDate(rs.getDate("creation_date"));
			document.setContentType(rs.getString("content_type"));
			document.setContentSize(rs.getBigDecimal("content_size"));
			document.setContentPath(rs.getString("content_path"));
			document.setCabinetId(rs.getString("cabinet_id"));
			documents.add(document);
		}
		return documents;
	}

	public void deleteDocument(String id) {
		try {
			connectDB.connect();
			connectDB.deleteDoc(id);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public DocumentSingleBean getDocSingleForEdit(String id) throws SQLException {
		try {
			connectDB.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DocumentSingleBean document = new DocumentSingleBean();
		ResultSet rs = connectDB.getDocSingleById(id);
		while (rs.next()) {
			document.setDocumentId(rs.getInt("document_id"));
			document.setDocumentName(rs.getString("document_name"));
			document.setDocumentType(rs.getString("document_type"));
			document.setCreationDate(rs.getDate("creation_date"));
			document.setContentType(rs.getString("content_type"));
			document.setContentSize(rs.getBigDecimal("content_size"));
			document.setContentPath(rs.getString("content_path"));
			document.setCabinetId(rs.getString("cabinet_id"));
		}
		return document;
	}

	public void saveDocEdit(String document_id, String cabinet_id, String documentName, String documentType,
			String authors, String keywords, String pathFile) throws SQLException {
		try {
			connectDB.connect();
			connectDB.salveazaDocEdit(document_id, cabinet_id, documentName, documentType, authors, keywords, pathFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveDocAdd(String cabinet_id, String documentName, String documentType, String authors, String keywords,
			String pathFile) throws SQLException {
		try {
			connectDB.connect();
			connectDB.salveazaDocAdd(cabinet_id, documentName, documentType, authors, keywords, pathFile);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
