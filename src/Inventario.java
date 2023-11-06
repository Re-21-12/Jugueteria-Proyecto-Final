import java.util.*;
public class Inventario {
	private ArrayList<Juguete> juguetes;
	private ArrayList<Distribuidor> distribuidores;
	private ArrayList<Cliente> clientes;
	
	/**
	 * @param juguetes
	 * @param distribuidores
	 * @param clientes
	 */
	public Inventario(ArrayList<Juguete> juguetes, ArrayList<Distribuidor> distribuidores,
			ArrayList<Cliente> clientes) {
		super();
		this.juguetes = juguetes;
		this.distribuidores = distribuidores;
		this.clientes = clientes;
	}
	public Inventario() {}

	public ArrayList<Juguete> getJuguetes() {
		return juguetes;
	}

	public void setJuguetes(ArrayList<Juguete> juguetes) {
		this.juguetes = juguetes;
	}

	public ArrayList<Distribuidor> getDistribuidores() {
		return distribuidores;
	}

	public void setDistribuidores(ArrayList<Distribuidor> distribuidores) {
		this.distribuidores = distribuidores;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	@Override
	public String toString() {
		return "Inventario [juguetes=" + juguetes + ", distribuidores=" + distribuidores + ", clientes=" + clientes
				+ "]";
	}
	

}
