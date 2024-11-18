package controllers;

import utils.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddArticleController {

    @FXML
    private TextField codigoField;

    @FXML
    private TextField nombreField;

    @FXML
    private TextField areaField;

    @FXML
    private TextField stockField;

    @FXML
    private TextField umField;

    @FXML
    private void handleSaveArticle() {
        String codigo = codigoField.getText();
        String nombre = nombreField.getText();
        String area = areaField.getText();
        int stock = Integer.parseInt(stockField.getText());
        String um = umField.getText();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO articulos (codigo_articulo, nombre_articulo, area_usuaria, stock_actual, UM) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, codigo);
            stmt.setString(2, nombre);
            stmt.setString(3, area);
            stmt.setInt(4, stock);
            stmt.setString(5, um);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                mostrarMensaje("Éxito", "Artículo agregado correctamente.");
            }
        } catch (SQLException e) {
            mostrarMensaje("Error", "No se pudo agregar el artículo: " + e.getMessage());
        }
    }

    @FXML
    private void handleBorrar() {
        codigoField.clear();
        nombreField.clear();
        areaField.clear();
        stockField.clear();
        umField.clear();
    }

    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
