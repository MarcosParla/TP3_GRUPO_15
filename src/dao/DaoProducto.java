package dao;

import entidad.producto;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;  
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DaoProducto {

	private static String url = "jdbc:mysql://localhost:3306/bdInventario?useSSL=false&serverTimezone=America/Argentina/Buenos_Aires";
    private static String user = "root";
    private static String pass = "root";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    // alta
    public boolean altaProducto(producto p) {
        String sql = "INSERT INTO Productos (Codigo, Nombre, Precio, Stock, IdCategoria) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getCodigo());     
            ps.setString(2, p.getNombre());
            ps.setBigDecimal(3, p.getPrecio());  
            ps.setInt(4, p.getStock());
            ps.setInt(5, p.getIdCategoria());

            int filas = ps.executeUpdate();
            System.out.println(filas == 1 ? "Producto agregado correctamente." : "No se pudo agregar el producto.");
            return filas == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //Alta sp
    public boolean altaProductoSP(producto p) {
        String call = "{ call sp_AgregarProducto(?, ?, ?, ?, ?) }";
        try (Connection conn = getConnection();
             CallableStatement cs = conn.prepareCall(call)) {

            cs.setString(1, p.getCodigo());       
            cs.setString(2, p.getNombre());
            cs.setBigDecimal(3, p.getPrecio());  
            cs.setInt(4, p.getStock());
            cs.setInt(5, p.getIdCategoria());

            int filas = cs.executeUpdate();
            System.out.println(filas == 1 
                ? "Producto agregado via sp correctamente." 
                : "No se pudo agregar el producto usando el sp.");
            return filas == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // baja
    public void bajaProducto(String codigo) {
        String sql = "DELETE FROM Productos WHERE Codigo = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, codigo);
            int filas = ps.executeUpdate();
            System.out.println(filas == 1 ? "Producto eliminado correctamente." : "No se encontro el producto.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // modificacion
    public void modificacionProducto(producto p) {
        String sql = "UPDATE Productos SET Nombre = ?, Precio = ?, Stock = ?, IdCategoria = ? WHERE Codigo = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setBigDecimal(2, p.getPrecio());
            ps.setInt(3, p.getStock());
            ps.setInt(4, p.getIdCategoria());
            ps.setString(5, p.getCodigo());

            int filas = ps.executeUpdate();
            System.out.println(filas == 1 ? "Producto modificado correctamente." : "No se modifico el producto.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // listado
    public List<producto> listarProductos() {
        List<producto> lista = new ArrayList<>();
        String sql = "SELECT Codigo, Nombre, Precio, Stock, IdCategoria FROM Productos ORDER BY Codigo";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                producto p = new producto(
                        rs.getString("Codigo"),
                        rs.getString("Nombre"),
                        rs.getBigDecimal("Precio"),
                        rs.getInt("Stock"),
                        rs.getInt("IdCategoria")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    //listado de codigos
    public Set<String> listarCodigos() {
        Set<String> codigos = new HashSet<>();
        String sql = "SELECT Codigo FROM Productos";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                codigos.add(rs.getString("Codigo"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return codigos;
    }
    
    public void cargarDatosIniciales(int idAlimentos, int idBebidas) {
        Set<String> codigosExistentes = listarCodigos();

        producto[] productos = {
            new producto("A001", "Arroz", new BigDecimal("1500.00"), 25, idAlimentos),
            new producto("A002", "Fideos", new BigDecimal("1800.00"), 15, idAlimentos),
            new producto("A003", "Pan", new BigDecimal("1200.00"), 10, idAlimentos),
            new producto("A004", "Palmeritas", new BigDecimal("2500.00"), 5, idAlimentos),
            new producto("B001", "Coca Cola", new BigDecimal("3000.00"), 20, idBebidas),
            new producto("B002", "Pepsi", new BigDecimal("2500.00"), 20, idBebidas),
            new producto("B003", "Fanta", new BigDecimal("2500.00"), 30, idBebidas),
            new producto("B004", "Cepita Naranja", new BigDecimal("1800.00"), 10, idBebidas)
        };

        for (producto p : productos) {
            if (!codigosExistentes.contains(p.getCodigo())) {
                altaProducto(p);
                System.out.println("Producto " + p.getCodigo() + " insertado correctamente.");
            } else {
                System.out.println("El producto con el codigo " + p.getCodigo() + " ya existe.");
            }
        }
    }


}

