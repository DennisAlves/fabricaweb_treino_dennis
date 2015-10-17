package br.com.dennis.fabricaweb;

import br.com.dennis.fabricaweb.persistencia.entidade.Usuario;
import br.com.dennis.fabricaweb.persistencia.jdbc.UsuarioDAO;

public class TesteUsuarioDAO {

	public static void main(String[] args) {
		testCadastrar();
	}
	public static void testCadastrar(){
		// criando usuario
		Usuario usu = new Usuario();
		usu.setNome("JãoZão");
		usu.setLogin("jzão");
		usu.setSenha("123");
		
		// cadastrar usuario no banco de dados
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.cadastrar(usu);
		
		System.out.println("cadastrado com sucesso!!!");
	}
	
	public static void testAlterar(){
		// alterando usuario
		Usuario usu = new Usuario();
		usu.setId(7);
		usu.setNome("JãoZão da silva");
		usu.setLogin("jzaum");
		usu.setSenha("12367");
		
		// cadastrar usuario no banco de dados
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.alterar(usu);
		
		System.out.println("alterado com sucesso!!!");
	}
	
	public static void testExcluir(){
		// alterando usuario
		Usuario usu = new Usuario();
		usu.setId(7);
		
		// cadastrar usuario no banco de dados
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.excluir(usu);
		
		System.out.println("excluido com sucesso!!!");
	}
}
