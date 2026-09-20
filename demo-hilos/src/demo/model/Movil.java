package demo.model;

import java.awt.Color;
import java.awt.Rectangle;

/**
 * Todo lo que se mueve en pantalla es un Movil, y todo Movil es un hilo.
 * Un movil SOLO sabe moverse a si mismo: no sabe que existen los demas,
 * no dibuja nada y no calcula colisiones.
 */
public abstract class Movil extends Thread {

    // volatile: varios hilos leen estas variables mientras este hilo las escribe.
    // Sin volatile, el hilo que pinta puede quedarse viendo un valor viejo.
    protected volatile int x;
    protected volatile int y;
    protected volatile boolean vivo = true;

    protected final int ancho;
    protected final int alto;
    protected final int velocidadX;  // negativa = hacia la izquierda
    protected final int pausaMs;

    public Movil(int x, int y, int ancho, int alto, int velocidadX, int pausaMs) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.velocidadX = velocidadX;
        this.pausaMs = pausaMs;
        setDaemon(true);   // si el programa termina, este hilo no lo detiene
    }

    public int getX()          { return x; }
    public int getY()          { return y; }
    public int getAncho()      { return ancho; }
    public int getAlto()       { return alto; }
    public boolean estaVivo()  { return vivo; }
    public void matar()        { vivo = false; }

    public Rectangle getCaja() {
        return new Rectangle(x, y, ancho, alto);
    }

    public abstract Color getColor();

    @Override
    public void run() {
        while (vivo) {
            x += velocidadX;

            // Si salio de pantalla se muere solo.
            // Si borran esta linea, los hilos se acumulan hasta tronar el programa.
            if (x + ancho < 0 || x > Config.ANCHO) {
                vivo = false;
            }

            try {
                Thread.sleep(pausaMs);
            } catch (InterruptedException e) {
                vivo = false;
            }
        }
    }
}
