package Clases;

public class Medicamento {
    private int codigo;
    private String descripcion;
    private double precioUnitario;
    private int stockDisponible;

    public Medicamento() {}

    public Medicamento(int codigo, String descripcion, double precioUnitario, int stockDisponible) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.stockDisponible = stockDisponible;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precioUnitario=" + precioUnitario +
                ", stockDisponible=" + stockDisponible +
                '}';
    }
}
