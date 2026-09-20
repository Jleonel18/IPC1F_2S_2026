package demo.model;

import java.awt.Color;

/**
 * El jugador no viaja solo: se mueve segun las banderas del teclado.
 * Por eso sobreescribe run().
 */
public class Jugador extends Movil {

    private final EntradaJugador entrada;
    private final int paso = 4;

    // Marca de tiempo hasta la cual esta bloqueado. Asi NO hace falta
    // dormir 2 segundos en el hilo de eventos y congelar la ventana.
    private volatile long bloqueadoHasta = 0;

    public Jugador(EntradaJugador entrada, int pausaMs) {
        super(40, Config.ALTO / 2, 34, 22, 0, pausaMs);
        this.entrada = entrada;
    }

    public void bloquear(int milisegundos) {
        bloqueadoHasta = System.currentTimeMillis() + milisegundos;
    }

    public boolean estaBloqueado() {
        return System.currentTimeMillis() < bloqueadoHasta;
    }

    @Override
    public Color getColor() {
        return estaBloqueado() ? Color.GRAY : new Color(90, 200, 255);
    }

    @Override
    public void run() {
        while (vivo) {

            if (estaBloqueado()) {
                // Bloqueado: no se mueve, pero la ventana sigue viva y repintando.
                dormir(20);
                continue;
            }

            if (entrada.arriba()    && y > 0)                        y -= paso;
            if (entrada.abajo()     && y < Config.ALTO - alto)       y += paso;
            if (entrada.izquierda() && x > 0)                        x -= paso;
            if (entrada.derecha()   && x < Config.ANCHO / 2)         x += paso;

            dormir(pausaMs);
        }
    }

    private void dormir(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            vivo = false;
        }
    }
}
