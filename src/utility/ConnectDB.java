package utility;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConnectDB implements Serializable {
	private static final long serialVersionUID = 1L;
	String error;
	Connection connection;
	public static Statement statement;

	public Connection connect() throws IOException {
		if (connection != null)
			return connection;
		else {
			try {

				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (statement != null)
				try {
					statement.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			try {
				connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/documentcontent", "root", "root");
				System.out.println("Connected!");

			} catch (Exception e) {
				System.out.println("Failed connect to server");
				e.printStackTrace();
				HttpServletResponse response = null;
				response.sendRedirect("http://localhost:8080/BackupDocumentContentReview/errorDatabase.jsp");
			}
		}
		return connection;
	}

	public void disconnect() {
		if (statement != null)
			try {
				statement.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		if (connection != null)
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		System.out.println("Connection Closed");
	}

	public ResultSet viewCabinet() {
		ResultSet rs = null;
		try {
			String queryString = "select * from cabinet;";
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(queryString);
		} catch (SQLException sqle) {
			error = "SQLException : Could not execute the query.";
		} catch (Exception e) {
			error = "Exception: An unknown error occurred while test.";
		}
		return rs;
	}

	public void deleteCabinet(String id) {
		try {
			Integer intId = null;
			System.out.println(id);
			intId = Integer.parseInt(id);
			System.out.println("ID=" + id);
			PreparedStatement prepStmt = connection.prepareStatement("delete from cabinet where cabinet_id=?");
			prepStmt.setInt(1, intId);
			prepStmt.executeUpdate();
		} catch (SQLException sqle) {
			error = "SQLException : Could not execute the query.";
		} catch (Exception e) {
			error = "Exception: An unknown error occurred while test.";
		}

	}

	public void addCabinet(String param1, String param2) {
		System.out.println(param1);
		System.out.println(param2);
		try {
			PreparedStatement prepStmt = connection.prepareStatement(
					"insert into cabinet(cabinet_name,creation_date,owner_name) values(?,current_date(),?) ");
			prepStmt.setString(1, param1);
			prepStmt.setString(2, param2);
			prepStmt.executeUpdate();
		} catch (SQLException sqle) {
			error = "SQLException : Could not execute the query.";
		} catch (Exception e) {
			error = "Exception: An unknown error occurred while test.";
		}
	}

	public ResultSet showCabinetDocuments(String id) throws IOException {
		ResultSet rs = null;
		Integer intId;
		System.out.println(id);
		intId = Integer.parseInt(id);
		System.out.println("ID=" + intId);
		try {
			PreparedStatement prepStmt = connection.prepareStatement("select * from document_s where cabinet_id=?");
			prepStmt.setInt(1, intId);
			rs = prepStmt.executeQuery();
			if (!rs.next()) {
				System.out.println("no data");
			}
			rs.beforeFirst();
		} catch (SQLException sqle) {
			error = "SQLException : Could not execute the query.";
		} catch (Exception e) {
			error = "Exception: An unknown error occurred while test.";
		}
		return rs;
	}

	public void deleteDoc(String id) {
		try {
			Integer intId;
			System.out.println(id);
			intId = Integer.parseInt(id);
			System.out.println("ID=" + id);
			PreparedStatement prepStmt = connection.prepareStatement("delete from document_s where document_id=?");
			prepStmt.setInt(1, intId);
			prepStmt.executeUpdate();
			System.out.println("Row with id : " + id + "deleted");

		} catch (SQLException sqle) {
			error = "SQLException : Could not execute the query.";
		} catch (Exception e) {
			error = "Exception: An unknown error occurred while test.";
		}
	}

	public ResultSet getDocSingleById(String id) {
		ResultSet rs = null;
		Integer intId;
		intId = Integer.parseInt(id);
		System.out.println("ID getDocSingleById=" + intId);
		try {
			PreparedStatement prepStmt = connection.prepareStatement("select * from document_s where document_id=?");
			prepStmt.setInt(1, intId);
			rs = prepStmt.executeQuery();
			if (!rs.next()) {
				System.out.println("no data");
			}
			rs.beforeFirst();
		} catch (SQLException sqle) {
			error = "SQLException : Could not execute the query.";
		} catch (Exception e) {
			error = "Exception: An unknown error occurred while test.";
		}
		return rs;
	}

	// show document for edititng
	public ResultSet getDocRepeatingById(String id) {
		ResultSet rs = null;
		Integer intId;
		intId = Integer.parseInt(id);
		System.out.println("ID getDocRepeatingById=" + intId);
		try {
			PreparedStatement prepStmt = connection.prepareStatement("select * from document_r where document_id=?");
			prepStmt.setInt(1, intId);
			rs = prepStmt.executeQuery();
			if (!rs.next()) {
				System.out.println("no data");
			}
			rs.beforeFirst();
		} catch (SQLException sqle) {
			error = "SQLException : Could not execute the query.";
		} catch (Exception e) {
			error = "Exception: An unknown error occurred while test.";
		}
		return rs;
	}

	// add document
	public void salveazaDocAdd(String cabinet_id, String documentName, String documentType, String authors,
			String keywords, String pathFile) throws SQLException {
		Integer intId = Integer.parseInt(cabinet_id);
		String contententType = "";
		String[] splitAuthors = authors.toString().split("\\n");
		String[] splitKeywords = keywords.toString().split("\\n");
		BigDecimal bytes = null;
		//String path = null;
		if (pathFile != null) {
			File f = new File(pathFile);
			bytes = getFileSize(f);
			contententType = getFileExtension(f);
		}

		// insert in document_s
		PreparedStatement prpStmDocS = connection.prepareStatement(
				"insert into document_s(document_name,document_type,creation_date,content_type,content_size,content_path,cabinet_id) values(?,?, current_date(),?,?,?,?);");
		prpStmDocS.setString(1, documentName);
		prpStmDocS.setString(2, documentType);
		prpStmDocS.setString(3, contententType);
		prpStmDocS.setBigDecimal(4, bytes);
		prpStmDocS.setString(5, pathFile);
		prpStmDocS.setInt(6, intId);
		prpStmDocS.executeUpdate();
		System.out.println("Document added!");

		prpStmDocS = connection.prepareStatement("select max(document_id) from document_s;");

		ResultSet rs = prpStmDocS.executeQuery();
		int generatedKey = 0;
		if (rs.next()) {
			generatedKey = rs.getInt(1);
		}

		// insert in document_r
		for (int i = 0; i < Math.max(splitAuthors.length, splitKeywords.length); i++) {
			PreparedStatement prepStmt1 = connection.prepareStatement(
					"insert into document_r(document_id,doc_index,doc_authors,doc_keywords) values (?,?,?,?)");
			prepStmt1.setInt(1, generatedKey);
			prepStmt1.setInt(2, i + 1);
			if (splitAuthors.length > i) {
				prepStmt1.setString(3, splitAuthors[i]);
			} else {
				prepStmt1.setString(3, "");
			}
			if (splitKeywords.length > i) {
				prepStmt1.setString(4, splitKeywords[i]);
			} else {
				prepStmt1.setString(4, "");
			}
			prepStmt1.executeUpdate();
		}
	}

	// get file extension
	private static String getFileExtension(File file) {
		String fileName = file.getName();
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		else
			return "";
	}

	// get file size
	public static BigDecimal getFileSize(File file) {
		BigDecimal bytes = null;
		if (file.exists()) {

			bytes = new BigDecimal(file.length());

			System.out.println("bytes : " + bytes);
		} else {
			System.out.println("File does not exists!");
		}
		return bytes;
	}

	// save edit document
	public void salveazaDocEdit(String document_id, String cabinet_id, String documentName, String documentType,
			String authors, String keywords, String pathFile) throws SQLException {
		Integer intIdDoc = Integer.parseInt(document_id);
		String contententType = "";
		String[] splitAuthors = authors.toString().split("\\n");
		String[] splitKeywords = keywords.toString().split("\\n");
		BigDecimal bytes = null;
		if (pathFile != null) {
			File f = new File(pathFile);
			bytes = getFileSize(f);

			contententType = getFileExtension(f);
		}
		System.out.println("File extension is: " + contententType);
		System.out.println(splitAuthors.length);
		System.out.println(splitKeywords.length);
		System.out.println("Max =" + Math.max(splitAuthors.length, splitKeywords.length));

		// insert in document_s
		PreparedStatement prpStmDocS = connection.prepareStatement(
				"update document_s set document_name=?, document_type=?, content_type=?, content_size=?,content_path=? where document_id=?");
		prpStmDocS.setString(1, documentName);
		prpStmDocS.setString(2, documentType);
		prpStmDocS.setString(3, contententType);
		prpStmDocS.setBigDecimal(4, bytes);
		prpStmDocS.setString(5, pathFile);
		prpStmDocS.setInt(6, intIdDoc);
		prpStmDocS.executeUpdate();
		System.out.println("Document added!");
		/* insert or update */
		for (int i = 0; i < Math.max(splitAuthors.length, splitKeywords.length); i++) {
			PreparedStatement prepStmtDocR = connection.prepareStatement(
					"insert into document_r(document_id,doc_index,doc_authors,doc_keywords) values (?,?,?,?) on duplicate key update doc_authors=values(doc_authors),doc_keywords=values(doc_keywords)");
			prepStmtDocR.setInt(1, intIdDoc);
			prepStmtDocR.setInt(2, i + 1);
			if (splitAuthors.length > i) {
				prepStmtDocR.setString(3, splitAuthors[i]);
			} else {prepStmtDocR.setString(3, "");}

			if (splitKeywords.length > i) {
				prepStmtDocR.setString(4, splitKeywords[i]);
			} else {prepStmtDocR.setString(4,"");}
			
			prepStmtDocR.executeUpdate();
			prepStmtDocR.close();
		}
		// delete row where authors and keywords are empty
		PreparedStatement prepStmt2 = connection.prepareStatement("delete from document_r where document_id=? and (doc_authors like '' or doc_authors like '\r') and (doc_keywords like '' or doc_keywords like '\r');");
		prepStmt2.setInt(1, intIdDoc);
		prepStmt2.executeUpdate();
		prepStmt2.close();
		System.out.println("Delete was");
	}

	public boolean downloadFile(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		boolean succes = false;
		if (request.getParameter("document_id") != null) {
			System.out.print("Id Doc : " + request.getParameter("document_id"));
			PreparedStatement prpStmDocS = connection
					.prepareStatement("select content_path from document_s where document_id=?");
			prpStmDocS.setString(1, request.getParameter("document_id"));
			ResultSet rs = prpStmDocS.executeQuery();
			String path = null;
			if (rs.next()) {
				path = rs.getString(1);
			}
			System.out.println("Path // : " + path);
			if (path != null) {
				File myfile = new File(path);
				if (myfile.exists()) {
					succes = true;
					BufferedInputStream buf = null;
					ServletOutputStream myOut = null;
					try {
						myOut = response.getOutputStream();
						// set response headers
						response.setContentType("text/plain");
						response.addHeader("Content-Disposition", "attachment; filename=" + path);
						response.setContentLength((int) myfile.length());
						FileInputStream input = new FileInputStream(myfile);
						buf = new BufferedInputStream(input);
						int readBytes = 0;

						// read from the file; write to the ServletOutputStream
						while ((readBytes = buf.read()) != -1)
							myOut.write(readBytes);

					} catch (IOException ioe) {

						throw new ServletException(ioe.getMessage());

					} finally {
						// close the input/output streams
						if (myOut != null) {
							myOut.close();
						}
						if (buf != null) {
							buf.close();
						}
					}
				}
			}
		}
		return succes;
	}
}
