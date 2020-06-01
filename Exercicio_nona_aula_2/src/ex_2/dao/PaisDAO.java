package ex_2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ex_2.model.Pais;

public class PaisDAO {

	public static void criar(Pais to) {
		String sqlInsert = "INSERT INTO pais(nome, populacao, area) VALUES (?, ?, ?)";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, to.getNome());
			stm.setLong(2, to.getPopulacao());
			stm.setDouble(3, to.getArea());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try(PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if(rs.next()){
					to.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void atualizar(Pais to) {
		String sqlUpdate = "UPDATE pais SET nome=?, populacao=?, area=? WHERE id=?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, to.getNome());
			stm.setLong(2, to.getPopulacao());
			stm.setDouble(3, to.getArea());
			stm.setInt(4, to.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void excluir(Pais to) {
		String sqlDelete = "DELETE FROM pais WHERE id = ?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
			stm.setInt(1, to.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Pais carregar(int id) {
		Pais to = new Pais();
		String sqlSelect = "SELECT id, nome, populacao, area FROM pais WHERE pais.id =?";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setInt(1, id);
			try (ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					to.setId(rs.getInt(1));
					to.setNome(rs.getString(2));
					to.setPopulacao(rs.getLong(3));
					to.setArea(rs.getDouble(4));
				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return to;
	}

	public static Pais maiorPais(Pais to) {

		String sqlSelect = "SELECT nome, populacao FROM pais ORDER BY populacao desc LIMIT 1";
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);){
			try(ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					to.setNome(rs.getString("nome"));
					to.setPopulacao(rs.getLong("populacao"));
				}
				rs.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return to;
	}

	public static Pais[] tresPaises(Pais[] toes){
		toes = new Pais[3];
		for(int i = 0; i < 3; i++) {
			toes[i] = new Pais();
		}

		String sqlSelect = "SELECT id, nome FROM pais";

		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try(ResultSet rs = stm.executeQuery();) {
				rs.next();
				for(int i = 0; i < 3 ; i++, rs.next()) {
					toes[i].setId(rs.getInt("id"));
					toes[i].setNome(rs.getString("nome"));
					System.out.println("Id: " + toes[i].getId() + " Pais: "  + toes[i].getNome());
				}
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return toes;
	}

	public static Pais menorArea(Pais to) {

		String sqlSelect = "SELECT nome, area FROM pais ORDER BY area LIMIT 1";
		try(Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			try(ResultSet rs = stm.executeQuery();) {
				if (rs.next()) {
					to.setNome(rs.getString("nome"));
					to.setArea(rs.getDouble("area"));
				}
				rs.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		} 
		return to;
	}

	public static ArrayList<Pais> listarTodos(){
		String sqlSelect = "SELECT id, nome, populacao, area FROM pais";
		ArrayList<Pais> to = new ArrayList<>();

		try(Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);
				ResultSet rs = stm.executeQuery();){
			while(rs.next()) {
				Pais container = new Pais();
				container.setId(rs.getInt("id"));
				container.setNome(rs.getString("nome"));
				container.setPopulacao(rs.getLong("populacao"));
				container.setArea(rs.getDouble("area"));
				to.add(container);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return to;
	}

	public static ArrayList<Pais> listarTodos(String chave) {
		String sqlSelect = "SELECT id, nome, populacao, area FROM pais where upper(nome) like ?";
		ArrayList<Pais> to = new ArrayList<>();

		try(Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
					stm.setString(1, "%" + chave.toUpperCase() + "%");
					try (ResultSet rs = stm.executeQuery();){
						while(rs.next()) {
							Pais container = new Pais();
							container.setId(rs.getInt("id"));
							container.setNome(rs.getString("nome"));
							container.setPopulacao(rs.getLong("populacao"));
							container.setArea(rs.getDouble("area"));
							to.add(container);
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return to;
	}

	public static void reset1() {

		String sqlQuery1 = "DELETE FROM pais WHERE id < 10";
		try(Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlQuery1);){
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sqlQuery2 = "alter table pais AUTO_INCREMENT = 1";
		try(Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlQuery2);){
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sqlQuery3 = "INSERT INTO `Pais` (`nome`,`populacao`,`area`) VALUES ('Brasil',210147125,8515767);";
		try(Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlQuery3);){
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sqlQuery4 = "INSERT INTO `Pais` (`nome`,`populacao`,`area`) VALUES ('Bielorussia',9491800,207600);";
		try(Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlQuery4);){
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sqlQuery5 = "INSERT INTO `Pais` (`nome`,`populacao`,`area`) VALUES ('Canada',37242571,9984670);";
		try(Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlQuery5);){
			stm.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}