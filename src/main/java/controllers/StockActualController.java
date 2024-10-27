package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Articulo;

public class StockActualController extends BaseController {

    @FXML
    private TextField codigoArticuloField;

    @FXML
    private TextField nombreArticuloField;

    @FXML
    private TextField areaUsuariaField;

    @FXML
    private TableView<Articulo> tablaStockActual;

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

        tablaStockActual.setItems(listaArticulos);
    }

    @FXML
    private void handleBuscar() {

        listaArticulos.clear();


        String codigo = codigoArticuloField.getText();
        String nombre = nombreArticuloField.getText();
        String area = areaUsuariaField.getText();

        // Simular búsqueda en la base de datos o lista
        // Acá se implementaria la lógica real para obtener los datos

        // Ejemplo de datos simulados
        if (codigo.isEmpty() && nombre.isEmpty() && area.isEmpty()) {
            // Si no se ingresó ningún filtro, mostrar todos
            listaArticulos.addAll(obtenerTodosLosArticulos());
        } else {
            // Filtrar según los campos ingresados
            listaArticulos.addAll(buscarArticulos(codigo, nombre, area));
        }
    }

    private ObservableList<Articulo> obtenerTodosLosArticulos() {

        ObservableList<Articulo> articulos = FXCollections.observableArrayList();
        articulos.add(new Articulo("ART001", "Televisor", "Electrónica", 10, "Unidad"));
        articulos.add(new Articulo("ART002", "Laptop", "Informática", 5, "Unidad"));
        return articulos;
    }

    private ObservableList<Articulo> buscarArticulos(String codigo, String nombre, String area) {

        ObservableList<Articulo> articulos = FXCollections.observableArrayList();


        if (codigo.equals("ART001") || nombre.equalsIgnoreCase("Televisor") || area.equalsIgnoreCase("Electrónica")) {
            articulos.add(new Articulo("ART001", "Televisor", "Electrónica", 10, "Unidad"));
        }

        return articulos;
    }
}
