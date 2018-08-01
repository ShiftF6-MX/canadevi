package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GrupoUsuario {

	//PROPIEDADES
	private ObjectProperty<Integer> sysPk;
	private StringProperty nombre;
	private StringProperty descripcion;
	
	//CONSTRUCTOR SIN PARAMETROS
	public GrupoUsuario() {
		this(0,"","");
	}//FIN CONSTRUCTOR
	
	//CONSTRUCTOR CON PARAMETROS
	public GrupoUsuario(Integer sysPk, String nombre, String descripcion) {
		this.sysPk = new SimpleObjectProperty<Integer>(sysPk);
		this.nombre = new SimpleStringProperty(nombre);
		this.descripcion = new SimpleStringProperty(descripcion);
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
	
	//METODOS PARA ACCESO A "NOMBRE"
	public void setNombre(String nombre) {
		this.nombre.set(nombre);
	}//FIN METODO
	
	public String getNombre() {
		return this.nombre.get();
	}//FIN METODO
	
	public StringProperty nombreProperty() {
		return this.nombre;
	}//FIN METODO
	//FIN METODOS "NOMBRE"
	
	//METODOS PARA ACCESO A "DESCRIPCI�N"
	public void setDescripcion(String descripcion) {
		this.descripcion.set(descripcion);
	}//FIN METODO
	
	public String getDescripcion() {
		return this.descripcion.get();
	}//FIN METODO
	
	public StringProperty descripcionProperty() {
		return this.descripcion;
	}//FIN METODO
	//FIN METODOS "DESCRIPCI�N"
	
	public String showInformacionGrupoUsuario() {
		String informacionGrupoUsuario = "SysPk: " + this.getSysPk() + "\n"
				+ "Nombre: " + this.getNombre() + "\n"
						+ "Descripci�n: " + this.getDescripcion();
		return informacionGrupoUsuario;
	}//FIN METODO
	
}//FIN CLASE
