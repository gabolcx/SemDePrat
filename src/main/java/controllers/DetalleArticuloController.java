package controllers;

import utils.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Articulo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetalleArticuloController {

    @FXML
    private TableView<Articulo> articuloTable;

    @FXML
    private TableColumn<Articulo, String> codigoColumn;

    @FXML
    private TableColumn<Articulo, String> nombreColumn;

    @FXML
    private TableColumn<Articulo, String> areaColumn;

    @FXML
    private TableColumn<Articulo, Integer> stockColumn;

    @FXML
    private TableColumn<Articulo, String> umColumn;

    private ObservableList<Articulo> articuloList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        codigoColumn.setCellValueFactory(new PropertyValueFactory<>("codigoArticulo"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombreArticulo"));
        areaColumn.setCellValueFactory(new PropertyValueFactory<>("areaUsuaria"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stockActual"));
        umColumn.setCellValueFactory(new PropertyValueFactory<>("unidadMedida"));

        articuloTable.setItems(articuloList);

        cargarArticulos();
    }

    private void cargarArticulos() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM articulos";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Articulo articulo = new Articulo(
                        rs.getString("codigo_articulo"),
                        rs.getString("nombre_articulo"),
                        rs.getString("area_usuaria"),
                        rs.getInt("stock_actual"),
                        rs.getString("UM")
                );
                articuloList.add(articulo);
            }
        } catch (SQLException e) {
            mostrarMensaje("Error", "No se pudieron cargar los artículos: " + e.getMessage());
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
    private void handleEliminarArticulo() {
        Articulo selectedArticulo = articuloTable.getSelectionModel().getSelectedItem();
        if (selectedArticulo == null) {
            mostrarMensaje("Error", "Seleccioná un artículo para eliminar.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "DELETE FROM articulos WHERE codigo_articulo = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, selectedArticulo.getCodigoArticulo());
            stmt.executeUpdate();

            articuloList.remove(selectedArticulo);
            mostrarMensaje("Éxito", "El artículo fue eliminado correctamente.");
        } catch (SQLException e) {
            mostrarMensaje("Error", "No se pudo eliminar el artículo: " + e.getMessage());
        }
    }
}
