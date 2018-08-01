package model;

import java.sql.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {
	
	//PROPIEDADES
	private ObjectProperty<Integer> sysPk;
	private StringProperty usuario;
	private StringProperty contrasena;
	private StringProperty correoElectronico;
	private ObjectProperty<Date> fechaRegistro;
	private ObjectProperty<Date> fechaBloqueo;
	private ObjectProperty<Integer> status;
	
	//CONSTANTES
	static final int BLOQUEADO = 0;
	static final int ACTIVO = 1;
	static final int BAJA = 2;
	
	//CONSTRUCTOR SIN PARAMETROS	
	public Usuario() {
		this(0,"","","",null,null,0);
	}//FIN CONSTRUCTOR
	
	//CONSTRUCTOR CON PARAMETROS
	public Usuario(Integer sysPk, String usuario, String contrasena, String correoElectronico, Date fechaRegistro, Date fechaBloqueo, Integer status) {
		this.sysPk = new SimpleObjectProperty<Integer>(sysPk);
		this.usuario = new SimpleStringProperty(usuario);
		this.contrasena = new SimpleStringProperty(contrasena);
		this.correoElectronico = new SimpleStringProperty(correoElectronico);
		this.fechaRegistro = new SimpleObjectProperty<Date>(fechaRegistro);
		this.fechaBloqueo = new SimpleObjectProperty<Date>(fechaBloqueo);
		this.status = new SimpleObjectProperty<Integer>(status);
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
	//FINT METODOS "SYSPK"
	
	//METODOS PARA ACCESO A "NOMBRE"
	public void setUsuario(String usuario) {
		this.usuario.set(usuario);
	}//FIN METODO
	
	public String getUsuario() {
		return this.usuario.get();
	}//FIN METODO
	
	public StringProperty usuarioProperty() {
		return this.usuario;
	}//FIN METODO
	//FIN METODOS "NOMBRE"
	
	//METODOS PARA ACCESO A "CONTRASEÑA"
	public void setContrasena(String contrasena) {
		this.contrasena.set(contrasena);
	}//FIN METODO
	
	public String getContrasena() {
		return this.contrasena.get();
	}//FIN METODO
	
	public StringProperty contrasenaProperty() {
		return this.contrasena;
	}//FIN METODO
	//FIN METODOS "CONTRASENA"
	
	//METODOS PARA ACCESO A "CORREO ELECTRONICO"
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico.set(correoElectronico);
	}//FIN METODO
	
	public String getCorreoElectronico( ) {
		return this.correoElectronico.get();
	}//FIN METODO
	
	public StringProperty correoElectronicoProperty() {
		return this.correoElectronico;
	}//FIN METODO
	//FIN METODOS "CORREO ELECTRONICO"
	
	//METODOS PARA ACCESO A "FECHA REGISTRO"
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro.set(fechaRegistro);
	}//FIN METODO
	
	public Date getFechaRegistro() {
		return this.fechaRegistro.get();
	}//FIN METODO
	
	public ObjectProperty<Date> fechaRegistroProperty() {
		return this.fechaRegistro;
	}//END METODO
	//END METODOS "FECHA REGISTRO"
	
	//METODOS PARA ACCESO A "FECHA BLOQUEO"
	public void setFechaBloqueo(Date fechaBloqueo) {
		this.fechaBloqueo.set(fechaBloqueo);
	}//FIN METODO
	
	public Date getFechaBloqueo() {
		return this.fechaBloqueo.get();
	}//FIN METODO
	
	public ObjectProperty<Date> fechaBloqueProperty() {
		return this.fechaBloqueo;
	}//FIN METODO
	//FIN METODOS "FECHA BLOQUEO"
	
	//METODOS PARA ACCESO A "STATUS"
	public void setStatus(Integer status) {
		this.status.set(status);
	}//FIN METODO
	
	public Integer getStatus() {
		return this.status.get();
	}//FIN METODO
	
	public ObjectProperty<Integer> statusProperty() {
		return this.status;
	}//FIN METODO
	//FIN METODO "STATUS"
	
	public String showInformacionUsuario() {
		String informacionUsuario = "SysPk: " + this.getSysPk() + "\n"
				+ "Nombre: " + this.getUsuario() + "\n"
						+ "Contraseña: " + this.getContrasena() + "\n"
								+ "Correo Electrónico: " + this.getCorreoElectronico() + "\n"
										+ "Fecha Registro: " + this.getFechaRegistro().toString() + "\n"
												+ "Fecha Bloqueo: " + this.getFechaBloqueo().toString() + "\n"
														+ "Status: " + this.getStatus();
		return informacionUsuario;
	}//FIN METODO

}//FIN CLASE
