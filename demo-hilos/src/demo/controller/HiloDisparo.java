package demo.controller;

import demo.model.Escena;
import demo.model.Jugador;
import demo.model.Proyectil;

/** Dispara solo, cada cierto tiempo. La cadencia depende de la dificultad. */
public class HiloDisparo extends Thread {

    private final Escena escena;
    private final Jugador jugador;
    private final int cadenciaMs;
    private volatile boolean activo = true;

    public HiloDisparo(Escena escena, Jugador jugador, int cadenciaMs) {
        this.escena = escena;
        this.jugador = jugador;
        this.cadenciaMs = cadenciaMs;
        setDaemon(true);
    }

    public void detener() { activo = false; }

    @Override
    public void run() {
        while (activo) {
            if (!jugador.estaBloqueado()) {
                Proyectil p = new Proyectil(
                        jugador.getX() + jugador.getAncho(),
                        jugador.getY() + jugador.getAlto() / 2);
                escena.agregar(p);
                p.start();          // desde aqui el proyectil ya se mueve solo
            }
            try {
                Thread.sleep(cadenciaMs);
            } catch (InterruptedException e) {
                activo = false;
            }
        }
    }
}
