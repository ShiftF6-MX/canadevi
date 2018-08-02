package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class SesionDAO implements ObjectDAO {
	public String query ="";
	//Metodo crear para crear sesion
		@Override
		public boolean crear(Connection connection, Object sesion) throws Exception {		
			Sesion nuevasesion=(Sesion)sesion;
			String query=" INSERT INTO sesiones (fechaA, horaA, fechaC, horaC, usuario)"
			        + " values ( ?, ?, ?, ?, ?)";
			try {			
				PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
				preparedStmt.setDate(1, nuevasesion.getFechaApertura());
				preparedStmt.setTime(2, nuevasesion.getHoraApertura());
				preparedStmt.setDate(3, nuevasesion.getFechaCierre());
				preparedStmt.setTime(4, nuevasesion.getHoraCierre());
				preparedStmt.setInt(5, nuevasesion.getUsuario().getSysPk());
				preparedStmt.execute();
				return true;                              
			} catch (SQLException e) {
				System.out.println("Error: En método crear");
				e.printStackTrace();
				return false;
			}
		}

		//Metodo para hacer un select a la tabla sesiones
		@Override
		public ArrayList<Object> leer(Connection connection, String campoBusqueda, String valorBusqueda) throws Exception {

			if(campoBusqueda.isEmpty() || valorBusqueda.isEmpty()) {
				query="SELECT * FROM sesiones WHERE ORDER BY sysPK";
			}else {
				query="SELECT * FROM sesiones WHERE ? = ?  ORDER BY sysPK";
			}
			
			ArrayList<Object> listaSesion = new ArrayList<Object>();
			
			try {
				PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
				preparedStmt.setString(1, campoBusqueda);
				preparedStmt.setString(2, valorBusqueda);
				ResultSet rs=preparedStmt.executeQuery();
				while (rs.next()) {
					Sesion nuevasesion=new Sesion();
					DateFormat formatoDate = new SimpleDateFormat("DD/MM/AAAA");
					DateFormat formatoTime = new SimpleDateFormat("HH:MM:SS");
					nuevasesion.setSysPk(Integer.parseInt(rs.getString(1)));
					Date sFechaA = (Date) formatoDate.parse(rs.getString(2));
					nuevasesion.setFechaApertura(sFechaA);
					Time sHoraApertura = (Time) formatoTime.parse(rs.getString(3));
					nuevasesion.setHoraApertura(sHoraApertura);
					Date sFechaC = (Date) formatoDate.parse(rs.getString(4));
					nuevasesion.setFechaCierre(sFechaC);
					Time sHoraCierre = (Time) formatoTime.parse(rs.getString(5));
					nuevasesion.setHoraCierre(sHoraCierre);
					Usuario nuevousuario = new Usuario();
					nuevousuario.setSysPk(rs.getInt(6));
					listaSesion.add(nuevasesion);
				}
			}catch (SQLException e) {
					System.out.println("Error: En método leer");
					e.printStackTrace();
				}
			return listaSesion;

		}

		//Metodo para hacer un update en la tabla usuarios
		@Override
		public boolean modificar(Connection connection, Object usuario) throws Exception {
			String query="UPDATE usuarios\r\n" + 
					"    SET sysPK= ?, fechaA= ?, horaA= ?, fechaC= ?, horaC= ?, usuario= ?\r\n" + 
					"    WHERE sysPK= ?;";
			try {
				Sesion nuevasesion=new Sesion();
				PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
				preparedStmt.setInt(1, nuevasesion.getSysPk());
				preparedStmt.setDate(2, nuevasesion.getFechaApertura());
				preparedStmt.setTime(3, nuevasesion.getHoraApertura());
				preparedStmt.setDate(4, nuevasesion.getFechaApertura());
				preparedStmt.setTime(5, nuevasesion.getHoraCierre());
				preparedStmt.setInt(6, nuevasesion.getUsuario().getSysPk());	
				preparedStmt.setInt(7, nuevasesion.getSysPk());
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
		public boolean eliminar(Connection connection, Object sesiones) throws Exception {
			String query=" DELETE FROM sesiones WHERE sysPK= ?";
			try {			
				Sesion nuevasesion=new Sesion();
				PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
				preparedStmt.setInt(1, nuevasesion.getSysPk());
				preparedStmt.execute();
				return true;
			} catch (SQLException e) {
				System.out.println("Error: En método eliminar");
				e.printStackTrace();
				return false;
			}		
		}
	

}
