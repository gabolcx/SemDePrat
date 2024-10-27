package models;

import javafx.beans.property.*;

public class Inventario {
    private StringProperty fecha;
    private StringProperty codigoArticulo;
    private StringProperty nombreArticulo;
    private StringProperty areaUsuaria;
    private IntegerProperty cantidad;
    private StringProperty unidadMedida;
    private StringProperty movimiento;
    private IntegerProperty stockFecha;
    private StringProperty observaciones;

    public Inventario(String fecha, String codigoArticulo, String nombreArticulo, String areaUsuaria, int cantidad, String unidadMedida, String movimiento, int stockFecha, String observaciones) {
        this.fecha = new SimpleStringProperty(fecha);
        this.codigoArticulo = new SimpleStringProperty(codigoArticulo);
        this.nombreArticulo = new SimpleStringProperty(nombreArticulo);
        this.areaUsuaria = new SimpleStringProperty(areaUsuaria);
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.unidadMedida = new SimpleStringProperty(unidadMedida);
        this.movimiento = new SimpleStringProperty(movimiento);
        this.stockFecha = new SimpleIntegerProperty(stockFecha);
        this.observaciones = new SimpleStringProperty(observaciones);
    }

    // Getters y setters para las Properties

    public StringProperty fechaProperty() {
        return fecha;
    }

    public String getFecha() {
        return fecha.get();
    }

    public void setFecha(String fecha) {
        this.fecha.set(fecha);
    }

    public StringProperty codigoArticuloProperty() {
        return codigoArticulo;
    }

    public String getCodigoArticulo() {
        return codigoArticulo.get();
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo.set(codigoArticulo);
    }

    public StringProperty nombreArticuloProperty() {
        return nombreArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo.get();
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo.set(nombreArticulo);
    }

    public StringProperty areaUsuariaProperty() {
        return areaUsuaria;
    }

    public String getAreaUsuaria() {
        return areaUsuaria.get();
    }

    public void setAreaUsuaria(String areaUsuaria) {
        this.areaUsuaria.set(areaUsuaria);
    }

    public IntegerProperty cantidadProperty() {
        return cantidad;
    }

    public int getCantidad() {
        return cantidad.get();
    }

    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }

    public StringProperty unidadMedidaProperty() {
        return unidadMedida;
    }

    public String getUnidadMedida() {
        return unidadMedida.get();
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida.set(unidadMedida);
    }

    public StringProperty movimientoProperty() {
        return movimiento;
    }

    public String getMovimiento() {
        return movimiento.get();
    }

    public void setMovimiento(String movimiento) {
        this.movimiento.set(movimiento);
    }

    public IntegerProperty stockFechaProperty() {
        return stockFecha;
    }

    public int getStockFecha() {
        return stockFecha.get();
    }

    public void setStockFecha(int stockFecha) {
        this.stockFecha.set(stockFecha);
    }

    public StringProperty observacionesProperty() {
        return observaciones;
    }

    public String getObservaciones() {
        return observaciones.get();
    }

    public void setObservaciones(String observaciones) {
        this.observaciones.set(observaciones);
    }
}
