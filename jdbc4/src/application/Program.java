package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"UPDATE seller "
					+ "SET BaseSalary = BaseSalary + ?" // o interrogação vai ser o valor acrescentado
					+ "WHERE " // WHERE voce esta colocando uma restrição, nunca faça uma atualizaçao sem a palavra WHEERE se nao atuali todos do banco		
					+ "(DepartmentId = ?)");
					st.setDouble(1, 200.0); // aumentando 200 a mais do que ja estava
					st.setInt(2, 2); // o primeiro 2 e referente a segunda interrogação e o segundo numero 2 e referente ao departamento
					
					int rowsAffected = st.executeUpdate();
					System.out.println("Done! Rows Affected " + rowsAffected);
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			DB.closeConnection();
			DB.closeStatement(st);	
		}
		
	}
}