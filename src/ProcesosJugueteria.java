import java.sql.*;
import java.util.*;
import java.time.*;
import java.io.*;

public class ProcesosJugueteria implements IProcesosJugueteria {
	// sql
	private String url = "jdbc:mariadb://127.0.0.1:3304/jugueteria";
	private String user = "root";
	private String password = "Alfredo+123";
	private Connection conexion = null;
	private PreparedStatement statement = null;
	// info
	private ArrayList<Juguete> juguetes = new ArrayList<Juguete>();
	private ArrayList<Distribuidor> distribuidores = new ArrayList<Distribuidor>();
	private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	Inventario inventario = new Inventario(juguetes, distribuidores, clientes);

	@Override
	public void revisarInventario(Inventario inventario) throws SQLException {
		// TODO Auto-generated method stub
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection(url, user, password);
			String selectQueryJuguete = "SELECT * FROM juguete";
			statement = conexion.prepareStatement(selectQueryJuguete);
			ResultSet resultados = statement.executeQuery();
			while (resultados.next()) {
				int id = resultados.getInt("id_juguete");
				String nombre = resultados.getString("nombre");
				double venta = resultados.getDouble("venta");
				String estado = resultados.getString("estado");
				Juguete juguete = new Juguete(id, nombre, venta, estado);
				juguetes.add(juguete);
			}
			ArrayList<Juguete> copia_juguetes = new ArrayList<Juguete>(juguetes);
			inventario.setJuguetes(copia_juguetes);
			juguetes.clear();
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
		} finally {
			// Cerrar recursos (statement y conexión)
			if (statement != null) {
				statement.close();
			}
			if (conexion != null) {
				conexion.close();
			}
		}
	}

	@Override
	public void actualizarJuguete(Juguete juguete, String nombreDistribuidor, String nombreCliente) throws SQLException {
		// TODO Auto-generated method stub
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection(url, user, password);
			String updateQuery = "UPDATE juguete SET nombre = ?, venta = ?, estado = ? WHERE id_juguete = ?";
			statement = conexion.prepareStatement(updateQuery);
			statement.setString(1, juguete.getNombre());
			statement.setDouble(2, juguete.getVenta());
			statement.setString(3, juguete.getEstado());
			statement.setInt(4, juguete.getId());
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				System.out.println("Juguete actualizado con éxito.");
			} else {
				System.out.println("No se pudo actualizar el juguete.");
			}
			//actualizar el distribuidor en base al juguete
			String updateQueryDistribuidor = "UPDATE distribuidor SET nombre = ? WHERE id_juguete = ?";
		//guardamos los datos en un arreglo
			int[] datosJuguete = selectDistribuidor(juguete.getId());
			statement = conexion.prepareStatement(updateQueryDistribuidor);
			statement.setString(1, nombreDistribuidor);
			statement.setInt(2, juguete.getId());
			int rowsUpdatedDistribuidor = statement.executeUpdate();
			if (rowsUpdatedDistribuidor > 0) {
				System.out.println("Distribuidor actualizado con éxito.");
			} else {
				System.out.println("No se pudo actualizar el distribuidor.");
			}
			String updateQueryCliente = "UPDATE cliente SET nombre = ? WHERE id_factura = ?";
			statement = conexion.prepareStatement(updateQueryCliente);
			statement.setString(1, nombreCliente);
			statement.setInt(2, datosJuguete[1]);
			int rowsUpdatedCliente = statement.executeUpdate();
			if (rowsUpdatedCliente > 0) {
				System.out.println("Cliente actualizado con éxito.");
			} else {
				System.out.println("No se pudo actualizar el cliente.");
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		} finally {
			// Cerrar recursos (statement y conexión)
			if (statement != null) {
				statement.close();
			}
			if (conexion != null) {
				conexion.close();
			}
		}
	}

	@Override
	public void eliminarJuguete(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		try {
			
			//primero eliminar el distribuidor y luego el juguete 
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection(url, user, password);
			String deleteQueryDistribuidor = "DELETE from distribuidor WHERE id_juguete = ? ";
			statement = conexion.prepareStatement(deleteQueryDistribuidor);
			statement.setInt(1, id);
			int rowsDeletedDistribuidor = statement.executeUpdate();
			if (rowsDeletedDistribuidor > 0) {
				System.out.println("Eliminado");
			}
			
			String deleteQuery = "DELETE from juguete WHERE id_juguete = ? ";
			statement = conexion.prepareStatement(deleteQuery);
			statement.setInt(1, id);
			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				System.out.println("Eliminado");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Cerrar recursos (statement y conexión)
			if (statement != null) {
				statement.close();
			}
			if (conexion != null) {
				conexion.close();
			}
		}
	}

	public Factura insertarJuguete(Juguete juguete, String nombreDistribuidor, String nombreCliente)
			throws SQLException {
		Factura factura = new Factura();
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection(url, user, password);
			String insertQuery = "INSERT INTO juguete (id_juguete, nombre, venta, estado) VALUES (?, ?, ?, ?)";
			statement = conexion.prepareStatement(insertQuery);
			Integer jugueteId = generarId();
			juguete.setId(jugueteId);
			System.out.println(juguete.getId());
			statement.setInt(1, juguete.getId());
			Integer facturaId = generarId();
			statement.setString(2, juguete.getNombre());
			// estado
			Double venta = 0.0;
			switch (juguete.getEstado()) {
			// "Oferta", "Normal", "Liquidacion", "Temporada"
			case "Oferta":
				// si es oferta 25%
				Double oferta = (juguete.getVenta()) - (juguete.getVenta() * 0.25);
				venta = oferta;
				break;
			case "Normal":
				venta = juguete.getVenta();
				break;
			case "Liquidacion":
				Double Liquidacion = (juguete.getVenta()) - (juguete.getVenta() * 0.75);
				venta = Liquidacion;
				break;
			case "Temporada":
				Double Temporada = (juguete.getVenta()) - (juguete.getVenta() * 0.50);
				venta = Temporada;
				break;
			}
			statement.setString(4, juguete.getEstado());
			statement.setDouble(3, venta);
			int rowsInserted = statement.executeUpdate();// se inserta el juguete
			if (rowsInserted != 0) {
				System.out.println("Se ha insertado la fila con exito");
			}
			Distribuidor distribuidor = new Distribuidor(0, "", juguete);
			// crea la factura
			factura = distribuidor.Generar_factura(facturaId, juguete.getVenta(), juguete, nombreDistribuidor);
			insertarFactura(juguete, factura);
			insertarDistribuidor(juguete, nombreDistribuidor, nombreCliente, factura);
			// inserta la factura

			// inserta el distribuidor
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
			e.printStackTrace();
		} finally {
			// Cerrar recursos (statement y conexión)
			if (statement != null) {
				statement.close();
			}
			if (conexion != null) {
				conexion.close();
			}
		}
		return factura;

	}

	private void insertarDistribuidor(Juguete juguete, String nombreDistribuidor, String nombreCliente, Factura factura)
			throws SQLException {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection(url, user, password);
			String insertQueryDistribuidor = "INSERT INTO distribuidor (id_distribuidor, id_juguete,id_factura, nombre) VALUES (?,?, ?, ?)";
			statement = conexion.prepareStatement(insertQueryDistribuidor);
			statement.setInt(2, juguete.getId());
			System.out.println(juguete.getId() + "juguete");
			System.out.println(factura.getId());
			statement.setInt(3, factura.getId());
			statement.setInt(1, generarId());
			statement.setString(4, nombreDistribuidor);
			int rowsInsertedDistribuidor = statement.executeUpdate();
			if (rowsInsertedDistribuidor != 0) {
				System.out.println("Se ha insertado el distribuidor con éxito");
			}
			insertarCliente(nombreCliente, factura);// inserta el cliente
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
			e.printStackTrace();

		}
	}

	private void insertarFactura(Juguete juguete, Factura factura) throws SQLException {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection(url, user, password);
			String insertQueryFactura = "INSERT INTO factura (id_Factura, fecha, total, nombre) VALUES (?, ?, ?, ?)";
			statement = conexion.prepareStatement(insertQueryFactura);
			statement.setInt(1, factura.getId());// aqui truena?
			System.out.println(factura.getId());
			Timestamp fecha = Timestamp.valueOf(factura.getFecha());
			statement.setTimestamp(2, fecha);
			statement.setDouble(3, juguete.getVenta());
			statement.setString(4, factura.getNombre());
			int rowsInsertedFactura = statement.executeUpdate();

			if (rowsInsertedFactura != 0) {
				System.out.println("Se ha insertado la factura con éxito");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
		}
	}

	private void insertarCliente(String nombre, Factura factura) throws SQLException {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection(url, user, password);
			String insertQueryCliente = "INSERT INTO cliente (id_cliente, id_factura, nombre) VALUES (?, ?, ?)";
			statement = conexion.prepareStatement(insertQueryCliente);
			statement.setInt(1, generarId());
			statement.setInt(2, factura.getId());
			statement.setString(3, nombre);
			int rowsInsertedCliente = statement.executeUpdate();
			if (rowsInsertedCliente != 0) {
				System.out.println("Se ha insertado el Cliente con éxito");
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
		}
	}

	private int[] selectDistribuidor(Integer id_juguete) {
	//usaremos un arreglo para guardar de manera mas efectiva los datos 
		int[] ids = new int[2];
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection(url, user, password);
			String selectQueryDistribuidor = "SELECT * FROM distribuidor WHERE id_juguete = ?";
			PreparedStatement preparedStatement = conexion.prepareStatement(selectQueryDistribuidor);
			preparedStatement.setInt(1, id_juguete);
			ResultSet resultados = preparedStatement.executeQuery();
		    if (resultados.next()) {
	            // Recuperar los datos del distribuidor y crear un objeto Distribuidor
	             ids[0] = resultados.getInt("id_distribuidor");
	             ids[1] = resultados.getInt("id_factura");
	        }
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
		}
		
		return ids;
	}
	private int selectCliente(Integer id_juguete) {
		int id_cliente = 0;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conexion = DriverManager.getConnection(url, user, password);
			String selectQueryDistribuidor = "SELECT * FROM cliente WHERE id_juguete = ?";
			PreparedStatement preparedStatement = conexion.prepareStatement(selectQueryDistribuidor);
			preparedStatement.setInt(1, id_juguete);
			ResultSet resultados = preparedStatement.executeQuery();
		    if (resultados.next()) {
	            // Recuperar los datos del distribuidor y crear un objeto Distribuidor
	             id_cliente = resultados.getInt("id_cliente");
	        }
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e);
		}
		return id_cliente;
	}
	

//metodos extra
	private Integer generarId() {
		Random random = new Random();
		int Id = random.nextInt(100000) + 1;
		return Id;
	}

}
