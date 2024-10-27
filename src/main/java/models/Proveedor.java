package models;

import javafx.beans.property.*;

public class Proveedor {
    private StringProperty cuitProveedor;
    private StringProperty nombreProveedor;
    private StringProperty categoria;
    private StringProperty direccion;
    private StringProperty telefono;
    private StringProperty email;

    public Proveedor(String cuitProveedor, String nombreProveedor, String categoria, String direccion, String telefono, String email) {
        this.cuitProveedor = new SimpleStringProperty(cuitProveedor);
        this.nombreProveedor = new SimpleStringProperty(nombreProveedor);
        this.categoria = new SimpleStringProperty(categoria);
        this.direccion = new SimpleStringProperty(direccion);
        this.telefono = new SimpleStringProperty(telefono);
        this.email = new SimpleStringProperty(email);
    }

    public StringProperty cuitProveedorProperty() {
        return cuitProveedor;
    }

    public String getCuitProveedor() {
        return cuitProveedor.get();
    }

    public void setCuitProveedor(String cuitProveedor) {
        this.cuitProveedor.set(cuitProveedor);
    }

    public StringProperty nombreProveedorProperty() {
        return nombreProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor.get();
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor.set(nombreProveedor);
    }

    public StringProperty categoriaProperty() {
        return categoria;
    }

    public String getCategoria() {
        return categoria.get();
    }

    public void setCategoria(String categoria) {
        this.categoria.set(categoria);
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public String getDireccion() {
        return direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public StringProperty telefonoProperty() {
        return telefono;
    }

    public String getTelefono() {
        return telefono.get();
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public StringProperty emailProperty() {
        return email;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
}
