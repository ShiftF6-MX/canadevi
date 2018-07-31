package mx.shf6.canadevi.model;

import java.util.List;

import mx.shf6.canadevi.model.Persona;
 
public interface PersonaDAO {	
	public boolean registrar(Persona persona);
	public List<Persona> obtener();
	public boolean actualizar(Persona persona);
	public boolean eliminar(Persona persona);
}
