package mx.shf6.canadevi.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
import mx.shf6.canadevi.utilities.ConnectionDB;
import mx.shf6.canadevi.model.PersonaDAO;
import mx.shf6.canadevi.model.Persona;
 
public class PersonaDAOmpl implements PersonaDAO {	
	
	@Override
	public boolean registrar(Persona persona) {
		boolean registrar = false;
		
		Statement stm= null;
		Connection con=null;
		
		String sql="INSERT INTO cliente values (NULL,'"+persona.getCedula()+"','"+persona.getNombre()+"','"+persona.getApellido()+"')";
		
		try {			
			con=ConnectionDB.conectarMySQL();
			stm= con.createStatement();
			stm.execute(sql);
			registrar=true;
			stm.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Error: En método registrar");
			e.printStackTrace();
		}
		return registrar;
	}
 
	@Override
	public List<Persona> obtener() {
		Connection co =null;
		Statement stm= null;
		ResultSet rs=null;
		
		String sql="SELECT * FROM PERSONA ORDER BY ID";
		
		List<Persona> listaCliente= new ArrayList<Persona>();
		
		try {			
			co= ConnectionDB.conectarMySQL();
			stm=co.createStatement();
			rs=stm.executeQuery(sql);
			while (rs.next()) {
				Persona c=new Persona();
				c.setId(rs.getInt(1));
				c.setCedula(rs.getString(2));
				c.setNombre(rs.getString(3));
				c.setApellido(rs.getString(4));
				listaCliente.add(c);
			}
			stm.close();
			rs.close();
			co.close();
		} catch (SQLException e) {
			System.out.println("Error: en método obtener");
			e.printStackTrace();
		}
		
		return listaCliente;
	}
 
	@Override
	public boolean actualizar(Persona persona) {
		Connection connect= null;
		Statement stm= null;
		
		boolean actualizar=false;
				
		String sql="UPDATE PERSONA SET cedula='"+persona.getCedula()+"', nombre='"+persona.getNombre()+"', apellidos='"+persona.getApellido()+"'" +" WHERE ID="+persona.getId();
		try {
			connect=ConnectionDB.conectarMySQL();
			stm=connect.createStatement();
			stm.execute(sql);
			actualizar=true;
		} catch (SQLException e) {
			System.out.println("Error: en método actualizar");
			e.printStackTrace();
		}		
		return actualizar;
	}
 
	@Override
	public boolean eliminar(Persona persona) {
		Connection connect= null;
		Statement stm= null;
		
		boolean eliminar=false;
				
		String sql="DELETE FROM PERSONA WHERE ID="+persona.getId();
		try {
			connect=ConnectionDB.conectarMySQL();
			stm=connect.createStatement();
			stm.execute(sql);
			eliminar=true;
		} catch (SQLException e) {
			System.out.println("Error: en método eliminar");
			e.printStackTrace();
		}		
		return eliminar;
	}
 
}