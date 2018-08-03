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
			Rol claseRol=(Rol)rol;
			String query=" INSERT INTO roles (codigoItem, descripcion)"
			        + " values ( ?, ?)";
			try {			
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
				preparedStatement.setString(1, claseRol.getCodigoItem());
				preparedStatement.setString(2, claseRol.getDescripcion());
				preparedStatement.execute();
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
					ResultSet resultSet = connection.createStatement().executeQuery(query);							
					System.out.println(query);
					Rol claseRol = new Rol();
					while (resultSet.next()) {						
						claseRol.setSysPk(Integer.parseInt(resultSet.getString(1)));
						claseRol.setCodigoItem(resultSet.getString(2));
						claseRol.setDescripcion(resultSet.getString(3));
						listaRol.add(claseRol);
						System.out.println(resultSet.next());
					}
				}catch (SQLException e) {
						System.out.println("Error: En método leer");
						e.printStackTrace();
					}
			}else {
				query="SELECT * FROM roles WHERE ? = ?  ORDER BY sysPK";
							
				try {
					System.out.println(query);
					PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
					preparedStatement.setString(1, campoBusqueda);
					preparedStatement.setString(2, valorBusqueda);
					ResultSet rs=preparedStatement.executeQuery();
					Rol claseRol = new Rol();
					while (rs.next()) {
						claseRol.setSysPk(Integer.parseInt(rs.getString(1)));
						claseRol.setCodigoItem(rs.getString(2));
						claseRol.setDescripcion(rs.getString(3));
						listaRol.add(claseRol);
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
				Rol claseRol=(Rol)rol;
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
				preparedStatement.setString(1, claseRol.getCodigoItem());
				preparedStatement.setString(2, claseRol.getDescripcion());	
				preparedStatement.setInt(3, claseRol.getSysPk());
				preparedStatement.execute();
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
				Rol claseRol=(Rol)rol;
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
				preparedStatement.setInt(1, claseRol.getSysPk());
				preparedStatement.execute();
				return true;
			} catch (SQLException e) {
				System.out.println("Error: En método eliminar");
				e.printStackTrace();
				return false;
			}		
		}

}
