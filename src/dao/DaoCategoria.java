package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import entidad.categoria;

public class DaoCategoria {
    private static String url = "jdbc:mysql://localhost:3306/bdInventario";
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

    public void altaCategoria(categoria c) {
        String sql = "INSERT INTO Categorias (IdCategoria, Nombre) VALUES (?, ?)"; 
        try (Connection conn = DaoCategoria.getConnection();						 
             PreparedStatement ps = conn.prepareStatement(sql)) {					
            
            ps.setInt(1, c.getIdCategoria());
            ps.setString(1, c.getNombre());
            
            ps.executeUpdate();
            System.out.println("Categoría agregada correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void bajaCategoria(int IdCategoria) {
    	String sql = "DELETE FROM Categorias WHERE IdCategoria=?";
    	try (Connection conn = DaoCategoria.getConnection();
    			PreparedStatement ps = conn.prepareStatement(sql)){
    			ps.setInt(1, IdCategoria);
    			ps.executeUpdate();
    			
    			System.out.println("Categoria eliminada correctamente");
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    		
    }
    public void modificacionCategoria(categoria c) {
    	String sql = "UPDATE Categorias SET Nombre=? WHERE IdCategoria=?";
    	try (Connection conn = DaoCategoria.getConnection();
    			PreparedStatement ps = conn.prepareStatement(sql)){
    		ps.setString(1,c.getNombre());
    		ps.setInt(2,c.getIdCategoria());
    		
    		ps.executeUpdate();
    		System.out.println("Categoria modificada correctamente");
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
}

