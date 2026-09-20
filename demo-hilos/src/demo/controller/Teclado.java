package demo.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import demo.model.EntradaJugador;

/**
 * El listener SOLO prende y apaga banderas. No mueve nada.
 * Quien mueve es el hilo del jugador, que lee estas banderas a traves de
 * la interfaz EntradaJugador (definida en el modelo).
 * Asi el movimiento sale fluido y no con los tirones del auto-repeat del teclado.
 */
public class Teclado extends KeyAdapter implements EntradaJugador {

    private volatile boolean arriba;
    private volatile boolean abajo;
    private volatile boolean izquierda;
    private volatile boolean derecha;

    @Override
    public boolean arriba()    { return arriba; }
    @Override
    public boolean abajo()     { return abajo; }
    @Override
    public boolean izquierda() { return izquierda; }
    @Override
    public boolean derecha()   { return derecha; }

    @Override
    public void keyPressed(KeyEvent e) {
        cambiar(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        cambiar(e.getKeyCode(), false);
    }

    private void cambiar(int codigo, boolean valor) {
        switch (codigo) {
            case KeyEvent.VK_UP:    arriba    = valor; break;
            case KeyEvent.VK_DOWN:  abajo     = valor; break;
            case KeyEvent.VK_LEFT:  izquierda = valor; break;
            case KeyEvent.VK_RIGHT: derecha   = valor; break;
            default: break;
        }
    }
}
