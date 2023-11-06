
public class Juguete {
private Double venta;
private String estado;
private int Id;
private String nombre;
/**
 * @param venta
 * @param estado
 * @param id
 * @param nombre
 */
public Juguete( int id, String nombre,Double venta, String estado) {
	super();
	this.venta = venta;
	this.estado = estado;
	Id = id;
	this.nombre = nombre;
}
public Juguete( String nombre,Double venta, String estado) {
	super();
	this.venta = venta;
	this.estado = estado;
	this.nombre = nombre;
}
public Double getVenta() {
	return venta;
}
public void setVenta(Double venta) {
	this.venta = venta;
}
public String getEstado() {
	return estado;
}
public void setEstado(String estado) {
	this.estado = estado;
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

public String toString() {
    return String.format("ID: %s%nNombre: %s%nVenta: %.2f%nEstado: %s%n", getId(), getNombre(), getVenta(), getEstado());
}
}
