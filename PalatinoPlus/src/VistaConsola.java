import java.util.Scanner;

public class VistaConsola {
    private Scanner scanner;

    public VistaConsola() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("\n===  VENTA DE BOLETOS: MUNDIAL FIFA FEMENINO 2027 ===");
        System.out.println("1. Registrarse");
        System.out.println("2. Nueva solicitud de boletos");
        System.out.println("3. Consultar disponibilidad total");
        System.out.println("4. Consultar disponibilidad individual");
        System.out.println("5. Reporte de caja");
        System.out.println("6. Salir");
    }

    public String pedirTexto(String mensaje) {
        System.out.println(mensaje);
        return scanner.nextLine();
    }

    public int pedirEntero(String mensaje) {
        System.out.println(mensaje);
        int numero = scanner.nextInt();
        scanner.nextLine(); 
        return numero;
    }

    public double pedirDouble(String mensaje) {
        System.out.println(mensaje);
        double decimal = scanner.nextDouble();
        scanner.nextLine();
        return decimal;
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}