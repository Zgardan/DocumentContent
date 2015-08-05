package model;

public class DocumentRepeatingBean {
	private Integer documentId;
	private Integer docIndex;
	private String docAuthors;
	private String docKeywords;
	
	public Integer getDocumentId() {
		return documentId;
	}
	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
	}
	public Integer getDocIndex() {
		return docIndex;
	}
	public void setDocIndex(Integer docIndex) {
		this.docIndex = docIndex;
	}
	public String getDocAuthors() {
		return docAuthors;
	}
	public void setDocAuthors(String docAuthors) {
		this.docAuthors = docAuthors;
	}
	public String getDocKeywords() {
		return docKeywords;
	}
	public void setDocKeywords(String docKeywords) {
		this.docKeywords = docKeywords;
	}
}
