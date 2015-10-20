package br.com.dennis.fabricaweb.controler;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dennis.fabricaweb.persistencia.entidade.Usuario;
import br.com.dennis.fabricaweb.persistencia.jdbc.UsuarioDAO;
//http://localhost:8080/fabricaweb/usucontroller.do
@SuppressWarnings("serial")
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
	resp.setContentType("text/html");
	String acao = req.getParameter("acao");
	if(acao.equals("excluir")){
		String id = req.getParameter("id");
		Usuario usu = new Usuario();
		
		if(id!=null)
			usu.setId(Integer.parseInt(id));
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.excluir(usu);
		resp.sendRedirect("usucontroller.do?acao=listar");
			System.out.println("do get!!");
		}else if(acao.equals("listar")){
			// implementar lista
			UsuarioDAO usuDAO	= new UsuarioDAO();
			List<Usuario> lista = usuDAO.buscarTodos();
			
			req.setAttribute("lista", lista);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listausu.jsp");
			dispatcher.forward(req, resp);	
		}
		else if(acao.equals("alterar")){
			String id = req.getParameter("id");
			UsuarioDAO usuDAO	= new UsuarioDAO();
			Usuario usuario = usuDAO.buscaPorId(Integer.parseInt(id));
			req.setAttribute("usu", usuario);
			RequestDispatcher dispatcher = req.getRequestDispatcher("formusuario.jsp");
			dispatcher.forward(req, resp);
		}
		else if(acao.equals("novcad")){
			Usuario usu = new Usuario();
			usu.setId(0);
			usu.setNome("");
			usu.setLogin("");
			usu.setSenha("");
			
			req.setAttribute("usu", usu);
			RequestDispatcher dispatcher = req.getRequestDispatcher("formusuario.jsp");
			dispatcher.forward(req, resp);
			resp.sendRedirect("usucontroller.do?acao=listar");
			
		}
	}	
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
	
		Usuario usu = new Usuario();
		
		if(id!=null)
			usu.setId(Integer.parseInt(id));
		
		usu.setNome(nome);
		usu.setLogin(login);
		usu.setSenha(senha);
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usu);
		resp.getWriter().print("<b>sucesso!!<b/>");
		System.out.print("sucesso!!");
}
@Override
	public void destroy(){
	System.out.println("destroy..");
		super.destroy();
	}
}
