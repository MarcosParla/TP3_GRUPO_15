package entidad;

import java.math.BigDecimal;

public class producto{
	private String codigo;
	private String nombre;
	private BigDecimal precio;
	private int stock;
	private int idCategoria;
	
	public producto() {
		
	}
	public producto(String codigo, String nombre, BigDecimal precio, int stock ,int idCategoria) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
		this.idCategoria=idCategoria;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String Nombre) {
		Nombre = nombre;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal Precio) {
		Precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int Stock) {
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
		return "Producto [Codigo = " + codigo + ", Nombre = " + nombre + ", Precio = " + precio + ", Stock = " + stock
				+ ", idCategoria = " + idCategoria + "]";
	}
	
	
}
