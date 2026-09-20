package demo.model;

import java.awt.Color;

/** Aparece en el borde derecho y viaja hacia la izquierda. */
public class Enemigo extends Movil {

    public Enemigo(int y, int velocidad, int pausaMs) {
        super(Config.ANCHO, y, 30, 24, -velocidad, pausaMs);
    }

    @Override
    public Color getColor() {
        return new Color(220, 70, 70);
    }
}
