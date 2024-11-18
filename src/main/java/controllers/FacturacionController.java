package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Factura;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FacturacionController extends BaseController {

    @FXML
    private TextField numeroFacturaField;

    @FXML
    private TextField nombreProveedorField;

    @FXML
    private TableView<Factura> facturasTable;

    @FXML
    private TableColumn<Factura, Integer> colNumeroFactura;

    @FXML
    private TableColumn<Factura, String> colNombreProveedor;

    @FXML
    private TableColumn<Factura, String> colFecha;

    @FXML
    private TableColumn<Factura, Double> colTotal;

    @FXML
    private TableColumn<Factura, String> colTipoOC;

    @FXML
    private TableColumn<Factura, String> colNumeroOC;

    @FXML
    private TableColumn<Factura, String> colDescripcion;

    private ObservableList<Factura> listaFacturas = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        colNumeroFactura.setCellValueFactory(cellData -> cellData.getValue().numeroFacturaProperty().asObject());
        colNombreProveedor.setCellValueFactory(cellData -> cellData.getValue().nombreProveedorProperty());
        colFecha.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
        colTotal.setCellValueFactory(cellData -> cellData.getValue().totalProperty().asObject());
        colTipoOC.setCellValueFactory(cellData -> cellData.getValue().tipoOCProperty());
        colNumeroOC.setCellValueFactory(cellData -> cellData.getValue().numeroOCProperty());
        colDescripcion.setCellValueFactory(cellData -> cellData.getValue().descripcionProperty());

        facturasTable.setItems(listaFacturas);
    }

    @FXML
    private void handleBuscar() {
        String numeroFactura = numeroFacturaField.getText();
        String nombreProveedor = nombreProveedorField.getText();

        listaFacturas.clear();
        listaFacturas.addAll(buscarFacturas(numeroFactura, nombreProveedor));
    }

    private ObservableList<Factura> buscarFacturas(String numeroFactura, String nombreProveedor) {
        ObservableList<Factura> facturas = FXCollections.observableArrayList();

        String query = "SELECT * FROM facturas WHERE " +
                "(CAST(numero_factura AS CHAR) LIKE ? OR ? IS NULL) AND " +
                "(nombre_proveedor LIKE ? OR ? IS NULL)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, "%" + numeroFactura + "%");
            stmt.setString(2, numeroFactura.isEmpty() ? null : numeroFactura);
            stmt.setString(3, "%" + nombreProveedor + "%");
            stmt.setString(4, nombreProveedor.isEmpty() ? null : nombreProveedor);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                facturas.add(new Factura(
                        rs.getInt("numero_factura"),
                        rs.getString("nombre_proveedor"),
                        rs.getString("fecha"),
                        rs.getDouble("total"),
                        rs.getString("tipo_oc"),
                        rs.getString("numero_oc"),
                        rs.getString("descripcion")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            mostrarError("Error al buscar facturas", "Ocurrió un error al buscar facturas en la base de datos.");
        }

        return facturas;
    }

    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
