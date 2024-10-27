package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Articulo;

public class BuscarArticuloController extends BaseController {

    @FXML
    private TextField codigoArticuloField;

    @FXML
    private TextField nombreArticuloField;

    @FXML
    private void handleBuscar() {
        String codigo = codigoArticuloField.getText();
        String nombre = nombreArticuloField.getText();

        // Simulamos la búsqueda del artículo (en realidad deberías buscar en tu lista o base de datos)
        Articulo articuloEncontrado = buscarArticulo(codigo, nombre);

        if (articuloEncontrado != null) {
            mostrarDetalleArticulo(articuloEncontrado);
        } else {
            // Podés mostrar una alerta indicando que no se encontró el artículo
            System.out.println("Artículo no encontrado.");
        }
    }

    private Articulo buscarArticulo(String codigo, String nombre) {
        // Acá deberías implementar la lógica para buscar el artículo
        // Por ahora, retornamos un objeto de ejemplo si el código o nombre no están vacíos
        if (!codigo.isEmpty() || !nombre.isEmpty()) {
            return new Articulo("ART001", "Televisor", "Electrónica", 10, "Unidad");
        }
        return null;
    }

    private void mostrarDetalleArticulo(Articulo articulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/DetalleArticuloView.fxml"));
            Parent root = loader.load();

            // Pasar el artículo al controlador de detalle
            DetalleArticuloController controller = loader.getController();
            controller.setArticulo(articulo);

            // Crear la escena y agregar el CSS
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/styles/styles.css").toExternalForm());

            Stage stage = new Stage();
            stage.setTitle("Detalle del Artículo");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
