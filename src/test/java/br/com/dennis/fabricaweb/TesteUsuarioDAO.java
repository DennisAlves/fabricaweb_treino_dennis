package br.com.dennis.fabricaweb;

import java.util.List;

import br.com.dennis.fabricaweb.persistencia.entidade.Usuario;
import br.com.dennis.fabricaweb.persistencia.jdbc.UsuarioDAO;

public class TesteUsuarioDAO {

	public static void main(String[] args) {
		//testeSalvar();
		//testeBuscarPorIde();
		//testeBuscarTodos();
		testeAutenticar();
	}
	private static void testeAutenticar() {
		
		UsuarioDAO  usuarioDAO = new UsuarioDAO();
		
		Usuario usu = new Usuario();
		usu.setLogin("mar");
		usu.setSenha("123");
		
		Usuario usuRetorno = usuarioDAO.autenticar(usu);
		System.out.println(usuRetorno);
		
	}
	
	private static void testeBuscarPorIde() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscaPorId(1);
		System.out.println(usuario);
		
	}
	private static void testeBuscarTodos() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> lista = usuarioDAO.buscarTodos();
		for(Usuario u: lista){
		System.out.println(u);
		}
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
	
	public static void testeSalvar(){
	
		Usuario usuario = new Usuario();
		//usuario.setId(1);
		usuario.setNome("huehue");
		usuario.setLogin("hue");
		usuario.setSenha("huehuehue");
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(usuario);
		
		System.out.println("Salvo com sucesso");
		
	}
}
