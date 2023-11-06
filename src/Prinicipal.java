import java.sql.*;
import javax.swing.*;

public class Prinicipal  {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hola mundo");
		String url = "jdbc:mariadb://127.0.0.1:3304/jugueteria";
		String user = "root";
		String password = "Alfredo+123";
		 try {
	            Class.forName("org.mariadb.jdbc.Driver");
	            Connection conexion = DriverManager.getConnection(url, user, password);
	            
	            if (conexion != null) {
	                System.out.println("Conexión a la base de datos establecida con éxito.");
	                // Realiza cualquier operación adicional que necesites en la base de datos
	                Ventana ventana = new Ventana();
	                
	            } else {
	                System.out.println("No se pudo establecer la conexión a la base de datos.");
	            }
	            
	            // Cerrar la conexión cuando hayas terminado
	            conexion.close();
	        } catch (Exception e) {
	            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
	        }
			/*
			 * JFrame frame = new JFrame("Jugueteria"); frame.setSize(800, 600);
			 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame.setVisible(true);
			 */
	}



}
