package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.Proveedor;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProveedoresController {

    @FXML
    private TextField cuitField;

    @FXML
    private TextField nombreField;

    @FXML
    private TextField categoriaField;

    @FXML
    private TableView<Proveedor> proveedoresTable;

    @FXML
    private TableColumn<Proveedor, String> cuitColumn;

    @FXML
    private TableColumn<Proveedor, String> nombreColumn;

    @FXML
    private TableColumn<Proveedor, String> categoriaColumn;

    @FXML
    private TableColumn<Proveedor, String> direccionColumn;

    @FXML
    private TableColumn<Proveedor, String> telefonoColumn;

    @FXML
    private TableColumn<Proveedor, String> emailColumn;

    private ObservableList<Proveedor> proveedorList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cuitColumn.setCellValueFactory(cellData -> cellData.getValue().cuitProveedorProperty());
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreProveedorProperty());
        categoriaColumn.setCellValueFactory(cellData -> cellData.getValue().categoriaProperty());
        direccionColumn.setCellValueFactory(cellData -> cellData.getValue().direccionProperty());
        telefonoColumn.setCellValueFactory(cellData -> cellData.getValue().telefonoProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        proveedoresTable.setItems(proveedorList);
    }

    @FXML
    private void handleBuscar() {
        String cuit = cuitField.getText();
        String nombre = nombreField.getText();
        String categoria = categoriaField.getText();

        proveedorList.clear();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM proveedores WHERE " +
                    "(cuit_proveedor LIKE ? OR ? = '') AND " +
                    "(nombre_proveedor LIKE ? OR ? = '') AND " +
                    "(categoria LIKE ? OR ? = '')";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + cuit + "%");
            stmt.setString(2, cuit);
            stmt.setString(3, "%" + nombre + "%");
            stmt.setString(4, nombre);
            stmt.setString(5, "%" + categoria + "%");
            stmt.setString(6, categoria);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Proveedor proveedor = new Proveedor(
                        rs.getString("cuit_proveedor"),
                        rs.getString("nombre_proveedor"),
                        rs.getString("categoria"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("mail")
                );
                proveedorList.add(proveedor);
            }
        } catch (SQLException e) {
            mostrarMensaje("Error", "No se pudieron obtener los datos de los proveedores: " + e.getMessage());
        }
    }

    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void handleEliminar() {
        Proveedor selectedProveedor = proveedoresTable.getSelectionModel().getSelectedItem();
        if (selectedProveedor == null) {
            mostrarMensaje("Advertencia", "Seleccione un proveedor para eliminar.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM proveedores WHERE cuit_proveedor = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, selectedProveedor.getCuitProveedor());
            stmt.executeUpdate();
            proveedorList.remove(selectedProveedor);
            mostrarMensaje("Éxito", "Proveedor eliminado correctamente.");
        } catch (SQLException e) {
            mostrarMensaje("Error", "No se pudo eliminar el proveedor: " + e.getMessage());
        }
    }
}
