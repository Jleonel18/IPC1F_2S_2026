package demo.model;

/** Constantes del mundo. Un solo lugar donde tocar numeros. */
public class Config {

    public static final int ANCHO = 800;
    public static final int ALTO  = 500;

    // Dificultad: la pausa del hilo es la perilla de velocidad.
    // Menos milisegundos = se mueve mas seguido = se ve mas rapido.
    public static final int PAUSA_JUGADOR_FACIL   = 10;
    public static final int PAUSA_JUGADOR_NORMAL  = 18;
    public static final int PAUSA_JUGADOR_DIFICIL = 30;

    public static final int CADENCIA_FACIL   = 2000;
    public static final int CADENCIA_NORMAL  = 1000;
    public static final int CADENCIA_DIFICIL = 300;
}
