import java.sql.*;

public interface IProcesosJugueteria {
	public abstract void revisarInventario(Inventario inventario)throws SQLException;
	public abstract Factura insertarJuguete(Juguete insJuguete, String nombreDistribuidor, String nombreCliente)throws SQLException;
	public abstract void actualizarJuguete(Juguete juguete)throws SQLException;
	public abstract void eliminarJuguete(Integer id)throws SQLException;

}
