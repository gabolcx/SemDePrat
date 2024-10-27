package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Articulo;

public class AddArticleController extends BaseController {

    @FXML
    private TextField codigoArticuloField;
    @FXML
    private TextField nombreArticuloField;
    @FXML
    private TextField areaUsuariaField;
    @FXML
    private TextField stockActualField;
    @FXML
    private TextField unidadMedidaField;

    @FXML
    public void initialize() {
        // Código de inicialización si es necesario
    }

    @FXML
    private void handleSaveArticle() {
        try {
            String codigoArticulo = codigoArticuloField.getText();
            String nombreArticulo = nombreArticuloField.getText();
            String areaUsuaria = areaUsuariaField.getText();
            String stockActualText = stockActualField.getText();
            String unidadMedida = unidadMedidaField.getText();

            // Validación básica
            if (codigoArticulo.isEmpty() || nombreArticulo.isEmpty() || areaUsuaria.isEmpty() || stockActualText.isEmpty() || unidadMedida.isEmpty()) {
                mostrarMensaje("Error", "Por favor, completá todos los campos.");
                return;
            }

            int stockActual;
            try {
                stockActual = Integer.parseInt(stockActualText);
            } catch (NumberFormatException e) {
                mostrarMensaje("Error", "El campo 'Stock Actual' debe ser un número entero.");
                return;
            }

            // Crear objeto Articulo
            Articulo articulo = new Articulo(codigoArticulo, nombreArticulo, areaUsuaria, stockActual, unidadMedida);

            // Aquí podés agregar el artículo a una lista o realizar alguna acción

            mostrarMensaje("Artículo guardado", "El artículo '" + nombreArticulo + "' ha sido guardado exitosamente.");

            // Limpiar los campos después de guardar
            limpiarCampos();

        } catch (Exception e) {
            mostrarMensaje("Error", "Ocurrió un error: " + e.getMessage());
        }
    }

    @FXML
    private void handleBorrar() {
        limpiarCampos();
    }

    private void limpiarCampos() {
        codigoArticuloField.clear();
        nombreArticuloField.clear();
        areaUsuariaField.clear();
        stockActualField.clear();
        unidadMedidaField.clear();
    }

    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
