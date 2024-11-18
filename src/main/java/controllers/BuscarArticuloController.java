package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Articulo;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BuscarArticuloController extends BaseController {

    @FXML
    private TextField codigoArticuloField;

    @FXML
    private TextField nombreArticuloField;

    @FXML
    private TableView<Articulo> articuloTable;

    @FXML
    private TableColumn<Articulo, String> colCodigoArticulo;

    @FXML
    private TableColumn<Articulo, String> colNombreArticulo;

    @FXML
    private TableColumn<Articulo, String> colAreaUsuaria;

    @FXML
    private TableColumn<Articulo, Integer> colStockActual;

    @FXML
    private TableColumn<Articulo, String> colUnidadMedida;

    private ObservableList<Articulo> listaArticulos = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        colCodigoArticulo.setCellValueFactory(cellData -> cellData.getValue().codigoArticuloProperty());
        colNombreArticulo.setCellValueFactory(cellData -> cellData.getValue().nombreArticuloProperty());
        colAreaUsuaria.setCellValueFactory(cellData -> cellData.getValue().areaUsuariaProperty());
        colStockActual.setCellValueFactory(cellData -> cellData.getValue().stockActualProperty().asObject());
        colUnidadMedida.setCellValueFactory(cellData -> cellData.getValue().unidadMedidaProperty());

        articuloTable.setItems(listaArticulos);
    }

    @FXML
    private void handleBuscar() {
        String codigo = codigoArticuloField.getText();
        String nombre = nombreArticuloField.getText();

        listaArticulos.clear();
        listaArticulos.addAll(buscarArticulos(codigo, nombre));
    }

    private ObservableList<Articulo> buscarArticulos(String codigo, String nombre) {
        ObservableList<Articulo> articulos = FXCollections.observableArrayList();

        String query = "SELECT * FROM articulos WHERE " +
                "(codigo_articulo LIKE ? OR ? IS NULL) AND " +
                "(nombre_articulo LIKE ? OR ? IS NULL)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + codigo + "%");
            stmt.setString(2, codigo.isEmpty() ? null : codigo);
            stmt.setString(3, "%" + nombre + "%");
            stmt.setString(4, nombre.isEmpty() ? null : nombre);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                articulos.add(new Articulo(
                        rs.getString("codigo_articulo"),
                        rs.getString("nombre_articulo"),
                        rs.getString("area_usuaria"),
                        rs.getInt("stock_actual"),
                        rs.getString("UM")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("Error al buscar artículos", "Ocurrió un error al buscar artículos en la base de datos.");
        }

        return articulos;
    }

    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
