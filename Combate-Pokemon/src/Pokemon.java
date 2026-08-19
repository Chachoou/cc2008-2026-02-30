public class Pokemon {
    private String nombre;
    private String tipo; 
    private int ataque;
    private int defensa;
    private HabilidadEspecial habilidadEspecial;
    private boolean pokemonUsado;

    public Pokemon(String nombre, String tipo, int ataque, int defensa, HabilidadEspecial habilidadEspecial) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ataque = ataque;
        this.defensa = defensa;
        this.habilidadEspecial = habilidadEspecial;
        this.pokemonUsado = false;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public HabilidadEspecial getHabilidadEspecial() {
        return habilidadEspecial;
    }

    public boolean isPokemonUsado() {
        return pokemonUsado;
    }

    public void setUsado() {
        this.pokemonUsado = true;
    }

    public double calcularAtaqueEfectivo(double potenciador) {
        return this.ataque + (this.ataque * potenciador);
    }

    public double calcularDefensaEfectiva(double potenciador) {
        return this.defensa + (this.defensa * potenciador);
    }
}