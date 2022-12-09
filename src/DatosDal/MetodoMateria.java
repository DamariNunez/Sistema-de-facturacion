package DatosDal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;

public class MetodoMateria {
	Datos.Materia materia = new Datos.Materia();
	
	public boolean GuardarMateria(String idMateria,String nombreMateria,String creditoMateria, String idDocente, String idDep){
		boolean estado=false;
		try{
			System.out.println("Ingreso guardar");
			Conection.conexion conex=new Conection.conexion();
			conex.getConexion();
			String sql="insert into materia(idMateria,nombreMateria,creditoMateria,idDocente,idDep) values ( ? , ? , ? , ? , ?)";
			PreparedStatement g=conex.getConexion().prepareStatement(sql);
			System.out.println("Prepared guardar");
			g.setString(1, idMateria);
			g.setString(2, nombreMateria);
			g.setString(3, creditoMateria);
			g.setString(4, idDocente);
			g.setString(5, idDep);
			System.out.println("Ejecuta guardar");
			g.execute();
			estado=true;
		}catch(SQLException e){
			e.printStackTrace();
			estado=false;
		}
		return true;
	}
	
	public boolean EliminarMateria(int fila, JTable table){
		try{
			Conection.conexion conex=new Conection.conexion();
			conex.getConexion();
			String sql="delete from materia where idMateria="+table.getValueAt(fila, 1);
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
	
	public boolean ActualizarMateria(String idMateria,String nombreMateria,String creditoMateria,String idDocente, String idDep){
		try{
			Conection.conexion conex=new Conection.conexion();
			conex.getConexion();
			String sql="update materia set nombreMateria=?,creditoMateria=?,idDocente=?,idDep=? where idMateria=?";
			PreparedStatement s=conex.getConexion().prepareStatement(sql);
			s.setString(1, nombreMateria);
			s.setString(2, creditoMateria);
			s.setString(3, idDocente);
			s.setString(4, idDep);
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
	
	public void ConsultarMateria(String codigo){
		try{
			Conection.conexion conex=new Conection.conexion();
			conex.getConexion();
			String sql="select * from materia where idMateria="+codigo;
			PreparedStatement s=conex.getConexion().prepareStatement(sql);
			ResultSet rs=(ResultSet) s.executeQuery(sql);
			while(rs.next()){
				materia.idMateria=rs.getString("idMateria");
				materia.nombreMateria=rs.getString("nombreMateria");
				materia.creditoMateria=rs.getInt("creditoMateria");
				materia.idDocente=rs.getString("idDocente");
				materia.idDep=rs.getString("idDep");
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
}