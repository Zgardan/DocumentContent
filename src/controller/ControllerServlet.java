package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CabinetBean;
import model.DocumentSingleBean;
import service.CabinetService;
import service.DocumentSingleService;
import utility.ConnectDB;

@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CabinetService cabServ;
	private DocumentSingleService docSingleServ;
	private ConnectDB connectDB;

	public ControllerServlet() {
		super();
		cabServ = new CabinetService();
		docSingleServ = new DocumentSingleService();
		connectDB = new ConnectDB();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String forward = "";
		String message = " ";
		// Creeaza cabinet
		if ("Creeaza".equalsIgnoreCase(request.getParameter("creeazaCab"))) {
			message = "";
			request.setAttribute("message", message);
			forward = "/cabinet.jsp";
		} else

		// Sterge cabinet
			if ("Sterge".equalsIgnoreCase(request.getParameter("stergeCab"))) {
			if (request.getParameter("cabinet_id") != null) {
				String id = request.getParameter("cabinet_id");
				cabServ.deleteCabinet(id);
				List<CabinetBean> cabinets = new ArrayList<CabinetBean>();
				cabinets = cabServ.getCabinets();
				request.setAttribute("cabinets", cabinets);
				message = "Cabinetul cu id-ul "+ id + "a fost sters!";
				request.setAttribute("message", message);
				forward = "/index.jsp";
			} else {
				List<CabinetBean> cabinets = new ArrayList<CabinetBean>();
				cabinets = cabServ.getCabinets();
				request.setAttribute("cabinets", cabinets);
				message = "Pentru a sterge, selectati un cabinet ! ";
				request.setAttribute("message", message);
				forward = "/index.jsp";
			}

		} else
				// Acceseaza cabinet
				if ("Acceseaza".equalsIgnoreCase(request.getParameter("acceseazaCab"))) {
			String id = request.getParameter("cabinet_id");
			if (id != null) {
				List<DocumentSingleBean> documents = new ArrayList<DocumentSingleBean>();
				try {
					documents = docSingleServ.getCabinetDoc(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("documents", documents);
				forward = "/listDocument.jsp?cabinet_id=" + request.getParameter("cabinet_id");
			} else {
				List<CabinetBean> cabinets = new ArrayList<CabinetBean>();
				cabinets = cabServ.getCabinets();
				request.setAttribute("cabinets", cabinets);
				message = "Nici un cabinet ales pentru a fi accesat !";
				request.setAttribute("message", message);
				forward = "/index.jsp";
			}
		} else
					// cabinet.jsp Salveaza
					if ("Salveaza".equalsIgnoreCase(request.getParameter("salveazaCab"))) {
			String cabName = request.getParameter("cabinet_name");
			String ownerName = request.getParameter("owner_name");
			cabServ.addCabinet(cabName, ownerName);
			List<CabinetBean> cabinets = new ArrayList<CabinetBean>();
			cabinets = cabServ.getCabinets();
			request.setAttribute("cabinets", cabinets);
			forward = "/index.jsp";
		} else

		// cabinet.jsp Anuleaza
						if ("Anuleaza".equalsIgnoreCase(request.getParameter("anuleazaCab"))) {
			List<CabinetBean> cabinets = new ArrayList<CabinetBean>();
			cabinets = cabServ.getCabinets();
			request.setAttribute("cabinets", cabinets);
			forward = "/index.jsp";
		} else

		// listDocument.jsp Sterge
							if ("Sterge".equalsIgnoreCase(request.getParameter("stergeDoc"))) {
			String idCab = request.getParameter("cabinet_id");
			String idDoc = request.getParameter("document_id");
			if (idDoc != null) {
				docSingleServ.deleteDocument(idDoc);
				List<DocumentSingleBean> documents = new ArrayList<DocumentSingleBean>();
				try {
					documents = docSingleServ.getCabinetDoc(idCab);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("documents", documents);
				forward = "/listDocument.jsp?cabinet_id=" + request.getParameter("cabinet_id");
			} else {
				List<DocumentSingleBean> documents = new ArrayList<DocumentSingleBean>();
				try {
					documents = docSingleServ.getCabinetDoc(idCab);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("documents", documents);
				message = "Nici un document selectat pentru stergere !";
				request.setAttribute("message", message);
				forward = "/listDocument.jsp?cabinet_id=" + request.getParameter("cabinet_id");
			}
		} else

		// listDocument.jsp Editare
			if ("Editare".equalsIgnoreCase(request.getParameter("editareDoc"))) {
			String idDoc = request.getParameter("document_id");
			String idCab = request.getParameter("cabinet_id");
			if (idDoc != null) {
				try {
					DocumentSingleBean document = docSingleServ.getDocSingleForEdit(idDoc);
					request.setAttribute("document", document);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				forward = "/editDocument.jsp?cabinet_id=" + request.getParameter("cabinet_id")
						+ "&document_id=" + request.getParameter("document_id");
			} else {
				List<DocumentSingleBean> documents = new ArrayList<DocumentSingleBean>();
				try {
					documents = docSingleServ.getCabinetDoc(idCab);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("documents", documents);
				message = "Nici un document selectat pentru editare !";
				request.setAttribute("message", message);
				forward = "/listDocument.jsp?cabinet_id=" + request.getParameter("cabinet_id");
			}
		} else
									// listDocument.jsp Adauga
									if ("Adauga".equalsIgnoreCase(request.getParameter("adaugaDoc"))) {
			forward = "/addDocumet.jsp?cabinet_id=" + request.getParameter("cabinet_id");
		} else

		// editDocument Salveaza
										if ("Salveaza".equalsIgnoreCase(request.getParameter("salveazaDocEdit"))) {
			String document_id = request.getParameter("document_id");
			String cabinet_id = request.getParameter("cabinet_id");
			String documentName = request.getParameter("documentName");
			String documentType = request.getParameter("documentType");
			String authors = request.getParameter("authors");
			String keywords = request.getParameter("keywords");
			String pathFile = request.getParameter("pathFile");
			try {
				docSingleServ.saveDocEdit(document_id, cabinet_id, documentName, documentType, authors, keywords,
						pathFile);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			List<DocumentSingleBean> documents = new ArrayList<DocumentSingleBean>();
			try {
				documents = docSingleServ.getCabinetDoc(cabinet_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("documents", documents);
			forward = "/listDocument.jsp?cabinet_id=" + request.getParameter("cabinet_id");
		} else
		// editDocumetn anuleaza
		if ("Anuleaza".equalsIgnoreCase(request.getParameter("anuleazaDocEdit"))) {
			String id = request.getParameter("cabinet_id");
			List<DocumentSingleBean> documents = new ArrayList<DocumentSingleBean>();
			try {
				documents = docSingleServ.getCabinetDoc(id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("documents", documents);	
			forward = "/listDocument.jsp?cabinet_id=" + request.getParameter("cabinet_id");
		} else

		// addDocument salveaza
			if ("Salveaza".equalsIgnoreCase(request.getParameter("salveazaDocAdd"))) {
			String cabinet_id = request.getParameter("cabinet_id");
			String documentName = request.getParameter("documentName");
			String documentType = request.getParameter("documentType");
			String authors = request.getParameter("authors");
			String keywords = request.getParameter("keywords");
			String pathFile = request.getParameter("pathFile");
			try {
				docSingleServ.saveDocAdd(cabinet_id, documentName, documentType, authors, keywords, pathFile);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			//String id = request.getParameter("cabinet_id");
			List<DocumentSingleBean> documents = new ArrayList<DocumentSingleBean>();
			try {
				documents = docSingleServ.getCabinetDoc(cabinet_id);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.setAttribute("documents", documents);	
			forward = "/listDocument.jsp?cabinet_id=" + request.getParameter("cabinet_id");
		} else
			// addDocument anuleaza
				if ("Anuleaza".equalsIgnoreCase(request.getParameter("anuleazaDocAdd"))) {
					String id = request.getParameter("cabinet_id");
					List<DocumentSingleBean> documents = new ArrayList<DocumentSingleBean>();
					try {
						documents = docSingleServ.getCabinetDoc(id);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					request.setAttribute("documents", documents);										
			forward = "/listDocument.jsp?cabinet_id=" + request.getParameter("cabinet_id");
		} else
			// listDocument descarca
			if ("Descarca".equalsIgnoreCase(request.getParameter("descarcaDoc"))) {
			boolean succes = false;
			String id = request.getParameter("cabinet_id");
			if (id != null) {
				try {
					connectDB.connect();
					succes = connectDB.downloadFile(request, response);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (succes == false) {
					message = "Nici exista document atasat !";
					request.setAttribute("message", message);
					List<DocumentSingleBean> documents = new ArrayList<DocumentSingleBean>();
					try {
						documents = docSingleServ.getCabinetDoc(id);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					request.setAttribute("documents", documents);
					forward = "/listDocument.jsp?cabinet_id=" + request.getParameter("cabinet_id");
				}
			} else {
				message = "Nu ati selectat document pentru descarcare !";
				request.setAttribute("message", message);
				List<DocumentSingleBean> documents = new ArrayList<DocumentSingleBean>();
				try {
					documents = docSingleServ.getCabinetDoc(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("documents", documents);
				forward = "/listDocument.jsp?cabinet_id=" + request.getParameter("cabinet_id");
			}
		} else {
			try {
				connectDB.connect();
				} catch(Exception ex) {
			 response.sendRedirect("errorDatabase.jsp");
			    return;
			}
			List<CabinetBean> cabinets = new ArrayList<CabinetBean>();
			cabinets = cabServ.getCabinets();
			request.setAttribute("cabinets", cabinets);
			forward = "/index.jsp";
		}

		if (!response.isCommitted()) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(forward);
			requestDispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
