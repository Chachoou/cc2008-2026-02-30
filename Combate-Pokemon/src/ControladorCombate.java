public class ControladorCombate {
    private Entrenador entrenador1;
    private Entrenador entrenador2;
    private int rondaActual;
    private boolean turnoJugador;
    private int victoriasJugador1;
    private int victoriasJugador2;
    private Vista vista;
    private Combate combateLogica;

    public ControladorCombate() {
        vista = new Vista();
        combateLogica = new Combate();
        victoriasJugador1 = 0;
        victoriasJugador2 = 0;
    }

    public void iniciarBatalla() {
        HabilidadEspecial h1 = new HabilidadEspecial("Volcán de Fuego", 30);
        HabilidadEspecial h2 = new HabilidadEspecial("Salvado por Erick", 40);
        HabilidadEspecial h3 = new HabilidadEspecial("Volteo destructor", 20);
        HabilidadEspecial h4 = new HabilidadEspecial("Luna ausente", 35);

        Pokemon p1 = new Pokemon("Charizard", "Fuego", 50, 40, h1);
        Pokemon p2 = new Pokemon("Venusaur", "Planta", 45, 55, h2);
        Pokemon p3 = new Pokemon("Pikachu", "Eléctrico", 60, 30, h3);
        Pokemon p4 = new Pokemon("Blastoise", "Agua", 40, 60, h4);
        entrenador1 = new Entrenador("Rojo", p1, p2, p3, p4);

        Pokemon p5 = new Pokemon("Arcanine", "Fuego", 55, 35, h1);
        Pokemon p6 = new Pokemon("Gyarados", "Agua", 60, 45, h4);
        Pokemon p7 = new Pokemon("Jolteon", "Eléctrico", 65, 25, h3);
        Pokemon p8 = new Pokemon("Sceptile", "Planta", 50, 40, h2);
        entrenador2 = new Entrenador("Azul", p5, p6, p7, p8);

        vista.mostrarInicio(entrenador1.getNombre(), entrenador2.getNombre());
        vista.pausarParaContinuar();

        for (rondaActual = 1; rondaActual <= 4; rondaActual++) {
            ejecutarRonda();
            vista.pausarParaContinuar();
        }

        finalizarBatalla();
    }

    public void ejecutarRonda() {
        if (rondaActual == 1 || rondaActual == 3) {
            turnoJugador = true; 
        } else {
            turnoJugador = false;
        }

        Entrenador atacante = turnoJugador ? entrenador1 : entrenador2;
        Entrenador defensor = turnoJugador ? entrenador2 : entrenador1;

        vista.mostrarRoles(atacante.getNombre(), defensor.getNombre(), rondaActual);

        Pokemon pokeAtacante = atacante.seleccionarPokemon();
        Pokemon pokeDefensor = defensor.seleccionarPokemon();

        vista.mostrarSeleccionPokemon(atacante.getNombre(), pokeAtacante.getNombre(), pokeAtacante.getTipo());
        vista.mostrarSeleccionPokemon(defensor.getNombre(), pokeDefensor.getNombre(), pokeDefensor.getTipo());
        System.out.println(); // Salto de linea para que se vea mas limpio en consola

        // Habilidades
        HabilidadEspecial habAtacante = pokeAtacante.getHabilidadEspecial();
        boolean actAtacante = habAtacante.intentarActivacion(habAtacante.getProbabilidad());
        double bonoAtacante = habAtacante.generarPotenciador();
        // Se le pasa el nombre de la habilidad y la palabra Ataque[cite: 3]
        vista.mostrarHabilidad(actAtacante, bonoAtacante, pokeAtacante.getNombre(), habAtacante.getNombre(), "Ataque");

        HabilidadEspecial habDefensor = pokeDefensor.getHabilidadEspecial();
        boolean actDefensor = habDefensor.intentarActivacion(habDefensor.getProbabilidad());
        double bonoDefensor = habDefensor.generarPotenciador();
        // Se le pasa el nombre de la habilidad y la palabra Defensa[cite: 3]
        vista.mostrarHabilidad(actDefensor, bonoDefensor, pokeDefensor.getNombre(), habDefensor.getNombre(), "Defensa");

        // Calculos efectivos
        double atkEfectivo = pokeAtacante.calcularAtaqueEfectivo(bonoAtacante);
        double defEfectiva = pokeDefensor.calcularDefensaEfectiva(bonoDefensor);

        int bonoTipo = combateLogica.evaluarEfectividad(pokeAtacante.getTipo(), pokeDefensor.getTipo());

        double totalDouble = atkEfectivo + bonoTipo - defEfectiva;
        int totalCombate = (int) totalDouble;

        vista.mostrarEstadisticas(atkEfectivo, defEfectiva, bonoTipo, totalDouble);

        String resultado = combateLogica.calcularResultado(totalCombate);
        vista.mostrarGanador(resultado, atacante.getNombre(), defensor.getNombre());

        // Registrar victoria
        if (resultado.equals("Atacante")) {
            if (turnoJugador) victoriasJugador1++;
            else victoriasJugador2++;
        } else if (resultado.equals("Defensor")) {
            if (turnoJugador) victoriasJugador2++;
            else victoriasJugador1++;
        }
    }

    public void finalizarBatalla() {
        if (victoriasJugador1 > victoriasJugador2) {
            vista.mostrarCampeon(entrenador1.getNombre());
        } else if (victoriasJugador2 > victoriasJugador1) {
            vista.mostrarCampeon(entrenador2.getNombre());
        } else {
            vista.mostrarCampeon("Empate");
        }
    }
}