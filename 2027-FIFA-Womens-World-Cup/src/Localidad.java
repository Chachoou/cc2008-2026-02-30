public class Localidad{
    private int numeroSector;
    private double precio;
    private int boletosVendidos;

    public Localidad(int numeroSector, double precio) {
        this.numeroSector = numeroSector;
        this.precio = precio;
        this.boletosVendidos = 0;
    }

    public int getNumeroSector() {
        return numeroSector;
    }
    public double getPrecio() {
        return precio;
    }
    public int getBoletosVendidos() {
        return boletosVendidos;
    }
    public boolean espacioDisponible() {
        return this.boletosVendidos < 20;
    }

    public int comprarBoletos(int cantidad) {
        int boletosDisponibles = 20 - this.boletosVendidos;
        
        if (cantidad <= boletosDisponibles) {
            this.boletosVendidos = this.boletosVendidos + cantidad;
            return cantidad;
        } else {
            this.boletosVendidos = this.boletosVendidos + boletosDisponibles;
            return boletosDisponibles; 
        }
    }
}