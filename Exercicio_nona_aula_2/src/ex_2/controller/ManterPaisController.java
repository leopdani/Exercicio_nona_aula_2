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

@WebServlet("/ManterPais.do")
public class ManterPaisController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String acao = request.getParameter("acao");


		//instanciar o service
		PaisService ps = new PaisService();
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession();

		if(!(session.getAttribute("nome") == null)) {
			if(acao.equals("Incluir")) {
				String pId = request.getParameter("id");
				String pNome = request.getParameter("nome");
				String pPopulacao = request.getParameter("populacao");
				String pArea = request.getParameter("area");
				int id = -1;
				try {
					id = Integer.parseInt(pId);
				} catch (NumberFormatException e) {
				}
				Pais pais = new Pais();
				pais.setId(id);
				pais.setNome(pNome);
				pais.setPopulacao(Long.parseLong(pPopulacao));
				pais.setArea(Double.parseDouble(pArea));
				ps.criar(pais);
				ArrayList<Pais> lista = new ArrayList<>();
				lista.add(pais);
				session.setAttribute("lista", lista);
				dispatcher = request.getRequestDispatcher("ListarPaises.jsp");

			} else if(acao.equals("Excluir")) {
				String pId = request.getParameter("id");
				String pNome = request.getParameter("nome");
				int id = -1;
				try {
					id = Integer.parseInt(pId);
				} catch (NumberFormatException e) {
				}
				Pais pais = new Pais();
				pais.setId(id);
				pais.setNome(pNome);
				ps.excluir(pais);
				ArrayList<Pais> lista = (ArrayList<Pais>)session.getAttribute("lista");
				lista.remove(busca(pais, lista));
				session.setAttribute("lista", lista);
				dispatcher = request.getRequestDispatcher("ListarPaises.jsp");

			} else if(acao.equals("Alterar")) {
				String pId = request.getParameter("id");
				String pNome = request.getParameter("nome");
				String pPopulacao = request.getParameter("populacao");
				String pArea = request.getParameter("area");
				int id = -1;
				try {
					id = Integer.parseInt(pId);
				} catch (NumberFormatException e) {
				}
				Pais pais = new Pais();
				pais.setId(id);
				pais.setNome(pNome);
				pais.setPopulacao(Long.parseLong(pPopulacao));
				pais.setArea(Double.parseDouble(pArea));
				ps.atualizar(pais);
				ArrayList<Pais> lista = (ArrayList<Pais>)session.getAttribute("lista");
				int pos = busca(pais, lista);
				lista.remove(pos);
				lista.add(pos, pais);
				session.setAttribute("lista", lista);
				request.setAttribute("pais", pais);
				dispatcher = request.getRequestDispatcher("VisualizarPais.jsp");

			} else if(acao.equals("Visualizar")) {
				String pId = request.getParameter("id");
				String pNome = request.getParameter("nome");
				int id = -1;
				try {
					id = Integer.parseInt(pId);
				} catch (NumberFormatException e) {
				}
				Pais pais = new Pais();
				pais.setId(id);
				pais.setNome(pNome);
				pais = ps.carregar(pais.getId());
				request.setAttribute("pais", pais);
				dispatcher = request.getRequestDispatcher("VisualizarPais.jsp");

			} else if(acao.equals("Editar")) {
				String pId = request.getParameter("id");
				String pNome = request.getParameter("nome");
				int id = -1;
				try {
					id = Integer.parseInt(pId);
				} catch (NumberFormatException e) {
				}
				Pais pais = new Pais();
				pais.setId(id);
				pais.setNome(pNome);
				pais = ps.carregar(pais.getId());
				request.setAttribute("pais", pais);
				dispatcher = request.getRequestDispatcher("AlterarPais.jsp");
			}

			dispatcher.forward(request, response);
		} else {
			dispatcher = request.getRequestDispatcher("Login.jsp");
			dispatcher.forward(request, response);
		}
	}

	public int busca(Pais pais, ArrayList<Pais> lista) {
		Pais to;
		for(int i = 0; i < lista.size(); i++){
			to = lista.get(i);
			if(to.getId() == pais.getId()){
				return i;
			}
		}
		return -1;
	}
}