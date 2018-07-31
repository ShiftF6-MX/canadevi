package mx.shf6.canadevi.model;

public class Persona {
	private int id;
	
	private String cedula;
	private String nombre;
	private String apellido;
	
	public Persona() {
	}
	
	public Persona(String cedula, String nombre, String apellido, int id) {
		this.id = id;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
	}
 
	public int getId() {
		return id;
	}
 
	public void setId(int id) {
		this.id = id;
	}
 
	public String getCedula() {
		return cedula;
	}
 
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
 
	public String getNombre() {
		return nombre;
	}
 
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
 
	public String getApellido() {
		return apellido;
	}
 
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	@Override
	public String toString() {
		return this.id+", "+this.cedula+", "+this.nombre+", "+this.apellido;
	}
}
