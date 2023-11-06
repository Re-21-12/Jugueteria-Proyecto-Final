
import java.time.*;
import java.util.*;

public class Factura {
	private int Id;
	private Double total;
	private Juguete juguetes;
	private LocalDateTime fecha;
	private String nombre;

	/**
	 * @param id
	 * @param total
	 * @param juguetes
	 * @param fecha
	 * @param nombre
	 */
	public Factura(int id, Double total, Juguete juguetes, LocalDateTime fecha, String nombre) {
		super();
		Id = id;
		this.total = total;
		this.juguetes = juguetes;
		this.fecha = fecha;
		this.nombre = nombre;
	}

	public Factura() {
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Juguete getJuguetes() {
		return juguetes;
	}

	public void setJuguetes(Juguete juguetes) {
		this.juguetes = juguetes;
	}

	public LocalDateTime getFecha() {
		LocalDateTime hoy = LocalDateTime.now();
		return hoy;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Factura [Id=" + Id + ", total=" + total + ", juguetes=" + juguetes + ", fecha=" + fecha + ", nombre="
				+ nombre + "]";
	}

}
