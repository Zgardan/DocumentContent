package model;


import java.io.Serializable;
import java.util.Date;

public class CabinetBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer cabinetId;
	private String cabinetName;
	private String ownerName;
	private Date creationDate;
	
	public Integer getCabinetId() {
		return cabinetId;
	}
	public void setCabinetId(Integer cabinetId) {
		this.cabinetId = cabinetId;
	}
	public String getCabinetName() {
		return cabinetName;
	}
	public void setCabinetName(String cabinetName) {
		this.cabinetName = cabinetName;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}	
}
