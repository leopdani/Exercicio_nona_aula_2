package ex_2.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ex_2.model.Pais;
import ex_2.service.PaisService;


@WebServlet("/listar_paises.do")
public class ListarPaisesController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String chave = request.getParameter("data[search]");
		String acao = request.getParameter("acao");
		PaisService ps = new PaisService();
		RequestDispatcher dispatcher = null;

		ArrayList<Pais>lista = null;
		HttpSession session = request.getSession();
		
		
		
		if(!(session.getAttribute("nome") == null)) {
			if (acao.equals("buscar")) {
				if (chave != null && chave.length() > 0) {
					lista = ps.listarTodos(chave);
				} else {
					lista = ps.listarTodos();
				}
				session.setAttribute("lista", lista);
			} else if (acao.equals("reiniciar")) {
				session.setAttribute("lista", null);
			}
			dispatcher = request
					.getRequestDispatcher("ListarPaises.jsp");
			dispatcher.forward(request, response);
		} else {
			dispatcher = request.getRequestDispatcher("Login.jsp");
			dispatcher.forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}