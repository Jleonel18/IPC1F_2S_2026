package demo.controller;

import demo.view.PanelEscena;

/** Unico hilo que pide repintar. repaint() se puede llamar desde cualquier hilo. */
public class HiloRender extends Thread {

    private final PanelEscena panel;
    private volatile boolean corriendo = true;

    public HiloRender(PanelEscena panel) {
        this.panel = panel;
        setDaemon(true);
    }

    public void detener() {
        corriendo = false;
    }

    @Override
    public void run() {
        while (corriendo) {
            panel.repaint();
            try {
                Thread.sleep(16);   // ~60 cuadros por segundo
            } catch (InterruptedException e) {
                corriendo = false;
            }
        }
    }
}
