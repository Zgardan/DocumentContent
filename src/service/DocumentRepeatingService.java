package service;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DocumentRepeatingBean;
import utility.ConnectDB;

public class DocumentRepeatingService {
	ConnectDB connectDB = new ConnectDB();
	public List<DocumentRepeatingBean> getDocRepeatingForEdit (String id) throws SQLException{
		try {
			connectDB.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<DocumentRepeatingBean> documents = new ArrayList<DocumentRepeatingBean>();
		ResultSet rs = connectDB.getDocRepeatingById(id);
		while(rs.next()){
			DocumentRepeatingBean document = new DocumentRepeatingBean();
			document.setDocumentId(rs.getInt("document_id"));
			document.setDocIndex(rs.getInt("doc_index"));
			document.setDocAuthors(rs.getString("doc_authors"));
			document.setDocKeywords(rs.getString("doc_keywords"));
			documents.add(document);
			}
		return documents;
	}
}
