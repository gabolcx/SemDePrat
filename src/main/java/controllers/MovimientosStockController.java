package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Inventario;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovimientosStockController extends BaseController {

    @FXML
    private TextField codigoArticuloField;

    @FXML
    private TextField nombreArticuloField;

    @FXML
    private TableView<Inventario> tablaMovimientosStock;

    @FXML
    private TableColumn<Inventario, String> colFecha;

    @FXML
    private TableColumn<Inventario, String> colCodigoArticulo;

    @FXML
    private TableColumn<Inventario, String> colNombreArticulo;

    @FXML
    private TableColumn<Inventario, String> colAreaUsuaria;

    @FXML
    private TableColumn<Inventario, Integer> colCantidad;

    @FXML
    private TableColumn<Inventario, String> colUnidadMedida;

    @FXML
    private TableColumn<Inventario, String> colMovimiento;

    @FXML
    private TableColumn<Inventario, Integer> colStockFecha;

    @FXML
    private TableColumn<Inventario, String> colObservaciones;

    private ObservableList<Inventario> listaMovimientos = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        colFecha.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
        colCodigoArticulo.setCellValueFactory(cellData -> cellData.getValue().codigoArticuloProperty());
        colNombreArticulo.setCellValueFactory(cellData -> cellData.getValue().nombreArticuloProperty());
        colAreaUsuaria.setCellValueFactory(cellData -> cellData.getValue().areaUsuariaProperty());
        colCantidad.setCellValueFactory(cellData -> cellData.getValue().cantidadProperty().asObject());
        colUnidadMedida.setCellValueFactory(cellData -> cellData.getValue().unidadMedidaProperty());
        colMovimiento.setCellValueFactory(cellData -> cellData.getValue().movimientoProperty());
        colStockFecha.setCellValueFactory(cellData -> cellData.getValue().stockFechaProperty().asObject());
        colObservaciones.setCellValueFactory(cellData -> cellData.getValue().observacionesProperty());

        tablaMovimientosStock.setItems(listaMovimientos);
    }

    @FXML
    private void handleBuscar() {
        listaMovimientos.clear();

        String codigo = codigoArticuloField.getText();
        String nombre = nombreArticuloField.getText();

        listaMovimientos.addAll(buscarMovimientos(codigo, nombre));
    }

    private ObservableList<Inventario> buscarMovimientos(String codigo, String nombre) {
        ObservableList<Inventario> movimientos = FXCollections.observableArrayList();

        String query = "SELECT fecha, codigo_articulo, nombre_articulo, area_usuaria, cantidad, UM, movimiento, stock_fecha, observaciones " +
                "FROM inventario WHERE (codigo_articulo = ? OR nombre_articulo LIKE ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, codigo.isEmpty() ? null : codigo);
            stmt.setString(2, nombre.isEmpty() ? null : "%" + nombre + "%");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                movimientos.add(new Inventario(
                        rs.getString("fecha"),
                        rs.getString("codigo_articulo"),
                        rs.getString("nombre_articulo"),
                        rs.getString("area_usuaria"),
                        rs.getInt("cantidad"),
                        rs.getString("UM"),
                        rs.getString("movimiento"),
                        rs.getInt("stock_fecha"),
                        rs.getString("observaciones")
                ));
            }

        } catch (SQLException e) {
            mostrarAlerta("Error", "Error al buscar movimientos en la base de datos: " + e.getMessage());
        }

        return movimientos;
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
