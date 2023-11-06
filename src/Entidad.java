
import java.time.*;
import java.util.*;

public class Entidad {
	private int Id;
	private String nombre;
	/**
	 * @param id
	 * @param nombre
	 */
	public Entidad(int id, String nombre) {
		super();
		Id = id;
		this.nombre = nombre;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Entidad [Id=" + Id + ", nombre=" + nombre + "]";
	}

}
