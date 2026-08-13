import java.util.Random;

public class HabilidadEspecial {
    private String nombre;
    private int bonusAtaque;
    private int bonusDefensa;
    private boolean activacion;
    private int probabilidad; // Se agrega para almacenar la probabilidad de esta habilidad

    public HabilidadEspecial(String nombre, int probabilidad) {
        this.nombre = nombre;
        this.probabilidad = probabilidad;
        this.activacion = false;
        this.bonusAtaque = 0;
        this.bonusDefensa = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public int getProbabilidad() {
        return probabilidad;
    }

    public boolean intentarActivacion(int probabilidadActivacion) {
        Random rand = new Random();
        int tiro = rand.nextInt(101);
        this.activacion = (tiro <= probabilidadActivacion);
        return this.activacion;
    }

    public double generarPotenciador() {
        if (this.activacion) {
            Random rand = new Random();
            return rand.nextInt(26) / 100.0; 
        }
        return 0.0;
    }
}