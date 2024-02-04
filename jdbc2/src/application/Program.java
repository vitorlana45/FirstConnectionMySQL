package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {
		Connection conn = null;
		Statement st = null;
		ResultSet  rs = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.createStatement(); // intanciando u objeto do tipo statement
			rs = st.executeQuery("select *from department"); // esse metodo ele espera que eu entre com uma String justamente o comando SQL
			// o resultado da  minha consulta pelo st sera passado para o result rs
			
			while (rs.next()) {
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			DB.closeConnection();
		}
	}
}
