package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class UsuarioDAO implements ObjectDAO {
	public String query ="";
	//Metodo crear para crear un usuario nuevo
	@Override
	public boolean crear(Connection connection, Object usuario){		
		Usuario nuevousuario=(Usuario)usuario;
		String query=" INSERT INTO usuarios (usuario, contrasena, correoElectronico, fechaRegistro, fechaBloqueo, status)"
		        + " values ( ?, ?, ?, ?, ?, ?, ?)";
		try {			
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setString(1, nuevousuario.getUsuario());
			preparedStmt.setString(2, nuevousuario.getContrasena());
			preparedStmt.setString(3, nuevousuario.getCorreoElectronico());
			preparedStmt.setDate(4, nuevousuario.getFechaRegistro());
			preparedStmt.setDate(5, nuevousuario.getFechaBloqueo());
			preparedStmt.setInt(6, nuevousuario.getStatus());
			preparedStmt.setInt(1, nuevousuario.getGrupoUsuario().getSysPk());
			preparedStmt.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("Error: En método crear");
			e.printStackTrace();
			return false;
		}
	}
	//Metodo para hacer un select a la tabla usuarios
	@Override
	public ArrayList<Object> leer(Connection connection, String campoBusqueda, String valorBusqueda) {
		
		if(campoBusqueda.isEmpty() || valorBusqueda.isEmpty()) {
			query="SELECT * FROM usuarios WHERE ORDER BY sysPK";
		}else {
			query="SELECT * FROM usuarios WHERE ? = ?  ORDER BY sysPK";
		}
		
		
		ArrayList<Object> listaUsuario = new ArrayList<Object>();
		
		try {
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setString(1, campoBusqueda);
			preparedStmt.setString(2, valorBusqueda);
			ResultSet rs=preparedStmt.executeQuery();
			
			while (rs.next()) {
				Usuario nuevousuario = new Usuario();
				DateFormat format = new SimpleDateFormat("DD/MM/AAAA");				
				nuevousuario.setSysPk(Integer.parseInt(rs.getString(1)));
				nuevousuario.setContrasena(rs.getString(2));
				nuevousuario.setCorreoElectronico(rs.getString(3));
				Date sFechaRegistro = (Date) format.parse(rs.getString(4));
				nuevousuario.setFechaRegistro(sFechaRegistro);
				Date sFechaBloqueo = (Date) format.parse(rs.getString(5));
				nuevousuario.setFechaBloqueo(sFechaBloqueo);
				nuevousuario.setStatus(Integer.parseInt(rs.getString(6)));
				listaUsuario.add(nuevousuario);
			}
		}catch (SQLException | ParseException e) {
				System.out.println("Error: En método leer");
				e.printStackTrace();
			}
		return listaUsuario;

	}
	//Metodo para hacer un update en la tabla usuarios
	@Override
	public boolean modificar(Connection connection, Object usuario){
		String query="UPDATE usuarios\r\n" + 
				"    SET usuario= ?, contrasena= ?, correoElectronico= ?, fechaRegistro= ?, fechaBloqueo= ?, status= ?, grupoUsuario= ? \r\n" + 
				"    WHERE sysPK= ?;";
		try {
			Usuario nuevousuario=(Usuario)usuario;			
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setString(1, nuevousuario.getUsuario());
			preparedStmt.setString(2, nuevousuario.getContrasena());
			preparedStmt.setString(3, nuevousuario.getCorreoElectronico());
			preparedStmt.setDate(4, nuevousuario.getFechaRegistro());
			preparedStmt.setDate(5, nuevousuario.getFechaBloqueo());
			preparedStmt.setInt(6, nuevousuario.getStatus());
			preparedStmt.setInt(7, nuevousuario.getGrupoUsuario().getSysPk());
			preparedStmt.setInt(8, nuevousuario.getSysPk());
			preparedStmt.setInt(8, nuevousuario.getGrupoUsuario().getSysPk());
			preparedStmt.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("Error: En método modificar");
			e.printStackTrace();
			return false;
		}
	}
	//Metodo para hacer un delete en la tabla usuarios
	@Override
	public boolean eliminar(Connection connection, Object usuario) {
		String query=" DELETE FROM usuarios WHERE sysPK= ?";
		try {			
			Usuario nuevousuario=(Usuario)usuario;
			PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(query);
			preparedStmt.setInt(1, nuevousuario.getSysPk());
			preparedStmt.execute();
			return true;
		} catch (SQLException e) {
			System.out.println("Error: En método eliminar");
			e.printStackTrace();
			return false;
		}
				
	}

}

