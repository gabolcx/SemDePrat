package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Inventario;

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

        // Simular búsqueda en la base de datos o lista
        listaMovimientos.addAll(buscarMovimientos(codigo, nombre));
    }

    private ObservableList<Inventario> buscarMovimientos(String codigo, String nombre) {
        ObservableList<Inventario> movimientos = FXCollections.observableArrayList();

        // Ejemplo de datos simulados
        if (codigo.equals("ART001") || nombre.equalsIgnoreCase("Televisor")) {
            movimientos.add(new Inventario("2023-10-15", "ART001", "Televisor", "Electrónica", 5, "Unidad", "INGRESO", 10, "Compra de stock"));
            movimientos.add(new Inventario("2023-10-16", "ART001", "Televisor", "Electrónica", -2, "Unidad", "EGRESO", 8, "Venta realizada"));
        }

        return movimientos;
    }
}
