package demo.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

import demo.controller.Arbitro;
import demo.model.Escena;
import demo.model.Jugador;
import demo.model.Movil;

/** El unico que dibuja. Lee una copia de la escena, nunca la modifica. */
public class PanelEscena extends JPanel {

    private final Escena escena;
    private final Jugador jugador;
    private final Arbitro arbitro;

    public PanelEscena(Escena escena, Jugador jugador, Arbitro arbitro) {
        this.escena = escena;
        this.jugador = jugador;
        this.arbitro = arbitro;

        setBackground(Color.BLACK);
        setDoubleBuffered(true);   // sin esto parpadea
        setFocusable(true);        // sin esto el teclado nunca llega
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);   // limpia el fondo; si lo borran, quedan estelas

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

        Movil[] copia = escena.instantanea();
        for (int i = 0; i < copia.length; i++) {
            Movil m = copia[i];
            g2.setColor(m.getColor());
            g2.fillRect(m.getX(), m.getY(), m.getAncho(), m.getAlto());
        }

        g2.setColor(Color.WHITE);
        g2.drawString("Puntaje: " + arbitro.getPuntaje(), 12, 20);
        g2.drawString("Moviles vivos: " + copia.length, 12, 38);
        g2.drawString("Hilos activos: " + Thread.activeCount(), 12, 56);

        if (jugador.estaBloqueado()) {
            g2.setColor(Color.ORANGE);
            g2.drawString("BLOQUEADO", jugador.getX() - 10, jugador.getY() - 8);
        }
    }
}
