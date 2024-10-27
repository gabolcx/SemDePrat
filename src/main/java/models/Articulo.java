package models;

import javafx.beans.property.*;

public class Articulo {
    private StringProperty codigoArticulo;
    private StringProperty nombreArticulo;
    private StringProperty areaUsuaria;
    private IntegerProperty stockActual;
    private StringProperty unidadMedida;

    public Articulo(String codigoArticulo, String nombreArticulo, String areaUsuaria, int stockActual, String unidadMedida) {
        this.codigoArticulo = new SimpleStringProperty(codigoArticulo);
        this.nombreArticulo = new SimpleStringProperty(nombreArticulo);
        this.areaUsuaria = new SimpleStringProperty(areaUsuaria);
        this.stockActual = new SimpleIntegerProperty(stockActual);
        this.unidadMedida = new SimpleStringProperty(unidadMedida);
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

    public StringProperty unidadMedidaProperty() {
        return unidadMedida;
    }

    public String getUnidadMedida() {
        return unidadMedida.get();
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida.set(unidadMedida);
    }
}
