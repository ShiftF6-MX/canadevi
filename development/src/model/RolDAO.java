package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class RolDAO implements ObjectDAO{
	public String query ="";
	//Metodo crear para crear un rol
		@Override
		public boolean crear(Connection connection, Object rol) throws Exception {		
			Rol nuevorol=(Rol)rol;
			String query=" INSERT INTO rol (codigoItem, descripcion)"
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
		public ArrayList<Object> leer(Connection connection, String campoBusqueda, String valorBusqueda) throws Exception {
			
			if(campoBusqueda.isEmpty() || valorBusqueda.isEmpty()) {
				query="SELECT * FROM roles WHERE ORDER BY sysPK";
			}else {
				query="SELECT * FROM roles WHERE ? = ?  ORDER BY sysPK";
			}
			
			ArrayList<Object> listaRol = new ArrayList<Object>();
			
			try {
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
				}
			}catch (SQLException e) {
					System.out.println("Error: En método leer");
					e.printStackTrace();
				}
			return listaRol;

		}

		//Metodo para hacer un update en la tabla rol
		@Override
		public boolean modificar(Connection connection, Object rol) throws Exception {
			String query="UPDATE rol SET codigoItem= ?, descripcion= ? WHERE sysPK= ?;";
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
		public boolean eliminar(Connection connection, Object rol) throws Exception {
			String query=" DELETE FROM rol WHERE sysPK= ?";
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
