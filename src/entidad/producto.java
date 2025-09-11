package entidad;

public class producto{
	private int codigo;
	private String Nombre;
	private int Precio;
	private int Stock;
	private int idCategoria;
	
	public producto() {
		
	}
	public producto(int codigo, String nombre, int precio, int stock ,int idCategoria) {
		super();
		this.codigo = codigo;
		this.Nombre = nombre;
		this.Precio = precio;
		this.Stock = stock;
		this.idCategoria=idCategoria;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public int getPrecio() {
		return Precio;
	}
	public void setPrecio(int precio) {
		Precio = precio;
	}
	public int getStock() {
		return Stock;
	}
	public void setStock(int stock) {
		Stock = stock;
	}
	
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	@Override
	public String toString() {
		return "producto [codigo=" + codigo + ", Nombre=" + Nombre + ", Precio=" + Precio + ", Stock=" + Stock
				+ ", idCategoria=" + idCategoria + "]";
	}
	
	
}
