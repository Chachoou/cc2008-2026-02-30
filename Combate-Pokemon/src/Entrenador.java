import java.util.Random;

public class Entrenador {
    private String nombre;
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private Pokemon pokemon3;
    private Pokemon pokemon4;

    public Entrenador(String nombre, Pokemon p1, Pokemon p2, Pokemon p3, Pokemon p4) {
        this.nombre = nombre;
        this.pokemon1 = p1;
        this.pokemon2 = p2;
        this.pokemon3 = p3;
        this.pokemon4 = p4;
    }

    public String getNombre() {
        return nombre;
    }

    public Pokemon seleccionarPokemon() {
        Pokemon[] equipo = {pokemon1, pokemon2, pokemon3, pokemon4};
        Random rand = new Random();
        Pokemon seleccionado = null;
        
        while (seleccionado == null) {
            int index = rand.nextInt(4);
            if (!equipo[index].isPokemonUsado()) {
                seleccionado = equipo[index];
                seleccionado.setUsado();
            }
        }
        return seleccionado;
    }
}