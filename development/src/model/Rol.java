package model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Rol {
	
	//PROPIEDADES
	private ObjectProperty<Integer> sysPk;
	private StringProperty codigoItem;
	private StringProperty descripcion;
	
	//CONSTRUCTOR SIN PARAMETROS
	public Rol() {
		this(0,"","");
	}//FIN CONSTRUCTOR
	
	//CONSTRUCTOR CON PARAMETROS
	public Rol(Integer sysPk, String codigoItem, String descripcion) {
		this.sysPk =  new SimpleObjectProperty<Integer>(sysPk);
		this.codigoItem = new SimpleStringProperty(codigoItem);
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
	}//FIN METODO
	//FIN METODOS "SYSPK"
	
	//METODOS PARA ACCESO A "CODIGO ITEM"
	public void setCodigoItem(String codigoItem) {
		this.codigoItem.set(codigoItem);
	}//FIN METODO
	
	public String getCodigoItem() {
		return this.codigoItem.get();
	}//FIN METODO
	
	public StringProperty codigoItemProperty() {
		return this.codigoItem;
	}//FIN METODO
	//FIN METODOS "CODIGO ITEM"
	
	//METODOS DE ACCESO PARA "DESCRIPCION"
	public void setDescripcion(String descripcion) {
		this.descripcion.set(descripcion);
	}//FIN METODO
	
	public String getDescripcion() {
		return this.descripcion.get();
	}//FIN METODO
	
	public StringProperty descripcionProperty() {
		return this.descripcion;
	}//FIN METODO
	//FIN METODOS "DESCRIPCION"
	
	public String showInformacionRol() {
		String informacionRol = "SysPK: " + this.getSysPk() + "\n"
				+ "C�digo Item: " + this.getCodigoItem() + "\n"
						+ "Descripci�n: " + this.getDescripcion();
		return informacionRol;
	}//FIN METODO
	
}//FIN CLASE
