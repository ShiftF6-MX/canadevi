package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RolGrupoUsuarioDAO implements ObjectDAO{
	//Metodo crear para crear la relacion  Rol y Grupo usuarion en la base de datos 
	@Override
	public boolean crear(Connection connection, Object rolgrupousuario){
		RolGrupoUsuario rolGrupoUsuario=(RolGrupoUsuario)rolgrupousuario;		
		String query=" INSERT INTO rolgruposusuario (grupoUsuario, rol) values ( ?, ?)";
		try {			
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setInt(1, rolGrupoUsuario.getGrupoUsuario().getSysPk());
			preparedStatement.setInt(2, rolGrupoUsuario.getRol().getSysPk());
			preparedStatement.execute();
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
		String query ="";
		ArrayList<Object> listaRolGrupoUsuario = new ArrayList<Object>();
		if(campoBusqueda.isEmpty() || valorBusqueda.isEmpty()) {
			query="SELECT * FROM rolgruposusuario ORDER BY sysPK";
			try {
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				RolGrupoUsuario rolGrupoUsuario = null;
				Rol rol = null;
				GrupoUsuario grupoUsuario = null;
				while (resultSet.next()) {	
					rolGrupoUsuario = new RolGrupoUsuario();	
					rolGrupoUsuario.setSysPk(resultSet.getInt(1));
					GrupoUsuarioDAO grupoUsuarioDAO = new  GrupoUsuarioDAO();
					ArrayList <Object> resultadoGrupoUsuario = grupoUsuarioDAO.leer(connection, "sysPK", resultSet.getString(2));					
					rolGrupoUsuario.setGrupoUsuario(grupoUsuario);
					RolDAO rolDAO = new RolDAO();
					ArrayList <Object> resultadoRol = rolDAO.leer(connection, "sysPK", resultSet.getString(2));
					rolGrupoUsuario.setRol(rol);
					listaRolGrupoUsuario.add(rolGrupoUsuario);
					}
				}catch (SQLException e) {
						System.out.println("Error: En método leer");
						e.printStackTrace();
					}
			}else {
				query="SELECT * FROM rolgruposusuario WHERE "+campoBusqueda+" = ?  ORDER BY sysPK";
							
				try {
					PreparedStatement preparedStatement = connection.prepareStatement(query);
					preparedStatement.setString(1, valorBusqueda);
					ResultSet resultSet=preparedStatement.executeQuery();
					RolGrupoUsuario rolGrupoUsuario =null;
					Rol rol = new Rol();
					GrupoUsuario grupoUsuario = new GrupoUsuario();
					while (resultSet.next()) {		
						rolGrupoUsuario = new RolGrupoUsuario();
						rolGrupoUsuario.setSysPk(resultSet.getInt(1));
						rolGrupoUsuario.setGrupoUsuario(grupoUsuario);
						rolGrupoUsuario.setRol(rol);
						listaRolGrupoUsuario.add(rolGrupoUsuario);
					}
				}catch (SQLException e) {
						System.out.println("Error: En método leer");
						e.printStackTrace();
					}
				
			}
			return listaRolGrupoUsuario;
		
		
		}

	//Metodo para hacer un update en la tabla rolgrupousuario
	@Override
	public boolean modificar(Connection connection, Object rolgrupoUsuario){
		String query="UPDATE rolgrupousuario SET grupoUsuario= ?, rol= ? WHERE sysPK= ?;";
		try {
			RolGrupoUsuario rolGrupoUsuario=(RolGrupoUsuario)rolgrupoUsuario;
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);			
			preparedStatement.setInt(1, rolGrupoUsuario.getGrupoUsuario().getSysPk());
			preparedStatement.setInt(2, rolGrupoUsuario.getRol().getSysPk());
			preparedStatement.setInt(3, rolGrupoUsuario.getSysPk());
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
		public boolean eliminar(Connection connection, Object rolgrupousuario) {
			String query=" DELETE FROM rolgruposusuario WHERE sysPK= ?";
			try {			
				RolGrupoUsuario rolGrupoUsuario=(RolGrupoUsuario)rolgrupousuario;
				PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
				preparedStmt.setInt(1, rolGrupoUsuario.getSysPk());
				preparedStmt.execute();
				return true;
				} catch (SQLException e) {
					System.out.println("Error: En método eliminar");
					e.printStackTrace();
					return false;
				}	
			}
		}
