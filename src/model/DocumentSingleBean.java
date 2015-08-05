package model;

import java.math.BigDecimal;
import java.util.Date;

public class DocumentSingleBean {
	private Integer documentId;
	private String documentName;
	private String documentType;
	private Date creationDate;
	private String contentType;
	private BigDecimal contentSize;
	private String contentPath;
	private String cabinetId;
	
	public Integer getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public BigDecimal getContentSize() {
		return contentSize;
	}

	public void setContentSize(BigDecimal contentSize) {
		this.contentSize = contentSize;
	}

	public String getCabinetId() {
		return cabinetId;
	}

	public void setCabinetId(String cabinetId) {
		this.cabinetId = cabinetId;
	}
	public String getContentPath() {
		return contentPath;
	}

	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}
}
