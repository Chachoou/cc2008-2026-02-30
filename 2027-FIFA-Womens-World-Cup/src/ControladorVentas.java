import java.util.Random;

public class ControladorVentas {
    private VistaConsola vista;
    private Comprador compradorActual;
    private Localidad[] localidades;

    public ControladorVentas() {
        this.vista = new VistaConsola();
        this.compradorActual = null; 

        this.localidades = new Localidad[3];
        this.localidades[0] = new Localidad(1, 100.0);
        this.localidades[1] = new Localidad(5, 500.0);
        this.localidades[2] = new Localidad(10, 1000.0); 
    }

    public void iniciar() {
        boolean salir = false;

        while (!salir) {
            vista.mostrarMenu();
            int opcion = vista.pedirEntero("Seleccione una opción de 1 al 6:");

            switch (opcion) {
                case 1:
                    vista.mostrarMensaje("\n--- Registro de Comprador ---");
                    String nombre = vista.pedirTexto("Ingrese su nombre:");
                    String email = vista.pedirTexto("Ingrese su email:");
                    int boletos = vista.pedirEntero("Cantidad de boletos a comprar:");
                    double presupuesto = vista.pedirDouble("Presupuesto máximo ($):");
                    
                    registrarUser(nombre, email, boletos, presupuesto);
                    break;
                case 2:
                    vista.mostrarMensaje("\n--- Nueva Solicitud de Boletos ---");
                    procesarCompra();
                    break;
                case 3:
                    vista.mostrarMensaje("\n--- Disponibilidad Total ---");
                    vista.mostrarMensaje(mostrarDisponibilidad());
                    break;
                case 4:
                    vista.mostrarMensaje("\n--- Disponibilidad Individual ---");
                    int numLoc = vista.pedirEntero("Ingrese el número de localidad a consultar (1, 5 o 10):");
                    vista.mostrarMensaje(mostrarDisponibilidad(numLoc));
                    break;
                case 5:
                    vista.mostrarMensaje("\n--- Reporte de Caja ---");
                    vista.mostrarMensaje("Dinero total recaudado: $" + generarReporteCaja());
                    break;
                case 6:
                    salir = true;
                    vista.mostrarMensaje("\nHas salido del sistema. ¡Hasta pronto!");
                    break;
                default:
                    vista.mostrarMensaje("Ingrese un número del 1 al 6.");
            }
        }
    }


    public void registrarUser(String nom, String mail, int cant, double pres) {
        Random rand = new Random();
        int ticketAleatorio = rand.nextInt(15000) + 1;
        
        this.compradorActual = new Comprador(nom, mail, cant, pres, ticketAleatorio);
        
        vista.mostrarMensaje("Su número de ticket asignado es: " + ticketAleatorio);
    }

    public void procesarCompra() {
        if (this.compradorActual == null) {
            vista.mostrarMensaje("Tienes que registrar a un comprador primero (Opción 1).");
            return;
        }

        Random rand = new Random();
        int a = rand.nextInt(15000) + 1;
        int b = rand.nextInt(15000) + 1;
        int ticket = this.compradorActual.getTicket();
        
        int min = Math.min(a, b);
        int max = Math.max(a, b);

        vista.mostrarMensaje("Su ticket es: " + ticket);
        vista.mostrarMensaje("El rango ganador de hoy es: [" + min + " a " + max + "]");

        if (ticket >= min && ticket <= max) {
            vista.mostrarMensaje("¡Felicidades! Su ticket es apto para comprar.");
            
            int indiceLocalidad = rand.nextInt(3);
            Localidad loc = this.localidades[indiceLocalidad];
            vista.mostrarMensaje("Se le ha asignado aleatoriamente la Localidad " + loc.getNumeroSector() + " (Precio: $" + loc.getPrecio() + ")");

            if (!loc.espacioDisponible()) {
                vista.mostrarMensaje("La localidad asignada ya está llena.");
                return;
            }

            if (loc.getPrecio() > this.compradorActual.getPresupuesto()) {
                vista.mostrarMensaje("Lo sentimos, su presupuesto de $" + this.compradorActual.getPresupuesto() + " no alcanza para esta localidad. Proceso terminado.");
                return;
            }

            int boletosDeseados = this.compradorActual.getBoletosDeseados();
            int boletosVendidos = loc.comprarBoletos(boletosDeseados);
            
            vista.mostrarMensaje("¡Compra exitosa! Se le han vendido " + boletosVendidos + " boleto(s) en la Localidad " + loc.getNumeroSector() + ".");
        } else {
            vista.mostrarMensaje("Lo sentimos, su ticket no está dentro del rango ganador. Intente con una nueva solicitud.");
        }
    }

    public String mostrarDisponibilidad() {
        int totalVendidos = 0;
        int totalDisponibles = 0;
        
        for (int i = 0; i < this.localidades.length; i++) {
            totalVendidos += this.localidades[i].getBoletosVendidos();
            totalDisponibles += (20 - this.localidades[i].getBoletosVendidos());
        }
        
        return "Boletos totales vendidos: " + totalVendidos + "\nBoletos totales disponibles: " + totalDisponibles;
    }

    public String mostrarDisponibilidad(int numeroLocalidad) {
        for (int i = 0; i < this.localidades.length; i++) {
            if (this.localidades[i].getNumeroSector() == numeroLocalidad) {
                int disponibles = 20 - this.localidades[i].getBoletosVendidos();
                return "Localidad " + numeroLocalidad + " -> Vendidos: " + this.localidades[i].getBoletosVendidos() + " | Disponibles: " + disponibles;
            }
        }
        return "Error: La localidad " + numeroLocalidad + " no existe. Solo tenemos localidades 1, 5 y 10.";
    }

    public double generarReporteCaja() {
        double totalRecaudado = 0;
        for (int i = 0; i < this.localidades.length; i++) {
            totalRecaudado += (this.localidades[i].getBoletosVendidos() * this.localidades[i].getPrecio());
        }
        return totalRecaudado;
    }
}