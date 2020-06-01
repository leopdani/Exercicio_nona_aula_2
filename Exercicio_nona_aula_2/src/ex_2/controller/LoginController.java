package ex_2.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ex_2.model.Log;
import ex_2.model.Usuario;
import ex_2.service.LoginService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController.do")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher dispatcher;
		
        String login = request.getParameter("login");  
        String senha = request.getParameter("senha");
        
        Usuario user = new Usuario();
        user.setLogin(login);
        user.setSenha(senha);
        
        try {
			user = LoginService.validarUsuario(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        if(user.getNome() == null) {
        	dispatcher = request.getRequestDispatcher("Login.jsp");
        	Log log = new Log();
        	log.abrir("NomesRejeitados.txt");
        	log.escrever("Login rejeitado. Credenciais: Login = " + login);
        	log.fechar();
        } else {
        	dispatcher = request.getRequestDispatcher("ListarPaises.jsp");
        	HttpSession session = request.getSession();
            session.setAttribute("nome", user.getNome());
        	Log log = new Log();
        	log.abrir("NomesAceitos.txt");
        	log.escrever("Login Aceito. Credenciais: Login = " + login);
        	log.fechar();
        }
       
        dispatcher.forward(request, response);
        
	}

}