package br.com.dennis.fabricaweb.persistencia.jdbc;

import br.com.dennis.fabricaweb.persistencia.entidade.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
	private Connection con = ConexaoFactory.getConnection();
	public void cadastrar(Usuario usu) {
		String sql = "insert into usuario (nome,login,senha) values (?,?,?)";
		//....
		try (PreparedStatement preparador  = con.prepareStatement(sql)){
			preparador.setString(1, usu.getNome());	// substitue ? pelo dado de usuario
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			// executando o comando sql no banco
			preparador.execute();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void alterar(Usuario usu) {
		String sql = "update usuario set nome=?, login=?, senha=? where id=?";
		//....
		try (PreparedStatement preparador  = con.prepareStatement(sql)){
			preparador.setString(1, usu.getNome());	// substitue ? pelo dado de usuario
			preparador.setString(2, usu.getLogin());
			preparador.setString(3, usu.getSenha());
			preparador.setInt(4, usu.getId());
			// executando o comando sql no banco
			preparador.execute();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void excluir(Usuario usu) {
		String sql = "delete from usuario where id=?";
		//....
		try (PreparedStatement preparador  = con.prepareStatement(sql)){
			
			preparador.setInt(1, usu.getId());
			// executando o comando sql no banco
			preparador.execute();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void salvar(Usuario usuario){
		if (usuario.getId()!=null && usuario.getId()!=0){
			alterar(usuario);
		}else{
			cadastrar(usuario);
		}
	}
	/**
	 * busca de um registro no banco de dados pelo numero do Id do usuario
	 * @param id eh um inteiro que representa o numero de um id do usuario a ser buscado
	 * @return um objeto usuario quando encontra ou nulo quando nao encontra
	 */
	public Usuario buscaPorId(Integer id){
		
		String sql = "Select * from usuario where id =?";
		try(PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setInt(1, id);
			
			ResultSet resultado = preparador.executeQuery();
			// posicionando o cursor no primeiro registro
			if(resultado.next()){
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				
				return usuario;
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * realiza a busca de todos os registros da tabela de usuarios
	 * @return	uma lista de objetos Usuario contendo 0 elementos quando nao
	 * tiver registros ou n elementos quando tiver resultados
	 */
	public List<Usuario> buscarTodos(){
		
		String sql = "Select * from usuario order by id";
		List<Usuario> lista = new ArrayList<Usuario>();
		try(PreparedStatement preparador = con.prepareStatement(sql)){
			
			ResultSet resultado = preparador.executeQuery();
			// posicionando o cursor no primeiro registro
			while(resultado.next()){
				Usuario usuario = new Usuario();
				usuario.setId(resultado.getInt("id"));
				usuario.setNome(resultado.getString("nome"));
				usuario.setLogin(resultado.getString("login"));
				usuario.setSenha(resultado.getString("senha"));
				// add usuario na lista
				lista.add(usuario);				
			}
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return lista;
	}
	/**
	 * metodo para autenticar usuario
	 * @param usuConsulta login e senha para realizar a busca
	 * @return objeto com os dados do usuario encontrado ou nulo se nao encontrar nada 
	 */
	public Usuario autenticar(Usuario usuConsulta){
		String sql = "Select *from usuario where login=? and senha=? order by id";
		
		try(PreparedStatement preparador = con.prepareStatement(sql)){
			preparador.setString(1, usuConsulta.getLogin());
			preparador.setString(2, usuConsulta.getSenha());
			ResultSet resultado = preparador.executeQuery();
			
			if(resultado.next()){
			Usuario usuario = new Usuario();
			usuario.setId(resultado.getInt("id"));
			usuario.setNome(resultado.getString("nome"));
			usuario.setLogin(resultado.getString("login"));
			usuario.setSenha(resultado.getString("senha"));
			
			return usuario;
			}else{
				return null;
			}
		}catch (SQLException e){
			e.printStackTrace();
		}
		return null;
		
	}
}
