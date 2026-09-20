package demo.controller;

import java.util.Random;

import demo.model.Config;
import demo.model.Enemigo;
import demo.model.Escena;

/** Genera enemigos en el borde derecho. */
public class HiloSpawn extends Thread {

    private final Escena escena;
    private final Random random = new Random();
    private volatile boolean activo = true;

    public HiloSpawn(Escena escena) {
        this.escena = escena;
        setDaemon(true);
    }

    public void detener() { activo = false; }

    @Override
    public void run() {
        while (activo) {
            int y = random.nextInt(Config.ALTO - 40);
            int velocidad = 2 + random.nextInt(3);

            Enemigo e = new Enemigo(y, velocidad, 18);
            escena.agregar(e);
            e.start();

            try {
                Thread.sleep(700);
            } catch (InterruptedException ex) {
                activo = false;
            }
        }
    }
}
