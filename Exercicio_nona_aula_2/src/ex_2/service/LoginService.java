package ex_2.service;

import ex_2.dao.LoginDAO;
import ex_2.model.Usuario;

public class LoginService {
	
	public static Usuario validarUsuario(Usuario usuario) throws Exception {
		return LoginDAO.validar(usuario);
	}
}