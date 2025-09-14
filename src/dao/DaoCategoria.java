package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import entidad.categoria;

public class DaoCategoria {
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

    public int altaCategoria(categoria c) {
        String sql = "INSERT INTO Categorias (Nombre) VALUES (?)";   // "?" funciona como placeholder
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getNombre());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    c.setIdCategoria(id);
                    System.out.println("Categoría agregada. Id: " + id);
                    return id;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return -1;
    }

    public void bajaCategoria(int idCategoria) {
        String sql = "DELETE FROM Categorias WHERE IdCategoria=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idCategoria);
            ps.executeUpdate();
            System.out.println("Categoría eliminada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modificacionCategoria(categoria c) {
        String sql = "UPDATE Categorias SET Nombre=? WHERE IdCategoria=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getIdCategoria());
            ps.executeUpdate();
            System.out.println("Categoría modificada correctamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<categoria> listarCategorias() {
        List<categoria> lista = new ArrayList<>();
        String sql = "SELECT IdCategoria, Nombre FROM Categorias ORDER BY IdCategoria";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
            	categoria c = new categoria(rs.getInt("IdCategoria"), rs.getString("Nombre"));
                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}