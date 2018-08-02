package model;
import java.util.ArrayList;

import java.sql.Connection;

public interface ObjectDAO {
	public boolean crear(Connection connection, Object objeto) throws Exception;
	public ArrayList<Object> leer(Connection connection, String campoBusqueda, String valorBusqueda) throws Exception;
	public boolean modificar(Connection connection, Object objeto) throws Exception;
	public boolean eliminar(Connection connection, Object objeto) throws Exception;
}