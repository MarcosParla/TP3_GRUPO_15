package main;

import dao.DaoCategoria;
import dao.DaoProducto;
import entidad.categoria;
import entidad.producto;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        DaoCategoria daoCat = new DaoCategoria();
        DaoProducto daoProd = new DaoProducto();

        int idAlimentos = daoCat.altaCategoria(new categoria(0, "Alimentos"));
        int idBebidas   = daoCat.altaCategoria(new categoria(0, "Bebidas"));

        System.out.println("Categorias:");
        daoCat.listarCategorias().forEach(System.out::println);

     // carga de productos
        daoProd.cargarDatosIniciales(idAlimentos, idBebidas);

        // listado inicial de productos
        System.out.println("\nProductos cargados:");
        daoProd.listarProductos().forEach(System.out::println);

        // alta con sp
        daoProd.bajaProducto("B007"); // elimino el producto antes para que no me genere un error
        producto pSP = new producto("B007", "Vino", new BigDecimal("3500.00"), 25, idBebidas);
        daoProd.altaProductoSP(pSP);

        // modificacion
        producto productoModificado = new producto("B003", "Agua Villavicencio", new BigDecimal("1750.00"), 35, idBebidas);
        daoProd.modificacionProducto(productoModificado);

        // modificacion muestra
        System.out.println("\nProducto modificado:");
        daoProd.listarProductos().stream()
              .filter(p -> p.getCodigo().equals("B003"))
              .forEach(System.out::println);

        // baja
        daoProd.bajaProducto("A001");

        // listado final
        System.out.println("\nListado de productos actualizado final:");
        daoProd.listarProductos().forEach(System.out::println);
    }
}



