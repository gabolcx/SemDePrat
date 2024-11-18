package controllers;

import models.Stock;
import utils.DatabaseConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockActualController {

    @FXML
    private TextField codigoField;

    @FXML
    private TextField nombreField;

    @FXML
    private TextField areaField;

    @FXML
    private TableView<Stock> stockTable;

    @FXML
    private TableColumn<Stock, String> codigoColumn;

    @FXML
    private TableColumn<Stock, String> nombreColumn;

    @FXML
    private TableColumn<Stock, String> areaColumn;

    @FXML
    private TableColumn<Stock, Integer> cantidadColumn;

    private ObservableList<Stock> stockList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        codigoColumn.setCellValueFactory(cellData -> cellData.getValue().codigoArticuloProperty());
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().nombreArticuloProperty());
        areaColumn.setCellValueFactory(cellData -> cellData.getValue().areaUsuariaProperty());
        cantidadColumn.setCellValueFactory(cellData -> cellData.getValue().cantidadProperty().asObject());
        stockTable.setItems(stockList);
    }

    @FXML
    private void handleSearch() {
        String codigo = codigoField.getText();
        String nombre = nombreField.getText();
        String area = areaField.getText();
        stockList.clear();

        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT codigo_articulo, nombre_articulo, area_usuaria, stock_fecha AS cantidad " +
                    "FROM inventario WHERE (codigo_articulo LIKE ? OR ? = '') " +
                    "AND (nombre_articulo LIKE ? OR ? = '') AND (area_usuaria LIKE ? OR ? = '')";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + codigo + "%");
            stmt.setString(2, codigo);
            stmt.setString(3, "%" + nombre + "%");
            stmt.setString(4, nombre);
            stmt.setString(5, "%" + area + "%");
            stmt.setString(6, area);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Stock stock = new Stock(
                        rs.getString("codigo_articulo"),
                        rs.getString("nombre_articulo"),
                        rs.getString("area_usuaria"),
                        rs.getInt("cantidad"),
                        rs.getInt("cantidad") // Duplicado para simular el campo "stock_actual"
                );
                stockList.add(stock);
            }
        } catch (SQLException e) {
            mostrarMensaje("Error", "No se pudieron obtener los datos de stock actual: " + e.getMessage());
        }
    }

    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
