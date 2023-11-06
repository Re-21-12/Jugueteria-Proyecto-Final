import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Ventana extends JFrame implements ActionListener {
	// info memoria
	Inventario inventario = new Inventario();
	// trae los métodos de Procesos para accionarlos
	private ProcesosJugueteria procesos = new ProcesosJugueteria();
	private Container panel;
	private JTextField id;
	private JTextField nombre;
	private JTextField venta;
	JComboBox<String> estadoComboBox;
	private JTextField nombreDistribuidor;
	private JTextField cliente;

	public Ventana() {
		super("Administracion Juguetes");
		panel = getContentPane();
		setLayout(null);
		setSize(640, 480);
		createUI();
		// id
		JLabel idlbl = new JLabel("Ingrese Id para [Actualizar/Eliminar]: ");
		idlbl.setSize(300, 20);
		idlbl.setLocation(230, 0);
		panel.add(idlbl);
		id = new JTextField("");
		id.setSize(200, 20);
		id.setLocation(230, 20);
		panel.add(id);
		// distribuidor
		JLabel nameDistirbuidorlbl = new JLabel("Distribuidor: ");
		nameDistirbuidorlbl.setSize(100, 20);
		nameDistirbuidorlbl.setLocation(20, 0);
		panel.add(nameDistirbuidorlbl);
		nombreDistribuidor = new JTextField("");
		nombreDistribuidor.setSize(200, 20);
		nombreDistribuidor.setLocation(20, 20);
		panel.add(nombreDistribuidor);
		// nombre
		JLabel namelbl = new JLabel("Nombre Juguete: ");
		namelbl.setSize(100, 20);
		namelbl.setLocation(20, 40);
		panel.add(namelbl);
		nombre = new JTextField("");
		nombre.setSize(200, 20);
		nombre.setLocation(20, 60);
		// venta
		JLabel ventalbl = new JLabel("Venta: ");
		ventalbl.setSize(100, 20);
		ventalbl.setLocation(20, 80);
		panel.add(ventalbl);
		venta = new JTextField("");
		venta.setSize(200, 20);
		venta.setLocation(20, 100);
		panel.add(venta);
		panel.add(nombre);
		// estado
		JLabel estadolbl = new JLabel("Seleccione un estado: ");
		estadolbl.setSize(200, 20);
		estadolbl.setLocation(20, 120);
		panel.add(estadolbl);
		String[] opcionesEstado = { "", "Oferta", "Normal", "Liquidacion", "Temporada" };
		estadoComboBox = new JComboBox<>(opcionesEstado);
		estadoComboBox.setSize(200, 20);
		estadoComboBox.setLocation(20, 140);
		panel.add(estadoComboBox);

		/*
		 * estado = new JTextField(""); estado.setSize(200, 20); estado.setLocation(20,
		 * 140); panel.add(estado);
		 */
		JLabel clientelbl = new JLabel("Nombre del Cliente: ");
		clientelbl.setSize(200, 20);
		clientelbl.setLocation(20, 160);
		panel.add(clientelbl);
		cliente = new JTextField("");
		cliente.setSize(200, 20);
		cliente.setLocation(20, 180);
		panel.add(cliente);
		setVisible(true);
		panel.setBackground(Color.lightGray); // Establece el color de fondo del panel

		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JButton) {
			JButton botonPresionado = (JButton) e.getSource();
			String textoBoton = botonPresionado.getText();

			String txtnombre = nombre.getText();
			String textoVenta = venta.getText();
			String estadoSeleccionado = (String) estadoComboBox.getSelectedItem();
			// String textoEstado = estado.getText();
			String textoDistribuidor = nombreDistribuidor.getText();
			String txtCliente = cliente.getText();
			String textoId = id.getText();
			String[] validacion = { txtnombre, textoVenta, estadoSeleccionado, textoDistribuidor, txtCliente };

			switch (textoBoton) {
			case "Revisar":
				try {
					procesos.revisarInventario(inventario);
					// permite tener String dinámicos
					StringBuilder jugueteStr = new StringBuilder();
					for (Juguete juguete : inventario.getJuguetes()) {
						jugueteStr.append("ID: ").append(juguete.getId()).append("\n");
						jugueteStr.append("Nombre: ").append(juguete.getNombre()).append("\n");
						jugueteStr.append("Venta: ").append(juguete.getVenta()).append("\n");
						jugueteStr.append("Estado: ").append(juguete.getEstado()).append("\n\n");
					}
					JTextArea textArea = new JTextArea(jugueteStr.toString());
					textArea.setWrapStyleWord(true);
					textArea.setLineWrap(true);
					textArea.setEditable(false);

					JScrollPane scrollPane = new JScrollPane(textArea);
					scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

					// Asegúrate de que el tamaño del JScrollPane sea adecuado
					scrollPane.setPreferredSize(new Dimension(400, 200));

					// Mostrar el cuadro de diálogo con el contenido del inventario
					JOptionPane.showMessageDialog(this, scrollPane, "Contenido del Inventario",
							JOptionPane.PLAIN_MESSAGE);

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				break;
			case "Venta":
				Integer i = 0;
				for (String error : validacion) {
					if (error.isEmpty())
						i++;
				}
				try {
					if (i == 0) {
						Juguete juguete = new Juguete(txtnombre, Double.parseDouble(textoVenta), estadoSeleccionado);
						Factura factura = procesos.insertarJuguete(juguete, textoDistribuidor, txtCliente);
						StringBuilder facturaMensaje = new StringBuilder();
						facturaMensaje.append("Número de factura: ").append(factura.getId()).append("\n");
						facturaMensaje.append("Total: ").append(factura.getTotal()).append("\n");
						facturaMensaje.append("Nombre del producto: ").append(factura.getJuguetes()).append("\n");
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"); // Personaliza el formato según tus necesidades
						String fechaFormateada = factura.getFecha().format(formatter);
						facturaMensaje.append("Fecha: ").append(fechaFormateada).append("\n");
						facturaMensaje.append("Entidad: ").append(factura.getNombre()).append("\n");
				        JOptionPane.showMessageDialog(null, facturaMensaje.toString(), "Detalles de la Factura", JOptionPane.INFORMATION_MESSAGE);
				        
						limpiartxt();
					} else {
						JOptionPane.showMessageDialog(this, i.toString(), " Campos faltantes",
								JOptionPane.WARNING_MESSAGE);
					}

				} catch (NumberFormatException | SQLException el) {
					// TODO Auto-generated catch block
					el.printStackTrace();
				}

				break;
			case "Actualizar":
				txtnombre = nombre.getText();
				textoVenta = venta.getText();
				Integer n = 0;
				for (String error : validacion) {
					if (error.isEmpty())
						n++;
				}
				estadoSeleccionado = (String) estadoComboBox.getSelectedItem();

				System.out.println(textoId);
				if (textoId.isEmpty()) {
					JOptionPane.showMessageDialog(this, "Debe colocar un Id", "Error", JOptionPane.WARNING_MESSAGE);
				}

				try {
					if (n == 0) {
						Juguete jugueteact = new Juguete(Integer.parseInt(textoId), txtnombre,
								Double.parseDouble(textoVenta), estadoSeleccionado);
						procesos.actualizarJuguete(jugueteact);
						limpiartxt();
					} else if (n > 0) {
						JOptionPane.showMessageDialog(this, "Debe ingresar al menos un campo a modificar", "Error",
								JOptionPane.WARNING_MESSAGE);
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			case "Eliminar":
				textoId = id.getText();
				try {

					if (textoId.isEmpty()) {
						JOptionPane.showMessageDialog(this, "Debe colocar un Id", "Error", JOptionPane.WARNING_MESSAGE);
					}else {
						
					procesos.eliminarJuguete(Integer.parseInt(textoId));
					for (Juguete juguetearr : inventario.getJuguetes()) {
						if (juguetearr.getId() == Integer.parseInt(textoId)) {

							JOptionPane.showMessageDialog(this,
									"El juguete " + juguetearr.toString() + " ha sido eliminado con éxito.",
									"Eliminación exitosa", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					limpiartxt();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			}
		}
	}

	private void createUI() {
		// Crear botones para las opciones
		JButton revisarButton = new JButton("Revisar");
		JButton ventaButton = new JButton("Venta");
		JButton eliminarButton = new JButton("Eliminar");
		JButton actualizarButton = new JButton("Actualizar");

		// Establecer tamaños y ubicaciones de los botones
		revisarButton.setBounds(20, 220, 120, 30);
		ventaButton.setBounds(160, 220, 120, 30);
		eliminarButton.setBounds(160, 260, 120, 30);
		actualizarButton.setBounds(20, 260, 120, 30);

		// Agregar ActionListener a los botones
		revisarButton.addActionListener(this);
		ventaButton.addActionListener(this);
		eliminarButton.addActionListener(this);
		actualizarButton.addActionListener(this);

		// Agregar los botones al panel
		panel.add(revisarButton);
		panel.add(ventaButton);
		panel.add(eliminarButton);
		panel.add(actualizarButton);
	}

	private void limpiartxt() {
		nombre.setText("");
		venta.setText("");
		estadoComboBox.setSelectedIndex(0);
		nombreDistribuidor.setText("");
		id.setText("");
		cliente.setText("");
	}
}
