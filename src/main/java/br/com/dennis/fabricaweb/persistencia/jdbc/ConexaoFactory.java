package br.com.dennis.fabricaweb.persistencia.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	public static Connection getConnection() {
		
		try {
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/fabricaweb","postgres","Fcsoldier855");
		} catch (SQLException e) {
			// relancando a exception
			throw new RuntimeException(e);
		}
	}

}
