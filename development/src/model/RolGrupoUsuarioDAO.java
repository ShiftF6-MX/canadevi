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
		
		String query=" INSERT INTO rolgruposusuario (grupoUsuario, rol) values ( ?, ?)";
		try {			
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setInt(1, rolGrupoUsuario.getGrupoUsuario().getSysPk());
			preparedStatement.setInt(2, rolGrupoUsuario.getRol().getSysPk());
			preparedStatement.execute();
			return true;
			} catch (SQLException e) {
				System.out.println("Error: En m�todo crear");
				e.printStackTrace();
				return false;
			}
		}

	//Metodo para hacer un select a la tabla rolgrupousuario
	@Override
	public ArrayList<Object> leer(Connection connection, String campoBusqueda, String valorBusqueda) {
			ArrayList<Object> listaRolGrupoUsuario = new ArrayList<Object>();
			GrupoUsuario grupoUsuario= new GrupoUsuario();	
			RolGrupoUsuario rolGrupoUsuario = new RolGrupoUsuario();
			Rol rol = new Rol();
			if(campoBusqueda.isEmpty() || valorBusqueda.isEmpty()) {
				query="SELECT * FROM rolgruposusuario ORDER BY sysPK";
				try {
					ResultSet resultSet = connection.createStatement().executeQuery(query);							
					System.out.println(query);
					while (resultSet.next()) {
						
						rolGrupoUsuario.setSysPk(Integer.parseInt(resultSet.getString(1)));
						rolGrupoUsuario.setGrupoUsuario(grupoUsuario);
						rolGrupoUsuario.setRol(rol);
						listaRolGrupoUsuario.add(rolGrupoUsuario);
						System.out.println(resultSet.next());
					}
				}catch (SQLException e) {
						System.out.println("Error: En m�todo leer");
						e.printStackTrace();
					}
			}else {
				query="SELECT * FROM gruposusuario WHERE ? = ?  ORDER BY sysPK";
							
				try {
					System.out.println(query);
					PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);
					preparedStatement.setString(1, campoBusqueda);
					preparedStatement.setString(2, valorBusqueda);
					ResultSet resultSet=preparedStatement.executeQuery();
					while (resultSet.next()) {						
						rolGrupoUsuario.setSysPk(Integer.parseInt(resultSet.getString(1)));
						rolGrupoUsuario.setGrupoUsuario(grupoUsuario);
						rolGrupoUsuario.setRol(rol);
						listaRolGrupoUsuario.add(rolGrupoUsuario);
						System.out.println(resultSet.next());
					}
				}catch (SQLException e) {
						System.out.println("Error: En m�todo leer");
						e.printStackTrace();
					}
				
			}
			return listaRolGrupoUsuario;
		
		
		}

	//Metodo para hacer un update en la tabla rolgrupousuario
	@Override
	public boolean modificar(Connection connection, Object rolgrupoUsuario){
		String query="UPDATE grupousuario SET grupoUsuario= ?, rol= ? WHERE sysPK= ?;";
		try {
			RolGrupoUsuario rolGrupoUsuario=(RolGrupoUsuario)rolgrupoUsuario;
			PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(query);			
			preparedStatement.setInt(1, rolGrupoUsuario.getGrupoUsuario().getSysPk());
			preparedStatement.setInt(2, rolGrupoUsuario.getRol().getSysPk());
			preparedStatement.setInt(3, rolGrupoUsuario.getSysPk());
			preparedStatement.execute();
			return true;
			} catch (SQLException e) {
				System.out.println("Error: En m�todo modificar");
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
				preparedStmt.setInt(1, rolGrupoUsuario.getSysPk());
				preparedStmt.execute();
				return true;
				} catch (SQLException e) {
					System.out.println("Error: En m�todo eliminar");
					e.printStackTrace();
					return false;
				}	
			}
		}
