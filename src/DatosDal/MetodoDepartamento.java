package DatosDal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;

public class MetodoDepartamento {
	Datos.Departamento dep=new Datos.Departamento();
	
	public boolean GuardarDepartamento(String idDep, String nombreDep, String directorDep){
		boolean estado=false;
		try{
			System.out.println("Ingreso guardar");
			Conection.conexion conex=new Conection.conexion();
			conex.getConexion();
			String sql="insert into departamento(idDep,nombreDep,directorDep) values ( ? , ? , ? )";
			PreparedStatement g=conex.getConexion().prepareStatement(sql);
			System.out.println("Prepared guardar");
			g.setString(1, idDep);
			g.setString(2, nombreDep);
			g.setString(3, directorDep);
			System.out.println("Ejecuta guardar");
			g.execute();
			estado=true;
		}catch(SQLException e){
			e.printStackTrace();
			estado=false;
		}
		return true;
	}
	
	public boolean EliminarDepartamento(int fila, JTable table){
		try{
			Conection.conexion conex=new Conection.conexion();
			conex.getConexion();
			String sql="delete from departamento where idDep="+table.getValueAt(fila, 1);
			PreparedStatement s=conex.getConexion().prepareStatement(sql);
			if(s.execute()){
				return true;
			}else{
				return false;
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return true;
	} 
	
	public boolean ActualizarDepartamento(String idDep, String nombreDep, String directorDep){
		try{
			Conection.conexion conex=new Conection.conexion();
			conex.getConexion();
			String sql="update departamento set nombreDep=?,director=? where idDep=?";
			PreparedStatement s=conex.getConexion().prepareStatement(sql);
			s.setString(1, nombreDep);
			s.setString(2, directorDep);
			if(s.execute()){
				return true;
			}else{
				return false;
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return true;
	}
	
	public void ConsultarDepartamento(String codigo){
		try{
			Conection.conexion conex=new Conection.conexion();
			conex.getConexion();
			String sql="select * from departamento where idDep="+codigo;
			PreparedStatement s=conex.getConexion().prepareStatement(sql);
			ResultSet rs=(ResultSet) s.executeQuery(sql);
			while(rs.next()){
				dep.idDep=rs.getString("idDep");
				dep.nombreDep=rs.getString("nombreDep");
				dep.directorDep=rs.getString("directorDep");
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
}
