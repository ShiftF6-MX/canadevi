package test;

import model.Rol;
import model.RolDAO;
import utilities.ConnectionDB;

public class TestRol{
	static Rol rol = new Rol();
	static RolDAO rolDAO = new RolDAO();
	static ConnectionDB connectionDB =  new ConnectionDB("canadevi", "localhost", "root" , "qwerty");
	
    public static void testCrear() throws Exception {    	
    	rol.setCodigoItem("2");
    	rol.setDescripcion("Prueba de insert en la tabla rol");  
    	rolDAO.crear(connectionDB, rol);
    }
    /*public static void testLeer() throws Exception {    	 
    	rolDAO.leer(connectionDB, "codigoItem", "2");
    }
    public static void testModificar() throws Exception {    	 
    	rol.setCodigoItem("2");
    	rol.setDescripcion("Cambio de descripcion");
    	rolDAO.modificar(connectionDB, rol);
    }
    public static void testEliminar() throws Exception {    	 
    	rol.setSysPk(2);
    	rolDAO.eliminar(connectionDB, rol);
    	
    }
    */
    public static void main(String[] args) throws Exception{
    	testCrear();
    }
	
}
