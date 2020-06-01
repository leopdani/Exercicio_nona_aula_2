package ex_2.controller;

import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ex_2.dao.CryptoAES;
import ex_2.dao.LoginDAO;
import ex_2.model.Usuario;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/CadastrarController.do")
public class CadastrarController extends HttpServlet {
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
		String nome = request.getParameter("nome");

		Usuario user = new Usuario();
		user.setLogin(login);
		user.setSenha(senha);
		user.setNome(nome);

		String chave = user.getLogin() + ".chave.simetrica";
		File f = new File(chave);
		if(!f.exists()) {
			System.out.println("Chave simétrica criada para o usuário " + user.getLogin());
			CryptoAES caes = new CryptoAES();
			try {
				caes.geraChave(new File(user.getLogin() + ".chave.simetrica"));
			} catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException | CertificateException
					| KeyStoreException | IOException e) {
				e.printStackTrace();
			}
			
			try {
				LoginDAO.inserir(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Usuario já existe");
		}
		
		
		
		dispatcher = request.getRequestDispatcher("Login.jsp");
		
		dispatcher.forward(request, response);

	}

}