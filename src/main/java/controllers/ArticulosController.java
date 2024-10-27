package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ArticulosController extends BaseController {

    @FXML
    private void handleAgregarArticulo() {
        cargarVentana("/views/AddArticleView.fxml", "Agregar Nuevo Artículo");
    }

    @FXML
    private void handleBuscarArticulo() {
        cargarVentana("/views/BuscarArticuloView.fxml", "Buscar Artículo");
    }
}
