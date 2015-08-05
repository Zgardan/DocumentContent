package service;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CabinetBean;
import utility.ConnectDB;

public class CabinetService {
	ConnectDB connectDB = new ConnectDB();

	public List<CabinetBean> getCabinets() throws IOException {
		List<CabinetBean> cabinets = new ArrayList<CabinetBean>();
		connectDB.connect();
		ResultSet rs = connectDB.viewCabinet();
		try {
			while(rs.next()){
				CabinetBean cabinetBean = new CabinetBean();
				cabinetBean.setCabinetId(rs.getInt("cabinet_id"));
				cabinetBean.setCabinetName(rs.getString("cabinet_name"));
				cabinetBean.setCreationDate(rs.getDate("creation_date"));
				cabinetBean.setOwnerName(rs.getString("owner_name"));
				cabinets.add(cabinetBean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cabinets;
	}
	
	public void deleteCabinet(String id) {
		try {
			connectDB.connect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		connectDB.deleteCabinet(id);
	}
	
	public void addCabinet(String param1, String param2) throws IOException{
		connectDB.connect();
		connectDB.addCabinet(param1, param2);
	}
}
