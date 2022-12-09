package DatosDal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTable;

public class MetodoDocente {
	Datos.Docente docente=new Datos.Docente(); 
	
	public boolean GuardarDocente(String idDocente, String cedulaDocente, String nombreDocente, String apellidoDocente,
								  String direccionDocente, String correoDocente, String telefonoDocente){
		boolean estado=false;
		try{
			System.out.println("Ingreso guardar");
			Conection.conexion conex=new Conection.conexion();
			conex.getConexion();
			String sql="insert into docente(idDocente,cedulaDocente,nombreDocente,apellidoDocente,direccionDocente,correoDocente,telefonoDocente) values ( ? , ? , ? , ? , ? , ? , ?)";
			PreparedStatement g=conex.getConexion().prepareStatement(sql);
			System.out.println("Prepared guardar");
			g.setString(1, idDocente);
			g.setString(2, cedulaDocente);
			g.setString(3, nombreDocente);
			g.setString(4, apellidoDocente);
			g.setString(5, direccionDocente);
			g.setString(6, correoDocente);
			g.setString(7, telefonoDocente);
			System.out.println("Ejecuta guardar");
			g.execute();
			estado=true;
		}catch(SQLException e){
			e.printStackTrace();
			estado=false;
		}
		return true;
	}
	
	public boolean EliminarDocente(int fila, JTable table){
		try{
			Conection.conexion conex=new Conection.conexion();
			conex.getConexion();
			String sql="delete from docente where cedulaDocente="+table.getValueAt(fila, 1);
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
	
	public boolean ActualizarDocente(String idDocente, String cedulaDocente, String nombreDocente, String apellidoDocente,
			  String direccionDocente, String correoDocente, String telefonoDocente){
		try{
			Conection.conexion conex=new Conection.conexion();
			conex.getConexion();
			String sql="update docente set cedulaDocente=?,nombreDocente=?,apellidoDocente=?,direccionDocente=?,correoDocente=?,telefonoDocente=? where idDocente=?";
			PreparedStatement s=conex.getConexion().prepareStatement(sql);
			s.setString(1, cedulaDocente);
			s.setString(2, nombreDocente);
			s.setString(3, apellidoDocente);
			s.setString(4, direccionDocente);
			s.setString(5, correoDocente);
			s.setString(6, telefonoDocente);
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
	
	public void ConsultarDocente(String cedula){
		try{
			Conection.conexion conex=new Conection.conexion();
			conex.getConexion();
			String sql="select * from docente where cedulaDocente="+cedula;
			PreparedStatement s=conex.getConexion().prepareStatement(sql);
			ResultSet rs=(ResultSet) s.executeQuery(sql);
			while(rs.next()){
				docente.idDocente=rs.getString("idDocente");
				docente.cedulaDocente=rs.getString("cedulaDocente");
				docente.nombreDocente=rs.getString("nombreDocente");
				docente.apellidoDocente=rs.getString("apellidoDocente");
				docente.direccionDocente=rs.getString("direccionDocente");
				docente.correoDocente=rs.getString("correoDocente");
				docente.telefonoDocente=rs.getString("telefonoDocente");
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
	}
	
	public boolean ValidarCedula(String cedula){
		boolean cedulaCorrecta=false;
		try{
			if(cedula.length()==10){
				int tercerDigito=Integer.parseInt(cedula.substring(2,3));
				if(tercerDigito<6){
					int [] coefValCedula={2,1,2,1,2,1,2,1,2};
					int verificador=Integer.parseInt(cedula.substring(9,10));
					int suma=0;
					int digito=0;
					for(int i=0;i<(cedula.length()-1);i++){
						digito=Integer.parseInt(cedula.substring(i,i+1))*coefValCedula[i];
						suma+=((digito%10)+(digito/10));
					}
					if((suma%10==0) && (suma%10==verificador)){
						cedulaCorrecta=true;
					}else if((10-(suma%10))==verificador){
						cedulaCorrecta=true;
					}else{
						cedulaCorrecta=false;
					}
				}else{
					cedulaCorrecta=false;
				}
			}else{
				cedulaCorrecta=false;
			}
		}catch(NumberFormatException nfe){
			cedulaCorrecta=false;
		}catch(Exception err){
			JOptionPane.showMessageDialog(null, "Una excepcion ocurrio en el proceso de validacion");
			cedulaCorrecta=false;
		}
		if(!cedulaCorrecta){
			JOptionPane.showMessageDialog(null, "La cedula ingresada es Incorrecta");
		}
		return cedulaCorrecta;
	}
}