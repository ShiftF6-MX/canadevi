package test;

import java.util.ArrayList;

import model.GrupoUsuario;
import model.Rol;
import model.RolGrupoUsuario;
import model.RolGrupoUsuarioDAO;
import utilities.ConnectionDB;

public class TestRolGrupoUsuario{
	static RolGrupoUsuario rolGrupoUsuario = new RolGrupoUsuario();	
	static Rol rol = new Rol();
	static GrupoUsuario grupoUsuario = new GrupoUsuario();
	static RolGrupoUsuarioDAO rolGrupoUsuarioDAO = new RolGrupoUsuarioDAO();
	
	static ConnectionDB connectionDB =  new ConnectionDB("canadevi", "192.168.0.216", "conn01" , "Simons83Mx");
	
    public static void testCrear() throws Exception {    
    	grupoUsuario = new GrupoUsuario(1,"Admin","Grupo de Grupo administradores");
    	rol=new Rol (10,"3","Rol de administradores");
		rolGrupoUsuario.setGrupoUsuario(grupoUsuario);
    	rolGrupoUsuario.setRol(rol) ;
    	rolGrupoUsuarioDAO.crear(connectionDB.conectarMySQL(), rolGrupoUsuario);
    }
    public static void testLeer() throws Exception {    	 
    	//ArrayList <Object> resultadoSelect = rolGrupoUsuarioDAO.leer(connectionDB.conectarMySQL(), "sysPK", "4");   
    	ArrayList <Object> resultadoSelect = rolGrupoUsuarioDAO.leer(connectionDB.conectarMySQL(), "", "");
    	for(Object rolGrupoUsuario: resultadoSelect) {
    		System.out.println(((RolGrupoUsuario) rolGrupoUsuario).showInformacionRolGrupoUsuario());
		}
    	    	
    }
    public static void testModificar() throws Exception {  
    	//Pendiente
    	rolGrupoUsuario.setSysPk(4);
    	grupoUsuario = new GrupoUsuario(1,"Admin","Grupo administradores");
    	rol=new Rol (10,"3","Prueba de insert en la tabla rol");
		rolGrupoUsuario.setGrupoUsuario(grupoUsuario);
    	rolGrupoUsuario.setRol(rol);
    	rolGrupoUsuarioDAO.modificar(connectionDB.conectarMySQL(), rolGrupoUsuario);
    }
    public static void testEliminar() throws Exception {    	 
    	rolGrupoUsuario.setSysPk(3);
    	rolGrupoUsuarioDAO.eliminar(connectionDB.conectarMySQL(), rolGrupoUsuario);
    	
    }
    public static void main(String[] args) throws Exception{
    	//testCrear();
    	testLeer();
    	//testModificar();
    	//testEliminar();
    }
	
}
