import java.util.Scanner;

public class Vista {
    private Scanner entrada;

    public Vista() {
        entrada = new Scanner(System.in);
    }

    public void mostrarInicio(String nombre1, String nombre2) {
        System.out.println("=================================================");
        System.out.println("¡Comienza la batalla en la Liga Elemental!");
        System.out.println(nombre1 + " VS " + nombre2);
        System.out.println("=================================================");
    }

    public void mostrarRoles(String atacante, String defensor, int ronda) {
        System.out.println("\n--- RONDA " + ronda + " ---");
        System.out.println(atacante + " toma la ofensiva. " + defensor + " se prepara para defender.");
    }

    public void mostrarSeleccionPokemon(String entrenador, String pokemon, String tipo) {
        System.out.println(entrenador + " envía a " + pokemon + " (Tipo: " + tipo + ")");
    }

    public void mostrarHabilidad(boolean activada, double bono, String pokemon, String nombreHab, String estadistica) {
        // Solo imprime si la habilidad se activa, ocultando el mensaje en caso contrario
        if (activada) {
            int porcentaje = (int)(bono * 100);
            System.out.println(pokemon + " ha activado su habilidad especial, " + nombreHab + " le otorga a " + pokemon + " un " + porcentaje + "% extra de " + estadistica + "!");
        }
    }

    public void mostrarEstadisticas(double atkEfectivo, double defEfectiva, int bonoTipo, double total) {
        System.out.println("\n[Datos del choque]");
        System.out.println("Ataque efectivo: " + atkEfectivo);
        System.out.println("Defensa efectiva: " + defEfectiva);
        System.out.println("Bono de tipo aplicado: " + bonoTipo);
        System.out.println("Cálculo final de daño: " + total);
    }

    public void mostrarGanador(String resultadoRonda, String atacante, String defensor) {
        System.out.println();
        if (resultadoRonda.equals("Atacante")) {
            System.out.println(atacante + " ha ganado la ronda tras un ataque fulminante.");
        } else if (resultadoRonda.equals("Defensor")) {
            System.out.println(defensor + " ha resistido el golpe a la perfección y gana la ronda.");
        } else {
            System.out.println("¡Ambos Pokémon han chocado con la misma fuerza! La ronda es un EMPATE.");
        }
    }

    public void mostrarCampeon(String ganadorFinal) {
        System.out.println("\n=================================================");
        System.out.println("FIN DEL COMBATE");
        if (ganadorFinal.equals("Empate")) {
            System.out.println("¡El combate ha terminado en un reñido empate!");
        } else {
            System.out.println("¡" + ganadorFinal + " ha ganado el combate y se corona campeón de la Liga Elemental!");
        }
        System.out.println("=================================================");
    }

    public void pausarParaContinuar() {
        System.out.println("\nPresione ENTER para continuar...");
        entrada.nextLine();
    }
}