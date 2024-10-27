package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StockController extends BaseController {

    @FXML
    private void handleStockActual() {
        cargarVentana("/views/StockActualView.fxml", "Stock Actual");
    }

    @FXML
    private void handleMovimientosStock() {
        cargarVentana("/views/MovimientosStockView.fxml", "Movimientos de Stock");
    }
}