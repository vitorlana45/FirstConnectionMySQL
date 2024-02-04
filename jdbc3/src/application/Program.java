package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;


import db.DB;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
	Connection conn = null;
	PreparedStatement st = null;
	try {
		conn = DB.getConnection(); // conectando com o banco 
		/*
		// o prepareStatement espera uma string que sera meu comando sql
		st = conn.prepareStatement( 	
				"INSERT INTO seller "
				+ "(Name, Email, BirthDate, BaseSalary, DepartmentId)" // colocar entre parentes os campos da tabela que eu quero preencher
				+ "VALUES"
				// colocando a interrogação transforma em um placeHolder um lungar onde depois coloca o valor
				+ "(?, ?, ?, ?, ?)", // neste caso a quantidade de Interrogação e pelo quantidade de valores acima como Name, Email etc..
				Statement.RETURN_GENERATED_KEYS);		
				
		// comandos para trocar as interrogações " os placeHolder " pelo valor que quiser
		
		st.setString(1, "Carl Purple"); // 1 referente a primeira interrogação depois e os dados que substituira 
		st.setString(2, "carl@gmail.com");
		// pra instanciar a data tenho que criar um objeteo simpleDateFormate
		st.setDate(3, new java.sql.Date(sdf.parse("22/04/1985").getTime()));
		st.setDouble(4, 3000);
		st.setInt(5, 4);
		
		*/  
		
		st = conn.prepareStatement
			// adicionando dois novos departamento e pegando seu ID
				
			("Insert into department (Name) values ('D1'),('D2')",
						Statement.RETURN_GENERATED_KEYS); // um comando so pra inserir dois departamentos ao mesmo tempo
		
		int rowAffected = st.executeUpdate(); // pra saber quantas lihas foram alteradas no banco de dados
		
		if (rowAffected > 0) {
			ResultSet rs = st.getGeneratedKeys();
			while(rs.next()) {
				int id = rs.getInt(1); // indicando que quero o valor da primeira coluna
				System.out.println("Done! Id = " + id);
			}
		}else {
			System.out.println("No row affected");
		}
			
	
	}catch (SQLException e) {
		e.printStackTrace();
	//}catch (ParseException e) {
		//e.printStackTrace();
	}finally { // fechar as conexoes abertas // sempre fecha a conexão com bancos de dados por ultimo;
		DB.closeStatement(st);
		DB.closeConnection(); 
		}
	}
}