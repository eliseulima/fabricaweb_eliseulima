package br.com.fabricadeprogramador.persistence.jdbc;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.fabricadeprogramador.persistence.entity.User;
public class UserDAO {
	private static Connection con = ConnectionFactory.getConnection();
	public static boolean save(User user){
		String sql = "INSERT INTO usuarios (nome, login, senha) VALUES(?,?,?	)";
		try {
			PreparedStatement preparator = con.prepareStatement(sql);
			preparator.setString(1, user.getNome());//subs ? by user.data
			preparator.setString(2, user.getLogin());
			preparator.setString(3, user.getSenha());
			
			preparator.execute();
			preparator.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public static boolean update(User user) {
		// TODO Auto-generated method stub
		String sql = "UPDATE usuarios SET nome = ?, login = ?,  senha = ? WHERE id = ?";
		try {
			PreparedStatement preparator = con.prepareStatement(sql);
			preparator.setString(1, user.getNome());//subs ? by user.data
			preparator.setString(2, user.getLogin());
			preparator.setString(3, user.getSenha());
			preparator.setInt(4, user.getId());
			preparator.execute();
			preparator.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean delete(User user) {
		String sql = "DELETE FROM usuarios WHERE id =?";
		try {
			PreparedStatement preparator = con.prepareStatement(sql);
			preparator.setInt(1, user.getId());
			preparator.execute();
			preparator.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			// TODO: handle exception
		}
	}
	public static boolean saveUser(User user){
		if(user.getId() != null && user.getId() > 0){
			if(update(user))
				return true;
			else return false;
		}else{
			if(save(user))
				return true;
			else return false;
		}
	}
	/**
	 * Find a register in fabricaweb's database by id field
	 * @param id Integer that represents the identification of a User
	 * @return User object when founds a User with the @param id, else, null
	 * */
	public static User findById(Integer id){
		User user = null;
		String sql = "SELECT * FROM usuarios WHERE id = ?";
		try {
			PreparedStatement preparator = con.prepareStatement(sql);
			preparator.setInt(1, id);
			ResultSet result = preparator.executeQuery();
			if(result.next()){
				user = new User();
				user.setId(result.getInt("id"));
				user.setNome(result.getString("nome"));
				user.setLogin(result.getString("login"));
				user.setSenha(result.getString("senha"));
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return user;
	}
	/**
	 * Finds all occurencies of users in users table
	 * @param
	 * @return all users in users table
	 * */
	public static List<User> findAll(){
		String sql = "SELECT * FROM usuarios";
		List<User> list = new ArrayList<User>();
		try {
			PreparedStatement preparator = con.prepareStatement(sql);
			ResultSet result = preparator.executeQuery();
			while(result.next() ){
				User user = new User();
				user.setId(result.getInt("id"));
				user.setNome(result.getString("nome"));
				user.setLogin(result.getString("login"));
				user.setSenha(result.getString("senha"));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}