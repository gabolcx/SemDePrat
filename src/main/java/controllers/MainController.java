package controllers;

import javafx.fxml.FXML;

public class MainController extends BaseController {

    @FXML
    private void handleArticulos() {
        cargarVentana("/views/ArticulosView.fxml", "Artículos");
    }

    @FXML
    private void handleStock() {
        cargarVentana("/views/StockView.fxml", "Gestión de Stock");
    }

    @FXML
    private void handleProveedores() {
        cargarVentana("/views/ProveedoresView.fxml", "Proveedores");
    }

    @FXML
    private void handleFacturacion() {
        cargarVentana("/views/FacturacionView.fxml", "Facturación");
    }
}
