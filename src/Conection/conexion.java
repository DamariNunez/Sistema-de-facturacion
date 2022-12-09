package Conection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
	private Connection conexion=null;
	private String servidor="localhost";
	private String database="gestionardocente";
	private String usuario="root";
	private String password="root";
	private String url="jdbc:mysql://localhost:8080/"+database;
	
	public conexion(){
		try{
			this.servidor=servidor;
			this.database=database;
			Class.forName("com.mysql.jdbc.Driver");
			url="jdbc:mysql://"+servidor+"/"+database;
			conexion=DriverManager.getConnection(url, usuario, password);
			System.out.println("Conexion a Base de Datos "+url+" . . . . . Ok");
		}catch(SQLException ex){
			System.out.println(ex);
		}catch(ClassNotFoundException ex){
			System.out.println(ex);
		}
	}
	
	public Connection getConexion(){
		return conexion;
	}
	
	public Connection cerrarConexion(){
		try{
			conexion.close();
			System.out.println("Cerrando conexion a "+url+". . . . . Ok");
		}catch(SQLException ex){
			System.out.println(ex);
		}
		conexion=null;
		return conexion;
	}
}
