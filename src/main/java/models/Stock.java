package models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Stock {
    private StringProperty codigoArticulo;
    private StringProperty nombreArticulo;
    private StringProperty areaUsuaria;
    private IntegerProperty stockActual;
    private IntegerProperty cantidad;

    public Stock(String codigoArticulo, String nombreArticulo, String areaUsuaria, int stockActual, int cantidad) {
        this.codigoArticulo = new SimpleStringProperty(codigoArticulo);
        this.nombreArticulo = new SimpleStringProperty(nombreArticulo);
        this.areaUsuaria = new SimpleStringProperty(areaUsuaria);
        this.stockActual = new SimpleIntegerProperty(stockActual);
        this.cantidad = new SimpleIntegerProperty(cantidad);
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

    public IntegerProperty stockActualProperty() {
        return stockActual;
    }

    public int getStockActual() {
        return stockActual.get();
    }

    public void setStockActual(int stockActual) {
        this.stockActual.set(stockActual);
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
}
