package demo.controller;

import demo.model.Escena;
import demo.model.Jugador;
import demo.model.Movil;

/**
 * UN SOLO hilo revisa colisiones. Si cada proyectil revisara por su cuenta,
 * dos proyectiles podrian matar al mismo enemigo y el puntaje se duplicaria.
 */
public class Arbitro extends Thread {

    private final Escena escena;
    private final Jugador jugador;
    private volatile boolean activo = true;
    private volatile int puntaje = 0;

    public Arbitro(Escena escena, Jugador jugador) {
        this.escena = escena;
        this.jugador = jugador;
        setDaemon(true);
    }

    public int getPuntaje() { return puntaje; }
    public void detener()   { activo = false; }

    @Override
    public void run() {
        while (activo) {
            Movil[] copia = escena.instantanea();

            for (int i = 0; i < copia.length; i++) {
                for (int j = i + 1; j < copia.length; j++) {

                    Movil a = copia[i];
                    Movil b = copia[j];

                    if (!a.estaVivo() || !b.estaVivo()) continue;
                    if (!chocan(a, b)) continue;

                    resolver(a, b);
                }
            }

            escena.limpiarMuertos();

            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                activo = false;
            }
        }
    }

    /**
     * ESTO ES SOLO UN EJEMPLO MINIMO, no la logica del juego.
     *
     * Este metodo es el lugar correcto para decidir que pasa cuando dos
     * moviles chocan (con instanceof o getClass() distingues de que tipo es
     * cada uno), pero las reglas concretas de tu juego -que combinaciones
     * existen, que le pasa a cada objeto, cuantos puntos otorga o quita
     * cada una, por cuanto tiempo bloquea, etc.- son parte de tu diseno.
     * Jugador ya trae bloquear(ms)/estaBloqueado() listos para que los uses
     * cuando definas esas reglas.
     *
     * Aqui, si el jugador esta involucrado, no hacemos nada (esa decision
     * es tuya). Para cualquier otro par, solo marcamos a ambos como
     * muertos, nada mas para dejar el patron deteccion -> reaccion
     * funcionando durante la demo.
     */
    private void resolver(Movil a, Movil b) {
        if (a instanceof Jugador || b instanceof Jugador) {
            return;
        }
        a.matar();
        b.matar();
        puntaje++;
    }

    /**
     * AABB: caja contra caja. Son CUATRO comparaciones y las cuatro hacen falta.
     * El error tipico es comparar solo x, y entonces le pega a enemigos de otra fila.
     */
    public static boolean chocan(Movil a, Movil b) {
        return a.getX() < b.getX() + b.getAncho()
            && a.getX() + a.getAncho() > b.getX()
            && a.getY() < b.getY() + b.getAlto()
            && a.getY() + a.getAlto() > b.getY();
    }
}
