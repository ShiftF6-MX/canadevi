package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class RolGrupoUsuarioDAO implements ObjectDAO{
	public String query ="";
	//Metodo crear para crear la relacion  Rol y Grupo usuarion en la base de datos 
	@Override
	public boolean crear(Connection connection, Object rolgrupousuario) throws Exception {
		RolGrupoUsuario nuevorolgrupousuario=(RolGrupoUsuario)rolgrupousuario;
		String query=" INSERT INTO rolgrupousuario (grupoUsuario, rol) values ( ?, ?)";
		try {			
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setInt(1, nuevorolgrupousuario.getGrupoUsuario().getSysPk());
			preparedStmt.setInt(2, nuevorolgrupousuario.getRol().getSysPk());
			preparedStmt.execute();
			return true;
			} catch (SQLException e) {
				System.out.println("Error: En m�todo crear");
				e.printStackTrace();
				return false;
			}
		}

	//Metodo para hacer un select a la tabla rolgrupousuario
	@Override
	public ArrayList<Object> leer(Connection connection, String campoBusqueda, String valorBusqueda) throws Exception {
		
		if(campoBusqueda.isEmpty() || valorBusqueda.isEmpty()) {
			query="SELECT * FROM rolgruposusuario WHERE ORDER BY sysPK";
		}else {
			query="SELECT * FROM rolgruposusuario WHERE ? = ?  ORDER BY sysPK";
		}
		ArrayList<Object> listaRolGrupoUsuario = new ArrayList<Object>();
		try {
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setString(1, campoBusqueda);
			preparedStmt.setString(2, valorBusqueda);
			ResultSet rs=preparedStmt.executeQuery();
			while (rs.next()) {
				RolGrupoUsuario nuevorolgrupousuario = new RolGrupoUsuario();
				nuevorolgrupousuario.setSysPk(Integer.parseInt(rs.getString(1)));
				GrupoUsuario nuevogrupousuario = new GrupoUsuario();
				nuevogrupousuario.setSysPk(rs.getInt(2));
				Rol nuevorol = new Rol();
				nuevorol.setSysPk(rs.getInt(3));
				listaRolGrupoUsuario.add(nuevorolgrupousuario);
				}
			}catch (SQLException e) {
				System.out.println("Error: En m�todo leer");
				e.printStackTrace();
				}
		return listaRolGrupoUsuario;
		}

	//Metodo para hacer un update en la tabla rolgrupousuario
	@Override
	public boolean modificar(Connection connection, Object rolgrupoUsuario) throws Exception {
		String query="UPDATE grupousuario SET grupoUsuario= ?, rol= ? WHERE sysPK= ?;";
		try {
			RolGrupoUsuario nuevorolgrupousuario=(RolGrupoUsuario)rolgrupoUsuario;
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);			
			preparedStmt.setInt(1, nuevorolgrupousuario.getGrupoUsuario().getSysPk());
			preparedStmt.setInt(2, nuevorolgrupousuario.getRol().getSysPk());
			preparedStmt.setInt(3, nuevorolgrupousuario.getSysPk());
			preparedStmt.execute();
			return true;
			} catch (SQLException e) {
				System.out.println("Error: En m�todo modificar");
				e.printStackTrace();
				return false;
				}
		}

	//Metodo para hacer un delete en la tabla rol
		@Override
		public boolean eliminar(Connection connection, Object rolgrupousuario) throws Exception {
			String query=" DELETE FROM rol WHERE sysPK= ?";
			try {			
				RolGrupoUsuario nuevorolgrupousuario=(RolGrupoUsuario)rolgrupousuario;
				PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
				preparedStmt.setInt(1, nuevorolgrupousuario.getSysPk());
				preparedStmt.execute();
				return true;
				} catch (SQLException e) {
					System.out.println("Error: En m�todo eliminar");
					e.printStackTrace();
					return false;
				}	
			}
		}
