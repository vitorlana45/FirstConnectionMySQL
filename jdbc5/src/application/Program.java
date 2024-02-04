package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"DELETE FROM department " 
					+ "WHERE " // colocando o WHERE voce poe uma restrição, sem ele voce apaga todo mundo 
					+ "Id = ?");
			
			st.setInt(1, 2); // a primeira interrogacao vai valer o ID 5 
			
					int rowsAffected = st.executeUpdate();
					System.out.println("Done! Rows Affected " + rowsAffected);
		}catch (SQLException e){
			throw new DbIntegrityException(e.getMessage());
		}finally {
			DB.closeConnection();
			DB.closeStatement(st);	
		}
		
	}
}