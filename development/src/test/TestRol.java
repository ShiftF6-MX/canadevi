package test;

import model.Rol;
import model.RolDAO;
import utilities.ConnectionDB;

public class TestRol{
	static Rol rol = new Rol();
	static RolDAO rolDAO = new RolDAO();
	static ConnectionDB connectionDB =  new ConnectionDB("canadevi", "192.168.0.216", "conn01" , "Simons83Mx");
	
    public static void testCrear() throws Exception {    	
    	rol.setCodigoItem("3");
    	rol.setDescripcion("Prueba de insert en la tabla rol");  
    	rolDAO.crear(connectionDB.conectarMySQL(), rol);
    }
    public static void testLeer() throws Exception {    	 
    	rolDAO.leer(connectionDB.conectarMySQL(), "", "");
    	    	
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
    	testLeer();
    	//testModificar();
    	//testEliminar();
    }
	
}
