package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class SesionDAO implements ObjectDAO {
	public String query ="";
	//Metodo crear para crear sesion
		@Override
		public boolean crear(Connection connection, Object sesion) {		
			Sesion nuevasesion=(Sesion)sesion;
			String query=" INSERT INTO sesiones (fechaA, horaA, fechaC, horaC, usuario)"
			        + " values ( ?, ?, ?, ?, ?)";
			try {			
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
				preparedStatement.setDate(1, nuevasesion.getFechaApertura());
				preparedStatement.setTime(2, nuevasesion.getHoraApertura());
				preparedStatement.setDate(3, nuevasesion.getFechaCierre());
				preparedStatement.setTime(4, nuevasesion.getHoraCierre());
				preparedStatement.setInt(5, nuevasesion.getUsuario().getSysPk());
				preparedStatement.execute();
				return true;                              
			} catch (SQLException e) {
				System.out.println("Error: En método crear");
				e.printStackTrace();
				return false;
			}
		}

		//Metodo para hacer un select a la tabla sesiones
		@Override
		public ArrayList<Object> leer(Connection connection, String campoBusqueda, String valorBusqueda) {

			if(campoBusqueda.isEmpty() || valorBusqueda.isEmpty()) {
				query="SELECT * FROM sesiones ORDER BY sysPK";
			}else {
				query="SELECT * FROM sesiones WHERE ? = ?  ORDER BY sysPK";
			}
			
			ArrayList<Object> listaSesion = new ArrayList<Object>();
			
			try {
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
				preparedStatement.setString(1, campoBusqueda);
				preparedStatement.setString(2, valorBusqueda);
				ResultSet rs=preparedStatement.executeQuery();
				while (rs.next()) {
					Sesion sesion=new Sesion();
					DateFormat formatoDate = new SimpleDateFormat("DD/MM/AAAA");
					DateFormat formatoTime = new SimpleDateFormat("HH:MM:SS");
					sesion.setSysPk(Integer.parseInt(rs.getString(1)));
					Date sFechaA = (Date) formatoDate.parse(rs.getString(2));
					sesion.setFechaApertura(sFechaA);
					Time sHoraApertura = (Time) formatoTime.parse(rs.getString(3));
					sesion.setHoraApertura(sHoraApertura);
					Date sFechaC = (Date) formatoDate.parse(rs.getString(4));
					sesion.setFechaCierre(sFechaC);
					Time sHoraCierre = (Time) formatoTime.parse(rs.getString(5));
					sesion.setHoraCierre(sHoraCierre);
					Usuario nuevousuario = new Usuario();
					nuevousuario.setSysPk(rs.getInt(6));
					listaSesion.add(sesion);
				}
			}catch (SQLException | ParseException e) {
					System.out.println("Error: En método leer");
					e.printStackTrace();
				}
			return listaSesion;

		}

		//Metodo para hacer un update en la tabla usuarios
		@Override
		public boolean modificar(Connection connection, Object usuario) {
			String query="UPDATE usuarios\r\n" + 
					"    SET sysPK= ?, fechaA= ?, horaA= ?, fechaC= ?, horaC= ?, usuario= ?\r\n" + 
					"    WHERE sysPK= ?;";
			try {
				Sesion sesion=new Sesion();
				PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
				preparedStmt.setInt(1, sesion.getSysPk());
				preparedStmt.setDate(2, sesion.getFechaApertura());
				preparedStmt.setTime(3, sesion.getHoraApertura());
				preparedStmt.setDate(4, sesion.getFechaApertura());
				preparedStmt.setTime(5, sesion.getHoraCierre());
				preparedStmt.setInt(6, sesion.getUsuario().getSysPk());	
				preparedStmt.setInt(7, sesion.getSysPk());
				preparedStmt.execute();
				return true;
			} catch (SQLException e) {
				System.out.println("Error: En método modificar");
				e.printStackTrace();
				return false;
			}
		}

		//Metodo para hacer un delete en la tabla sesiones
		@Override
		public boolean eliminar(Connection connection, Object sesiones) {
			String query=" DELETE FROM sesiones WHERE sysPK= ?";
			try {			
				Sesion sesion=new Sesion();
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
				preparedStatement.setInt(1, sesion.getSysPk());
				preparedStatement.execute();
				return true;
			} catch (SQLException e) {
				System.out.println("Error: En método eliminar");
				e.printStackTrace();
				return false;
			}		
		}
	

}
