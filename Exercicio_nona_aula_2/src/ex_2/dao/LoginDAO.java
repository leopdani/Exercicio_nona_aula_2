package ex_2.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ex_2.model.Usuario;

public class LoginDAO {

	public static void inserir(Usuario user) throws Exception{
		String sqlInsert = "INSERT INTO usuario(usuario, nome, senha) VALUES (?, ?, ?)";
		
		try(Connection conn = ConnectionFactory.obtemConexao(); PreparedStatement stm =  conn.prepareStatement(sqlInsert);) {
			stm.setString(1, user.getLogin());
			stm.setString(2, user.getNome());
			
			
			String sSenha = user.getSenha();
			byte[] bSenha = sSenha.getBytes("ISO-8859-1");
			CryptoAES caes = new CryptoAES();
			String chave = user.getLogin() + ".chave.simetrica";
			caes.geraCifra(bSenha, new File(chave));
			bSenha = caes.getTextoCifrado();
			
			
			stm.setBytes(3, bSenha);
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Usuario validar (Usuario user) throws Exception {
		
		String sqlInsert = "SELECT * from usuario where usuario=?";
		Usuario u = new Usuario();
		
		try (Connection conn = ConnectionFactory.obtemConexao(); PreparedStatement stm = conn.prepareStatement(sqlInsert);){
			stm.setString(1, user.getLogin());
			stm.execute();
			
			try(ResultSet rs = stm.executeQuery();){
				
				if(rs.next()) {
					u.setId(rs.getInt(1));
					byte[] bSenha = rs.getBytes(4);
					CryptoAES caes = new CryptoAES();
					String chave = user.getLogin() + ".chave.simetrica";
					caes.geraDecifra(bSenha, new File(chave));
					bSenha = caes.getTextoDecifrado();
					String sSenha = (new String(bSenha, "ISO-8859-1"));
					if(user.getSenha().equals(sSenha)) {
						u.setNome(rs.getString(3));
						u.setSenha(sSenha);
					}						
					u.setLogin(rs.getString(2));
				}
					
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			conn.close();
			
		} catch(SQLException e){
			e.printStackTrace();
		}
		
		return u;
	}
}