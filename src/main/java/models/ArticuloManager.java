package models;

import java.util.ArrayList;
import java.util.List;

public class ArticuloManager {
    private List<Articulo> listaArticulos;

    // Constructor
    public ArticuloManager() {
        this.listaArticulos = new ArrayList<>();
    }

    // Método para agregar un artículo
    public void agregarArticulo(Articulo articulo) {
        listaArticulos.add(articulo);
    }

    // Método para obtener la lista de artículos
    public List<Articulo> getListaArticulos() {
        return listaArticulos;
    }

    // Método para buscar un artículo por código (utilizando un algoritmo de búsqueda)
    public Articulo buscarArticuloPorCodigo(String codigo) {
        for (Articulo articulo : listaArticulos) {
            if (articulo.getCodigoArticulo().equals(codigo)) {
                return articulo;
            }
        }
        return null; // Si no se encuentra
    }

    // Método para ordenar los artículos por nombre (algoritmo de ordenación sencillo)
    public void ordenarArticulosPorNombre() {
        listaArticulos.sort((a1, a2) -> a1.getNombreArticulo().compareToIgnoreCase(a2.getNombreArticulo()));
    }
}
