import java.time.LocalDateTime;
import java.util.*;
public class Distribuidor extends Entidad implements GenerarFactura {
private Juguete juguete;
private Integer Id_factura;
/**
 * @param id
 * @param nombre
 * @param juguetes
 */

//	public Factura(int id, Double total, List<Juguete> juguetes, LocalDateTime fecha, String nombre
public Factura Generar_factura(int id, Double venta, Juguete juguete, String nombreDistribuidor) {
	LocalDateTime hoy = LocalDateTime.now();
	Factura factura = new Factura(id, venta, juguete, hoy, nombreDistribuidor);
	return factura;
}
/**
 * @param id
 * @param nombre
 * @param juguete
 * @param id_factura
 */
public Distribuidor(int id, String nombre, Juguete juguete, Integer id_factura) {
	super(id, nombre);
	this.juguete = juguete;
	Id_factura = id_factura;
}
public Distribuidor(int id, String nombre, Juguete juguete) {
	super(id, nombre);
	this.juguete = juguete;
}
public Juguete getJuguete() {
	return juguete;
}
public void setJuguete(Juguete juguete) {
	this.juguete = juguete;
}
public Integer getId_factura() {
	return Id_factura;
}
public void setId_factura(Integer id_factura) {
	Id_factura = id_factura;
}
@Override
public String toString() {
	return "Distribuidor [juguete=" + juguete + ", Id_factura=" + Id_factura + "]";
}

}
