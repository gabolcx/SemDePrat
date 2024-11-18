package models;

import javafx.beans.property.*;

public class Factura {

    private final IntegerProperty numeroFactura;
    private final StringProperty nombreProveedor;
    private final StringProperty fecha;
    private final DoubleProperty total;
    private final StringProperty tipoOC;
    private final StringProperty numeroOC;
    private final StringProperty descripcion;

    public Factura(int numeroFactura, String nombreProveedor, String fecha, double total, String tipoOC, String numeroOC, String descripcion) {
        this.numeroFactura = new SimpleIntegerProperty(numeroFactura);
        this.nombreProveedor = new SimpleStringProperty(nombreProveedor);
        this.fecha = new SimpleStringProperty(fecha);
        this.total = new SimpleDoubleProperty(total);
        this.tipoOC = new SimpleStringProperty(tipoOC);
        this.numeroOC = new SimpleStringProperty(numeroOC);
        this.descripcion = new SimpleStringProperty(descripcion);
    }

    public int getNumeroFactura() {
        return numeroFactura.get();
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura.set(numeroFactura);
    }

    public IntegerProperty numeroFacturaProperty() {
        return numeroFactura;
    }

    public String getNombreProveedor() {
        return nombreProveedor.get();
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor.set(nombreProveedor);
    }

    public StringProperty nombreProveedorProperty() {
        return nombreProveedor;
    }

    public String getFecha() {
        return fecha.get();
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }

    public StringProperty fechaProperty() {
        return fecha;
    }

    public double getTotal() {
        return total.get();
    }

    public void setTotal(double total) {
        this.total.set(total);
    }

    public DoubleProperty totalProperty() {
        return total;
    }

    public String getTipoOC() {
        return tipoOC.get();
    }

    public void setTipoOC(String tipoOC) {
        this.tipoOC.set(tipoOC);
    }

    public StringProperty tipoOCProperty() {
        return tipoOC;
    }

    public String getNumeroOC() {
        return numeroOC.get();
    }

    public void setNumeroOC(String numeroOC) {
        this.numeroOC.set(numeroOC);
    }

    public StringProperty numeroOCProperty() {
        return numeroOC;
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }
}