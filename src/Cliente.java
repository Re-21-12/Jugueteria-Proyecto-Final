
public class Cliente {
	private int Id;
	private String nombre;
	private Integer id_factura;
	
	/**
	 * @param id
	 * @param nombre
	 * @param id_factura
	 */
	public Cliente(int id, String nombre, Integer id_factura) {
		super();
		Id = id;
		this.nombre = nombre;
		this.id_factura = id_factura;
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

	public Integer getId_factura() {
		return id_factura;
	}

	public void setId_factura(Integer id_factura) {
		this.id_factura = id_factura;
	}

	@Override
	public String toString() {
		return "Cliente [Id=" + Id + ", nombre=" + nombre + ", id_factura=" + id_factura + "]";
	}
	
	
}
