public class Combate {
    private int numCombate;
    private String resultadoCombate;

    public int evaluarEfectividad(String atacante, String defensor) {
        // Matriz de efectividad segun el pdf[cite: 3]
        if (atacante.equals("Fuego")) {
            if (defensor.equals("Agua")) return -10;
            if (defensor.equals("Planta")) return 20;
        } else if (atacante.equals("Agua")) {
            if (defensor.equals("Fuego")) return 20;
            if (defensor.equals("Planta")) return -10;
        } else if (atacante.equals("Planta")) {
            if (defensor.equals("Fuego")) return -10;
            if (defensor.equals("Agua")) return 20;
        } else if (atacante.equals("Eléctrico")) {
            if (defensor.equals("Agua")) return 20;
        }
        return 0; // Neutral en el resto de los casos[cite: 3]
    }

    public String calcularResultado(int ataqueTotal) {
        if (ataqueTotal > 0) {
            this.resultadoCombate = "Atacante";
        } else if (ataqueTotal < 0) {
            this.resultadoCombate = "Defensor";
        } else {
            this.resultadoCombate = "Empate";
        }
        return this.resultadoCombate;
    }
}