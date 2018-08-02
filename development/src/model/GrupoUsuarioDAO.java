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
	public boolean crear(Connection connection, Object grupousuario) throws Exception {
		GrupoUsuario nuevogrupousuario=(GrupoUsuario)grupousuario;
		String query=" INSERT INTO grupousuario (nombre, descripcion)"
		+ " values ( ?, ?)";
		try {			
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setString(1, nuevogrupousuario.getNombre());
			preparedStmt.setString(2, nuevogrupousuario.getDescripcion());
			preparedStmt.execute();
			return true;
			} catch (SQLException e) {
				System.out.println("Error: En método crear");
				e.printStackTrace();
				return false;
				}
		}
	//Metodo para hacer un select a la tabla grupousuario
	@Override
	public ArrayList<Object> leer(Connection connection, String campoBusqueda, String valorBusqueda) throws Exception {
		
		if(campoBusqueda.isEmpty() || valorBusqueda.isEmpty()) {
			query="SELECT * FROM grupousuario WHERE ORDER BY sysPK";
		}else {
			query="SELECT * FROM grupousuario WHERE ? = ?  ORDER BY sysPK";
		}
		
		ArrayList<Object> listaGrupoUsuario = new ArrayList<Object>();
		try {
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setString(1, campoBusqueda);
			preparedStmt.setString(2, valorBusqueda);
			ResultSet rs=preparedStmt.executeQuery();
			while (rs.next()) {
				GrupoUsuario nuevogrupousuario= new GrupoUsuario();
				nuevogrupousuario.setSysPk(Integer.parseInt(rs.getString(1)));
				nuevogrupousuario.setNombre(rs.getString(2));
				nuevogrupousuario.setDescripcion(rs.getString(3));
				listaGrupoUsuario.add(nuevogrupousuario);
				}
			}catch (SQLException e) {
				System.out.println("Error: En método leer");
				e.printStackTrace();
				}
		return listaGrupoUsuario;
		}
	//Metodo para hacer un update en la tabla grupousuario
	@Override
	public boolean modificar(Connection connection, Object grupousuario) throws Exception {
		String query="UPDATE grupousuario SET nombreGrupo= ?, descripcion= ? WHERE sysPK= ?;";
		try {
			GrupoUsuario nuevogrupousuario=(GrupoUsuario)grupousuario;
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setString(1, nuevogrupousuario.getNombre());
			preparedStmt.setString(2, nuevogrupousuario.getDescripcion());	
			preparedStmt.setInt(3, nuevogrupousuario.getSysPk());
			preparedStmt.execute();
			return true;
			} catch (SQLException e) {
				System.out.println("Error: En método modificar");
				e.printStackTrace();
				return false;
				}
		}
	//Metodo para hacer un delete en la tabla grupousuario
	@Override
	public boolean eliminar(Connection connection, Object grupousuario) throws Exception {
		String query=" DELETE FROM grupousuario WHERE sysPK= ?";
		try {			
			GrupoUsuario nuevogrupousuario=(GrupoUsuario)grupousuario;
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setInt(1, nuevogrupousuario.getSysPk());
			preparedStmt.execute();
			return true;
			} catch (SQLException e) {
				System.out.println("Error: En método eliminar");
				e.printStackTrace();
				return false;
				}	
		}
}
