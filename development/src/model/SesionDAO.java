package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
			String query=" INSERT INTO sesiones (fechaA, horaA, fechaC, horaC, usuario values ( ?, ?, ?, ?, ?)";
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
			String query ="";
			ArrayList<Object> listaSesion = new ArrayList<Object>();
			if(campoBusqueda.isEmpty() || valorBusqueda.isEmpty()) {
				query="SELECT * FROM roles ORDER BY sysPK;";
				try {
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(query);
					Sesion sesion = null;
					while (resultSet.next()) {
						sesion=new Sesion();
						DateFormat formatoDate = new SimpleDateFormat("DD/MM/AAAA");
						DateFormat formatoTime = new SimpleDateFormat("HH:MM:SS");
						sesion.setSysPk(Integer.parseInt(resultSet.getString(1)));
						Date sFechaA = (Date) formatoDate.parse(resultSet.getString(2));
						sesion.setFechaApertura(sFechaA);
						Time sHoraApertura = (Time) formatoTime.parse(resultSet.getString(3));
						sesion.setHoraApertura(sHoraApertura);
						Date sFechaC = (Date) formatoDate.parse(resultSet.getString(4));
						sesion.setFechaCierre(sFechaC);
						Time sHoraCierre = (Time) formatoTime.parse(resultSet.getString(5));
						sesion.setHoraCierre(sHoraCierre);
						Usuario nuevousuario = new Usuario();
						nuevousuario.setSysPk(resultSet.getInt(6));
						listaSesion.add(sesion);
					}
				}catch (SQLException | ParseException e) {
						System.out.println("Error: En método leer");
						e.printStackTrace();
					}
			}else {
				query="SELECT * FROM roles WHERE "+campoBusqueda+" = ? ORDER BY sysPK;";
				
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, valorBusqueda);
					ResultSet resultSet=preparedStatement.executeQuery();
					Sesion sesion = null;
					while (resultSet.next()) {
						sesion=new Sesion();
						DateFormat formatoDate = new SimpleDateFormat("DD/MM/AAAA");
						DateFormat formatoTime = new SimpleDateFormat("HH:MM:SS");
						sesion.setSysPk(Integer.parseInt(resultSet.getString(1)));
						Date sFechaA = (Date) formatoDate.parse(resultSet.getString(2));
						sesion.setFechaApertura(sFechaA);
						Time sHoraApertura = (Time) formatoTime.parse(resultSet.getString(3));
						sesion.setHoraApertura(sHoraApertura);
						Date sFechaC = (Date) formatoDate.parse(resultSet.getString(4));
						sesion.setFechaCierre(sFechaC);
						Time sHoraCierre = (Time) formatoTime.parse(resultSet.getString(5));
						sesion.setHoraCierre(sHoraCierre);
						Usuario nuevousuario = new Usuario();
						nuevousuario.setSysPk(resultSet.getInt(6));
						listaSesion.add(sesion);
					}
				}catch (SQLException | ParseException e) {
						System.out.println("Error: En método leer");
						e.printStackTrace();
					}
				
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
