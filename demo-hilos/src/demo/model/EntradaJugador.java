package demo.model;

/**
 * Lo minimo que el modelo (Jugador) necesita saber sobre "que teclas estan
 * presionadas ahora mismo". El modelo no debe depender de una clase de UI
 * como un KeyListener; por eso depende de esta interfaz, y es el
 * controlador (Teclado) quien la implementa.
 */
public interface EntradaJugador {
    boolean arriba();
    boolean abajo();
    boolean izquierda();
    boolean derecha();
}
