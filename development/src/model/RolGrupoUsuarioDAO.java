package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RolGrupoUsuarioDAO implements ObjectDAO{
	public String query ="";
	//Metodo crear para crear la relacion  Rol y Grupo usuarion en la base de datos 
	@Override
	public boolean crear(Connection connection, Object rolgrupousuario){
		RolGrupoUsuario rolGrupoUsuario=(RolGrupoUsuario)rolgrupousuario;
		String query=" INSERT INTO rolgrupousuario (grupoUsuario, rol) values ( ?, ?)";
		try {			
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setInt(1, rolGrupoUsuario.getGrupoUsuario().getSysPk());
			preparedStmt.setInt(2, rolGrupoUsuario.getRol().getSysPk());
			preparedStmt.execute();
			return true;
			} catch (SQLException e) {
				System.out.println("Error: En método crear");
				e.printStackTrace();
				return false;
			}
		}

	//Metodo para hacer un select a la tabla rolgrupousuario
	@Override
	public ArrayList<Object> leer(Connection connection, String campoBusqueda, String valorBusqueda) {
			ArrayList<Object> listaRolGrupoUsuario = new ArrayList<Object>();
			if(campoBusqueda.isEmpty() || valorBusqueda.isEmpty()) {
				query="SELECT * FROM gruposusuario ORDER BY sysPK";
				try {
					ResultSet resultSet = connection.createStatement().executeQuery(query);							
					System.out.println(query);
					while (resultSet.next()) {
						Rol rol = new Rol();
						rol.setSysPk(Integer.parseInt(resultSet.getString(1)));
						rol.setCodigoItem(resultSet.getString(2));
						rol.setDescripcion(resultSet.getString(3));
						listaRol.add(rol);
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

	//Metodo para hacer un update en la tabla rolgrupousuario
	@Override
	public boolean modificar(Connection connection, Object rolgrupoUsuario){
		String query="UPDATE grupousuario SET grupoUsuario= ?, rol= ? WHERE sysPK= ?;";
		try {
			RolGrupoUsuario rolGrupoUsuario=(RolGrupoUsuario)rolgrupoUsuario;
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);			
			preparedStmt.setInt(1, rolGrupoUsuario.getGrupoUsuario().getSysPk());
			preparedStmt.setInt(2, rolGrupoUsuario.getRol().getSysPk());
			preparedStmt.setInt(3, rolGrupoUsuario.getSysPk());
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
		public boolean eliminar(Connection connection, Object rolgrupousuario) {
			String query=" DELETE FROM rol WHERE sysPK= ?";
			try {			
				RolGrupoUsuario rolGrupoUsuario=(RolGrupoUsuario)rolgrupousuario;
				PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
				preparedStmt.setInt(1, nuevorolgrupousuario.getSysPk());
				preparedStmt.execute();
				return true;
				} catch (SQLException e) {
					System.out.println("Error: En método eliminar");
					e.printStackTrace();
					return false;
				}	
			}
		}
