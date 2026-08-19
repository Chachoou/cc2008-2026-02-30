public class Comprador{
    private String nombre;
    private String email;
    private int boletosDeseados;
    private double presupuesto;
    private int ticket;

    public Comprador(String nombre, String email, int boletosDeseados, double presupuesto, int ticket) {
        this.nombre = nombre;
        this.email = email;
        this.boletosDeseados = boletosDeseados;
        this.presupuesto = presupuesto;
        this.ticket = ticket;
    }

    public String getNombre() {
        return nombre;
    }
    public String getEmail() {
        return email;
    }
    public int getBoletosDeseados() {
        return boletosDeseados;
    }
    public double getPresupuesto() {
        return presupuesto;
    }
    public int getTicket() {
        return ticket;
    }
}