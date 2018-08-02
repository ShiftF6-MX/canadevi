package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class GrupoUsuarioDAO implements ObjectDAO {
	public String query ="";
	//Metodo crear para crear un Grupo Usuario
	@Override
	public boolean crear(Connection connection, Object grupousuario) {
		GrupoUsuario grupoUsuario=(GrupoUsuario)grupousuario;
		String query=" INSERT INTO gruposusuario (nombre, descripcion)"
		+ " values ( ?, ?)";
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
		ArrayList<Object> listaGrupoUsuario = new ArrayList<Object>();
		if(campoBusqueda.isEmpty() || valorBusqueda.isEmpty()) {
			query="SELECT * FROM gruposusuario ORDER BY sysPK";
			try {
				ResultSet resultSet = connection.createStatement().executeQuery(query);							
				System.out.println(query);
				while (resultSet.next()) {
					GrupoUsuario grupoUsuario = new GrupoUsuario();
					grupoUsuario.setSysPk(Integer.parseInt(resultSet.getString(1)));
					grupoUsuario.setNombre(resultSet.getString(2));
					grupoUsuario.setDescripcion(resultSet.getString(3));
					listaGrupoUsuario.add(grupoUsuario);
					System.out.println(resultSet.next());
				}
			}catch (SQLException e) {
					System.out.println("Error: En método leer");
					e.printStackTrace();
				}
		}else {
			query="SELECT * FROM gruposusuario WHERE ? = ?  ORDER BY sysPK";
						
			try {
				System.out.println(query);
				PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
				preparedStatement.setString(1, campoBusqueda);
				preparedStatement.setString(2, valorBusqueda);
				ResultSet rs=preparedStatement.executeQuery();
				while (rs.next()) {
					GrupoUsuario grupoUsuario = new GrupoUsuario();
					grupoUsuario.setSysPk(Integer.parseInt(rs.getString(1)));
					grupoUsuario.setNombre(rs.getString(2));
					grupoUsuario.setDescripcion(rs.getString(3));
					listaGrupoUsuario.add(grupoUsuario);
					System.out.println(rs.next());
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
