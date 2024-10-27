package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import models.Proveedor;

public class ProveedoresController extends BaseController {

    @FXML
    private TextField cuitProveedorField;

    @FXML
    private TextField nombreProveedorField;

    @FXML
    private TextField categoriaField;

    @FXML
    private TableView<Proveedor> tablaProveedores;

    @FXML
    private TableColumn<Proveedor, String> colCuitProveedor;

    @FXML
    private TableColumn<Proveedor, String> colNombreProveedor;

    @FXML
    private TableColumn<Proveedor, String> colCategoria;

    @FXML
    private TableColumn<Proveedor, String> colDireccion;

    @FXML
    private TableColumn<Proveedor, String> colTelefono;

    @FXML
    private TableColumn<Proveedor, String> colEmail;

    private ObservableList<Proveedor> listaProveedores = FXCollections.observableArrayList();

    @FXML
    private Button btnGuardar;

    @FXML
    private void initialize() {
        colCuitProveedor.setCellValueFactory(cellData -> cellData.getValue().cuitProveedorProperty());
        colNombreProveedor.setCellValueFactory(cellData -> cellData.getValue().nombreProveedorProperty());
        colCategoria.setCellValueFactory(cellData -> cellData.getValue().categoriaProperty());
        colDireccion.setCellValueFactory(cellData -> cellData.getValue().direccionProperty());
        colTelefono.setCellValueFactory(cellData -> cellData.getValue().telefonoProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        tablaProveedores.setItems(listaProveedores);

        // Deshabilitar edición por defecto
        deshabilitarEdicion();
    }

    @FXML
    private void handleBuscar() {
        listaProveedores.clear();

        String cuit = cuitProveedorField.getText();
        String nombre = nombreProveedorField.getText();
        String categoria = categoriaField.getText();

        listaProveedores.addAll(buscarProveedores(cuit, nombre, categoria));
    }

    private ObservableList<Proveedor> buscarProveedores(String cuit, String nombre, String categoria) {
        ObservableList<Proveedor> proveedores = FXCollections.observableArrayList();

        // Datos simulados
        if (cuit.equals("20304050") || nombre.equalsIgnoreCase("Proveedor X") || categoria.equalsIgnoreCase("Electrónica")) {
            proveedores.add(new Proveedor("20304050", "Proveedor X", "Electrónica", "Calle Falsa 123", "123456789", "contacto@proveedorx.com"));
        }

        return proveedores;
    }

    @FXML
    private void handleEliminarProveedor() {
        Proveedor seleccionado = tablaProveedores.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Eliminar Proveedor");
            confirmacion.setHeaderText(null);
            confirmacion.setContentText("¿Estás seguro de que deseas eliminar al proveedor seleccionado?");
            confirmacion.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    listaProveedores.remove(seleccionado);

                    mostrarMensaje("Proveedor eliminado", "El proveedor ha sido eliminado correctamente.");
                }
            });
        } else {
            mostrarMensaje("Seleccione un proveedor", "Por favor, seleccione un proveedor de la tabla.");
        }
    }

    @FXML
    private void handleModificarProveedor() {
        Proveedor seleccionado = tablaProveedores.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            habilitarEdicion();
            btnGuardar.setVisible(true);
        } else {
            mostrarMensaje("Seleccione un proveedor", "Por favor, seleccione un proveedor de la tabla.");
        }
    }

    @FXML
    private void handleGuardar() {
        Proveedor seleccionado = tablaProveedores.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {

            deshabilitarEdicion();
            btnGuardar.setVisible(false);
            mostrarMensaje("Proveedor modificado", "Los datos del proveedor han sido actualizados correctamente.");
        } else {
            mostrarMensaje("Seleccione un proveedor", "Por favor, seleccione un proveedor de la tabla.");
        }
    }

    private void habilitarEdicion() {
        tablaProveedores.setEditable(true);
        colNombreProveedor.setCellFactory(TextFieldTableCell.forTableColumn());
        colCategoria.setCellFactory(TextFieldTableCell.forTableColumn());
        colDireccion.setCellFactory(TextFieldTableCell.forTableColumn());
        colTelefono.setCellFactory(TextFieldTableCell.forTableColumn());
        colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    private void deshabilitarEdicion() {
        tablaProveedores.setEditable(false);
    }

    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
