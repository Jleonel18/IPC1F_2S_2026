package demo.model;

import java.awt.Color;

/** Sale del jugador hacia la derecha. Mismo patron que el enemigo, signo contrario. */
public class Proyectil extends Movil {

    public Proyectil(int x, int y) {
        super(x, y, 10, 4, +9, 12);
    }

    @Override
    public Color getColor() {
        return Color.YELLOW;
    }
}
