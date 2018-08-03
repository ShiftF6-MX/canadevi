package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class GrupoUsuarioDAO implements ObjectDAO {
	//Metodo crear para crear un Grupo Usuario
	@Override
	public boolean crear(Connection connection, Object grupousuario) {
		GrupoUsuario grupoUsuario=(GrupoUsuario)grupousuario;
		String query=" INSERT INTO gruposusuario (nombre, descripcion) values ( ?, ?)";
		try {			
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setString(1, grupoUsuario.getNombre());
			preparedStatement.setString(2, grupoUsuario.getDescripcion());
			preparedStatement.execute();
			return true;
			} catch (SQLException e) {
				System.out.println("Error: En método crear");
				e.printStackTrace();
				return false;
				}
		}
	//Metodo para hacer un select a la tabla grupousuario
	@Override
	public ArrayList<Object> leer(Connection connection, String campoBusqueda, String valorBusqueda) {
		String query ="";
		ArrayList<Object> listaGrupoUsuario = new ArrayList<Object>();
		if(campoBusqueda.isEmpty() || valorBusqueda.isEmpty()) {
			query="SELECT * FROM gruposusuario ORDER BY sysPK";
			try {
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				GrupoUsuario grupoUsuario = null;
				while (resultSet.next()) {
					grupoUsuario = new GrupoUsuario();
					grupoUsuario.setSysPk(Integer.parseInt(resultSet.getString(1)));
					grupoUsuario.setNombre(resultSet.getString(2));
					grupoUsuario.setDescripcion(resultSet.getString(3));
					listaGrupoUsuario.add(grupoUsuario);
				}
			}catch (SQLException e) {
					System.out.println("Error: En método leer");
					e.printStackTrace();
				}
		}else {
			query="SELECT * FROM gruposusuario WHERE "+campoBusqueda+" = ?  ORDER BY sysPK";
						
			try {
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
				preparedStatement.setString(1, valorBusqueda);
				ResultSet resultSet=preparedStatement.executeQuery();
				GrupoUsuario grupoUsuario = null;
				while (resultSet.next()) {
					grupoUsuario = new GrupoUsuario();
					grupoUsuario.setSysPk(resultSet.getInt(1));
					grupoUsuario.setNombre(resultSet.getString(2));
					grupoUsuario.setDescripcion(resultSet.getString(3));
					listaGrupoUsuario.add(grupoUsuario);
				}
			}catch (SQLException e) {
					System.out.println("Error: En método leer");
					e.printStackTrace();
				}
			
		}
		return listaGrupoUsuario;
	
		}
	//Metodo para hacer un update en la tabla grupousuario
	@Override
	public boolean modificar(Connection connection, Object grupousuario) {
		String query="UPDATE gruposusuario SET nombreGrupo= ?, descripcion= ? WHERE sysPK= ?;";
		try {
			GrupoUsuario grupoUsuario=(GrupoUsuario)grupousuario;
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setString(1, grupoUsuario.getNombre());
			preparedStatement.setString(2, grupoUsuario.getDescripcion());	
			preparedStatement.setInt(3, grupoUsuario.getSysPk());
			preparedStatement.execute();
			return true;
			} catch (SQLException e) {
				System.out.println("Error: En método modificar");
				e.printStackTrace();
				return false;
				}
		}
	
	//Metodo para hacer un delete en la tabla grupousuario
	@Override
	public boolean eliminar(Connection connection, Object grupousuario) {
		String query=" DELETE FROM gruposusuario WHERE sysPK= ?";
		try {			
			GrupoUsuario grupoUsuario=(GrupoUsuario)grupousuario;
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setInt(1, grupoUsuario.getSysPk());
			preparedStatement.execute();
			return true;
			} catch (SQLException e) {
				System.out.println("Error: En método eliminar");
				e.printStackTrace();
				return false;
				}	
		}
}