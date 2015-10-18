package br.com.dennis.fabricaweb.controler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dennis.fabricaweb.persistencia.entidade.Usuario;
import br.com.dennis.fabricaweb.persistencia.jdbc.UsuarioDAO;
//http://localhost:8080/fabricaweb/usucontroller.do
@WebServlet("/usucontroller.do")
public class UsuarioControler extends HttpServlet{
	
	public UsuarioControler(){
		System.out.println("construtor");
	}
	
@Override
	public void init() throws ServletException{
	System.out.println("init..");
	super.init();
}
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("do get!!");
	}	
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
	
		Usuario usu = new Usuario();
		usu.setNome(nome);
		usu.setLogin(login);
		usu.setSenha(senha);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usu);
		resp.getWriter().print("<b>sucesso!!<b/>");
}
@Override
	public void destroy(){
	System.out.println("destroy..");
		super.destroy();
	}
}
