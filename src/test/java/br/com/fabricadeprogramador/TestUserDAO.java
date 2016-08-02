package br.com.fabricadeprogramador;
import br.com.fabricadeprogramador.persistence.entity.User;
import br.com.fabricadeprogramador.persistence.jdbc.UserDAO;
import java.util.List;

import javax.management.RuntimeErrorException;


public class TestUserDAO {
	

	

public static void main(String[] args){
	
	
		
	//User user = new User();
	//user.setId(6);
	
	
	//UserDAO userDAO = new UserDAO();
	//userDAO.delete(user);
	//testInsert("Joaozinho", "joao@gmail.com", "123456");
		//testUpdate("Joaozao", "mauro@gmail.com", "654321", 1);
		//testDelete(4);
		//testSave();
		testFindById(1);
		//testFindAll();
		
	}
	public static void testFindAll() {
		
		List<User> list = UserDAO.findAll();
		if(!list.isEmpty()){
			for(User u : list){
				System.out.println(u);
			}
		}
		
	}
	public static void testFindById(Integer id) {
		
		if(id != null && id > 0){
			User ret = new User();
			ret = UserDAO.findById(id);
			
			if(ret != null){
				System.out.println("Name: "+ret.getNome());
				System.out.println("Login: "+ret.getLogin());
				System.out.println("Password: "+ret.getSenha());
			}else{
				System.out.println("User not found!");
			}
		}
		
	}
	public static boolean testInsert(String nome, String login, String senha){
		User user = new User();
		user.setNome(nome);
		user.setLogin(login);
		user.setSenha(senha);
		
		UserDAO userDAO = new UserDAO();
		if(userDAO.save(user)){
			System.out.println("User created successfuly");
			return true;
		}
		else{
			System.out.println("User couldn't be created");
			return false;
		}
	}
	public static boolean testUpdate(String nome, String login, String senha, Integer id){
		User user = new User();
		user.setId(id);
		user.setNome(nome);
		user.setLogin(login);
		user.setSenha(senha);
		
		UserDAO userDAO = new UserDAO();
		if(userDAO.update(user)){
			System.out.println("User updated successfuly");
			return true;
		}
		else{
			System.out.println("User couldn't be updated");
			return false;
		}
	}
	public static boolean testDelete(Integer id){
		User user = new User();
		user.setId(id);
		UserDAO userDAO = new UserDAO();
		if(userDAO.delete(user)){
			System.out.println("User deleted successfuly");
			return true;
		}
		else{
			System.out.println("User couldn't be deleted");
			return false;
		}
	}
	public static boolean testSave(){
		User user = new User();
		//user.setId(3);
		user.setNome("Jonas");
		user.setLogin("jonas@gmail.com");
		user.setSenha("741852963");
		UserDAO userDAO= new UserDAO();
		if(userDAO.saveUser(user)){
			System.out.println("User created successfuly");
			return true;
		}else{
			System.out.println("User couldn't be deleted");
			return false;
		}
	}
	
}



