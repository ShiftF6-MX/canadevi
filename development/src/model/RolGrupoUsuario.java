package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class RolGrupoUsuario {

	//PROPIEDADES
	private ObjectProperty<Integer> sysPk;
	private ObjectProperty<GrupoUsuario> grupoUsuario;
	private ObjectProperty<Rol> rol;
	
	//CONSTRUCTOR SIN PARAMETROS
	public RolGrupoUsuario() {
		this(0,null,null);
	}//FIN CONSTRUCTOR
	
	//CONSTRUCTOR CON PARAMETROS
	public RolGrupoUsuario(Integer sysPk, GrupoUsuario grupoUsuario, Rol rol) {
		this.sysPk = new SimpleObjectProperty<Integer>(sysPk);
		this.grupoUsuario = new SimpleObjectProperty<GrupoUsuario>(grupoUsuario);
		this.rol = new SimpleObjectProperty<Rol>(rol);
	}//FIN CONSTRUCTOR
	
	//METODOS PARA ACCESO A "SYSPK"
	public void setSysPk(Integer sysPk) {
		this.sysPk.set(sysPk);
	}//FIN METODO
	
	public Integer getSysPk() {
		return this.sysPk.get();
	}//FIN METODO
	
	public ObjectProperty<Integer> sysPkProperty() {
		return this.sysPk;
	}//FIN METODOD
	//FIN METODOS "SYSPK"
	
	//METODOS PARA ACCESO A "GRUPO USUARIO"
	public void setGrupoUsuario(GrupoUsuario grupoUsuario) {
		this.grupoUsuario.set(grupoUsuario);
	}//FIN METODO
	
	public GrupoUsuario getGrupoUsuario() {
		return this.grupoUsuario.get();
	}//FIN METODO
	
	public ObjectProperty<GrupoUsuario> grupoUsuarioProperty() {
		return this.grupoUsuario;
	}//FIN METODO
	//FIN METODOS "GRUPO USUARIO"
	
	//METODOS PARA ACCESO A "ROL"
	public void setRol(Rol rol) {
		this.rol.set(rol);
	}//FIN METODO
	
	public Rol getRol() {
		return this.rol.get();
	}//FIN METODO
	
	public ObjectProperty<Rol> rolProperty() {
		return this.rol;
	}//FIN METODO
	//FIN METODOS
	
	public String showInformacionRolGrupoUsuario() {
		String informacionRolGrupoUsuario = "SysPk: " + this.getSysPk() + "\n"
				+ "Nombre Grupo Usuario: " + this.getGrupoUsuario().getNombre() + "\n"
						+ "Código Item Rol: " + this.getRol().getCodigoItem();
		return informacionRolGrupoUsuario;
	}//FIN CLASE
	
}//FIN CLASE
