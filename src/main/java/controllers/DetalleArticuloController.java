package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.Articulo;

public class DetalleArticuloController extends BaseController {

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
    private Button btnGuardar;

    private Articulo articulo;

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
        mostrarDatosArticulo();
    }

    private void mostrarDatosArticulo() {
        if (articulo != null) {
            codigoArticuloField.setText(articulo.getCodigoArticulo());
            nombreArticuloField.setText(articulo.getNombreArticulo());
            areaUsuariaField.setText(articulo.getAreaUsuaria());
            stockActualField.setText(String.valueOf(articulo.getStockActual()));
            unidadMedidaField.setText(articulo.getUnidadMedida());
        }
    }

    @FXML
    private void handleEliminarArticulo() {
        // Confirmación antes de eliminar
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Eliminar Artículo");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que querés eliminar este artículo?");
        alert.showAndWait().ifPresent(response -> {
            if (response.getText().equals("Aceptar")) {

                System.out.println("Artículo eliminado.");

            }
        });
    }

    @FXML
    private void handleModificarArticulo() {

        nombreArticuloField.setEditable(true);
        areaUsuariaField.setEditable(true);
        stockActualField.setEditable(true);
        unidadMedidaField.setEditable(true);

        btnGuardar.setVisible(true);
    }

    @FXML
    private void handleGuardar() {

        String nuevoNombre = nombreArticuloField.getText();
        String nuevaArea = areaUsuariaField.getText();
        int nuevoStock;
        try {
            nuevoStock = Integer.parseInt(stockActualField.getText());
        } catch (NumberFormatException e) {
            // Mostrar error si el stock no es un número válido
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("El campo 'Stock Actual' debe ser un número entero.");
            alert.showAndWait();
            return;
        }
        String nuevaUnidad = unidadMedidaField.getText();

        // Actualizar el objeto Articulo
        articulo.setNombreArticulo(nuevoNombre);
        articulo.setAreaUsuaria(nuevaArea);
        articulo.setStockActual(nuevoStock);
        articulo.setUnidadMedida(nuevaUnidad);

        // Lógica para guardar los cambios


        nombreArticuloField.setEditable(false);
        areaUsuariaField.setEditable(false);
        stockActualField.setEditable(false);
        unidadMedidaField.setEditable(false);


        btnGuardar.setVisible(false);


        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Artículo Modificado");
        alert.setHeaderText(null);
        alert.setContentText("Los datos del artículo se han actualizado correctamente.");
        alert.showAndWait();
    }
}
