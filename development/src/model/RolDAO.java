package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RolDAO implements ObjectDAO{
	public String query ="";
	//Metodo crear para crear un rol
		@Override
		public boolean crear(Connection connection, Object rol) {		
			Rol nuevorol=(Rol)rol;
			String query=" INSERT INTO roles (codigoItem, descripcion)"
			        + " values ( ?, ?)";
			try {			
				PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
				preparedStmt.setString(1, nuevorol.getCodigoItem());
				preparedStmt.setString(2, nuevorol.getDescripcion());
				preparedStmt.execute();
				return true;
			} catch (SQLException e) {
				System.out.println("Error: En método crear");
				e.printStackTrace();
				return false;
			}
		}

		//Metodo para hacer un select a la tabla rol
		@Override
		public ArrayList<Object> leer(Connection connection, String campoBusqueda, String valorBusqueda) {
			ArrayList<Object> listaRol = new ArrayList<Object>();
			if(campoBusqueda.isEmpty() || valorBusqueda.isEmpty()) {
				query="SELECT * FROM roles ORDER BY sysPK";
				try {
					ResultSet rs = connection.createStatement().executeQuery(query);							
					System.out.println(query);
					while (rs.next()) {
						Rol nuevorol = new Rol();
						nuevorol.setSysPk(Integer.parseInt(rs.getString(1)));
						nuevorol.setCodigoItem(rs.getString(2));
						nuevorol.setDescripcion(rs.getString(3));
						listaRol.add(nuevorol);
						System.out.println(rs.next());
					}
				}catch (SQLException e) {
						System.out.println("Error: En método leer");
						e.printStackTrace();
					}
			}else {
				query="SELECT * FROM roles WHERE ? = ?  ORDER BY sysPK";
							
				try {
					System.out.println(query);
					PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
					preparedStmt.setString(1, campoBusqueda);
					preparedStmt.setString(2, valorBusqueda);
					ResultSet rs=preparedStmt.executeQuery();
					while (rs.next()) {
						Rol nuevorol = new Rol();
						nuevorol.setSysPk(Integer.parseInt(rs.getString(1)));
						nuevorol.setCodigoItem(rs.getString(2));
						nuevorol.setDescripcion(rs.getString(3));
						listaRol.add(nuevorol);
						System.out.println(rs.next());
					}
				}catch (SQLException e) {
						System.out.println("Error: En método leer");
						e.printStackTrace();
					}
				
			}
			return listaRol;
			
			

		}

		//Metodo para hacer un update en la tabla rol
		@Override
		public boolean modificar(Connection connection, Object rol) {
			String query="UPDATE roles SET codigoItem= ?, descripcion= ? WHERE sysPK= ?;";
			try {
				Rol nuevorol=(Rol)rol;
				PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
				preparedStmt.setString(1, nuevorol.getCodigoItem());
				preparedStmt.setString(2, nuevorol.getDescripcion());	
				preparedStmt.setInt(3, nuevorol.getSysPk());
				preparedStmt.execute();
				return true;
			} catch (SQLException e) {
				System.out.println("Error: En método modificar");
				e.printStackTrace();
				return false;
			}
		}

		//Metodo para hacer un delete en la tabla rol
		@Override
		public boolean eliminar(Connection connection, Object rol) {
			String query=" DELETE FROM roles WHERE sysPK= ?";
			try {			
				Rol nuevorol=(Rol)rol;
				PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
				preparedStmt.setInt(1, nuevorol.getSysPk());
				preparedStmt.execute();
				return true;
			} catch (SQLException e) {
				System.out.println("Error: En método eliminar");
				e.printStackTrace();
				return false;
			}		
		}

}
