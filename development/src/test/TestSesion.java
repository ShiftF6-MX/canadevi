package test;

import java.util.ArrayList;

import model.Rol;
import model.RolDAO;
import model.Sesion;
import model.SesionDAO;
import model.Usuario;
import utilities.ConnectionDB;

public class TestSesion {

	static Sesion sesion = new Sesion();	
	static SesionDAO sesionDAO = new SesionDAO();
	static ConnectionDB connectionDB =  new ConnectionDB("canadevi", "192.168.0.216", "conn01" , "Simons83Mx");
	
    public static void testCrear() throws Exception {    	
    	Usuario usaurio = new Usuario();  
    	rolDAO.crear(connectionDB.conectarMySQL(), rol);
    }
    public static void testLeer() throws Exception {    	 
    	ArrayList <Object> resultadoSelect = rolDAO.leer(connectionDB.conectarMySQL(), "sysPK", "10");      
    	for(Object rol: resultadoSelect) {
    		System.out.println(((Rol) rol).showInformacionRol());
    	}
    	    	
    }
    public static void testModificar() throws Exception {  
    	rol.setSysPk(8);
    	rol.setCodigoItem("1");
    	rol.setDescripcion("Cambio de descripcion");
    	rolDAO.modificar(connectionDB.conectarMySQL(), rol);
    }
    public static void testEliminar() throws Exception {    	 
    	rol.setSysPk(8);
    	rolDAO.eliminar(connectionDB.conectarMySQL(), rol);
    	
    }
    public static void main(String[] args) throws Exception{
    	//testCrear();
    	//testLeer();
    	//testModificar();
    	//testEliminar();
    }

}
