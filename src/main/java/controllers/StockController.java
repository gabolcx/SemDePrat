package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Stock;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockController extends BaseController {

    @FXML
    private TextField codigoArticuloField;

    @FXML
    private TextField nombreArticuloField;

    @FXML
    private TextField areaUsuariaField;

    @FXML
    private TableView<Stock> tableView;

    @FXML
    private TableColumn<Stock, String> colCodigoArticulo;

    @FXML
    private TableColumn<Stock, String> colNombreArticulo;

    @FXML
    private TableColumn<Stock, String> colAreaUsuaria;

    @FXML
    private TableColumn<Stock, Integer> colStockActual;

    @FXML
    private TableColumn<Stock, Integer> colCantidad;

    private ObservableList<Stock> stockList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        colCodigoArticulo.setCellValueFactory(cellData -> cellData.getValue().codigoArticuloProperty());
        colNombreArticulo.setCellValueFactory(cellData -> cellData.getValue().nombreArticuloProperty());
        colAreaUsuaria.setCellValueFactory(cellData -> cellData.getValue().areaUsuariaProperty());
        colStockActual.setCellValueFactory(cellData -> cellData.getValue().stockActualProperty().asObject());
        colCantidad.setCellValueFactory(cellData -> cellData.getValue().cantidadProperty().asObject());

        tableView.setItems(stockList);
    }

    @FXML
    private void handleBuscar() {
        stockList.clear();

        String codigoArticulo = codigoArticuloField.getText();
        String nombreArticulo = nombreArticuloField.getText();
        String areaUsuaria = areaUsuariaField.getText();

        cargarStockDesdeBaseDeDatos(codigoArticulo, nombreArticulo, areaUsuaria);
    }

    private void cargarStockDesdeBaseDeDatos(String codigoArticulo, String nombreArticulo, String areaUsuaria) {
        String query = "SELECT codigo_articulo, nombre_articulo, area_usuaria, stock_actual, cantidad " +
                "FROM articulos WHERE 1=1";

        if (!codigoArticulo.isEmpty()) {
            query += " AND codigo_articulo LIKE ?";
        }
        if (!nombreArticulo.isEmpty()) {
            query += " AND nombre_articulo LIKE ?";
        }
        if (!areaUsuaria.isEmpty()) {
            query += " AND area_usuaria LIKE ?";
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            int paramIndex = 1;

            if (!codigoArticulo.isEmpty()) {
                preparedStatement.setString(paramIndex++, "%" + codigoArticulo + "%");
            }
            if (!nombreArticulo.isEmpty()) {
                preparedStatement.setString(paramIndex++, "%" + nombreArticulo + "%");
            }
            if (!areaUsuaria.isEmpty()) {
                preparedStatement.setString(paramIndex++, "%" + areaUsuaria + "%");
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                stockList.add(new Stock(
                        resultSet.getString("codigo_articulo"),
                        resultSet.getString("nombre_articulo"),
                        resultSet.getString("area_usuaria"),
                        resultSet.getInt("stock_actual"),
                        resultSet.getInt("cantidad")
                ));
            }

        } catch (SQLException e) {
            mostrarError("Error al cargar datos de la base de datos", e.getMessage());
        }
    }

    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
