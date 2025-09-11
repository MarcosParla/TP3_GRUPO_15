package entidad;

public class categoria {
	public  int IdCategoria;
	private String Nombre;
	
	public categoria(){
		
	}

	public categoria(int idCategoria, String nombre) {
		super();
		this.IdCategoria = idCategoria;
		this.Nombre = nombre;
	}

	public int getIdCategoria() {
		return IdCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		IdCategoria = idCategoria;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	@Override
	public String toString() {
		return "categoria [IdCategoria=" + IdCategoria + ", Nombre=" + Nombre + "]";
	}
	
}
